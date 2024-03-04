package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CompoundSerializer implements TagSerializer<CompoundTag> {

    @Override
    public void serialize(@NotNull CompoundTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        if (depth == 0) {
            stream.writeUTF("");
        }

        for (String name : tag.getValue().keySet()) {
            NamedTag<?> nt = tag.getValue().get(name);
            write(name, nt, stream, registry, depth + 1);
        }

        write(null, new EndTag(), stream, registry, depth + 1);
    }

    @Override
    public @NotNull CompoundTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, int depth) throws IOException {
        String name = null;
        if (depth < 1) {
            name = stream.readUTF();
        }

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

                Tag tag = serializer.deserialize(stream, registry, depth + 1);
                if (tag instanceof NamedTag<?> nt) {
                    compound.put(subName, nt);
                } else {
                    throw new IOException("Tag inside compound is not named");
                }
            } else {
                ended = true;
            }
        }

        if (name == null) {
            return new CompoundTag(compound);
        } else {
            CompoundTag ct = new CompoundTag();
            ct.setCompound(name, new CompoundTag(compound));
            return ct;
        }
    }

    private void write(String name, Tag tag, DataOutput stream, TagRegistry registry, int depth) throws IOException {
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

        write(tag, serializer, stream, registry, depth);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write(Tag tag, TagSerializer<T> serializer, DataOutput stream, TagRegistry registry, int depth) throws IOException {
        serializer.serialize((T) tag, stream, registry, depth + 1);
    }

}
