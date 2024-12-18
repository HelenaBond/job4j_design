package ru.job4j.ood.core.srp;

public interface Filter<T> {
    void filter(String name);
    void filter(int age);

    /**
     * Проблема - фильтрация не должна отвечать за удаление.
     * Решение - CRUD вынести в отдельный интерфейс.
      */
    void delete(T obj);
}
