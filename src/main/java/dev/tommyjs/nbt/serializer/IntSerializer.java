package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.IntTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntSerializer implements TagSerializer<IntTag> {

    @Override
    public void serialize(@NotNull IntTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        stream.writeInt(tag.getValue());
    }

    @Override
    public @NotNull IntTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                       @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        int data = stream.readInt();
        return new IntTag(data);
    }

}
