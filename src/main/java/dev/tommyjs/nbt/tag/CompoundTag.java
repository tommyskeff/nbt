package dev.tommyjs.nbt.tag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class CompoundTag extends NamedTag<Map<String, NamedTag<?>>> {

    public CompoundTag(@Nullable String name, @NotNull Map<String, NamedTag<?>> value) {
        super(name, value);
    }

    public CompoundTag(@Nullable String name) {
        this(name, new HashMap<>());
    }

    public CompoundTag() {
        this(null);
    }

    @SuppressWarnings("unchecked")
    public <T> T getTag(@NotNull String name, @NotNull Class<T> clazz) {
        NamedTag<?> tag = getValue().get(name);
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
        return containsTag(name) ? getTag(name, CompoundTag.class) : new CompoundTag();
    }

    public @NotNull ListTag<?> getList(String name) {
        return containsTag(name) ? getTag(name, ListTag.class) : new ListTag<>();
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
        setTag(name, new StringTag(name, data));
    }

    public void setByte(@NotNull String name, byte data) {
        setTag(name, new ByteTag(name, data));
    }

    public void setShort(@NotNull String name, short data) {
        setTag(name, new ShortTag(name, data));
    }

    public void setInt(@NotNull String name, int data) {
        setTag(name, new IntTag(name, data));
    }

    public void setLong(@NotNull String name, long data) {
        setTag(name, new LongTag(name, data));
    }

    public void setFloat(@NotNull String name, float data) {
        setTag(name, new FloatTag(name, data));
    }

    public void setDouble(@NotNull String name, double data) {
        setTag(name, new DoubleTag(name, data));
    }

    public void setByteArray(@NotNull String name, byte[] data) {
        setTag(name, new ByteArrayTag(name, data));
    }

    public void setIntArray(@NotNull String name, int[] data) {
        setTag(name, new IntArrayTag(name, data));
    }

    public void setLongArray(@NotNull String name, long[] data) {
        setTag(name, new LongArrayTag(name, data));
    }

    private String format(boolean showName) {
        StringBuilder sb = new StringBuilder("CompoundTag{");
        if (showName) {
            String name = getName() == null ? "" : getName();
            sb.append("name='").append(name).append("', {");
        }

        int i = 0;
        for (String name : getValue().keySet()) {
            NamedTag<?> tag = getValue().get(name);
            String val;

            if (tag instanceof CompoundTag) {
                val = ((CompoundTag) tag).format(false);
            } else {
                val = tag.formatValue();
            }

            sb.append("'").append(name).append("': ").append(val);

            if (i++ < getValue().size() - 1) {
                sb.append(", ");
            }
        }

        if (showName) {
            sb.append("}");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return format(true);
    }

}
