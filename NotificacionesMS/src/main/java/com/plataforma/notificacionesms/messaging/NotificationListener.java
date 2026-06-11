package com.plataforma.notificacionesms.messaging;

import com.plataforma.notificacionesms.config.RabbitConfig;
import com.plataforma.notificacionesms.dto.NotificationEvent;
import com.plataforma.notificacionesms.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = RabbitConfig.QUEUE_NOTIFICATIONS)
    public void receive(NotificationEvent event) {
        notificationService.processEvent(event);
        log.info("Notificación procesada para {}", event.getDestinatario());
    }
}
