package dev.tommyjs.nbt;

import dev.tommyjs.nbt.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface NbtAPI {

    byte[] serialize(@NotNull Tag tag, @NotNull NbtOptions options) throws IOException;

    void write(@NotNull Tag tag, @NotNull OutputStream stream, @NotNull NbtOptions options) throws IOException;

    void write(@NotNull Tag tag, @NotNull File file, @NotNull NbtOptions options) throws IOException;

    @NotNull Tag deserialize(byte[] data, @NotNull NbtOptions options) throws IOException;

    @NotNull Tag read(@NotNull InputStream stream, @NotNull NbtOptions options) throws IOException;

    @NotNull Tag read(@NotNull File file, @NotNull NbtOptions options) throws IOException;

}
