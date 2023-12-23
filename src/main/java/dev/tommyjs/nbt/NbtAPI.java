package dev.tommyjs.nbt;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.Tag;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface NbtAPI {

    byte[] serialize(Tag tag) throws IOException;

    void write(Tag tag, OutputStream stream) throws IOException;

    void write(Tag tag, File file) throws IOException;

    CompoundTag deserialize(byte[] data) throws IOException;

    CompoundTag read(InputStream stream) throws IOException;

    CompoundTag read(File file) throws IOException;

    TagRegistry getRegistry();

}
