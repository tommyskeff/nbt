package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.LongArrayTag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongArraySerializer implements TagSerializer<LongArrayTag> {

    @Override
    public void serialize(@NotNull LongArrayTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);

        stream.writeInt(tag.getValue().length);
        for (long d : tag.getValue()) {
            stream.writeLong(d);
        }
    }

    @Override
    public @NotNull LongArrayTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);

        int len = stream.readInt();
        long[] data = new long[len];

        for (int i = 0; i < len; i++) {
            data[i] = stream.readLong();
        }

        return new LongArrayTag(data);
    }

}
