package com.plataforma.notificacionesms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
    private String tipo;
    private String titulo;
    private String mensaje;
    private String destinatario;
    private Long timestamp;
    private Map<String, Object> metadata;
}
