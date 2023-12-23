package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.Tag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface TagSerializer<T extends Tag> {

    void serialize(T tag, DataOutput stream, TagRegistry registry, boolean includeNames) throws IOException;

    T deserialize(DataInput stream, TagRegistry apiregistry, boolean includeNames) throws IOException;

}
