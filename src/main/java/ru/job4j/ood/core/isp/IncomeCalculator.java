package ru.job4j.ood.core.isp;

/** Когда в интерфейсе есть методы как для частных так и для общих случаев.
 * Проблема - Класс который будет реализовывать зарплаты временным сотрудникам
 * будет вынужден реализовывать меод расчёта годового бонуса,
 * хотя временным сотрудникам он не положен.
 * Решение - разделение интерфейсов для разных видов расчётов.
 * Класс для постоянных сотрудников реализует оба интерфейса.
 * Для временных реализует один интерфейс.
 * public interface SalaryCalculator {
 *     void calculateSalary();
 * }
 * public interface FinalBonusCalculator {
 *         void calculateFinalBonus();
 * }
 */
public interface IncomeCalculator {
    void calculateSalary();
    void calculateFinalBonus();
}
