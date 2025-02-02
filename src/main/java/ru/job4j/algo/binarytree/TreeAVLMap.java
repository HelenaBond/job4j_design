package ru.job4j.algo.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private AvlTree<MapNode> tree = new AvlTree<>();

    public boolean insert(T key, V value) {
        MapNode node = new MapNode(key, value);
        if (tree.contains(node)) {
            tree.remove(node);
            return tree.insert(node);
        }
        return tree.insert(node);
    }

    public boolean remove(T key) {
        return tree.remove(new MapNode(key));
    }

    public V get(T key) {
        Optional<MapNode> node = tree.get(new MapNode(key));
        return node.map(value -> value.value).orElse(null);
    }

    public Set<T> keySet() {
        return tree.inSymmetricalOrder().stream().map(mapNode -> mapNode.key).collect(Collectors.toSet());
    }

    public Collection<V> values() {
        return tree.inSymmetricalOrder().stream().map(mapNode -> mapNode.value).collect(Collectors.toList());
    }

    private class MapNode implements Comparable<MapNode> {
        private T key;
        private V value;

        public MapNode(T key) {
            this.key = key;
        }

        MapNode(T key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(MapNode o) {
            return this.key.compareTo(o.key);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            MapNode mapNode = (MapNode) object;
            return Objects.equals(key, mapNode.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
