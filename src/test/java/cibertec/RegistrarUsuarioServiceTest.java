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
}