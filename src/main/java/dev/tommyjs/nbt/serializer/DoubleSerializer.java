package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.DoubleTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleSerializer extends NamedTagSerializer<Double, DoubleTag> {

    @Override
    public void serialize0(Double data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeDouble(data);
    }

    @Override
    public DoubleTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        double data = stream.readDouble();
        return new DoubleTag(name, data);
    }

}
