package cibertec;

public class RegistrarUsuarioService {
    public String registrarUsuario(String usuario, String contrasenia, String correo, Integer edad) {

        if (usuario == null || usuario.trim().isEmpty()
                || contrasenia == null || contrasenia.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || edad == null) {
            return "Debe completar todos los campos requeridos";
        }
        return "El usuario ha sido registrado correctamente";

    }
}
