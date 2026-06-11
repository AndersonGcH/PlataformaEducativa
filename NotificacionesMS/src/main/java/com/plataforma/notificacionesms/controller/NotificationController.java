package com.plataforma.notificacionesms.controller;

import com.plataforma.notificacionesms.model.Notification;
import com.plataforma.notificacionesms.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<Notification> all() {
        return notificationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> byId(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/destinatario/{destinatario}")
    public List<Notification> byDestinatario(@PathVariable String destinatario) {
        return notificationService.findByDestinatario(destinatario);
    }

    @GetMapping("/destinatario/{destinatario}/no-leidas")
    public List<Notification> unread(@PathVariable String destinatario) {
        return notificationService.findNoLeidas(destinatario);
    }

    @PatchMapping("/{id}/leida")
    public ResponseEntity<Notification> markRead(@PathVariable Long id) {
        return notificationService.marcarComoLeida(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
