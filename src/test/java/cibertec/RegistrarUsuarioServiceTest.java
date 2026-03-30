package cibertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarUsuarioServiceTest {

    private RegistrarUsuarioService service;

    @BeforeEach
    void setUp() {
        service = new RegistrarUsuarioService();
    }

    @Test
    void deberiaRegistrarUsuarioCuandoTodosLosDatosSonValidos() {
        String resultado = service.registrarUsuario("user123", "clave1234", "mail@edu.com", 20);
        assertEquals("El usuario ha sido registrado correctamente", resultado);
    }

    @Test
    void deberiaFallarCuandoHayCamposVacios() {
        String resultado = service.registrarUsuario("", "clave1234", "mail@edu.com", 20);
        assertEquals("Debe completar todos los campos requeridos", resultado);
    }

    @Test
    void deberiaFallarCuandoUsernameEsInvalido() {
        String resultado = service.registrarUsuario("us 1", "clave1234", "mail@edu.com", 20);
        assertEquals("El nombre de usuario no es válido", resultado);
    }

    @Test
    void deberiaFallarCuandoPasswordNoCumpleReglas() {
        String resultado = service.registrarUsuario("user123", "soloabcd", "mail@edu.com", 20);
        assertEquals("La contraseña debe tener al menos 8 caracteres y contener letras y números", resultado);
    }

    @Test
    void deberiaFallarCuandoEmailEsInvalido() {
        String resultado = service.registrarUsuario("user123", "clave1234", "mail.edu", 20);
        assertEquals("Ingrese un correo electrónico válido", resultado);
    }

    @Test
    void deberiaFallarCuandoEdadEsMenorDe18() {
        String resultado = service.registrarUsuario("user123", "clave1234", "mail@edu.com", 17);
        assertEquals("Debe ser mayor de edad para registrarse", resultado);
    }
}