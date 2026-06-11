package com.plataforma.notificacionesms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private String destinatario;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @Column(nullable = false)
    private Boolean leida;

    @Column(nullable = false)
    private LocalDateTime creadaEn;

    private LocalDateTime leidaEn;

    @PrePersist
    public void onCreate() {
        if (leida == null) {
            leida = false;
        }
        if (creadaEn == null) {
            creadaEn = LocalDateTime.now();
        }
    }
}
