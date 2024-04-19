/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grado;

import java.time.LocalDate;
import Personas.*;
import Util.Validaciones;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author Raúl León García Clase para gestionar la creación de cursos, así cómo
 * los alumnos inscritos, las asignaturas y los profesores que las imparten.
 */
public class Curso {

    private final int NUM_ASIGNATURAS = 7;
    private final String NOMBRE = "DAM 1 E-Learning";
    private static LocalDate fechaInicio;
    private static LocalDate fechaFin;
    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private ArrayList<Profesor> listaProfesores = new ArrayList<>();
    private TreeMap<String, int[]> notas = new TreeMap<>();

    /*
    private int[][] notasAlum = new int[MAX_ALUMNOS][NUM_ASIGNATURAS];
    private int numAlumnosMatriculados = 0;*/
    private static boolean creado = false;

    public Curso(LocalDate fechaInicio, LocalDate fechaFin) {
        Curso.fechaInicio = fechaInicio;
        Curso.fechaFin = fechaFin;
        Curso.creado = true;

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

    public static boolean isCreado() {
        return creado;
    }

    public static void setCreado(boolean creado) {
        Curso.creado = creado;
    }

    public static boolean compruebaFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaFin.isAfter(fechaInicio);
    }

    //Métodos con Estudiantes
    /**
     * Inserta un estudiante tras comprobar si el curso ha sido creado y si el
     * estudiante no existe.
     *
     * @param dni Cadena de texto con el valor del DNI
     * @param id Cadena de texto con el valor del identificador
     * @param nom Cadena con el nombre completo
     * @param email Cadena de texto con el valor del correo electrónico
     * @param matricula Fecha en la que se matricula
     */
    public void insertaEstudiante(String dni, String id, String nom, String email, LocalDate matricula) {
        if (!Curso.isCreado()) {
            System.out.println("El curso debe estar creado primero");
        }
        if (existeEstudiante(id)) {
            System.out.println("El estudiante con identificador "+id+" ya existe");
        } else {
            listaEstudiantes.add(new Estudiante(dni, id, nom, email, matricula));
        }
    }

    /**
     * Comprueba si existe el alumno en la ArrayLista de estudiantes para ello
     * utiliza el método compareTo() de la clase estudiantes
     *
     * @param id Cadena de texto con el identificador del estudiante
     * @return Devuelve true si el estudiante ya existe
     */
    public boolean existeEstudiante(String id) {
        boolean exist = false;
        for (Estudiante a : listaEstudiantes) {
            if (a.compareTo(listaEstudiantes) == 0) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    /**
     * Elimina a un estudiante del ArrayList de estudiantes y borra también la
     * clave del TreeMap con sus notas asociadas
     *
     * @param id Cadena de texto con el identificador del estudiante
     */
    public void eliminaEstudiante(String id) {
        if(existeEstudiante(id)){
            listaEstudiantes.remove(listaEstudiantes.indexOf(id));
            eliminaNotas(id);
            System.out.println(Validaciones.separador(40));

        }else {
            System.out.println("El alumno no existe");
        }
    }

    public void imprimeListaEstudiante(){
        Collections.sort(listaEstudiantes);
        for (Estudiante le : listaEstudiantes) {
            System.out.println(le.getNombreCompleto()+" "+imprimeNotas(le.getIdentificador()));
        }
        System.out.println(Validaciones.separador(40));
    }

    public void insertaProfesor(String dni, String id, String nom, String email, LocalDate alta, Asignaturas asignatura) {
        listaProfesores.set(asignatura.ordinal(), new Profesor(dni, id, nom, email, alta, asignatura));

    }

    public boolean existeProfesor(String id) {
        boolean exist = false;
        for (Profesor a : listaProfesores) {
            if (a.getIdentificador().equalsIgnoreCase(id)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public void creaNotas(String id, int[] nota) {
        notas.put(id, nota);
    }

    public void eliminaNotas(String id) {
        notas.remove(id);
        System.out.println("Borrado con éxito");
        System.out.println(Validaciones.separador(40));
    }
    public String imprimeNotas(String id){
        return Arrays.toString(notas.get(id));
    }
    
    /*
    public void inicializar() {
        Random generador = new Random(100);
        int numAlumnos = generador.nextInt();
        String nomEst, codEst, DNIEst, mailEst;
        LocalDate fechaMat;
        int[] notasAlu = new int[NUM_ASIGNATURAS];
        for (int i = 0; i < numAlumnos - 1; i++) {
            nomEst = String.format("Nombre alumno %2d", i);
            codEst = String.format("NIES%06dES", i);
            DNIEst = "98765432X";
            mailEst = String.format("alumno&03d@iesjuanbosco", i);
            fechaMat = getFechaInicio();
            for (int j = 0; j < NUM_ASIGNATURAS; j++) {
                notasAlu[i] = generador.nextInt(11);

            }
            insertaEstudiante(DNIEst, codEst, nomEst, mailEst, fechaMat);
            creaNotas(codEst,notasAlu);
        }
        
        int ordenAsig;
        String nomProf, idProf, DNIProf, mailProf;
        LocalDate fAltaProf;
        for (Asignaturas asignatura : Asignaturas.values()) {
            ordenAsig = asignatura.ordinal();
            nomProf = "Profesor de " + asignatura.getCodigo();
            idProf = String.format("590%05dINF", ordenAsig);
            DNIProf = "12345678A";
            mailProf = String.format("profesor%03d@iesjuanbosco.es", ordenAsig);
            fAltaProf = LocalDate.now();
            insertaProfesor(DNIProf, idProf, nomProf, mailProf, fAltaProf, asignatura);
        }*/
    
}
