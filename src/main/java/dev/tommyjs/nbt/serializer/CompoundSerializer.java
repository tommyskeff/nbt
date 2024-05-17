package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompoundSerializer implements TagSerializer<CompoundTag> {

    @Override
    public void serialize(@NotNull CompoundTag tag, @NotNull NbtOptions options, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);

        for (String name : tag.getValue().keySet()) {
            NamedTag<?> nt = tag.getValue().get(name);
            write(name, nt, options, stream, registry, depth + 1);
        }

        write(null, new EndTag(), options, stream, registry, depth + 1);
    }

    @Override
    public @NotNull CompoundTag deserialize(@NotNull DataInput stream, @NotNull NbtOptions options, @NotNull TagRegistry registry, int depth) throws IOException {
        NbtUtil.checkDepth(depth, options);
        Map<String, NamedTag<?>> compound = new HashMap<>();

        boolean ended = false;
        while (!ended) {
            int tagId = stream.readByte();
            if (tagId > 0) {
                String subName = stream.readUTF();

                TagSerializer<?> serializer = registry.getDeserializer(tagId);
                if (serializer == null) {
                    throw new IOException("Tag deserializer not found in registry for id " + tagId);
                }

                Tag tag = serializer.deserialize(stream, options, registry, depth + 1);
                if (tag instanceof NamedTag<?> nt) {
                    compound.put(subName, nt);
                } else {
                    throw new IOException("Tag inside compound is not named");
                }
            } else {
                ended = true;
            }
        }

        return new CompoundTag(compound);
    }

    private void write(String name, Tag tag, NbtOptions options, DataOutput stream, TagRegistry registry, int depth) throws IOException {
        Class<?> type = tag.getClass();
        TagSerializer<?> serializer = registry.getSerializer(type);

        if (serializer == null) {
            throw new IOException("Tag serializer not found in registry for class " + type);
        }

        Integer id = registry.getId(type);
        if (id == null) {
            throw new IOException("Tag serializer not found in registry for class " + type.getName());
        }

        stream.writeByte(id);
        if (name != null) {
            stream.writeUTF(name);
        }

        write(tag, options, serializer, stream, registry, depth);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write(Tag tag, NbtOptions options, TagSerializer<T> serializer, DataOutput stream, TagRegistry registry, int depth) throws IOException {
        serializer.serialize((T) tag, options, stream, registry, depth + 1);
    }

}
