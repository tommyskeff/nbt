package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class IntArrayTag extends NamedTag<int[]> {

    public IntArrayTag(int[] value) {
        super(value);
    }

    @Override
    public String toString() {
        return Arrays.toString(getValue());
    }

}
