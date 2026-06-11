package com.plataforma.notificacionesms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plataforma.notificacionesms.dto.NotificationEvent;
import com.plataforma.notificacionesms.model.Notification;
import com.plataforma.notificacionesms.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Notification processEvent(NotificationEvent event) {
        Notification notification = new Notification();
        notification.setTipo(event.getTipo());
        notification.setTitulo(event.getTitulo());
        notification.setMensaje(event.getMensaje());
        notification.setDestinatario(event.getDestinatario());
        notification.setPayload(toJson(event.getMetadata()));
        notification.setLeida(false);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> findByDestinatario(String destinatario) {
        return notificationRepository.findByDestinatarioOrderByCreadaEnDesc(destinatario);
    }

    @Override
    public List<Notification> findNoLeidas(String destinatario) {
        return notificationRepository.findByDestinatarioAndLeidaFalseOrderByCreadaEnDesc(destinatario);
    }

    @Override
    public Optional<Notification> marcarComoLeida(Long id) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setLeida(true);
            notification.setLeidaEn(LocalDateTime.now());
            return notificationRepository.save(notification);
        });
    }

    private String toJson(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.warn("No se pudo serializar metadata de notificación. Tipo de metadata: {}", value.getClass().getName(), e);
            return "{}";
        }
    }
}
