package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ByteArrayTag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteArraySerializer implements TagSerializer<ByteArrayTag> {

    @Override
    public void serialize(@NotNull ByteArrayTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        stream.writeInt(tag.getValue().length);
        stream.write(tag.getValue());
    }

    @Override
    public @NotNull ByteArrayTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        int len = stream.readInt();
        byte[] data = new byte[len];
        stream.readFully(data);
        return new ByteArrayTag(data);
    }

}
