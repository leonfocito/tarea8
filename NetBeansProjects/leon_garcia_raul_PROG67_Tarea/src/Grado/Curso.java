/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grado;

import java.time.LocalDate;
import Personas.*;
import Util.Validaciones;
import java.util.Random;

/**
 *
 * @author Raúl León García
 * Clase para gestionar la creación de cursos, así cómo los alumnos inscritos, las asignaturas y los profesores que las imparten.  
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
    private static boolean creado = false;

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
    /**Recorre el array relacionAlumnos[] y busca la primera posición vacía, entonces la guarda
    @return Devuelve el número de alumnos matriculados en el curso */
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
                String decimal = String.format("%.2f", notaMediaAlum(id));
                double porcAprobados;
                porcAprobados = ((double) numAprobadosAlum(id) / NUM_ASIGNATURAS) * 100;
                String porcentaje = String.format("%.2f", porcAprobados);
                System.out.println("El alumno " + relacionAlumnos[e].getNombreCompleto() + " ha aprobado " + numAprobadosAlum(id) + " lo que supone un " + porcentaje + "% de aprobados y su nota media es " + decimal);
            }
        }

    }
    /**Comprueba si la cadena pasada por parámetro coincide con algún identtificador de profesor
     @param id Cadena con el identificador del profesor
     @return Devuelve true si el profesor exite*/

    public boolean existeProfesor(String id) {
        boolean existe = false;
        for (int i = 0; i < relacionProfesores.length; i++) {
            if (relacionProfesores[i] != null) {
                if (relacionProfesores[i].getIdentificador().equals(id)) {
                    existe = true;
                    break;
                }
            }          
        } return existe;
    }
    /**Devuelve la pasición del profesor en el array relacionProfesore[]
     @param id Cadena con el identificador del profesor
     @return Devuelve un entero con la posición del profesor*/
    public int posProfesor(String id) {
        int pos = 0;
        for (int p = 0; p < relacionProfesores.length; p++) {
            if (relacionProfesores[p].getIdentificador().equalsIgnoreCase(id)) {
                pos = p;
            }
        }
        return pos;
    }

    /**
     * Método que comprueba si el curso no ha sido creado (cod=-1), si el alumno ya existe(cod=-2) y si el curso ya no admite más alumnos(cod=-3).En caso contrario, añade al nuevo estudiante (cod=0)
     *
     * @param dni Cadena con el dni del estudiante
     * @param id Id por el que buscar al alumno
     * @param nom Cadena con el nombre
     * @param email Cadena con el email
     * @param matricula LocalDate con la fehca de matricula
     * @return Devuelve un entero con el valor del código 
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
     * Método que inserta un profesor pasándole los parámetros que requiere el
     * constructor de la clase profesor
     *
     * @param dni Cadena con el dni
     * @param id Id por el que buscar al alumno
     * @param nom Cadena con el nombre
     * @param email Cadena con el emaik
     * @param alta LocalDate con la fecha de alta
     * @param asignatura Enum con la asignatura que imparte
     *
     */
    public void insertaProfesor(String dni, String id, String nom, String email, LocalDate alta, Asignaturas asignatura) {
        int pos = Asignaturas.obtenerPosicionPorCodigo(asignatura.getCodigo());
        relacionProfesores[pos] = new Profesor(dni, id, nom, email, alta, asignatura);
    }

    /**COmprueba si el alumno existe.Si es así, actualiza el valor de la posción del alumno-asignatura con la nueva nota en el array notasAlum[]
     * @param id Cadena con el identificador del alumno
     * @param asignatura Enum con la asignatura
     * @param nota Entero con la nota a actualizar
     */
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
            System.out.println(Validaciones.separador(40));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El alumno o la asignatura no existen");
        }

    }
/**Recorre el array notasAlum y almacena su valor en suma para luego dividirlo entre el numAsignaturas
 @param id Cadena con el identificador del alumno
 @return Devuelve un double con la nota media*/
    public double notaMediaAlum(String id) {
        int posAlumno = 0;
        double suma = 0;
        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                posAlumno = e;
            }
        }
        for (int i = 0; i < Asignaturas.values().length; i++) {
            suma = suma + (double) notasAlum[posAlumno][i];
        }
        return suma / NUM_ASIGNATURAS;
    }
/**Comprueba el valor de cada posicion del array notasAlum que coinicda con el id del alumno y los que sean superiores o iguales a 5 se guardarán en cont.
 * @param id Cadena con el identificador del alumno
 * @return Devuelve un entero con el nçumero de asiganturas aprobadas
 */
    public int numAprobadosAlum(String id) {
        int posAlumno = 0;
        int cont = 0;
        for (int e = 0; e < numAlumnosMatriculados; e++) {
            if (relacionAlumnos[e].getIdentificador().equalsIgnoreCase(id)) {
                posAlumno = e;
            }
        }
        for (int i = 0; i < Asignaturas.values().length; i++) {
            if (notasAlum[posAlumno][i] >= 5) {
                cont++;
            }
        }
        return cont;
    }

    public int getMAX_ALUMNOS() {
        return MAX_ALUMNOS;
    }
