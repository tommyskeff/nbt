package dev.tommyjs.nbt.impl;

import dev.tommyjs.nbt.NbtAPI;
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
    public byte[] serialize(@NotNull CompoundTag tag) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        write(tag, stream);
        return stream.toByteArray();
    }

    @Override
    public void write(@NotNull CompoundTag  tag, @NotNull OutputStream stream1) throws IOException {
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
        write0(tag, serializer, stream);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write0(CompoundTag tag, TagSerializer<T> serializer, DataOutputStream stream) throws IOException {
        serializer.serialize((T) tag, stream, registry, 0);
    }

    @Override
    public void write(@NotNull CompoundTag tag, @NotNull File file) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            write(tag, stream);
        }
    }

    @Override
    public @NotNull CompoundTag deserialize(byte[] data) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        return read(stream);
    }

    @Override
    public @NotNull CompoundTag read(@NotNull InputStream stream1) throws IOException {
        DataInputStream stream = new DataInputStream(stream1);
        int tagId = stream.read();

        TagSerializer<? extends Tag> serializer = registry.getDeserializer(tagId);
        if (serializer == null) {
            throw new IOException("Tag deserializer not found in registry for id " + tagId);
        }

        Tag tag = serializer.deserialize(stream, registry, 0);
        if (tag instanceof CompoundTag ct) {
            return ct;
        } else if (tag instanceof NamedTag<?> nt) {
            CompoundTag ct = new CompoundTag();
            ct.setTag("", nt);
            return ct;
        } else {
            throw new IOException("Root tag is not named");
        }
    }

    @Override
    public @NotNull CompoundTag read(@NotNull File file) throws IOException {
        try (FileInputStream stream = new FileInputStream(file)) {
            return read(stream);
        }
    }

}
