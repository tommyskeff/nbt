package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.ListTag;
import dev.tommyjs.nbt.tag.Tag;
import dev.tommyjs.nbt.util.NbtStats;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListSerializer<T extends Tag> implements TagSerializer<ListTag<T>> {

    @Override
    public void serialize(@NotNull ListTag<T> tag, @NotNull DataOutput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.incrementDepth();

        List<T> data = tag.getValue();
        Class<?> type = !data.isEmpty() ? data.get(0).getClass() : EndTag.class;

        //noinspection unchecked
        TagSerializer<T> serializer = (TagSerializer<T>) registry.getSerializer(type);
        if (serializer == null) {
            throw new IOException("Tag serializer not found in registry for class " + type);
        }

        Integer id = registry.getId(type);
        if (id == null) {
            throw new IOException("Tag serializer not found in registry for class " + type.getName());
        }

        stats.attemptSize(5);
        stream.writeByte(id);
        stream.writeInt(data.size());

        for (T element : data) {
            serializer.serialize(element, stream, registry, stats);
        }

        stats.decrementDepth();
    }

    @Override
    public @NotNull ListTag<T> deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry, @NotNull NbtStats stats) throws IOException {
        stats.incrementDepth();

        stats.attemptSize(5);
        int tagId = stream.readByte();
        int len = stream.readInt();

        //noinspection unchecked
        TagSerializer<T> serializer = (TagSerializer<T>) registry.getDeserializer(tagId);
        if (serializer == null) {
            throw new IOException("Tag deserializer not found in registry for id " + tagId);
        }

        List<T> lst = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            T element = serializer.deserialize(stream, registry, stats);
            lst.add(element);
        }

        stats.decrementDepth();
        return new ListTag<>(lst);
    }

}
