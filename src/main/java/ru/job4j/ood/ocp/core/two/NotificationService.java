package ru.job4j.ood.ocp.core.two;

public class NotificationService {

    /**
     * Проблема - жесткая зависимость сервиса уведомленний от уведомлений через email
     * Решение - Создать интерфейс реализацией которого будет EmailNotification.
     * В качестве свойства использовать этот интерфейс а не реализацию.
     */
    private EmailNotification emailNotification;

    public NotificationService(EmailNotification emailNotification) {
        this.emailNotification = emailNotification;
    }

    public void sendNotification(String message) {
        emailNotification.sendEmail(message);
    }
}
