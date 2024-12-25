package ru.job4j.ood.core.dip.three;

public class DatabaseService {
    /**
     * @return Метод возвращает конкретную реализацию.
     * Решение - класс MySQLConnection должен имплементировать интерфейс
     * и тип возвращаемого значения метода должен быть этим интерфейсом.
     * Тогда класс клиент не будет зависеть от текущей реализации.
     */
    public MySQLConnection getConnection() {
        return new MySQLConnection();
    }
}
