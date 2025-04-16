package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ByteArrayTag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteArraySerializer implements TagSerializer<ByteArrayTag> {

    @Override
    public void serialize(@NotNull ByteArrayTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        stream.writeInt(tag.getValue().length);
        stats.attemptArraySize(tag.getValue().length, 1);
        stream.write(tag.getValue());
    }

    @Override
    public @NotNull ByteArrayTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.attemptSize(4);
        int len = stream.readInt();
        stats.attemptArraySize(len, 1);
        byte[] data = new byte[len];
        stream.readFully(data);
        return new ByteArrayTag(data);
    }

}
