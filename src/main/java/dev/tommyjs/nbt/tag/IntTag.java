package dev.tommyjs.nbt.tag;

public class IntTag extends NamedTag<Integer> {

    public IntTag(String name, int value) {
        super(name, value);
    }

    public IntTag(int value) {
        super(null, value);
    }

}
