package ru.job4j.ood.core.dip.one;

public class Switch {

    /**
     * Зависимость от конкретного класса.
     * Решение - класс Light имплемнтирует интерфейс.
     * Этот интерфейс используется как поле текущего класса.
     * Тогда состояние текущего уровня абстракции не будет зависеть от
     * состояния низкого уровня абстракции.
     */
    private Light light;

    public Switch(Light light) {
        this.light = light;
    }

    public void press() {
        light.turnOn();
    }
}
