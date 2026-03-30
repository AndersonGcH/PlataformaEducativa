package cibertec;

public class RegistrarUsuarioService {
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

        if (edad < 18) {
            return "Debe ser mayor de edad para registrarse";
        }

        return "El usuario ha sido registrado correctamente";

    }
}
