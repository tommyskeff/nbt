package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ShortTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortSerializer extends NamedTagSerializer<Short, ShortTag> {

    @Override
    public void serialize0(Short data, DataOutput stream, TagRegistry registry) throws IOException {
        stream.writeShort(data);
    }

    @Override
    public ShortTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        short data = stream.readShort();
        return new ShortTag(name, data);
    }

}
