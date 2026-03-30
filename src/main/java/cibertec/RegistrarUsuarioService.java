package cibertec;

public class RegistrarUsuarioService {
    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public String registrarUsuario(String usuario, String contrasenia, String correo, Integer edad) {

        if (estaVacio(usuario) || estaVacio(contrasenia) || estaVacio(correo) || edad == null) {
            return "Debe completar todos los campos requeridos";
        }

        return "El usuario ha sido registrado correctamente";

    }
}
