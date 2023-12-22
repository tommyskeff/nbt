package dev.tommyjs.nbt.tag;

public abstract class NamedTag<T> implements Tag {

    private final String name;
    private final T value;

    public NamedTag(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
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
