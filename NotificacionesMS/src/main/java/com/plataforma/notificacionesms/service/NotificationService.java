package com.plataforma.notificacionesms.service;

import com.plataforma.notificacionesms.dto.NotificationEvent;
import com.plataforma.notificacionesms.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification processEvent(NotificationEvent event);

    List<Notification> findAll();

    Optional<Notification> findById(Long id);

    List<Notification> findByDestinatario(String destinatario);

    List<Notification> findNoLeidas(String destinatario);

    Optional<Notification> marcarComoLeida(Long id);
}
