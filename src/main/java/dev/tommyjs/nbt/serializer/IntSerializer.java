package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.IntTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntSerializer extends NamedTagSerializer<Integer, IntTag> {

    @Override
    public void serialize0(Integer data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeInt(data);
    }

    @Override
    public IntTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        int data = stream.readInt();
        return new IntTag(name, data);
    }

}
