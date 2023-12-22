package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class ByteArrayTag extends NamedTag<byte[]> {

    public ByteArrayTag(String name, byte[] value) {
        super(name, value);
    }

    @Override
    public String formatValue() {
        return Arrays.toString(getValue());
    }

}
