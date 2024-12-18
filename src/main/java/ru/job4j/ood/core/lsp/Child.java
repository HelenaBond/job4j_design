package ru.job4j.ood.core.lsp;

public class Child extends Parent {

    private int age;

    /**
     * В переопределённом или в исходном методе не должно быть валидации полей класса.
     */
    @Override
    public void drive() {
        if (age > 18) {
            super.drive();
        }
    }

    @Override
    public void eat(String food) {
        super.eat(food);
    }
}
