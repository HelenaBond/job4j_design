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
        if (Objects.isNull(key) || Objects.isNull(root)) {
            return false;
        }
        return remove(root, key, null);
    }

    private boolean remove(Node current, T key, Node parent) {
        if (current == null) {
            return false;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            return remove(current.left, key, current);
        } else if (cmp > 0) {
            return remove(current.right, key, current);
        }
        /**
         * Найден узел для удаления
         */
        if (current.left == null && current.right == null) {
            swap(parent, current, null);
        } else if (current.left == null) {
            swap(parent, current, current.right);
        } else if (current.right == null) {
            swap(parent, current, current.left);
        } else {
            Node heir = getHeir(current);
            swap(parent, current, heir);
            /**
             * Подключаем левое поддерево
             */
            heir.left = current.left;
        }

        /**
         * Очищаем ссылки на потомков
         */
        current.key = null;
        current.left = null;
        current.right = null;

        return true;
    }

    private void swap(Node parent, Node current, Node replacement) {
        if (parent == null) {
            root = replacement;
        } else if (parent.left == current) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
    }

    private Node getHeir(Node delNode) {
        /**
         * сылки на удаляемый узел и его правое поддерево
         */
        Node nodeParent = delNode;
        Node node = delNode;
        /**
         * самый левосторонний потомок в правом поддереве
         */
        Node current = delNode.right;
        while (current != null) {
            nodeParent = node;
            node = current;
            current = current.left;
        }
        /**
         * если найденный узел не является непосредственным правым потомком удаляемого узла,
         * то открепляем его от его родителя и соединяем с правым поддеревом
         */
        if (node != delNode.right) {
            /**
             * Обновляем ссылку родителя на правого потомка
             */
            nodeParent.left = node.right;
            /**
             * Переносим правое поддерево удаляемого узла на место найденного узла
             */
            node.right = delNode.right;
        }
        /**
         * Возвращаем самого левого потомка правого поддерева
         */
        return node;
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
