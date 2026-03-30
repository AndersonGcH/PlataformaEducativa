package cibertec;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarUsuarioServiceTest {
    @Test
    void deberiaRegistrarUsuarioCuandoTodosLosDatosSonValidos() {
        RegistrarUsuarioService service = new RegistrarUsuarioService();
        String resultado = service.registrarUsuario("user123", "clave1234", "mail@edu.com", 20);
        assertEquals("El usuario ha sido registrado correctamente", resultado);
    }

    @Test
    void deberiaFallarCuandoHayCamposVacios() {
        RegistrarUsuarioService service = new RegistrarUsuarioService();
        String resultado = service.registrarUsuario("", "clave1234", "mail@edu.com", 20);
        assertEquals("Debe completar todos los campos requeridos", resultado);
    }

    @Test
    void deberiaFallarCuandoUsernameEsInvalido() {
        RegistrarUsuarioService service = new RegistrarUsuarioService();
        String resultado = service.registrarUsuario("us 1", "clave1234", "mail@edu.com", 20);
        assertEquals("El nombre de usuario no es válido", resultado);
    }

    @Test
    void deberiaFallarCuandoPasswordNoCumpleReglas() {
        RegistrarUsuarioService service = new RegistrarUsuarioService();
        String resultado = service.registrarUsuario("user123", "soloabcd", "mail@edu.com", 20);
        assertEquals("La contraseña debe tener al menos 8 caracteres y contener letras y números", resultado);
    }
}