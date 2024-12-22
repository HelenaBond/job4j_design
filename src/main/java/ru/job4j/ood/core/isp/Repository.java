package ru.job4j.ood.core.isp;

/**
 * Смешивание разных уровней абстракций.
 * Класс манимулирующий бизнес логикой будет реализовывать первый метод и зависеть от второго.
 * Решение - разделение интерфейса и использование их на разных уровнях.
 * public interface Repository {
 *     void save();
 * }
 * public interface Storage {
 *     void saveToStorage();
 * }
 * public class StorageImpl implements Storage {
 *     public void saveToStorage(){}
 * }
 * public class RepositoryImpl implements Repository {
 *     private Storage storage;
 *     public RepositoryImpl(Storage storage) {
 *         this.storage = storage;
 *     }
 *     public void save() {
 *         storage.saveToStorage();
 *     }
 * }
 */
public interface Repository {
    void save();
    void saveToStorage();
}
