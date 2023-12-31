package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ByteArrayTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteArraySerializer extends NamedTagSerializer<byte[], ByteArrayTag> {

    @Override
    public void serialize0(byte[] data, DataOutput stream, TagRegistry registry) throws IOException {
        stream.writeInt(data.length);
        stream.write(data);
    }

    @Override
    public ByteArrayTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        int len = stream.readInt();
        byte[] data = new byte[len];
        stream.readFully(data);
        return new ByteArrayTag(name, data);
    }

}
