/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grado;

/**
 *
 * @author Raúl León García
 * Contiene un tipo enumerado con las asignaturas
 * disponibles, además de su código y el número de crédito. Contiene dos
 * métodos: obtenerDescripcionAsignatura y obtenerCodigoPorPosicion.
 */
public enum Asignaturas {
    BD("Bases de datos", "BD", 4),
    ED("Entornos de desarrollo", "ED", 3),
    FOL("Formación y orientación laboral", "FOL", 2),
    ING("Inglés técnico", "ING", 3),
    LMSGI("Lenguajes de marcas", "LMSGI", 3),
    PROG("Programación", "PROG", 5),
    SI("Sistemas informáticos", "SI", 4);
    private final String descripcion;
    private final String codigo;
    private final int creditos;

    private Asignaturas(String descripcion, String codigo, int creditos) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    /**
     * Método estático para obtener la descripción de una asignatura a partir
     * del código dado por parámetro.
     *
     * @param codigo introduce una cadena de texto como código
     * @return la descripción de la asignatura si el codigo coincide con alguno
     * de los valores almacenados en el enum o código no encontrado en caso de
     * que el código no sea válido.
     */
    public static String obtenerDescripcionAsignatura(String codigo) {
        for (Asignaturas asignatura : values()) {
            if (asignatura.getCodigo().equals(codigo)) {
                return asignatura.getDescripcion();
            }
        }
        return "Código no encontrado";
    }

    /**
     * Método estático para obtener el código de una asignatura indicándole la
     * posición que ocupa en el enum
     *
     * @param posicion Entero que indica la posición por la que se quiere
     * encontrar el código de la asignatura.
     * @return Devuelve el código de la posición especificada. En caso contrario
     * devolverá null.
     */
    public static String obtenerCodigoPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < values().length) {
            return values()[posicion].getCodigo();
        } else {
            return null;
        }
    }
    /**
     * Método estático para obtener el código de una asignatura indicándole la
     * posición que ocupa en el enum
     *
     * @param codigo Cadena de caracteres que indican un código de asignatura
     * @return Devuelve la posición especificada por el código.
     * En caso contrario el valor -1.
     */
    public static int obtenerPosicionPorCodigo(String codigo){
        for (Asignaturas asignatura : values()) {
            if (asignatura.getCodigo().equals(codigo)) {
                return asignatura.ordinal();
            }
        }return -1;
    }

    public static void imprimeAsignatura() {
        System.out.println("Seleccionar el oficio del operario (teclea el número de la lista):");
        for (int i = 0; i < Asignaturas.values().length; i++) {
            System.out.println((i + 1) + "." + Asignaturas.values()[i].getDescripcion());
        }
    }
}
