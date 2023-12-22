package dev.tommyjs.nbt.tag;

public class ByteTag extends NamedTag<Byte> {

    public ByteTag(String name, byte value) {
        super(name, value);
    }

    public ByteTag(byte value) {
        super(null, value);
    }

}
