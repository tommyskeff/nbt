package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface TagSerializer<T extends Tag> {

    void serialize(@NotNull T tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException;

    @NotNull T deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException;

}
