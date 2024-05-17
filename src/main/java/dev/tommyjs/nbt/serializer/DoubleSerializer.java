package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.DoubleTag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleSerializer implements TagSerializer<DoubleTag> {

    @Override
    public void serialize(@NotNull DoubleTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        stream.writeDouble(tag.getValue());
    }

    @Override
    public @NotNull DoubleTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        double data = stream.readDouble();
        return new DoubleTag(data);
    }

}
