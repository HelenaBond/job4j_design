package ru.job4j.ood.core.isp;

/**
 * Когда интерфейс групирует методы может оказаться
 * что разным классам реализациям понадобятся разные наборы методов.
 * Решение разбить все методы на интерфейсы и делать множественную групповую имплементацию.
 * public interface Peel {
 *     peel();
 * }
 * public interface Cut {
 *  cut();
 * }
 * public interface Boil {
 *  boil();
 * }
 * public interface Fry {
 *  fry();
 * }
 * И тогда для приготовления мяса Cut и Fry,
 * для салата Peel и Cut,
 * для криветок Peel и Fry
 */
public interface Cook {
    void peel();
    void cut();
    void boil();
    void fry();
}


