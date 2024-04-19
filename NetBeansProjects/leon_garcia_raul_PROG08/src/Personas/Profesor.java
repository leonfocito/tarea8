/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personas;

import java.time.LocalDate;
import Grado.Asignaturas;
/**
 *
 * @author leonfocito
 * Subclase de Persona. Usa los atributos de la clase padre: nombre, dni,
 * identificador y email, además añade uno propio: fecha_alta y asignatura
 */
public class Profesor extends Persona {

    private LocalDate fecha_alta;
    private Asignaturas asignatura;

    public Profesor(String dni, String id, String nom, String email, LocalDate alta, Asignaturas asignatura) {
        super(dni, id, nom, email);
        fecha_alta = alta;
        this.asignatura = asignatura;

    }

    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Asignaturas getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }
   
       /**Sobreescribe el método de la clase Persona imprimeDatos()
     * @return Devuelve cadena con los datos del profesor*/ 
    @Override
   public String imprimeDatos(){
        
        return "Nombre del profesor: "+getNombreCompleto()+"\n"
                + "Dni: "+getDni()+"\n"
                + "Identificador: "+ getIdentificador()+"\n"
                + "Email: "+ getEmail()+"\n"
                + "Fecha de matrícula:"+ this.fecha_alta+"\n"
                + "Asignatura que imparte: "+this.asignatura.getDescripcion();
        
    }
     
    /**
    * Método que comprueba mediante una expresión regular si el identificador es válido.
    * @param id Identificador del profesor
    *@return true si es el código es válido 
    */
     
    public static boolean identificadorValido(String id) {
        return id.matches("^(([5][9])[0-2]){1}[0-9]{5}((INF)|(ING)|(FOL)|(SIA)){1}$")||id.equalsIgnoreCase("x");
    }
    
    
}
