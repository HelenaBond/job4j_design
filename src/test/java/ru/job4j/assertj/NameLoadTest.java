package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void parseEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("empty");
    }

    @Test
    void validateContainEqual() {
        NameLoad nameLoad = new NameLoad();
        String data = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(data)
                .hasMessageContaining("=");
    }

    @Test
    void validateContainKey() {
        NameLoad nameLoad = new NameLoad();
        String data = "=value";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(data)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void validateContainValue() {
        NameLoad nameLoad = new NameLoad();
        String data = "key=";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(data)
                .hasMessageContaining("not contain a value");
    }
}
