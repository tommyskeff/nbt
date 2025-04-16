package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.LongArrayTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongArraySerializer implements TagSerializer<LongArrayTag> {

    @Override
    public void serialize(@NotNull LongArrayTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        stream.writeInt(tag.getValue().length);
        stats.attemptArraySize(tag.getValue().length, 1);
        for (long d : tag.getValue()) {
            stream.writeLong(d);
        }
    }

    @Override
    public @NotNull LongArrayTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        int len = stream.readInt();
        stats.attemptArraySize(len, 8);
        long[] data = new long[len];
        for (int i = 0; i < len; i++) {
            data[i] = stream.readLong();
        }
        return new LongArrayTag(data);
    }

}
