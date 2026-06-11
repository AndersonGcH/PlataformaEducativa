package cibertec.notificaciones;

public interface NotificationEventPublisher {
    void publish(NotificationEvent event);
}
