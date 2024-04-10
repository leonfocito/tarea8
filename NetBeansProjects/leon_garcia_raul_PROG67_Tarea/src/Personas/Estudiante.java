/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;
import java.time.LocalDate;
import Grado.Curso;
/**
 *
 * @author Raúl León García
 * 
 * Subclase de Persona. Usa los atributos de la clase padre: nombre, dni,
 * identificador y email, además añade uno propio: fecha_matricula.
 */
public class Estudiante extends Persona{
    private LocalDate fecha_matricula;
    
    public Estudiante(String dni, String id, String nom, String email, LocalDate matricula){
        super(dni, id, nom, email);
        this.fecha_matricula=matricula;
    }
   
   /**
    Método que comprueba mediante una expresión regular si el identificador es válido.
     * @param id Cadena con el identificador del alumno
    @return true si es el código es válido
    */
    public static boolean identificadorValido(String id){
        return id.matches("^(NIES){1}[0-9]{6}((AB)|(CR)|(CU)|(GU)|(TO)|(ES)|(XX)){1}$")||id.equalsIgnoreCase("x");
    }
    
    /**
    Método que comprueba mediante una expresión regular si el identificador es válido.
    @return true si es el código es válido
    */
    public boolean compruebaFechaMatricula(){
        return fecha_matricula.isBefore(Curso.getFechaFin()) || fecha_matricula.isAfter(Curso.getFechaInicio());
    }
    /**Sobreescribe el método de la clase Persona imprimeDatos()
     * @return Devuelve cadena con los datos del alumno*/
    @Override
    public String imprimeDatos(){
        
        return "Nombre del alumno"+getNombreCompleto()+"\n"
                + "Dni: "+getDni()+"\n"
                + "Identificador: "+ getIdentificador()+"\n"
                + "Email: "+ getEmail()+"\n"
                + "Fecha de matrícula:"+ this.fecha_matricula;
        
    }
}


