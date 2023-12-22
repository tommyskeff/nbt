package dev.tommyjs.nbt.tag;

public class FloatTag extends NamedTag<Float> {

    public FloatTag(String name, float value) {
        super(name, value);
    }

    public FloatTag(float value) {
        super(null, value);
    }

}
