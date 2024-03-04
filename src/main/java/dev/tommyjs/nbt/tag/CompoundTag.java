package dev.tommyjs.nbt.tag;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CompoundTag extends NamedTag<Map<String, NamedTag<?>>> {

    public CompoundTag(Map<String, NamedTag<?>> value) {
        super(value);
    }

    public CompoundTag() {
        this(new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    public <T> T getTag(@NotNull String name, @NotNull Class<T> clazz) {
        Tag tag = getValue().get(name);
        if (tag == null) {
            return null;
        }

        if (!clazz.isAssignableFrom(tag.getClass())) {
            throw new IllegalArgumentException("Tag " + name + " is of type " + tag.getClass().getName());
        }

        return (T) tag;
    }

    public @NotNull Class<?> getTagType(@NotNull String name) {
        return containsTag(name) ? getValue().get(name).getClass() : Void.class;
    }

    public boolean containsTag(@NotNull String name) {
        return getValue().containsKey(name);
    }
    
    public void setTag(@NotNull String name, @NotNull NamedTag<?> data) {
        getValue().put(name, data);
    }

    public void remove(@NotNull String name) {
        getValue().remove(name);
    }

    public @NotNull CompoundTag getCompound(String name) {
        if (containsTag(name)) {
            return getTag(name, CompoundTag.class);
        } else {
            CompoundTag tag = new CompoundTag();
            setTag(name, tag);
            return tag;
        }
    }

    public @NotNull ListTag<?> getList(String name) {
        if (containsTag(name)) {
            return getTag(name, ListTag.class);
        } else {
            ListTag<?> tag = new ListTag<>();
            setTag(name, tag);
            return tag;
        }
    }

    public @NotNull String getString(String name) {
        StringTag tag = getTag(name, StringTag.class);
        return tag != null ? tag.getValue() : "";
    }

    public byte getByte(@NotNull String name) {
        ByteTag tag = getTag(name, ByteTag.class);
        return tag != null ? tag.getValue() : 0;
    }

    public short getShort(@NotNull String name) {
        ShortTag tag = getTag(name, ShortTag.class);
        return tag != null ? tag.getValue() : 0;
    }

    public int getInt(@NotNull String name) {
        IntTag tag = getTag(name, IntTag.class);
        return tag != null ? tag.getValue() : 0;
    }

    public long getLong(@NotNull String name) {
        LongTag tag = getTag(name, LongTag.class);
        return tag != null ? tag.getValue() : 0;
    }

    public float getFloat(@NotNull String name) {
        FloatTag tag = getTag(name, FloatTag.class);
        return tag != null ? tag.getValue() : 0F;
    }

    public double getDouble(@NotNull String name) {
        DoubleTag tag = getTag(name, DoubleTag.class);
        return tag != null ? tag.getValue() : 0D;
    }

    public byte[] getByteArray(@NotNull String name) {
        ByteArrayTag tag = getTag(name, ByteArrayTag.class);
        return tag != null ? tag.getValue() : new byte[0];
    }

    public int[] getIntArray(@NotNull String name) {
        IntArrayTag tag = getTag(name, IntArrayTag.class);
        return tag != null ? tag.getValue() : new int[0];
    }

    public long[] getLongArray(@NotNull String name) {
        LongArrayTag tag = getTag(name, LongArrayTag.class);
        return tag != null ? tag.getValue() : new long[0];
    }

    public void setCompound(@NotNull String name, CompoundTag data) {
        setTag(name, data);
    }

    public void setList(@NotNull String name, ListTag<?> data) {
        setTag(name, data);
    }

    public void setString(@NotNull String name, String data) {
        setTag(name, new StringTag(data));
    }

    public void setByte(@NotNull String name, byte data) {
        setTag(name, new ByteTag(data));
    }

    public void setShort(@NotNull String name, short data) {
        setTag(name, new ShortTag(data));
    }

    public void setInt(@NotNull String name, int data) {
        setTag(name, new IntTag(data));
    }

    public void setLong(@NotNull String name, long data) {
        setTag(name, new LongTag(data));
    }

    public void setFloat(@NotNull String name, float data) {
        setTag(name, new FloatTag(data));
    }

    public void setDouble(@NotNull String name, double data) {
        setTag(name, new DoubleTag(data));
    }

    public void setByteArray(@NotNull String name, byte[] data) {
        setTag(name, new ByteArrayTag(data));
    }

    public void setIntArray(@NotNull String name, int[] data) {
        setTag(name, new IntArrayTag(data));
    }

    public void setLongArray(@NotNull String name, long[] data) {
        setTag(name, new LongArrayTag(data));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CompoundTag{");
        int i = 0;
        for (String name : getValue().keySet()) {
            NamedTag<?> tag = getValue().get(name);
            String val = tag.toString();
            sb.append("'").append(name).append("': ").append(val);
            if (i++ < getValue().size() - 1) {
                sb.append(", ");
            }
        }
        return sb.append("}").toString();
    }

}
