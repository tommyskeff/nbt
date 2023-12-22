package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.StringTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringSerializer extends NamedTagSerializer<String, StringTag> {

    @Override
    public void serialize0(String data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeUTF(data);
    }

    @Override
    public StringTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        String data = stream.readUTF();
        return new StringTag(name, data);
    }

}
