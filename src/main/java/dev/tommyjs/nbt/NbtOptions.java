package dev.tommyjs.nbt;

public record NbtOptions(boolean includeRootName, int maxDepth) {

    public static final NbtOptions DEFAULT = new NbtOptions(true, 50);

}
