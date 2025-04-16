package dev.tommyjs.nbt.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.UTFDataFormatException;

public class NbtUtil {

    public static void attemptWriteString(String data, int maxLength, DataOutput out, NbtStats stats) throws IOException {
        int strLength = data.length();
        int utfLength = strLength;

        for (int i = 0; i < strLength; i++) {
            int c = data.charAt(i);
            if (c >= 0x80 || c == 0) {
                utfLength += (c >= 0x800) ? 2 : 1;
            }
        }

        if (utfLength > maxLength || utfLength > 65535 || utfLength < strLength) {
            throw new IOException("maximum nbt string length exceeded (" + utfLength + " > " + maxLength + ")");
        }

        stats.attemptSize(utfLength + 2);

        byte[] bytes = new byte[utfLength + 2];
        int count = 0;
        bytes[count++] = (byte) ((utfLength >>> 8) & 0xFF);
        bytes[count++] = (byte) ((utfLength) & 0xFF);

        int i;
        for (i = 0; i < strLength; i++) {
            int c = data.charAt(i);
            if (c >= 0x80 || c == 0) {
                break;
            }
            bytes[count++] = (byte) c;
        }

        for (; i < strLength; i++) {
            int c = data.charAt(i);
            if (c < 0x80 && c != 0) {
                bytes[count++] = (byte) c;
            } else if (c >= 0x800) {
                bytes[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                bytes[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
                bytes[count++] = (byte) (0x80 | ((c) & 0x3F));
            } else {
                bytes[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                bytes[count++] = (byte) (0x80 | ((c) & 0x3F));
            }
        }

        out.write(bytes, 0, utfLength + 2);
    }

    public static String attemptReadString(int maxLength, DataInput in, NbtStats stats) throws IOException {
        int length = in.readUnsignedShort();
        if (length > maxLength) {
            throw new IOException("maximum nbt string length exceeded (" + length + " > " + maxLength + ")");
        }

        byte[] bytes = new byte[length];
        char[] chars = new char[length];

        int curr, char2, char3;
        int count = 0;
        int i = 0;

        stats.attemptSize(length);
        in.readFully(bytes, 0, length);

        while (count < length) {
            curr = (int) bytes[count] & 0xff;
            if (curr > 127) {
                break;
            }
            count++;
            chars[i++] = (char) curr;
        }

        while (count < length) {
            curr = (int) bytes[count] & 0xff;
            switch (curr >> 4) {
                case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                    count++;
                    chars[i++] = (char) curr;
                }
                case 12, 13 -> {
                    count += 2;
                    if (count > length) {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
                    char2 = bytes[count - 1];
                    if ((char2 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException("malformed input around byte " + count);
                    }
                    chars[i++] = (char) (((curr & 0x1F) << 6) | (char2 & 0x3F));
                }
                case 14 -> {
                    count += 3;
                    if (count > length) {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
                    char2 = bytes[count - 2];
                    char3 = bytes[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
                        throw new UTFDataFormatException("malformed input around byte " + (count - 1));
                    }
                    chars[i++] = (char) (((curr & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F)));
                }
                default -> throw new UTFDataFormatException("malformed input around byte " + count);
            }
        }

        return new String(chars, 0, i);
    }

}
