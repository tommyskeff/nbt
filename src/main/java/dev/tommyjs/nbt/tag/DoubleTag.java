package dev.tommyjs.nbt.tag;

public class DoubleTag extends NamedTag<Double> {

    public DoubleTag(String name, double value) {
        super(name, value);
    }

    public DoubleTag(double value) {
        super(null, value);
    }

}
