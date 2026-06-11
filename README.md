# PlataformaEducativa + NotificacionesMS

Este repositorio ahora tiene dos partes:

- `PlataformaEducativa` (app principal): valida y registra usuarios, y publica eventos de notificación en RabbitMQ.
- `NotificacionesMS` (microservicio separado): consume eventos de RabbitMQ, guarda historial de notificaciones y expone API REST.

## Eventos RabbitMQ

- Exchange: `plataforma.educativa.notifications`
- Routing key: `notification.user.registered`
- Queue (microservicio): `plataforma.educativa.notifications.queue`

## Levantar RabbitMQ

```bash
docker compose up -d
```

RabbitMQ UI: `http://localhost:15672` (guest/guest)

## Ejecutar microservicio de notificaciones

```bash
cd /home/runner/work/PlataformaEducativa/PlataformaEducativa/AndersonGcH/PlataformaEducativa/NotificacionesMS
mvn spring-boot:run
```

## API de NotificacionesMS

- `GET /api/notificaciones`
- `GET /api/notificaciones/{id}`
- `GET /api/notificaciones/destinatario/{destinatario}`
- `GET /api/notificaciones/destinatario/{destinatario}/no-leidas`
- `PATCH /api/notificaciones/{id}/leida`

## Integración mínima en app principal

`RegistrarUsuarioService` ahora publica un evento `USUARIO_REGISTRADO` al finalizar un registro válido.
Si RabbitMQ falla, el registro del usuario no se interrumpe.
