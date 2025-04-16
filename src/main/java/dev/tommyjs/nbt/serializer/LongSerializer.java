package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.LongTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongSerializer implements TagSerializer<LongTag> {

    @Override
    public void serialize(@NotNull LongTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(8);
        stream.writeLong(tag.getValue());
    }

    @Override
    public @NotNull LongTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                        @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(8);
        long data = stream.readLong();
        return new LongTag(data);
    }

}
