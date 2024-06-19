package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .containsSequence("three", "four")
                .doesNotContain("six")
                .containsExactly("first", "second", "three", "four", "five");
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> actual = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(actual)
                .hasSize(5)
                .allSatisfy(p -> {
                    assertThat(p.length()).isGreaterThan(3);
                    assertThat(p.length()).isLessThan(10);
                })
                .filteredOn(p -> p.length() == 5)
                .hasSize(2)
                .first().isEqualTo("first");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> actual = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(actual)
                .contains("three")
                .anySatisfy(p -> {
                    assertThat(p).isEqualTo("four");
                    assertThat(p.length()).isLessThan(5);
                })
                .filteredOnAssertions(p -> assertThat(p.length()).isLessThan(7)).hasSize(5);
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> actual = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(actual)
                .hasSize(5)
                .containsKey("first")
                .containsValue(4)
                .doesNotContainEntry("six", 5);
    }
}
