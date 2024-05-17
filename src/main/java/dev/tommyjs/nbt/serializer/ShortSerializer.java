package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ShortTag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortSerializer implements TagSerializer<ShortTag> {

    @Override
    public void serialize(@NotNull ShortTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        stream.writeShort(tag.getValue());
    }

    @Override
    public @NotNull ShortTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        short data = stream.readShort();
        return new ShortTag(data);
    }

}
