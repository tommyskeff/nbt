package dev.tommyjs.nbt.tag;

public class LongTag extends NamedTag<Long> {

    public LongTag(String name, long value) {
        super(name, value);
    }

    public LongTag(long value) {
        this(null, value);
    }

}
