package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.IntArrayTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntArraySerializer extends NamedTagSerializer<int[], IntArrayTag> {

    @Override
    public void serialize0(int[] data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeInt(data.length);
        for (int d : data) {
            stream.writeInt(d);
        }
    }

    @Override
    public IntArrayTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        int len = stream.readInt();
        int[] data = new int[len];

        for (int i = 0; i < len; i++) {
            data[i] = stream.readInt();
        }

        return new IntArrayTag(name, data);
    }

}
