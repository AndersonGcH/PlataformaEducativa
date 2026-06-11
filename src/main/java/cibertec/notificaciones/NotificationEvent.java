package cibertec.notificaciones;

import java.util.Map;

public record NotificationEvent(
        String tipo,
        String titulo,
        String mensaje,
        String destinatario,
        Long timestamp,
        Map<String, Object> metadata
) {
}
