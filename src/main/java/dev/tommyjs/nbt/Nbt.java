package dev.tommyjs.nbt;

import dev.tommyjs.nbt.impl.SimpleNbt;
import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.serializer.*;
import dev.tommyjs.nbt.tag.*;
import org.jetbrains.annotations.NotNull;

public class Nbt {

    public static final TagRegistry DEFAULT_REGISTRY = new TagRegistry()
        .withTag(0, EndTag.class, new EndSerializer())
        .withTag(1, ByteTag.class, new ByteSerializer())
        .withTag(2, ShortTag.class, new ShortSerializer())
        .withTag(3, IntTag.class, new IntSerializer())
        .withTag(4, LongTag.class, new LongSerializer())
        .withTag(5, FloatTag.class, new FloatSerializer())
        .withTag(6, DoubleTag.class, new DoubleSerializer())
        .withTag(7, ByteArrayTag.class, new ByteArraySerializer())
        .withTag(8, StringTag.class, new StringSerializer())
        .withTag(9, ListTag.class, new ListSerializer())
        .withTag(10, CompoundTag.class, new CompoundSerializer())
        .withTag(11, IntArrayTag.class, new IntArraySerializer())
        .withTag(12, LongArrayTag.class, new LongArraySerializer());

    private static final NbtAPI API = new SimpleNbt(DEFAULT_REGISTRY);

    public static @NotNull NbtAPI getApi() {
        return API;
    }

}
