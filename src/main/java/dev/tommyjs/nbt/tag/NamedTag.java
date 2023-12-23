package dev.tommyjs.nbt.tag;

import org.jetbrains.annotations.Nullable;

public abstract class NamedTag<T> implements Tag {

    private final @Nullable String name;
    private final T value;

    public NamedTag(@Nullable String name, T value) {
        this.name = name;
        this.value = value;
    }

    public @Nullable String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public String formatValue() {
        return value.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + (name == null ?
            "{" + formatValue() + "}" :
            "{" + "name='" + name + '\'' + ", value=" + formatValue() + "}"
        );
    }

}
