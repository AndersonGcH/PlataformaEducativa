package cibertec;

public class RegistrarUsuarioService {
    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public String registrarUsuario(String usuario, String contrasenia, String correo, Integer edad) {

        if (estaVacio(usuario) || estaVacio(contrasenia) || estaVacio(correo) || edad == null) {
            return "Debe completar todos los campos requeridos";
        }

        if (!usuario.matches("^[a-zA-Z0-9]{6,12}$")) {
            return "El nombre de usuario no es válido";
        }

        return "El usuario ha sido registrado correctamente";

    }
}
