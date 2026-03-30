package cibertec;

public class RegistrarUsuarioService {
    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private boolean usernameValido(String usuario) {
        return usuario.matches("^[a-zA-Z0-9]{6,12}$");
    }

    public String registrarUsuario(String usuario, String contrasenia, String correo, Integer edad) {

        if (estaVacio(usuario) || estaVacio(contrasenia) || estaVacio(correo) || edad == null) {
            return "Debe completar todos los campos requeridos";
        }

        if (!usernameValido(usuario)) {
            return "El nombre de usuario no es válido";
        }

        if (contrasenia.length() < 8 || !contrasenia.matches(".*[A-Za-z].*") || !contrasenia.matches(".*\\d.*")) {
            return "La contraseña debe tener al menos 8 caracteres y contener letras y números";
        }

        return "El usuario ha sido registrado correctamente";

    }
}
