package org.example.UsuarioCrud;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

public class GestionUsuario {

    private List<Usuario> usuarios = new ArrayList<>();

    public void Insertar(Usuario usuario) {
        //Al programar el orden es importante

        //1. Primero verificamos la existencia del usuario
        if(usuario == null){
            throw new IllegalArgumentException("Usuario null");
        }
        //2. //Despúes en caso exista, verficamos las demás características
        if(!usuarios.isEmpty()){
            Usuario u = Read(usuario.getCorreo());
            if(u.equals(usuario)){
                throw new IllegalStateException("Usuario existente");
            }
        }
        usuarios.add(usuario);
    }

    public Usuario Read(String correo) {
        if(correo == null){
            throw new IllegalArgumentException("Usuario con correo null");
        }
         Usuario usuarioRetornado = usuarios.stream().filter(u -> u.getCorreo().equals(correo)).findFirst().orElse(null);
        if(usuarioRetornado == null){
            throw  new IllegalStateException("Usuario no encontrado");
        }
        return usuarioRetornado;
    }

    public void Actualizar(Usuario usuario) {
        if(usuario == null){
            throw new IllegalArgumentException("Usuario null");
        }
        int indice = -1;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuario.getCorreo().equals(usuarios.get(i).getCorreo())){
                indice = i;
                break;
            }
        }

        if(indice == -1){
            throw new IllegalStateException("Usuario no encontrado");
        }

        usuarios.set(indice, usuario);
    }

    public void Eliminar(Usuario user) {
        if(user == null){
            throw new IllegalArgumentException("Usuario null");
        }
        int indice = -1;
        for(int i = 0; i < usuarios.size(); i++){
            if(user.getCorreo().equals(usuarios.get(i).getCorreo())){
                indice = i;
            }
        }

        if(indice == -1){
            throw new IllegalStateException("Usuario no encontrado");
        }

        usuarios.remove(indice);
    }

    public List<Usuario> Listar() {
        return  usuarios;
    }
}
