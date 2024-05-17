package dev.tommyjs.nbt;

import dev.tommyjs.nbt.tag.NamedTag;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface NbtAPI {

    byte[] serialize(@NotNull NamedTag<?> tag, @NotNull NbtOptions options) throws IOException;

    void write(@NotNull NamedTag<?> tag, @NotNull OutputStream stream, @NotNull NbtOptions options) throws IOException;

    void write(@NotNull NamedTag<?> tag, @NotNull File file, @NotNull NbtOptions options) throws IOException;

    @NotNull NamedTag<?> deserialize(byte[] data, @NotNull NbtOptions options) throws IOException;

    @NotNull NamedTag<?> read(@NotNull InputStream stream, @NotNull NbtOptions options) throws IOException;

    @NotNull NamedTag<?> read(@NotNull File file, @NotNull NbtOptions options) throws IOException;

}
