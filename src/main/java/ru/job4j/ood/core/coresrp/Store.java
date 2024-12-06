package ru.job4j.ood.core.coresrp;

import java.util.ArrayList;
import java.util.List;

public class Store {

    /**
     * Проблема - создание объекта другого класса внутри текущего.
     * Решене - свойство объекта инициализировать через конструктор и входящие параметры.
      */
    List<Toy> data = new ArrayList<>();
    int size = 1;

    /**
     * Проблема - метод не только сохраняет но и создаёт сохраняемый объект.
     * Решение - объект создавать извне и передавать его в качестве параметра в метод для сохранения.
      */
    public Toy add(String name) {
        Toy toy = new Toy(name);
        toy.setId(size++);
        data.add(toy);
        return toy;
    }
}
