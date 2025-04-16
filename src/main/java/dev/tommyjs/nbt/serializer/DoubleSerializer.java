package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.DoubleTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleSerializer implements TagSerializer<DoubleTag> {

    @Override
    public void serialize(@NotNull DoubleTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(8);
        stream.writeDouble(tag.getValue());
    }

    @Override
    public @NotNull DoubleTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                          @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(8);
        double data = stream.readDouble();
        return new DoubleTag(data);
    }

}
