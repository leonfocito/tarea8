/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.util.Scanner;
import Util.Validaciones;
import Personas.*;
import Grado.*;
import java.time.LocalDate;

/**
 *
 * @author Raúl León García
 */
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        Curso c = null;
        do {

            System.out.println("Escoje una opción:\n"
                    + "1. Crear curso \n"
                    + "2. Nuevo Profesor\n"
                    + "3. Nuevo estudiante\n"
                    + "4. Actualizar Nota\n"
                    + "5. Obtener informe profesor\n"
                    + "6. Obtener informe alumno\n"
                    + "7. Obtener informe general\n"
                    + "8. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    LocalDate inicio = null;
                    LocalDate fin;
                    while (inicio == null) {
                        System.out.println("Introduce la fecha de incio en formato DD/MM/AAA");
                        inicio = Validaciones.formateaFecha(sc.nextLine());
                    }
                    do {
                        System.out.println("Introduce la fecha de fin en formato DD/MM/AAA");
                        fin = Validaciones.formateaFecha(sc.nextLine());
                        if (fin != null && Curso.compruebaFecha(inicio, fin)) {
                            c = new Curso(inicio, fin);
                            System.out.println("Curso creado con éxito");
                            System.out.println(Validaciones.separador());
                        } else {
                            System.out.println("La fecha de fin debe ser posterior a la de inicio");
                        }
                    } while (fin == null || !Curso.compruebaFecha(inicio, fin));

                    break;

                case 2:
                    if (!Curso.getCreado()) {
                        System.out.println("El curso debe estar creado primero");
                    } else {
                        String dni = "";
                        String id = "";
                        String nom = "";
                        LocalDate fechaAltaProfesor = null;
                        String email;
//Comprobamos y añadimos el dni si cumple requisitos
                        while (!Validaciones.validaDni(dni)) {
                            System.out.println("Escribe el dni");
                            dni = sc.nextLine();
                            Validaciones.validaDni(dni);
                        }
//Comprobamos y añadimos el identificador si cumple requisitos
                        while (!Profesor.identificadorValido(id)) {
                            System.out.println("Introduce el identificador del profesor");
                            id = sc.nextLine();
                            if (id.equalsIgnoreCase("x")) {
                                System.out.println("Operación cancelada");
                                break;
                            }
                            if (Profesor.identificadorValido(id)) {
                                System.out.println("ID Válido\n");
                            }
                        }
//Comprobamos y añadimos el nombre si cumple requisitos
                        while (Persona.compruebaNombreVacio(nom)) {
                            System.out.println("Introduce el nombre del profesor");
                            nom = sc.nextLine();
                            if (Persona.compruebaNombreVacio(nom)) {
                                System.out.println("El campo no puede estar en blanco\n");
                            } else if (nom.length() > 40) {
                                nom = nom.substring(0, 40);
                                System.out.println("El nombre es demasiado largo. Solo se guardarán los 40 primeros caracteres\n");
                            }
                        }
//Comprobamos si la fecha de alta es anterior a la del inicio del curso
                        try {
                            do {
                                System.out.println("Escribe la fecha de alta del profesor");
                                fechaAltaProfesor = Validaciones.formateaFecha(sc.nextLine());
                                if (fechaAltaProfesor != null) {
                                    if (fechaAltaProfesor.isAfter(Curso.getFechaInicio())) {
                                        System.out.println("La fecha debe ser anterior al inicio del curso");
                                    }
                                }
                            } while (fechaAltaProfesor == null || fechaAltaProfesor.isAfter(Curso.getFechaInicio()));
                        } catch (NullPointerException ex) {
                            System.out.println("La fecha no es válida");
                        }

//Comprobamos si el email es correcto 
                        do {
                            System.out.println("Escribe el email");
                            email = sc.nextLine();
                            if (!Validaciones.compruebaEmail(email)) {
                                System.out.println("El email no es válido");
                            }
                        } while (!Validaciones.compruebaEmail(email));
                        /*Imprime el menú de asignaturas, guarda la asignatura seleccionada
y crea el objeto con el valor de la posición reservada para esa asignatura*/

                        opcion = 0;
                        while (opcion < 1 || opcion > 8) {
                            try {
                                Asignaturas.imprimeAsignatura();
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if (opcion >= 1 || opcion < 8) {
                                    Asignaturas asignatura = Asignaturas.values()[opcion - 1];
                                    c.insertaProfesor(dni, id, nom, email, fechaAltaProfesor, asignatura);
                                    System.out.println("Profesor creado con éxito");
                                    System.out.println(Validaciones.separador());
                                }
                            } catch (Exception e) {
                                System.out.println("El número tiene que ser entre 1 y 7");
                            }

                        }

                        break;
                    }
                case 3:
                    if (!Curso.getCreado()) {
                        System.out.println("El curso debe estar creado primero");
                    } else {
                        String id = "";
                        String nom = "";
                        LocalDate fechaMatricula = null;
                        String dni = "";
                        String email;
//Comprueba el identificador del estudiante                    
                        while (!Estudiante.identificadorValido(id)) {
                            System.out.println("Introduce el identificador del alumno");
                            id = sc.nextLine();
                            if (id.equalsIgnoreCase("x")) {
                                System.out.println("Operación cancelada");
                                break;
                            }
                            if (Estudiante.identificadorValido(id)) {
                                System.out.println("ID Válido\n");
                            }
                        }
//Comprueba que el campo nombre no esté vacío y si su longitud es mayor de 40 caracteres                      
                        while (Persona.compruebaNombreVacio(nom)) {
                            System.out.println("Introduce el nombre del alumno");
                            nom = sc.nextLine();
                            if (Persona.compruebaNombreVacio(nom)) {
                                System.out.println("El campo no puede estar en blanco\n");
                            } else if (nom.length() > 40) {
                                nom = nom.substring(0, 40);
                                System.out.println("El nombre es demasiado largo. Solo se guardarán los 40 primeros caracteres\n");
                            }
                        }
//Comprueba que la fecha de matrícula tenga el formato correcto y que esté comprendinda entre el inicio y el fin del curso                 
                        try {
                            do {
                                System.out.println("Escribe la fecha de alta del profesor");
                                fechaMatricula = Validaciones.formateaFecha(sc.nextLine());
                                if (fechaMatricula != null) {
                                    if (fechaMatricula.isAfter(Curso.getFechaInicio())) {
                                        System.out.println("La fecha debe ser anterior al inicio del curso");
                                    }
                                }
                            } while (fechaMatricula == null || fechaMatricula.isAfter(Curso.getFechaInicio()) && fechaMatricula.isBefore(Curso.getFechaFin()));
                        } catch (NullPointerException ex) {
                            System.out.println("La fecha no es válida");
                        }
                        //Comprueba que el DNI/NIE sea válido
                        while (!Validaciones.validaDni(dni)) {
                            System.out.println("Escribe el dni");
                            dni = sc.nextLine();
                            Validaciones.validaDni(dni);
                        }
//Comprueba el correo del estudiante
                        do {
                            System.out.println("Escribe el email");
                            email = sc.nextLine();
                            if (!Validaciones.compruebaEmail(email)) {
                                System.out.println("El email no es válido");
                            }
                        } while (!Validaciones.compruebaEmail(email));
                        //Se crea el usuario invocando el método insertaAlumno() de la clase Curso
                        try {
                            switch (c.insertaAlumno(dni, id, nom, email, fechaMatricula)) {
                                case 0:
                                    System.out.println("Estudiante creado con éxito");
                                    System.out.println(Validaciones.separador());
                                    int numAlumno = c.devuelveAlmunosMatriculados() - 1;
                                    int[][] nota = new int[c.getMAX_ALUMNOS()][c.getNUM_ASIGNATURAS()];
                                    for (int i = 0; i < Asignaturas.values().length; i++) {   
                                        do {
                                            System.out.println("Introduce la nota de " + Asignaturas.values()[i].getDescripcion());
                                            nota[numAlumno][i] = sc.nextInt();
                                            sc.nextLine();
                                            if (nota[numAlumno][i] < 0 || nota[numAlumno][i] > 10) {
                                                System.out.println("La nota tiene que estar entre 1 y 10");
                                            }
                                        } while (nota[numAlumno][i] < 0 || nota[numAlumno][i] > 10);                                      
                                    }
                                    c.setNotasAlum(nota);
                                    break;
                                case -1:
                                    System.out.println("El curso debe estar creado primero");
                                    break;
                                case -2:
                                    System.out.println("El estudiante ya existe");
                                    break;
                                case -3:
                                    System.out.println("No hay más espacio");
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("El cupo de alumnos está lleno");
                        }
                    }

                    break;

                case 4:
                    if (!Curso.getCreado()) {
                        System.out.println("El curso debe estar creado primero");
                    } else {
                        String id;
                        Asignaturas asignatura = null;
                        System.out.println("Escoge el alumno por su id");
                        for (int i = 0; i < c.getNumAlumnosMatriculados(); i++) {
                            System.out.println("id: " + c.getRelacionAlumnos()[i].getIdentificador() + " " + c.getRelacionAlumnos()[i].getNombreCompleto());
                        }
                        id = sc.nextLine();
                        opcion = 0;
                        while (opcion < 1 || opcion > 8) {
                            try {
                                Asignaturas.imprimeAsignatura();
                                opcion = sc.nextInt();
                                sc.nextLine();
                                if (opcion >= 1 || opcion < 8) {
                                    asignatura = Asignaturas.values()[opcion - 1];
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("El número tiene que ser entre 1 y 7");
                            }

                        }
                        int nota;
                        do {
                            System.out.println("Introduce la nueva nota");
                            nota = sc.nextInt();
                            sc.nextLine();
                            if (nota < 0 || nota > 10) {
                                System.out.println("La nota tiene que estar entre 1 y 10");
                            }
                        } while (nota < 0 || nota > 10);
                        c.actualizaNota(id, asignatura, nota);
                    }
                    break;
                case 5:
                    if (!Curso.getCreado()) {
                        System.out.println("El curso debe estar creado primero");
                    } else {
                        String id;                       
                        System.out.println("Dame el id del profesor");
                        id = sc.nextLine();                     
                        if (!c.existeProfesor(id)) {
                            System.out.println("El id no es válido");
                        } else {
                            Asignaturas asig=c.getRelacionProfesores()[c.posProfesor(id)].getAsignatura();
                            System.out.println("Id:" + id + "\n"
                                    + "Asignatura:"+asig.getDescripcion());
                            System.out.println(Validaciones.separador());
                            c.informeAsig(asig.getCodigo());
                            
                        }
                    }
                    break;
                case 6:
                    if (!Curso.getCreado()) {
                        System.out.println("El curso debe estar creado primero");
                    } else {
                        System.out.println("Escoge el alumno por su id");
                        for (int i = 0; i < c.getNumAlumnosMatriculados(); i++) {
                            System.out.println("Id: " + c.getRelacionAlumnos()[i].getIdentificador() + " Nombre: " + c.getRelacionAlumnos()[i].getNombreCompleto());
                        }
                        try {
                            c.buscaAlumno(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("El id no existe");
                        }                      
                    }
                    break;
                case 7:

                    break;
                case 8:

                    break;
            }

        } while (opcion != 8);
    }
}
