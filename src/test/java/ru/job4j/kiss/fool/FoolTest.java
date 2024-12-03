package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FoolTest {

    @Test
    public void whenFizzBuzz() {
        assertThat(Fool.step(15)).isEqualTo("FizzBuzz");
    }

    @Test
    public void whenFizz() {
        assertThat(Fool.step(3)).isEqualTo("Fizz");
    }

    @Test
    public void whenBuzz() {
        assertThat(Fool.step(5)).isEqualTo("Buzz");
    }

    @Test
    public void when11() {
        assertThat(Fool.step(11)).isEqualTo("11");
    }
}