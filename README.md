# NBT
Simple yet powerful library for serializing and deserializing data in NBT format. 
 
### Examples
```java
FileInputStream stream = new FileInputStream("bigtest.nbt");
GZIPInputStream gis = new GZIPInputStream(stream);
Tag tag = Nbt.getApi().read(gis);
System.out.println(tag);
```

```java
CompoundTag tag = new CompoundTag("A",
    new IntTag("B", 4),
    new CompoundTag("C",
        new ByteArrayTag("D", new byte[]{1, 2, 3, 4}),
        new IntTag("E", 3746)
    ),
    new ListTag<>("MyList", new StringTag("Hi"), new StringTag("Bob"))
);

byte[] binData = Nbt.getApi().serialize(tag);
System.out.println(Arrays.toString(binData));
```

```java
byte[] data = ...
CompoundTag t1 = (CompoundTag) Nbt.getApi().deserialize(data);
System.out.println(t1.getInt("MyIntegerField"));
```