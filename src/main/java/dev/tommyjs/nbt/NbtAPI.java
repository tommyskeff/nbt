package dev.tommyjs.nbt;

import dev.tommyjs.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface NbtAPI {

    byte[] serialize(@NotNull CompoundTag tag) throws IOException;

    void write(@NotNull CompoundTag tag, @NotNull OutputStream stream) throws IOException;

    void write(@NotNull CompoundTag tag, @NotNull File file) throws IOException;

    @NotNull CompoundTag deserialize(byte[] data) throws IOException;

    @NotNull CompoundTag read(@NotNull InputStream stream) throws IOException;

    @NotNull CompoundTag read(@NotNull File file) throws IOException;

}
