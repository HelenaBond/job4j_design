package ru.job4j.algo.binarytree;

import ru.job4j.algo.tree.visual.PrintTree;
import ru.job4j.algo.tree.visual.VisualNode;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        boolean result = false;
        if (node.key.compareTo(key) > 0) {
            if (node.left != null) {
                result = put(node.left, key);
            } else {
                node.left = new Node(key);
                result = true;
            }
        } else if (node.key.compareTo(key) < 0) {
            if (node.right != null) {
                result = put(node.right, key);
            } else {
                node.right = new Node(key);
                result = true;
            }
        }
        return result;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        Node result = null;
        if (node.key.compareTo(key) > 0) {
            if (node.left != null) {
                result = find(node.left, key);
            }
        } else if (node.key.compareTo(key) < 0) {
            if (node.right != null) {
                result = find(node.right, key);
            }
        } else {
            result = node;
        }
        return result;
    }

    public boolean remove(T key) {
        /**
         *  Метод будет реализован в следующих уроках
         */
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        inSymmetricalOrder(root, result);
        return result;
    }

    private void inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        inPreOrder(root, result);
        return result;
    }

    private void inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            inPreOrder(localRoot.left, list);
            inPreOrder(localRoot.right, list);
        }
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        inPostOrder(root, result);
        return result;
    }

    private void inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inPostOrder(localRoot.left, list);
            inPostOrder(localRoot.right, list);
            list.add(localRoot.key);
        }
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
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
            return key.toString();
        }
    }
}
