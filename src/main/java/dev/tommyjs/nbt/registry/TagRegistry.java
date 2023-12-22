package dev.tommyjs.nbt.registry;

import dev.tommyjs.nbt.serializer.TagSerializer;
import dev.tommyjs.nbt.tag.Tag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TagRegistry {

    private final Map<Integer, TagSerializer<? extends Tag>> idIndex;
    private final Map<Class<?>, TagSerializer<? extends Tag>> typeIndex;
    private final Map<Class<?>, Integer> classIdIndex;

    public TagRegistry() {
        this.idIndex = new HashMap<>();
        this.typeIndex = new HashMap<>();
        this.classIdIndex = new HashMap<>();
    }

    public <T extends Tag> TagRegistry withTag(int id, @NotNull Class<T> clazz, TagSerializer<T> serializer) {
        if (idIndex.containsKey(id)) {
            throw new IllegalArgumentException("Tag ID already registered");
        }

        if (typeIndex.containsKey(clazz)) {
            throw new IllegalArgumentException("Tag type already registered");
        }

        if (classIdIndex.containsKey(clazz)) {
            throw new IllegalArgumentException("Tag type already registered");
        }

        idIndex.put(id, serializer);
        typeIndex.put(clazz, serializer);
        classIdIndex.put(clazz, id);

        return this;
    }

    public @Nullable TagSerializer<? extends Tag> getSerializer(@NotNull Class<?> clazz) {
        return typeIndex.get(clazz);
    }

    public @Nullable TagSerializer<? extends Tag> getDeserializer(int id) {
        return idIndex.get(id);
    }

    public @Nullable Integer getId(@NotNull Class<?> clazz) {
        return classIdIndex.get(clazz);
    }

}
