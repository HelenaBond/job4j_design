package ru.job4j.algo.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        AvlTree<String> tree = new AvlTree<>();
        assertThat(tree.insert("first")).isTrue();
        assertThat(tree.insert("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        AvlTree<String> tree = new AvlTree<>();
        tree.insert("first");
        tree.insert("second");
        tree.insert("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.insert(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.insert(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.insert(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveRootThenTreeUpdated() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(5);
        assertThat(tree.remove(4)).isTrue();
        assertThat(tree.contains(4)).isFalse();
        assertThat(tree.contains(2)).isTrue();
        assertThat(tree.contains(6)).isTrue();
        assertThat(tree.contains(5)).isTrue();
    }

    @Test
    void whenRemoveLeafThenTreeUpdated() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(4);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        assertThat(tree.remove(1)).isTrue();
        assertThat(tree.contains(1)).isFalse();
        assertThat(tree.contains(4)).isTrue();
        assertThat(tree.contains(2)).isTrue();
        assertThat(tree.contains(7)).isTrue();
    }

    @Test
    void whenRemoveNonExistingElementThenNothingChanges() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        assertThat(tree.remove(8)).isFalse();
        assertThat(tree.contains(4)).isTrue();
        assertThat(tree.contains(2)).isTrue();
        assertThat(tree.contains(6)).isTrue();
    }

    @Test
    void whenRemoveNodeWithOneChildThenTreeUpdated() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(4);
        tree.insert(2);
        tree.insert(3);
        tree.insert(7);
        assertThat(tree.remove(2)).isTrue();
        assertThat(tree.contains(2)).isFalse();
        assertThat(tree.contains(4)).isTrue();
        assertThat(tree.contains(3)).isTrue();
        assertThat(tree.contains(7)).isTrue();
    }
}
