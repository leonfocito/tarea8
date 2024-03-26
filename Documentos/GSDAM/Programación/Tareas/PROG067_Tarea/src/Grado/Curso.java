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
    private int[][] notasAlum = new int[MAX_ALUMNOS][NUM_ASIGNATURAS];
    private int numAlumnosMatriculados = 0;
    private static boolean creado = true;

    public Curso(LocalDate fechaInicio, LocalDate fechaFin) {
        Curso.fechaInicio = fechaInicio;
        Curso.fechaFin = fechaFin;
        Curso.creado = true;

    }

    public int[][] getNotasAlum() {
        return notasAlum;
    }

    public void setNotasAlum(int[][] notasAlum) {
        this.notasAlum = notasAlum;
    }

    public int getNumAlumnosMatriculados() {
        return numAlumnosMatriculados;
    }

    /*
    Metodos getter y setter
     */
    public int getNUM_ASIGNATURAS() {
        return NUM_ASIGNATURAS;
    }

    public void setNumAlumnosMatriculados(int numAlumnosMatriculados) {
        this.numAlumnosMatriculados = numAlumnosMatriculados;
    }

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

    public boolean existeProfesor(String id) {

        for (int p = 0; p < relacionProfesores.length - 1; p++) {
            if (relacionProfesores[p].getIdentificador().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
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

    /**
     * Método que busca un alumno por su id.
     *
     * @param id Id por el que buscar al alumno
     * @return
     */
    public String informeAlumno(String id) {
        return buscaAlumno(id);
    }

    public void insertaProfesor(String dni, String id, String nom, String email, LocalDate alta, Asignaturas asignatura) {
        int pos = Asignaturas.obtenerPosicionPorCodigo(asignatura.getCodigo());
        relacionProfesores[pos] = new Profesor(dni, id, nom, email, alta, asignatura);
    }

    public void actualizaNota(String id, Asignaturas asignatura, int nota) {
        try {
            int posAlumno = 0;
            for (int e = 0; e < numAlumnosMatriculados; e++) {
                if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                    posAlumno = e;
                } else {
                    System.out.println("El id no existe");
                }

            }
            int posAsign;
            posAsign = Asignaturas.obtenerPosicionPorCodigo(asignatura.getCodigo());
            notasAlum[posAlumno][posAsign] = nota;
            System.out.println("Nota actualizada");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El alumno o la asignatura no existen");
        }

    }
}
