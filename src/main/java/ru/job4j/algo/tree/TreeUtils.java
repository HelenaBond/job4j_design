package ru.job4j.algo.tree;

import ru.job4j.collection.Queue;
import ru.job4j.collection.SimpleArrayList;
import ru.job4j.collection.SimpleList;
import ru.job4j.collection.SimpleQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        AtomicInteger amountNode = new AtomicInteger(0);
        traverseTree(root, node -> amountNode.getAndIncrement());
        return amountNode.get();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        SimpleList<T> binaryHeap = new SimpleArrayList<>(1);
        traverseTree(root, node -> binaryHeap.add(node.getValue()));
        return binaryHeap;
    }

    private void traverseTree(Node<T> root, Consumer<Node<T>> action) {
        Queue<Node<T>> queue = new SimpleQueue<>();
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty.");
        }
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            action.accept(node);
            for (Node<T> child : node.getChildren()) {
                queue.push(child);
            }
        }
    }
}