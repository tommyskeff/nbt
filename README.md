# NBT
Simple yet powerful library for serializing and deserializing data in NBT format. 
 
### Examples
```java
FileInputStream stream = new FileInputStream("bigtest.nbt");
GZIPInputStream gis = new GZIPInputStream(stream);
CompoundTag tag = Nbt.getApi().read(gis);
System.out.println(tag);
```
```java
Tag tag = ...
byte[] binData = Nbt.getApi().serialize(tag);
System.out.println(Arrays.toString(binData));
```

```java
byte[] data = ...
CompoundTag tag = Nbt.getApi().deserialize(data);
System.out.println(tag.getInt("MyIntegerField"));
```