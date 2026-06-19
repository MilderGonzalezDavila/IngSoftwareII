package org.example.UsuarioCrud;

public class Usuario {

    private String Nombre;
    private String Correo;
    private String Password;

    public Usuario(String nombre, String correo, String password){

        ValidarNombre(nombre);
        ValidarCorreo(correo);
        ValidarPassword(password);

        this.Nombre = nombre;
        this.Correo = correo;
        this.Password = password;
    }

    public void ValidarNombre(String nombre){
        if(nombre == null){
            throw new IllegalArgumentException("El nombre no debe ser nulo");
        }
        if(nombre.isBlank()){
            throw new IllegalArgumentException("El nombre debe ser valido");
        }
        if(nombre.length() < 2){
            throw new IllegalArgumentException("El nombre debe tener minimo dos caracteres");
        }
        if(nombre.length() > 30){
            throw new IllegalArgumentException("El nombre debe tener maximo treinta caracteres");
        }
        if(nombre.matches(".*[0-9].*")){
            throw new IllegalArgumentException("El nombre debe no debe de contener numeros");
        }
        if(nombre.matches(".*[^a-zA-Z0-9ñÑ].*")){
            throw new IllegalArgumentException("El nombre debe de contener caracteres especiales");
        }
    }

    public void ValidarCorreo(String correo){
        if(correo == null){
            throw new IllegalArgumentException("El correo no debe ser null");
        }
        if(correo.isEmpty()){
            throw new IllegalArgumentException("El correo no debe ser vacio");
        }
        if(correo.isBlank()){
            throw new IllegalArgumentException("El correo no debe tener solo espacio en blanco");
        }
        if(!correo.matches(".+@.+")){
            throw new IllegalArgumentException("El correo debe contener arroba con textos a los lados");
        }
        if(!correo.matches(".*\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("El correo debe tener un dominio");
        }
        if(!(correo.chars().filter(c -> c == '@').count() == 1)){
            throw new IllegalArgumentException("El correo debe contener un unico arroba");
        }

    }

    public void ValidarPassword(String password){
        if(password == null){
            throw new IllegalArgumentException("El password no debe ser null");
        }
        if(password.isEmpty()){
            throw new IllegalArgumentException("El password no debe estar vacio");
        }
        if(password.isBlank()){
            throw new IllegalArgumentException("El password no debe tener espacio en blanco");
        }
        if(password.length() < 6){
            throw new IllegalArgumentException("El password debe tener como minimo 6 caracteres");
        }
        if(!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("El password debe tener como minimo una letra mayuscula");
        }
        if(!password.matches(".*[a-z].*")){
            throw new IllegalArgumentException("El password debe tener como minimo una letra minuscula");
        }
        if(!password.matches(".*[0-9].*")){
            throw new IllegalArgumentException("El password debe tener como minimo un numero");
        }
        if(!password.matches(".*[@#$&%^+=!].*")){
            throw  new IllegalArgumentException("El password debe tener como minimo un caracter especial");
        }
    }

    public String getNombre(){
        return this.Nombre;
    }

    public String getCorreo(){
        return this.Correo;
    }

    public String getPassword(){
        return this.Password;
    }
}
