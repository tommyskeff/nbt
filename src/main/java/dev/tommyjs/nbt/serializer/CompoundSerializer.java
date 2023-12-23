package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CompoundSerializer extends NamedTagSerializer<Map<String, NamedTag<?>>, CompoundTag> {

    @Override
    public void serialize0(Map<String, NamedTag<?>> data, DataOutput stream, TagRegistry registry) throws IOException {
        for (String name : data.keySet()) {
            NamedTag<?> tag = data.get(name);
            write(tag, stream, registry);
        }

        write(new EndTag(), stream, registry);
    }

    @Override
    public CompoundTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        Map<String, NamedTag<?>> compound = new HashMap<>();
        boolean ended = false;

        while (!ended) {
            int tagId = stream.readByte();
            if (tagId > 0) {
                TagSerializer<?> serializer = registry.getDeserializer(tagId);
                if (serializer == null) {
                    throw new IOException("Tag deserializer not found in registry for id " + tagId);
                }

                Tag tag = serializer.deserialize(stream, registry, true);
                if (tag instanceof NamedTag<?> nt) {
                    compound.put(nt.getName(), nt);
                } else {
                    throw new IOException("Unnamed tag in compound");
                }
            } else {
                ended = true;
            }
        }

        return new CompoundTag(name, compound);
    }

    private void write(Tag tag, DataOutput stream, TagRegistry registry) throws IOException {
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
        write(tag, serializer, stream, registry);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write(Tag tag, TagSerializer<T> serializer, DataOutput stream, TagRegistry registry) throws IOException {
        serializer.serialize((T) tag, stream, registry, true);
    }

}
