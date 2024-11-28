package ru.job4j.tree;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * не потокобезопасен
 * @param <T>
 */
public class PriorityStack<T> {
    private final TreeSet<Node<T>> data;
    private Node<T> tail;
    private int nodeId = 1;

    public PriorityStack(Comparator<T> comparator) {

        this.data = new TreeSet<>((o1, o2) -> comparator.compare(o1.value, o2.value) == 0
                ? Integer.compare(o1.id, o2.id) : comparator.compare(o1.value, o2.value));
    }

    private class Node<T> {
        private int id;
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            this.id = nodeId++;
        }
    }

    public void push(T item) {
        Node<T> current = new Node<>(item);
        if (tail != null) {
            tail.next = current;
            current.prev = tail;
        }
        tail = current;
        data.add(current);
    }

    public T pop() {
        if (tail == null) {
            return null;
        }
        Node<T> deleted = tail;
        tail = tail.prev;
        data.remove(deleted);
        return deleted.value;
    }

    public T peekMax() {
        if (data.isEmpty()) {
            return null;
        }
        Node<T> res = data.last();
        return res.value;
    }
}
