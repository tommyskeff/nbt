package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.CompoundTag;
import dev.tommyjs.nbt.tag.EndTag;
import dev.tommyjs.nbt.tag.NamedTag;
import dev.tommyjs.nbt.tag.Tag;
import dev.tommyjs.nbt.util.NbtStats;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CompoundSerializer implements TagSerializer<CompoundTag> {

    @Override
    public void serialize(@NotNull CompoundTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
        stats.incrementDepth();

        Map<String, NamedTag<?>> tags = tag.getValue();
        if (tags.size() > stats.opts().maxCompoundLength()) {
            throw new IOException("maximum compound length exceeded (" + tags.size() + " > " + stats.opts().maxCompoundLength() + ")");
        }

        for (String name : tags.keySet()) {
            NamedTag<?> nt = tags.get(name);
            write(name, nt, stream, registry, stats);
        }

        write(null, EndTag.INSTANCE, stream, registry, stats);
        stats.decrementDepth();
    }

    @Override
    public @NotNull CompoundTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                            @NotNull NbtStats stats) throws IOException {
        stats.incrementDepth();

        Map<String, NamedTag<?>> result = new LinkedHashMap<>();
        boolean ended = false;
        int i = 0;

        while (!ended) {
            stats.attemptSize(1);
            int tagId = stream.readByte();
            if (tagId > 0) {
                if (++i > stats.opts().maxCompoundLength()) {
                    throw new IOException("maximum compound length exceeded (" + i + " > " + stats.opts().maxCompoundLength() + ")");
                }

                String name = NbtUtil.attemptReadString(stats.opts().maxNameLength(), stream, stats);

                TagSerializer<?> serializer = registry.getDeserializer(tagId);
                if (serializer == null) {
                    throw new IOException("Invalid tag id " + tagId);
                }

                Tag tag = serializer.deserialize(stream, registry, stats);
                if (tag instanceof NamedTag<?> nt) {
                    result.put(name, nt);
                } else {
                    throw new IOException("Unnamed tag in compound");
                }
            } else {
                ended = true;
            }
        }

        stats.decrementDepth();
        return new CompoundTag(result);
    }

    private void write(String name, Tag tag, DataOutput stream, TagRegistry registry, NbtStats stats) throws IOException {
        Class<?> type = tag.getClass();
        TagSerializer<?> serializer = registry.getSerializer(type);

        if (serializer == null) {
            throw new IOException("Tag serializer not found in registry for class " + type);
        }

        Integer id = registry.getId(type);
        if (id == null) {
            throw new IOException("Tag serializer not found in registry for class " + type.getName());
        }

        stats.attemptSize(1);
        stream.writeByte(id);

        if (name != null) {
            NbtUtil.attemptWriteString(name, stats.opts().maxNameLength(), stream, stats);
        }

        write(tag, serializer, stream, registry, stats);
    }

    @SuppressWarnings("unchecked")
    private <T extends Tag> void write(Tag tag, TagSerializer<T> serializer, DataOutput stream, TagRegistry registry,
                                       NbtStats stats) throws IOException {
        serializer.serialize((T) tag, stream, registry, stats);
    }

}
