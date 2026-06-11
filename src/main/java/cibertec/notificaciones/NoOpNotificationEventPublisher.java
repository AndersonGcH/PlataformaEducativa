package cibertec.notificaciones;

public class NoOpNotificationEventPublisher implements NotificationEventPublisher {

    @Override
    public void publish(NotificationEvent event) {
        // No-op publisher to keep registration flow decoupled when RabbitMQ is not configured.
    }
}
