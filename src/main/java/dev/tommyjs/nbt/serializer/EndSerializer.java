package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EndSerializer implements TagSerializer<EndTag> {

    @Override
    public void serialize(@NotNull EndTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
    }

    @Override
    public @NotNull EndTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        return new EndTag();
    }

}
