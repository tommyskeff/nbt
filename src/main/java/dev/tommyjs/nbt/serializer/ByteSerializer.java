package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.ByteTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteSerializer extends NamedTagSerializer<Byte, ByteTag> {

    @Override
    public void serialize0(Byte data, DataOutput stream, NbtAPI api) throws IOException {
        stream.writeByte(data);
    }

    @Override
    public ByteTag deserialize0(String name, DataInput stream, NbtAPI api) throws IOException {
        byte data = stream.readByte();
        return new ByteTag(name, data);
    }

}
