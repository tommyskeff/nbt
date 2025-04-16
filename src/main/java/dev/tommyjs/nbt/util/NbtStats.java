package dev.tommyjs.nbt.util;

import dev.tommyjs.nbt.NbtOptions;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NbtStats {

    private final NbtOptions options;

    private int length;
    private int depth;

    public NbtStats(@NotNull NbtOptions options) {
        this.options = options;
        this.length = 0;
        this.depth = 0;
    }

    public void attemptSize(int size) throws IOException {
        if ((this.length += size) > options.maxDataLength()) {
            throw new IOException("maximum nbt data length exceeded (" + this.length + " > " + options.maxDataLength() + ")");
        }
    }

    public void attemptArraySize(int size, int elementSize) throws IOException {
        if (size > options.maxArrayLength()) {
            throw new IOException("maximum nbt array length exceeded (" + size + " > " + options.maxArrayLength() + ")");
        }

        if ((this.length += (size * elementSize)) > options.maxDataLength()) {
            throw new IOException("maximum nbt data length exceeded (max=" + options.maxDataLength() + ")");
        }
    }

    public void incrementDepth() throws IOException {
        if (++this.depth > options.maxDepth()) {
            throw new IOException("maximum nbt depth exceeded (" + this.depth + " > " + options.maxDepth() + ")");
        }
    }

    public void decrementDepth() {
        this.depth--;
    }

    public int depth() {
        return depth;
    }

    public NbtOptions opts() {
        return options;
    }

}
