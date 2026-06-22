package org.example.UsuarioCrud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuarioTest {

    private GestionUsuario gestionUsuario;
    private Usuario usuario;

    @BeforeEach
    public void setUp()
    {
       gestionUsuario = new GestionUsuario();
       usuario = new Usuario("Keller", "KellerBROWN67@gmail.com", "Keller1234@BROWN");
    }

    //Insert
    //1. Usuario null
    //2. Usuario  valido y no existente, entonces se inserta correctamente
    //3. Usuario valido y existente (con correo ya existe) lanza excepcion

    @Test
    @DisplayName("Insertamos un usuario null")
    public void Insertar_UsuarioNull_RetornaExcepcion()
    {
        //E1: gestionUsuario.Insertar(null);
        Exception e = assertThrows(IllegalArgumentException.class, () -> gestionUsuario.Insertar(null));
        assertEquals(0, gestionUsuario.Listar().size());
        assertEquals("Usuario null", e.getMessage());
    }

    @Test
    @DisplayName("Insertar un usuario valido")
    public void Insertar_UsuarioValido_Registra()
    {
        gestionUsuario.Insertar(usuario);
         int count = gestionUsuario.Listar().size();
         assertEquals(1, count);
    }

    @Test
    @DisplayName("Insertar usuario valido ya existente")
    public void Insertar_UsuarioValidoExistente_RetornaExcepcion(){
        gestionUsuario.Insertar(usuario);
        Exception e =  assertThrows(IllegalStateException.class, () -> gestionUsuario.Insertar(usuario));
        assertEquals(1, gestionUsuario.Listar().size());
        assertEquals("Usuario existente", e.getMessage());
    }


    //Read
    //1. Usuario null
    //3. Usuario que no existe
    //2. Usuario válido

    @Test
    @DisplayName("Leemos un usuario existente con correo null")
    public void Read_UsuarioCorreoNull_RetornaExcepcion()
    {
        Exception e = assertThrows(IllegalArgumentException.class, () -> gestionUsuario.Read(null));
        assertEquals("Usuario con correo null", e.getMessage());
    }

    @Test
    @DisplayName("Leer un usuario no registrado, con correo no nulo")
    public void Read_UsuarioNoRegistrado_RetornaException(){
        Exception e = assertThrows(IllegalStateException.class, () -> gestionUsuario.Read("USUARIOInexistente123@gmail.com"));
        assertEquals("Usuario no encontrado", e.getMessage());
    }

    @Test
    @DisplayName("Leer un usuario registrado")
    public void Leer_UsuarioRegistrado_RetornaExcepcion()
    {
        gestionUsuario.Insertar(usuario);
        Usuario u = gestionUsuario.Read(usuario.getCorreo());
        assertEquals(usuario, u );
    }

    //List
    //1. Si no hay usuario, retorna una lista vacía,
    //2. Retornar lista con usuarios
    //3. Nunca debe retornar null
    @Test
    @DisplayName("Listar sin usuarios ")
    public void Listar_SinUsuarios_RetornaListaVacia()
    {
        List<Usuario> usuarios = gestionUsuario.Listar();
        assertTrue( usuarios.isEmpty());
    }

    @Test
    @DisplayName("Listar usuario ya registrados")
    public void Listar_UsuarioRegistrados_RetornarListaUsuario(){
        gestionUsuario.Insertar(usuario);
        List<Usuario> usuarios = gestionUsuario.Listar();
        assertEquals(1 , usuarios.size());
    }

    @Test
    @DisplayName("Nunca debe de retornar null")
    public void Lista_NuncaRetornaNull()
    {
        List<Usuario> usuarios = gestionUsuario.Listar();
        assertNotNull(usuarios);
    }
    //Update
    //1. Usuario null
    @Test
    @DisplayName("Actualizar un usuario null lanza una IllegalArgumentException")
    public void Actualizar_UsuarioNull_RetornaException(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> gestionUsuario.Actualizar(null));
        assertEquals("Usuario null", e.getMessage());
    }
    //2. Usuario válido
    @Test
    @DisplayName("Actualizar usuario valido y registrado")
    public void Actualizar_UsuarioValido_Registrado()
    {
        gestionUsuario.Insertar(usuario);

        Usuario usarioBefore = new Usuario("James", usuario.getCorreo(), "JamesBROWN1234@!");
        gestionUsuario.Actualizar(usarioBefore);

        Usuario usuarioAfter = gestionUsuario.Read(usuario.getCorreo());
        assertNotEquals(usuario, usuarioAfter);
        assertEquals(usarioBefore.getNombre(), usuarioAfter.getNombre());
        assertEquals(usarioBefore.getCorreo(), usuarioAfter.getCorreo());
        assertEquals(usarioBefore.getPassword(), usuarioAfter.getPassword());
    }

    //3. Usuario no registrado
    @Test
    @DisplayName("Actualizar usuario no encontrado")
    public void Actualizar_UsuarioNoRegistrado_RetornaExcepcion(){
        gestionUsuario.Insertar(usuario);
        Usuario user = new Usuario("James", "BROWNJames12@gmail.com", "JamesBROWN1234@!");
        Exception e =  assertThrows(IllegalStateException.class, () -> gestionUsuario.Actualizar(user));
        assertEquals("Usuario no encontrado", e.getMessage());
    }

    //Delete
    //1. Usuario null
    //2. Usuario válido
    //3. Usuario no registrado
    @Test
    @DisplayName("Eliminar usuario de valor null")
    public void Eliminar_UsuarioNull_RetornaNull(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> gestionUsuario.Eliminar(null));
        assertEquals("Usuario null", e.getMessage());
    }

    @Test
    @DisplayName("Eliminar usuario existente y con valores validos")
    public void Eliminar_UsuarioExistente_ConValoresValidos(){
        gestionUsuario.Insertar(usuario);
        gestionUsuario.Eliminar(usuario);
        List<Usuario> us = gestionUsuario.Listar();
        assertEquals(new ArrayList<>(), us);
        assertEquals(0, us.size());
    }

    @Test
    @DisplayName("Eliminar usuario no registrado")
    public void Eliminar_UsuarioNoRegistrado_RetornaExcepcion()
    {
        gestionUsuario.Insertar(usuario);
        Usuario u = new Usuario("Lucia", "LuciaRODRIGUEZ1234@gmail.com", "LuciaRODRIGUEZ1234#");
        Exception e =  assertThrows(IllegalStateException.class, () -> gestionUsuario.Eliminar(u));
        assertEquals("Usuario no encontrado", e.getMessage());
    }
}
