package ru.job4j.algo.tree;

import ru.job4j.collection.*;
import ru.job4j.collection.Queue;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        AtomicInteger amountNode = new AtomicInteger(0);
        bfsTree(root, node -> amountNode.getAndIncrement());
        return amountNode.get();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        SimpleList<T> ascValues = new SimpleArrayList<>(1);
        bfsTree(root, node -> ascValues.add(node.getValue()));
        return ascValues;
    }

    private void bfsTree(Node<T> root, Consumer<Node<T>> action) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty.");
        }
        Queue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            action.accept(node);
            for (Node<T> child : node.getChildren()) {
                queue.push(child);
            }
        }
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null.
     */
    public boolean add(Node<T> root, T parent, T child) {
        Node<T> parentNode = findByKey(root, parent).orElse(null);
        if (parentNode == null) {
            return false;
        }
        Node<T> childNode = findByKey(parentNode, child).orElse(null);
        if (childNode == null) {
            List<Node<T>> children = parentNode.getChildren();
            children.add(new Node<>(child));
            return true;
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        return dfsTree(
                root,
                node -> node.getValue().equals(key));
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root != null && root.getValue().equals(key)) {
            return Optional.of(root);
        }
        Optional<Node<T>> result = Optional.empty();
        Optional<Node<T>> parent = dfsTree(
                root,
                node -> node.getChildren().contains(new Node<>(key)));
        if (parent.isPresent()) {
            result = findByKey(parent.get(), key);
            List<Node<T>> children = parent.get().getChildren();
            children.remove(result.get());
        }
        return result;
    }

    private Optional<Node<T>> dfsTree(
            Node<T> root,
            Predicate<Node<T>> predicate) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty.");
        }
        Optional<Node<T>> result = Optional.empty();
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (predicate.test(node)) {
                result = Optional.of(node);
                break;
            } else {
                for (Node<T> child : node.getChildren()) {
                    stack.push(child);
                }
            }
        }
        return result;
    }
}