/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grado;

import java.time.LocalDate;
import Personas.*;

/**
 *
 * @author Raúl León García
 */
public class Curso {

    private final int NUM_ASIGNATURAS = 7;
    private final int MAX_ALUMNOS = 100;
    private final String NOMBRE = "DAM 1 E-Learning";
    
    private static LocalDate fechaInicio;
    private static LocalDate fechaFin;
    private Estudiante[] relacionAlumnos = new Estudiante[MAX_ALUMNOS];
    private Profesor[] relacionProfesores = new Profesor[NUM_ASIGNATURAS];
    private int[][] asignaturasAlumnos = new int[MAX_ALUMNOS][NUM_ASIGNATURAS];
    private int numAlumnosMatriculados = 0;
    private static boolean creado = true;

    public Curso(LocalDate fechaInicio, LocalDate fechaFin) {
        Curso.fechaInicio = fechaInicio;
        Curso.fechaFin = fechaFin;
        Curso.creado = true;
        
    }

    /*
    Metodos getter y setter
     */
    public static LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public static void setFechaInicio(LocalDate fechaInicio) {
        Curso.fechaInicio = fechaInicio;
    }

    public static LocalDate getFechaFin() {
        return fechaFin;
    }

    public static void setFechaFin(LocalDate fechaFin) {
        Curso.fechaFin = fechaFin;
    }

    public static boolean getCreado() {
        return creado;
    }

    public Estudiante[] getRelacionAlumnos() {
        return relacionAlumnos;
    }

    public void setRelacionAlumnos(Estudiante[] relacionAlumnos) {
        this.relacionAlumnos = relacionAlumnos;
    }

    public Profesor[] getRelacionProfesores() {
        return relacionProfesores;
    }

    public void setRelacionProfesores(Profesor[] relacionProfesores) {
        this.relacionProfesores = relacionProfesores;
    }

    /**
     * Método que comprueba si la fecha introducida es posterior a la del incio
     * del curso.
     *
     * @param fechaInicio Fecha de incio del curso
     * @param fechaFin Fecha final del curso
     * @return true si es la fecha es válida
     */
    public static boolean compruebaFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaFin.isAfter(fechaInicio);
    }

    public int devuelveAlmunosMatriculados() {
        for (int i = 0; i < relacionAlumnos.length - 1; i++) {
            if (relacionAlumnos[i] == null) {
                numAlumnosMatriculados = i;
                break;
            }
        }
        return numAlumnosMatriculados;
    }

    /**
     * Método que busca un alumno por su id.
     *
     * @param id Id por el que buscar al alumno
     * @return
     */
    public String buscaAlumno(String id) {
        String datos = "";
        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                datos = relacionAlumnos[e].imprimeDatos();
                break;
            } else {
                datos = "null";
            }
        }
        return datos;

    }

    /**
     * Método que busca un alumno por su id.
     *
     * @param id Id por el que buscar al alumno
     * @return
     */
    public int insertaAlumno(String dni, String id, String nom, String email, LocalDate matricula) {
        int cod = 0;
        if (!creado) {
            cod = -1;
        }
        if (id.equals(id)) {
            cod = -2;
        }
        if (numAlumnosMatriculados > 100) {
            cod = -3;

        } else {
            relacionAlumnos[numAlumnosMatriculados] = new Estudiante(dni, id, nom, email, matricula);
            numAlumnosMatriculados++;
            cod = 0;
        }

        return cod;
    }

    public void insertaNotas(int nota) {

        for (int i = 0; i < NUM_ASIGNATURAS - 1; i++) {
            asignaturasAlumnos[numAlumnosMatriculados][i] = nota;
        }
        /**
         * Método que busca un alumno por su id.
         *
         * @param id Id por el que buscar al alumno
         * @return
         */

    }

    public String informeAlumno(String id) {
        return buscaAlumno(id);
    }

    public void insertaProfesor(String dni, String id, String nom, String email, LocalDate alta, Asignaturas asignatura) {
        int pos=Asignaturas.obtenerPosicionPorCodigo(asignatura.getCodigo());
        relacionProfesores[pos]=new Profesor(dni, id, nom, email, alta, asignatura);
    }
}
