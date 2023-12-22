package dev.tommyjs.nbt.tag;

import java.util.Arrays;

public class IntArrayTag extends NamedTag<int[]> {

    public IntArrayTag(String name, int[] value) {
        super(name, value);
    }

    @Override
    public String formatValue() {
        return Arrays.toString(getValue());
    }

}
