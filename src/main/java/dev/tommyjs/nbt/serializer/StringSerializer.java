package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.StringTag;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringSerializer implements TagSerializer<StringTag> {

    @Override
    public void serialize(@NotNull StringTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        stream.writeUTF(tag.getValue());
    }

    @Override
    public @NotNull StringTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        String data = stream.readUTF();
        return new StringTag(data);
    }

}
