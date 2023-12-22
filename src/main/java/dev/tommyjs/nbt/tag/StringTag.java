package dev.tommyjs.nbt.tag;

public class StringTag extends NamedTag<String> {

    public StringTag(String name, String value) {
        super(name, value);
    }

    public StringTag(String value) {
        this(null, value);
    }

}
