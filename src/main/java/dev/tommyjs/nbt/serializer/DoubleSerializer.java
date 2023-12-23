package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.DoubleTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleSerializer extends NamedTagSerializer<Double, DoubleTag> {

    @Override
    public void serialize0(Double data, DataOutput stream, TagRegistry registry) throws IOException {
        stream.writeDouble(data);
    }

    @Override
    public DoubleTag deserialize0(String name, DataInput stream, TagRegistry registry) throws IOException {
        double data = stream.readDouble();
        return new DoubleTag(name, data);
    }

}
