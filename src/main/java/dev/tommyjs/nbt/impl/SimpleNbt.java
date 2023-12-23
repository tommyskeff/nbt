package dev.tommyjs.nbt.impl;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.serializer.TagSerializer;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;

import java.io.*;

public class SimpleNbt implements NbtAPI {

    private final TagRegistry registry;

    public SimpleNbt(TagRegistry registry) {
        this.registry = registry;
    }

    @Override
    public byte[] serialize(Tag tag) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        write(tag, stream);
        return stream.toByteArray();
    }

    @Override
    public void write(Tag tag, OutputStream stream1) throws IOException {
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
    private <T extends Tag> void write0(Tag tag, TagSerializer<T> serializer, DataOutputStream stream) throws IOException {
        serializer.serialize((T) tag, stream, this, true);
    }

    @Override
    public void write(Tag tag, File file) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            write(tag, stream);
        }
    }

    @Override
    public CompoundTag deserialize(byte[] data) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        return read(stream);
    }

    @Override
    public CompoundTag read(InputStream stream1) throws IOException {
        DataInputStream stream = new DataInputStream(stream1);
        int tagId = stream.read();

        TagSerializer<? extends Tag> serializer = registry.getDeserializer(tagId);
        if (serializer == null) {
            throw new IOException("Tag deserializer not found in registry for id " + tagId);
        }

        Tag tag = serializer.deserialize(stream, this, true);
        if (!(tag instanceof NamedTag)) {
            throw new IOException("Root tag is not named");
        }

        if (tag instanceof CompoundTag ct) {
            return ct;
        }

        CompoundTag ct = new CompoundTag();
        ct.setTag("root", (NamedTag<?>) tag);

        return ct;
    }

    @Override
    public CompoundTag read(File file) throws IOException {
        try (FileInputStream stream = new FileInputStream(file)) {
            return read(stream);
        }
    }

    @Override
    public TagRegistry getRegistry() {
        return registry;
    }

}
