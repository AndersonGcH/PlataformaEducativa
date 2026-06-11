package com.plataforma.notificacionesms.repository;

import com.plataforma.notificacionesms.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDestinatarioOrderByCreadaEnDesc(String destinatario);

    List<Notification> findByDestinatarioAndLeidaFalseOrderByCreadaEnDesc(String destinatario);
}
