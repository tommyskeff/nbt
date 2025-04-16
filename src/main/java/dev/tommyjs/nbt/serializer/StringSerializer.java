package dev.tommyjs.nbt.serializer;

import dev.tommyjs.nbt.registry.TagRegistry;
import dev.tommyjs.nbt.tag.StringTag;
import dev.tommyjs.nbt.util.NbtStats;
import dev.tommyjs.nbt.util.NbtUtil;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringSerializer implements TagSerializer<StringTag> {

    @Override
    public void serialize(@NotNull StringTag tag, @NotNull DataOutput stream, @NotNull TagRegistry registry,
                          @NotNull NbtStats stats) throws IOException {
        NbtUtil.attemptWriteString(tag.getValue(), stats.opts().maxStringLength(), stream, stats);
    }

    @Override
    public @NotNull StringTag deserialize(@NotNull DataInput stream, @NotNull TagRegistry registry,
                                          @NotNull NbtStats stats) throws IOException {
        return new StringTag(NbtUtil.attemptReadString(stats.opts().maxStringLength(), stream, stats));
    }

}
