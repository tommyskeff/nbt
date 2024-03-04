package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.ByteTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteSerializer implements TagSerializer<ByteTag> {

    @Override
    public void serialize(@NotNull ByteTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        stream.writeByte(tag.getValue());
    }

    @Override
    public @NotNull ByteTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        byte data = stream.readByte();
        return new ByteTag(data);
    }

}
