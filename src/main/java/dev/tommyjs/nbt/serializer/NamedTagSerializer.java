package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.NamedTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NamedTagSerializer<K, T extends NamedTag<K>> implements TagSerializer<T> {

    @Override
    public void serialize(T tag, DataOutput stream, TagRegistry registry, boolean includeNames) throws IOException {
        if (includeNames) {
            String name = tag.getName() == null ? "" : tag.getName();
            stream.writeUTF(name);
        }

        serialize0(tag.getValue(), stream, registry);
    }

    @Override
    public T deserialize(DataInput stream, TagRegistry registry, boolean includeNames) throws IOException {
        String name = includeNames ? stream.readUTF() : null;
        return deserialize0(name, stream, registry);
    }

    public abstract void serialize0(K data, DataOutput stream, TagRegistry registry) throws IOException;

    public abstract T deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException;

}
