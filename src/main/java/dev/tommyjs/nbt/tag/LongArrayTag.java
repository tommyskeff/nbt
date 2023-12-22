package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class LongArrayTag extends NamedTag<long[]> {

    public LongArrayTag(String name, long[] value) {
        super(name, value);
    }

    @Override
    public String formatValue() {
        return Arrays.toString(getValue());
    }

}
