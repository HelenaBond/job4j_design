package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }


    @Test
    void when2ElFindFirstThen2() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        assertThat(tree.findBy(2)).isPresent();
    }

    @Test
    void when1ElFindThen1() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(1)).isPresent();
    }

    @Test
    void when3ThenNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void when0ThenBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary()).isTrue();
    }
}
