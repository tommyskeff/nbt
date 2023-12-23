package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.StringTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringSerializer extends NamedTagSerializer<String, StringTag> {

    @Override
    public void serialize0(String data, DataOutput stream, TagRegistry registry) throws IOException {
        stream.writeUTF(data);
    }

    @Override
    public StringTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        String data = stream.readUTF();
        return new StringTag(name, data);
    }

}
