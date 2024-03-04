package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class ByteArrayTag extends NamedTag<byte[]> {

    public ByteArrayTag(byte[] value) {
        super(value);
    }

    @Override
    public String toString() {
        return Arrays.toString(getValue());
    }

}
