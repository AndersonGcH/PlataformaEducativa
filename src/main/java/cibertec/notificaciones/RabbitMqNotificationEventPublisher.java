package cibertec.notificaciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class RabbitMqNotificationEventPublisher implements NotificationEventPublisher {

    public static final String EXCHANGE = "plataforma.educativa.notifications";
    public static final String ROUTING_KEY = "notification.user.registered";

    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;

    public RabbitMqNotificationEventPublisher(String host, int port, String username, String password) {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost(host);
        this.connectionFactory.setPort(port);
        this.connectionFactory.setUsername(username);
        this.connectionFactory.setPassword(password);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void publish(NotificationEvent event) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE, "topic", true);
            byte[] body = objectMapper.writeValueAsString(event).getBytes(StandardCharsets.UTF_8);
            channel.basicPublish(EXCHANGE, ROUTING_KEY, null, body);
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo publicar el evento de notificación", e);
        }
    }
}
