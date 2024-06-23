package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    private void grow() {
        int newCapacity = container.length == 0 ? 10 : container.length * 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    /**
     * @param index
     * @param newValue
     * @return old value
     * Throws: IndexOutOfBoundsException – if in list no element in this index
     */
    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T old = container[index];
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T result = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - index - 1
        );
        size--;
        container[size] = null;
        modCount++;
        return result;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
