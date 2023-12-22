package dev.tommyjs.nbt.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompoundTag extends NamedTag<List<NamedTag<?>>> {

    private Map<String, NamedTag<?>> index;

    public CompoundTag(String name, List<NamedTag<?>> value) {
        super(name, value);
    }

    private void ensureIndexed() {
        if (index != null) {
            return;
        }

        index = new HashMap<>();
        for (NamedTag<?> tag : getValue()) {
            // System.out.println(getName() + " " + index);
            index.put(tag.getName(), tag);
        }
    }

    public Object getTag(String name) {
        ensureIndexed();
        return index.get(name);
    }

    public CompoundTag getCompoundTag(String name) {
        return (CompoundTag) getTag(name);
    }

    public ByteTag getByteTag(String name) {
        return (ByteTag) getTag(name);
    }

    public ShortTag getShortTag(String name) {
        return (ShortTag) getTag(name);
    }

    public IntTag getIntTag(String name) {
        return (IntTag) getTag(name);
    }

    public LongTag getLongTag(String name) {
        return (LongTag) getTag(name);
    }

    public FloatTag getFloatTag(String name) {
        return (FloatTag) getTag(name);
    }

    public DoubleTag getDoubleTag(String name) {
        return (DoubleTag) getTag(name);
    }

    public ByteArrayTag getByteArrayTag(String name) {
        return (ByteArrayTag) getTag(name);
    }

    public StringTag getStringTag(String name) {
        return (StringTag) getTag(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends Tag> ListTag<T> getListTag(String name) {
        return (ListTag<T>) getTag(name);
    }

    public IntArrayTag getIntArrayTag(String name) {
        return (IntArrayTag) getTag(name);
    }

    public LongArrayTag getLongArrayTag(String name) {
        return (LongArrayTag) getTag(name);
    }

    public byte getByte(String name) {
        return getByteTag(name).getValue();
    }

    public short getShort(String name) {
        return getShortTag(name).getValue();
    }

    public int getInt(String name) {
        return getIntTag(name).getValue();
    }

    public long getLong(String name) {
        return getLongTag(name).getValue();
    }

    public float getFloat(String name) {
        return getFloatTag(name).getValue();
    }

    public double getDouble(String name) {
        return getDoubleTag(name).getValue();
    }

    public byte[] getByteArray(String name) {
        return getByteArrayTag(name).getValue();
    }

    public String getString(String name) {
        return getStringTag(name).getValue();
    }

    public <T extends Tag> List<T> getList(String name) {
        ListTag<T> lst = getListTag(name);
        return lst.getValue();
    }

    public int[] getIntArray(String name) {
        return getIntArrayTag(name).getValue();
    }

    public long[] getLongArray(String name) {
        return getLongArrayTag(name).getValue();
    }

}
