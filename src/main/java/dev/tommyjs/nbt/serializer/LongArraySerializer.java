package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.LongArrayTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongArraySerializer extends NamedTagSerializer<long[], LongArrayTag> {

    @Override
    public void serialize0(long[] data, DataOutput stream, TagRegistry registry) throws IOException {
        stream.writeInt(data.length);
        for (long d : data) {
            stream.writeLong(d);
        }
    }

    @Override
    public LongArrayTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        int len = stream.readInt();
        long[] data = new long[len];

        for (int i = 0; i < len; i++) {
            data[i] = stream.readLong();
        }

        return new LongArrayTag(name, data);
    }

}
