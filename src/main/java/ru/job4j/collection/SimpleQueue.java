package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<T> implements Queue<T>, Iterable<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    @Override
    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 0; i < size; i++) {
            output.push(input.pop());
        }
        T result = output.pop();
        size--;
        for (int i = 0; i < size; i++) {
            input.push(output.pop());
        }
        return result;
    }

    @Override
    public void push(T value) {
        input.push(value);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return input.iterator();
    }
}
