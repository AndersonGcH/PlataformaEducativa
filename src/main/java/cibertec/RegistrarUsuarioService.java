package cibertec;

import cibertec.notificaciones.NoOpNotificationEventPublisher;
import cibertec.notificaciones.NotificationEvent;
import cibertec.notificaciones.NotificationEventPublisher;

import java.time.Instant;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrarUsuarioService {

    private static final Logger LOGGER = Logger.getLogger(RegistrarUsuarioService.class.getName());

    private final NotificationEventPublisher notificationEventPublisher;

    public RegistrarUsuarioService() {
        this(new NoOpNotificationEventPublisher());
    }

    public RegistrarUsuarioService(NotificationEventPublisher notificationEventPublisher) {
        this.notificationEventPublisher = notificationEventPublisher;
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean usernameValido(String usuario) {
        return usuario.matches("^[a-zA-Z0-9]{6,12}$");
    }

    private boolean passwordValida(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Za-z].*") &&
                password.matches(".*\\d.*");
    }

    private boolean emailValido(String correo) {
        return correo.length() >= 8 && correo.contains("@");
    }

    private boolean edadValida(Integer edad) {
        return edad >= 18;
    }

    public String registrarUsuario(String usuario, String contrasenia, String correo, Integer edad) {

        if (estaVacio(usuario) || estaVacio(contrasenia) || estaVacio(correo) || edad == null) {
            return "Debe completar todos los campos requeridos";
        }

        if (!usernameValido(usuario)) {
            return "El nombre de usuario no es válido";
        }

        if (!passwordValida(contrasenia)) {
            return "La contraseña debe tener al menos 8 caracteres y contener letras y números";
        }

        if (!emailValido(correo)) {
            return "Ingrese un correo electrónico válido";
        }

        if (!edadValida(edad)) {
            return "Debe ser mayor de edad para registrarse";
        }

        publicarEventoRegistro(usuario, correo, edad);
        return "El usuario ha sido registrado correctamente";
    }

    private void publicarEventoRegistro(String usuario, String correo, Integer edad) {
        NotificationEvent event = new NotificationEvent(
                "USUARIO_REGISTRADO",
                "Nuevo registro en PlataformaEducativa",
                "Se registró el usuario " + usuario,
                correo,
                Instant.now().toEpochMilli(),
                Map.of("usuario", usuario, "edad", edad)
        );

        try {
            notificationEventPublisher.publish(event);
        } catch (Exception exception) {
            LOGGER.log(Level.WARNING, "No se pudo publicar la notificación de registro para usuario " + usuario + " y correo " + correo, exception);
        }
    }
}
