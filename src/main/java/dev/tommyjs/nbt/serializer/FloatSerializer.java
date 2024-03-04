package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.FloatTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatSerializer implements TagSerializer<FloatTag> {

    @Override
    public void serialize(@NotNull FloatTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        stream.writeFloat(tag.getValue());
    }

    @Override
    public @NotNull FloatTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        float data = stream.readFloat();
        return new FloatTag(data);
    }

}
