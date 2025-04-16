package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EndSerializer implements TagSerializer<EndTag> {

    @Override
    public void serialize(@NotNull EndTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
    }

    @Override
    public @NotNull EndTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                       @NotNull NbtStats stats) throws IOException {
        return EndTag.INSTANCE;
    }

}
