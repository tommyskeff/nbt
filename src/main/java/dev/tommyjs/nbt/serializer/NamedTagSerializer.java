package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.NamedTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class NamedTagSerializer<K, T extends NamedTag<K>> implements TagSerializer<T> {

    @Override
    public void serialize(T tag, DataOutput stream, NbtAPI api, boolean includeNames) throws IOException {
        if (includeNames) {
            String name = tag.getName() == null ? "" : tag.getName();
            stream.writeUTF(name);
        }

        serialize0(tag.getValue(), stream, api);
    }

    @Override
    public T deserialize(DataInput stream, NbtAPI api, boolean includeNames) throws IOException {
        String name = includeNames ? stream.readUTF() : null;
        return deserialize0(name, stream, api);
    }

    public abstract void serialize0(K data, DataOutput stream, NbtAPI api) throws IOException;

    public abstract T deserialize0(String name, DataInput stream, NbtAPI api) throws IOException;

}
