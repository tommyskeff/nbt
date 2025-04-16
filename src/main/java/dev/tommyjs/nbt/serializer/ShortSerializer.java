package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ShortTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortSerializer implements TagSerializer<ShortTag> {

    @Override
    public void serialize(@NotNull ShortTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        stream.writeShort(tag.getValue());
    }

    @Override
    public @NotNull ShortTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        short data = stream.readShort();
        return new ShortTag(data);
    }

}
