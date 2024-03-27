/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grado;

import java.time.LocalDate;
import Personas.*;
import Util.Validaciones;

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
     *
     */
    public void buscaAlumno(String id) {

        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                System.out.println(relacionAlumnos[e].imprimeDatos());
                for (int i = 0; i < Asignaturas.values().length; i++) {
                    System.out.println("Nota de " + Asignaturas.values()[i].getDescripcion() + " " + notasAlum[e][i]);
                }
                String decimal=String.format("%.2f",notaMediaAlum(id));
                double porcAprobados;
                porcAprobados=(double)(numAprobadosAlum(id)/NUM_ASIGNATURAS)*100;
                String porcentaje=String.format("%.2f", porcAprobados);
                System.out.println("El alumno "+relacionAlumnos[e].getNombreCompleto()+" ha aprobado "+numAprobadosAlum(id)+" lo que supone un "+porcentaje+"% de aprobados y su nota media es "+decimal);
            }
        }

    }

    public boolean existeProfesor(String id) {

        for (int p = 0; p < relacionProfesores.length - 1; p++) {
            if (relacionProfesores[p].getIdentificador().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    public int posProfesor(String id){
        int pos=0;
        for (int p = 0; p < relacionProfesores.length - 1; p++) {
            if (relacionProfesores[p].getIdentificador().equalsIgnoreCase(id)) {
                pos=p;
            }
        }
        return pos;
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
     * 
     */
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
            System.out.println(Validaciones.separador());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El alumno o la asignatura no existen");
        }

    }

    public double notaMediaAlum(String id) {
        int posAlumno = 0;
        double suma = 0;
        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                posAlumno = e;
            } else {
                System.out.println("El id no existe");
            }
        }
        for (int i = 0; i < Asignaturas.values().length; i++) {
            suma = suma + (double)notasAlum[posAlumno][i];
        }
        return suma / NUM_ASIGNATURAS;
    }
    public int numAprobadosAlum(String id) {
        int posAlumno = 0;
        int cont = 0;
        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                posAlumno = e;
            } else {
                System.out.println("El id no existe");
            }
        }
        for (int i = 0; i < Asignaturas.values().length; i++) {
            if(notasAlum[posAlumno][i]>=5){
                cont++;
            }
        }
        return cont;
    }
    
    public int getMAX_ALUMNOS() {
        return MAX_ALUMNOS;
    }
    public String informeAsig(String codigo){
        int pos=Asignaturas.obtenerPosicionPorCodigo(codigo);
        int apro=0;
        int susp=0;
        double porApro;
        double porSusp;
        double suma=0;
        int notaMax=notasAlum[0][0];
        int notaMin=notasAlum[0][0];
        for(int i=0;i<numAlumnosMatriculados;i++){
            suma=suma+notasAlum[i][pos];
            if(notasAlum[i][pos]>notaMax){
                notaMax=notasAlum[i][pos];
            }
            if(notasAlum[i][pos]>notaMin){
                notaMin=notasAlum[i][pos];
            }
            if (notasAlum[i][pos]>=5){
                apro++;
            }else{
                susp++;
            }
        }
        double notaMedia=suma/numAlumnosMatriculados;
        porApro=(double)(apro/numAlumnosMatriculados)*100;
        porSusp=(double)(susp/numAlumnosMatriculados)*100;
        return "El número de aprobados es de "+apro+". Esto es un "+String.format("%.2f",porApro)+"%\n"
                + "El número de suspensos es de "+susp+". Esto es un "+String.format("%.2f",porSusp)+"%\n"
                + "La nota media de esta asignatura es "+String.format("%.2f",notaMedia);
    }
}
