package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.LongTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongSerializer extends NamedTagSerializer<Long, LongTag> {

    @Override
    public void serialize0(Long data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeLong(data);
    }

    @Override
    public LongTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        long data = stream.readLong();
        return new LongTag(name, data);
    }

}
