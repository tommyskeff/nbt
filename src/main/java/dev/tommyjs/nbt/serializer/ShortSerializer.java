package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.ShortTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortSerializer extends NamedTagSerializer<Short, ShortTag> {

    @Override
    public void serialize0(Short data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeShort(data);
    }

    @Override
    public ShortTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        short data = stream.readShort();
        return new ShortTag(name, data);
    }

}
