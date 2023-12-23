package dev.tommyjs.nbt.tag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ListTag<T extends Tag> extends NamedTag<List<T>> implements List<T> {

    public ListTag(@Nullable String name, List<T> value) {
        super(name, value);
    }

    public ListTag(@Nullable String name) {
        this(name, new ArrayList<>());
    }

    public ListTag() {
        this(null);
    }

    @Override
    public int size() {
        return getValue().size();
    }

    @Override
    public boolean isEmpty() {
        return getValue().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getValue().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return getValue().iterator();
    }

    @Override
    public Object[] toArray() {
        return getValue().toArray();
    }

    @Override
    public <T1> T1[] toArray(@NotNull T1[] a) {
        return getValue().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return getValue().add(t);
    }

    @Override
    public boolean remove(Object o) {
        return getValue().remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return getValue().containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return getValue().addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        return getValue().addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return getValue().removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return getValue().retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        getValue().replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        getValue().sort(c);
    }

    @Override
    public void clear() {
        getValue().clear();
    }

    @Override
    public T get(int index) {
        return getValue().get(index);
    }

    @Override
    public T set(int index, T element) {
        return getValue().set(index, element);
    }

    @Override
    public void add(int index, T element) {
        getValue().add(index, element);
    }

    @Override
    public T remove(int index) {
        return getValue().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return getValue().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getValue().lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return getValue().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return getValue().listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getValue().subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<T> spliterator() {
        return getValue().spliterator();
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return getValue().toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return getValue().removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        return getValue().stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return getValue().parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        getValue().forEach(action);
    }

}
