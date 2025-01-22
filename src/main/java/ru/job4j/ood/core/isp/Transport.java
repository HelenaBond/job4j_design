package ru.job4j.ood.core.isp;

/** Слишком обобщённая абстракция.
 * Проблема - каждый отдельный вид транспорта вынужден реализовывать методы
 * которыми не будет пользоваться никогда. Например машины только ездят, лодки только плывут.
 * Решение - разделение интерфейсов для разных типов транспортов.
 * public interface Drivable {
 *     void drive();
 * }
 * public interface Swimable {
 *     void sail();
 * }
 * public interface Flyable {
 *     void fly();
 * }
 */
public interface Transport {
    void drive();
    void swim();
    void fly();
}
