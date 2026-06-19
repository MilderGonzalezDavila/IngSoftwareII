package org.example.UsuarioCrud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    //Nombre
    @Test
    @DisplayName("Crear usuario con todos sus valores validos")
    public void crearPersona_ValoresValidos_RetornaPersona(){
        String nombre = "Keller";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        Usuario usuario = new Usuario(nombre, correo, password);
        assertNotNull(usuario);
        assertEquals(usuario.getNombre(),  nombre);
        assertEquals(usuario.getCorreo(), correo);
        assertEquals(usuario.getPassword(), password);
    }

    @Test
    @DisplayName("Crear usuario con nombre nulo")
    public void crearUsuario_NombreNull_LanzaExcepcion(){
        String nombre = null;
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre no debe ser nulo");
        assertEquals("El nombre no debe ser nulo", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con nombre vacio")
    public void crearUsuario_NombreVacio_LanzaExcepcion(){
        String nombre = "";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe ser valido");
        assertEquals("El nombre debe ser valido", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con el nombre en espacio en blanco")
    public void crearUsuario_NombreEspacioBlanco_LanzaExcepcion(){
        String nombre = "      ";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe ser valido");
        assertEquals("El nombre debe ser valido", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con nombre de menos de dos caracteres")
    public void crearUsuario_NombreMenosDosCaracteres_LanzaExcepcion(){
        String nombre = "A";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe tener minimo dos caracteres");
        assertEquals("El nombre debe tener minimo dos caracteres", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un nombre de mas de 30 caracteres")
    public void crearUsuario_NombreMasTreintaCaracteres_LanzaExcepcion(){
        String nombre = "A".repeat(40);
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe tener maximo treinta caracteres");
        assertEquals("El nombre debe tener maximo treinta caracteres", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un nombre de que contiene numeros")
    public void crearUsuario_NombreContieneNumeros_LanzaExcepcion(){
        String nombre = "Alisa123";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe no debe de contener numeros");
        assertEquals("El nombre debe no debe de contener numeros", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un nombre que contiene caracteres especiales")
    public void crearUsuario_NombreCaracteresEspeciales_LanzaExcepcion(){
        String nombre = "Alisa@#";
        String correo = "keller67@gmail.com";
        String password = "Keller1234@BROWN";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Usuario(nombre, correo, password), "El nombre debe de contener caracteres especiales");
        assertEquals("El nombre debe de contener caracteres especiales", e.getMessage());
    }

    //Correo
    @Test
    @DisplayName("Crear usuario con un correo null")
    public void crearUsuario_CorreoNull_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = null;
        String password = "Alisa1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password),
                "El correo no debe ser null");
        assertEquals("El correo no debe ser null", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un correo vacio")
    public void crearUsuario_CorreoVacio_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "";
        String password = "Alisa1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password),
                "El correo no debe ser vacio");
        assertEquals("El correo no debe ser vacio", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un correo con espacio en blanco")
    public void crearUsuario_CorreoEspaciosBlanco_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "     ";
        String password = "Alisa1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password),
                "El correo no debe tener solo espacio en blanco");
        assertEquals("El correo no debe tener solo espacio en blanco", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un correo si dominio")
    public void crearUsuario_CorreoSinDominio_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER@gmail";
        String password = "Alisa1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El correo debe tener un dominio");
        assertEquals("El correo debe tener un dominio", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un correo Que tiene multiples arrobas")
    public void crearUsuario_CorreoArrobaInicio_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisa@KELLER435@gmail.com";
        String password = "Alisa1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El correo debe contener un unico arroba");
        assertEquals("El correo debe contener un unico arroba", e.getMessage());
    }

    //Contraseña
    @Test
    @DisplayName("Crear usuario con un password null")
    public void crearUsuario_PasswordNull_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = null;
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password no debe ser null");
        assertEquals("El password no debe ser null", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password vacio")
    public void crearUsuario_PasswordVacio_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password no debe estar vacio");
        assertEquals("El password no debe estar vacio", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password con espacio en blanco")
    public void crearUsuario_PasswordEspaciosBlanco_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "      ";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password no debe tener espacio en blanco");
        assertEquals("El password no debe tener espacio en blanco", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password con menos de seis caracteres")
    public void crearUsuario_PasswordConMenosSeisCaracteres_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "Ali1";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password debe tener como minimo 6 caracteres");
        assertEquals("El password debe tener como minimo 6 caracteres", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password que no tiene mayusculas")
    public void crearUsuario_PasswordSinMayusculas_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "alisa1234@keller";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password debe tener como minimo una letra mayuscula");
        assertEquals("El password debe tener como minimo una letra mayuscula", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password que no tiene minusculas")
    public void crearUsuario_PasswordSinMinusculas_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "ALISA1234@KELLER";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password debe tener como minimo una letra minuscula");
        assertEquals("El password debe tener como minimo una letra minuscula", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password que no tiene numeros")
    public void crearUsuario_PasswordSinNumeros_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "ALISAKeller@";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password debe tener como minimo un numero");
        assertEquals("El password debe tener como minimo un numero", e.getMessage());
    }

    @Test
    @DisplayName("Crear usuario con un password que no tiene numeros")
    public void crearUsuario_PasswordSinCaracteresEspeciales_LanzaExcepcion(){
        String nombre = "Alisa";
        String correo = "alisaKELLER435@gmail.com";
        String password = "ALISA1234Keller";
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario(nombre, correo, password), "El password debe tener como minimo un caracter especial");
        assertEquals("El password debe tener como minimo un caracter especial", e.getMessage());
    }


}
