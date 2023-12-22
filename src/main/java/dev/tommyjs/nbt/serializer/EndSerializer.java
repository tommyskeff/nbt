package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.NbtAPI;
import dev.tommyjs.nbt.tag.EndTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EndSerializer implements TagSerializer<EndTag> {

    @Override
    public void serialize(EndTag tag, DataOutput stream, NbtAPI api, boolean includeNames) throws IOException {

    }

    @Override
    public EndTag deserialize(DataInput stream, NbtAPI api, boolean includeNames) throws IOException {
        return new EndTag();
    }

}
