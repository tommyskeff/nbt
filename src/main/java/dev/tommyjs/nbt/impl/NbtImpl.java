package dev.tommyjs.nbt.impl;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.NbtOptions;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.serializer.TagSerializer;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class NbtImpl implements NbtAPI {

    private final TagRegistry registry;

    public NbtImpl(TagRegistry registry) {
        this.registry = registry;
    }

    @Override
    public byte[] serialize(@NotNull Tag tag, @NotNull NbtOptions options) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        write(tag, stream, options);
        return stream.toByteArray();
    }

    @Override
    public void write(@NotNull Tag tag, @NotNull OutputStream stream1, @NotNull NbtOptions options) throws IOException {
        DataOutputStream stream = new DataOutputStream(stream1);
        Class<?> clazz = tag.getClass();

        TagSerializer<? extends Tag> serializer = registry.getSerializer(clazz);
        if (serializer == null) {
            throw new IOException("Tag serializer not found in registry for " + clazz.getName());
        }

        Integer tagId = registry.getId(clazz);
        if (tagId == null) {
            throw new IOException("Tag ID not found in registry for " + clazz.getName());
        }

        stream.write(tagId & 0xFF);

        if (options.includeRootName()) {
            stream.writeUTF("");
        }

        write0(tag, serializer, stream, options);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write0(Tag tag, TagSerializer<T> serializer, DataOutputStream stream, @NotNull NbtOptions options) throws IOException {
        serializer.serialize((T) tag, options, stream, registry, 0);
    }

    @Override
    public void write(@NotNull Tag tag, @NotNull File file, @NotNull NbtOptions options) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            write(tag, stream, options);
        }
    }

    @Override
    public @NotNull Tag deserialize(byte[] data, @NotNull NbtOptions options) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        return read(stream, options);
    }

    @Override
    public @NotNull Tag read(@NotNull InputStream stream1, @NotNull NbtOptions options) throws IOException {
        DataInputStream stream = new DataInputStream(stream1);
        int tagId = stream.read();

        TagSerializer<? extends Tag> serializer = registry.getDeserializer(tagId);
        if (serializer == null) {
            throw new IOException("Tag deserializer not found in registry for id " + tagId);
        }

        String name = null;
        if (options.includeRootName()) {
            name = stream.readUTF();
        }

        Tag tag = serializer.deserialize(stream, options, registry, 0);
        if (name == null || name.isEmpty() || !(tag instanceof NamedTag<?> namedTag)) {
            return tag;
        } else {
            CompoundTag result = new CompoundTag();
            result.setTag(name, namedTag);
            return result;
        }
    }

    @Override
    public @NotNull Tag read(@NotNull File file, @NotNull NbtOptions options) throws IOException {
        try (FileInputStream stream = new FileInputStream(file)) {
            return read(stream, options);
        }
    }

}
