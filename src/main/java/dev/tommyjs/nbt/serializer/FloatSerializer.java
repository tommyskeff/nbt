package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.FloatTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatSerializer extends NamedTagSerializer<Float, FloatTag> {

    @Override
    public void serialize0(Float data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeFloat(data);
    }

    @Override
    public FloatTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        float data = stream.readFloat();
        return new FloatTag(name, data);
    }

}
