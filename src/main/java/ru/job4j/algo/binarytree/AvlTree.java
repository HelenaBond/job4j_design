package ru.job4j.algo.binarytree;

import ru.job4j.algo.tree.visual.PrintTree;
import ru.job4j.algo.tree.visual.VisualNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AvlTree<T extends Comparable<T>> {

    public Node root;

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node node, T value) {
        return get(node, value) != null;
    }

    public Optional<T> get(T value) {
        T result = get(root, value);
        return result == null ? Optional.empty() : Optional.of(result);
    }

    private T get(Node node, T value) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(value)) {
            return node.key;
        }
        return value.compareTo(node.key) < 0
                ? get(node.left, value)
                : get(node.right, value);
    }

    public boolean insert(T value) {
        boolean result = false;
        if (Objects.nonNull(value) && !contains(root, value)) {
            root = insert(root, value);
            result = true;
        }
        return result;
    }

    private Node insert(Node node, T value) {
        Node result = new Node(value);
        if (Objects.nonNull(node)) {
            int comparisonResult = value.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = insert(node.left, value);
            } else {
                node.right = insert(node.right, value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean remove(T element) {
        boolean result = false;
        if (Objects.nonNull(element) && Objects.nonNull(root) && contains(root, element)) {
            root = remove(root, element);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left);
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right);
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public T minimum() {
        return minimum(root);
    }

    private T minimum(Node node) {
        Node step = node;
        while (step.left != null) {
            step = step.left;
        }
        return step.key;
    }

    public T maximum() {
        return maximum(root);
    }

    private T maximum(Node node) {
        Node step = node;
        while (step.right != null) {
            step = step.right;
        }
        return step.key;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
        return result;
    }

    private void inSymmetricalOrder(Node node, List<T> result) {
        if (node != null) {
        inSymmetricalOrder(node.left, result);
        result.add(node.key);
        inSymmetricalOrder(node.right, result);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        inPreOrder(root, result);
        return result;
    }

    private void inPreOrder(Node node, List<T> result) {
        if (node != null) {
            result.add(node.key);
            inPreOrder(node.left, result);
            inPreOrder(node.right, result);
        }
    }


    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node node, List<T> result) {
        if (node != null) {
            inPostOrder(node.left, result);
            inPostOrder(node.right, result);
            result.add(node.key);
        }
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private int height;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return String.valueOf(key);
        }
    }
}
