package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEmpty()
                .isEqualTo("Sphere")
                .startsWithIgnoringCase("s");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotBlank()
                .startsWithIgnoringCase("u");
    }

    @Test
    void getNumberOfVerticesExists() {
        Box box = new Box(8, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isPositive()
                .isGreaterThan(4);
    }

    @Test
    void getNumberOfVerticesUnknown() {
        Box box = new Box(2, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isNegative()
                .isEqualTo(-1);
    }

    @Test
    void isExistsTrue() {
        Box box = new Box(4, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isExistsFalse() {
        Box box = new Box(3, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void getAreaWithPrecision() {
        double edge = 10;
        Box box = new Box(8, (int) edge);
        double actual = box.getArea();
        double expected = 600.1d;
        assertThat(actual)
                .isLessThan(expected)
                .isCloseTo(expected, withPrecision(0.1d));
    }

    @Test
    void getAreaWithPercentage() {
        double edge = 10;
        Box box = new Box(8, (int) edge);
        double actual = box.getArea();
        assertThat(actual)
                .isGreaterThan(599)
                .isCloseTo(600.1d, Percentage.withPercentage(1.0d));
    }
}
