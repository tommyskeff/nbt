package dev.tommyjs.nbt.util;

import dev.tommyjs.nbt.NbtOptions;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NbtUtil {

    public static void checkDepth(int depth, @NotNull NbtOptions options) throws IOException {
        if (depth > options.maxDepth()) {
            throw new IOException("Max depth exceeded");
        }
    }

}
