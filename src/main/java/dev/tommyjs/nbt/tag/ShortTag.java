package dev.tommyjs.nbt.tag;

public class ShortTag extends NamedTag<Short> {

    public ShortTag(String name, short value) {
        super(name, value);
    }

    public ShortTag(short value) {
        this(null, value);
    }

}
