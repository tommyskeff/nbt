package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.ListTag;
import dev.tommyjs.nbt.tag.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListSerializer<T extends Tag> extends NamedTagSerializer<List<T>, ListTag<T>> {

    @Override
    @SuppressWarnings("unchecked")
    public void serialize0(List<T> data, DataOutput stream, NbtAPI api) throws IOException {
        Class<?> type = data.size() > 0 ? data.get(0).getClass() : EndTag.class;
        TagSerializer<T> serializer = (TagSerializer<T>) api.getRegistry().getSerializer(type);

        if (serializer == null) {
            throw new IOException("Tag serializer not found in registry for class " + type);
        }

        Integer id = api.getRegistry().getId(type);
        if (id == null) {
            throw new IOException("Tag serializer not found in registry for class " + type.getName());
        }

        stream.writeByte(id);
        stream.writeInt(data.size());

        for (T tag : data) {
            serializer.serialize(tag, stream, api, false);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListTag<T> deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        int tagId = stream.readByte();
        int len = stream.readInt();

        TagSerializer<T> serializer = (TagSerializer<T>) api.getRegistry().getDeserializer(tagId);
        if (serializer == null) {
            throw new IOException("Tag deserializer not found in registry for id " + tagId);
        }

        List<T> lst = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            T tag = serializer.deserialize(stream, api, false);
            lst.add(tag);
        }

        return new ListTag<>(name, lst);
    }

}
