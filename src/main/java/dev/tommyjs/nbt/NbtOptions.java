package dev.tommyjs.nbt;

public record NbtOptions(boolean includeRootName, int maxDepth, int maxDataLength, int maxNameLength,
                         int maxStringLength, int maxCompoundLength, int maxArrayLength) {

    public static final NbtOptions DEFAULT = new NbtOptions(true, 50, 1048576, 256, 65536, 1024, 1024);

    public NbtOptions withIncludeRootName(boolean includeRootName) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxDepth(int maxDepth) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxDataLength(int maxDataLength) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxNameLength(int maxNameLength) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxStringLength(int maxStringLength) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxCompoundLength(int maxCompoundLength) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

    public NbtOptions withMaxArrayLength(int maxArrayLength) {
        return new NbtOptions(includeRootName, maxDepth, maxDataLength, maxNameLength, maxStringLength,
                maxCompoundLength, maxArrayLength);
    }

}