/**Genera un informe con el número de aprobados y suspensos y sus porcentajes dada una asignatura.Calcula la nota media e identifica la nota mínima y la máxima
 @param codigo Cadena con el código de la asignatura
 @return Devuelve una cadena con la información anterior*/
    public String informeAsig(String codigo) {
        int pos = Asignaturas.obtenerPosicionPorCodigo(codigo);
        int apro = 0;
        int susp = 0;
        double porApro;
        double porSusp;
        double suma = 0;
        int notaMax = notasAlum[0][0];
        int notaMin = notasAlum[0][0];
        for (int i = 0; i < numAlumnosMatriculados; i++) {
            suma = suma + notasAlum[i][pos];
            if (notasAlum[i][pos] > notaMax) {
                notaMax = notasAlum[i][pos];
            }
            if (notasAlum[i][pos] < notaMin) {
                notaMin = notasAlum[i][pos];
            }
            if (notasAlum[i][pos] >= 5) {
                apro++;
            } else {
                susp++;
            }
        }
        double notaMedia = suma / numAlumnosMatriculados;
        porApro = ((double) apro / numAlumnosMatriculados) * 100;
        porSusp = ((double) susp / numAlumnosMatriculados) * 100;
        return "El número de aprobados es de " + apro + ". Esto es un " + String.format("%.2f", porApro) + "%\n"
                + "El número de suspensos es de " + susp + ". Esto es un " + String.format("%.2f", porSusp) + "%\n"
                + "La nota media de esta asignatura es " + String.format("%.2f", notaMedia) + "\n"
                + "La nota mínima es un " + notaMin + "\n"
                + "La nota másxima es un " + notaMax;
    }
/**Genera un informe del objeto curso con los alumnos matrículados y las notas de cada asignatura
 */
    public void informeCurso() {
        System.out.println("Informe general del curso " + fechaInicio.getYear() + "/" + fechaFin.getYear());
        System.out.println(NOMBRE);
        System.out.print(String.format("%-42s", "Nombre" + "|"));
        for (int i = 0; i < Asignaturas.values().length; i++) {
            System.out.print(String.format("%-6s", Asignaturas.values()[i].getCodigo()) + "|");

        }
        System.out.println(String.format("%-12s", "Nota media" + "|"));
        for (int j = 0; j < numAlumnosMatriculados; j++) {
            System.out.print(String.format("%-42s", relacionAlumnos[j].getNombreCompleto()));
            for (int z = 0; z < Asignaturas.values().length; z++) {
                System.out.print(String.format("%-7d", notasAlum[j][z]));

            }
            System.out.println(String.format("%.2f", notaMediaAlum(relacionAlumnos[j].getIdentificador())));
            System.out.println();
        }

    }

    public void inicializar() {
        Random generador = new Random();
        int numAlumnos = generador.nextInt(MAX_ALUMNOS);
        String nomEst, codEst, DNIEst, mailEst;
        LocalDate fechaMat;
        int[][] notasAlu = new int[numAlumnos][NUM_ASIGNATURAS];
        for (int i = 0; i < numAlumnos - 1; i++) {
            nomEst = String.format("Nombre alumno %2d", i);
            codEst = String.format("NIES%06dES", i);
            DNIEst = "98765432X";
            mailEst = String.format("alumno&03d@iesjuanbosco", i);
            fechaMat = getFechaInicio();
            for (int j = 0; j < NUM_ASIGNATURAS; j++) {
                notasAlu[i][j] = generador.nextInt(11);

            }
            insertaAlumno(DNIEst, codEst, nomEst, mailEst, fechaMat);
            setNotasAlum(notasAlu);
        }
        int ordenAsig;
        String nomProf, idProf, DNIProf, mailProf;
        LocalDate fAltaProf;
        for (Asignaturas asignatura : Asignaturas.values()) {
            ordenAsig = Asignaturas.obtenerPosicionPorCodigo(asignatura.getCodigo());
            nomProf = "Profesor de " + asignatura.getCodigo();
            idProf = String.format("590%05dINF", ordenAsig);
            DNIProf = "12345678A";
            mailProf = String.format("profesor%03d@iesjuanbosco.es", ordenAsig);
            fAltaProf = LocalDate.now();
            insertaProfesor(DNIProf, idProf, nomProf, mailProf, fAltaProf, asignatura);
        }
    }
}
