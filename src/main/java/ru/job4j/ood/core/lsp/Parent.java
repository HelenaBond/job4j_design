package ru.job4j.ood.core.lsp;

public class Parent {

    public void drive() {
        System.out.println("I am driving.");
    }

    /**
     * В исходном или переопределённом методе не должно быть валидации входящих параметров метода
     */
    public void eat(String food) {
        if (!"cake".equals(food)) {
            System.out.println("I am eating.");
        }
    }
}
