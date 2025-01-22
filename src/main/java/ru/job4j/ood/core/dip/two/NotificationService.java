package ru.job4j.ood.core.dip.two;

public class NotificationService {
    /**
     *
     * @param email Параметр зависит от конкретного класса.
     * Решение - класс Email должен имплементировать интерфейс - например Sender.
     * Тогда текущий класс не будет зависеть от реализации класса поставщика.
     */
    public void notifyUser(Email email, String message) {
        email.sendEmail(message);
    }
}
