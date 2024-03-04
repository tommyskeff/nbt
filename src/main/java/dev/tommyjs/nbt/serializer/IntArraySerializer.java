package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.IntArrayTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntArraySerializer implements TagSerializer<IntArrayTag> {

    @Override
    public void serialize(@NotNull IntArrayTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        stream.writeInt(tag.getValue().length);
        for (int d : tag.getValue()) {
            stream.writeInt(d);
        }
    }

    @Override
    public @NotNull IntArrayTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        int len = stream.readInt();
        int[] data = new int[len];

        for (int i = 0; i < len; i++) {
            data[i] = stream.readInt();
        }

        return new IntArrayTag(data);
    }

}
