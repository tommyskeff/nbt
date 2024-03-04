package dev.tommyjs.nbt.tag;

import org.jetbrains.annotations.NotNull;

public class NamedTag<T> implements Tag {

    private final @NotNull T value;

    public NamedTag(@NotNull T value) {
        this.value = value;
    }

    public @NotNull T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
