/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;

/**
 *
 * @author Raúl León García
 * 
 * Esta super clase contiene los atributos comunes a cualquier persona ya sea
 * profesor o estudiante o algún otro en el futuro 
 */
public abstract class Persona {
    private String dni;
    private String identificador;
    private String nombreCompleto;
    private String email;
    
    public Persona(String dni, String id, String nom, String email){
        this.dni=dni;
        identificador=id;
        nombreCompleto=nom;
        this.email=email;
    }
    public abstract String imprimeDatos();

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static boolean compruebaNombreVacio(String nom){
        return nom.isBlank();
    }
    
    
    
}
