package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class LongArrayTag extends NamedTag<long[]> {

    public LongArrayTag(long[] value) {
        super(value);
    }

    @Override
    public String toString() {
        return Arrays.toString(getValue());
    }

}
