package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.IntArrayTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntArraySerializer implements TagSerializer<IntArrayTag> {

    @Override
    public void serialize(@NotNull IntArrayTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        stream.writeInt(tag.getValue().length);
        stats.attemptArraySize(tag.getValue().length, 1);
        for (int d : tag.getValue()) {
            stream.writeInt(d);
        }
    }

    @Override
    public @NotNull IntArrayTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        int len = stream.readInt();
        stats.attemptArraySize(len, 4);
        int[] data = new int[len];
        for (int i = 0; i < len; i++) {
            data[i] = stream.readInt();
        }
        return new IntArrayTag(data);
    }

}
