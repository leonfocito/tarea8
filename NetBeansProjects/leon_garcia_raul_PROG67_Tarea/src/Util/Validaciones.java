/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Raúl León García
 * Contiene métodos estáticos para realizar las validaciones del DNI y del email
 */

public class Validaciones {
/** * @param dni DNI o NIE a comprobar su valides
     * @throws java.lang.Exception Captura que el formato sea correcto
     * @return Devuelve true si todo es correcto
 
 */
    public static boolean validaDni(String dni) throws Exception {
        boolean dniOk = false;
        boolean nieOk = false;
        boolean todoOk = false;
        int resto;
        char letra;
        char letrasNIF[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y',
            'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z',
            'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        if (dni.matches("[0-9]{1,9}[A-Za-z]")) {
            String numDNIs = dni.substring(0, dni.length() - 1);
            int num;
            try {
                num = Integer.parseInt(numDNIs);
            } catch (NumberFormatException e) {
                throw new Exception("El formato de los numeros no es correcto");
            }
            // Comprobacion de la letra
            char letraDNI = dni.toUpperCase().charAt(dni.length() - 1);
            boolean existe = false;
            for (int i = 0; i < letrasNIF.length; i++) {
                if (letrasNIF[i] == letraDNI) {
                    existe = true;
                }
            }
            if (!existe) {
                throw new Exception("La letra no es valida");
            }
            // Comprobacion de si el DNI es correcto
            resto = num % 23;

            letra = letrasNIF[resto];

            if (dni.equals(numDNIs + letra)) {
                throw new Exception("El DNI no es correcto");
            } else {
                dniOk = true;
            }
        }
        if (dni.matches("[XYZxyz]+{1}[0-9]{1,9}[A-Za-z]")) {
            String letraInicial = dni.substring(0);
            StringBuilder nie = new StringBuilder(dni.substring(0, dni.length() - 1));
            int numNie = 0;
            if (letraInicial.equalsIgnoreCase("x")) {
                nie.replace(0, 1, "0");

            }
            if (letraInicial.equalsIgnoreCase("y")) {
                nie.replace(0, 1, "1");

            }
            if (letraInicial.equalsIgnoreCase("z")) {
                nie.replace(0, 1, "2");

            }

            try {
                numNie = Integer.parseInt(nie.toString());
            } catch (NumberFormatException e) {
                System.out.println("No se puede convertir a número");
            }
            char letraNie = dni.toUpperCase().charAt(dni.length() - 1);
            boolean existe = false;
            for (int i = 0; i < letrasNIF.length; i++) {
                if (letrasNIF[i] == letraNie) {
                    existe = true;
                }
            }
            if (!existe) {
                throw new Exception("La letra no es valida");
            }
            resto = numNie % 23;

            letra = letrasNIF[resto];

            if (dni.equals(nie.toString() + letra)) {
                throw new Exception("El NIE no es correcto");
            } else {
                nieOk = true;
            }
            
        }
        if (dniOk || nieOk) {
                todoOk = true;
            }
        return todoOk;
    }
/**Comprueba la validez de un email mediante una expresión regular
 @param email Email a comprobar
 @return Devuelve true en caso de que sea válido*/
    public static boolean compruebaEmail(String email) {
        return email.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
    }
/**Formatea la fecha para que coincida con el patrón dd//MM//yyyy
 @param fecha Cadena con la fecha a formatear
 @return Devuelve la fecha en formato localDate o null en caso de que no tenga el formato correcto*/
    public static LocalDate formateaFecha(String fecha) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate f = null;
        try {
            f = LocalDate.parse(fecha, dateTimeFormatter);
            return f;
        } catch (Exception ex) {
            System.out.println("La fecha no tiene el formato correcto");

        }
        return f;
    }
/**Genera una cadena de n veces el símbolo '-'
 * @param numR número de veces que se repite el símbolo
 * @return Cadena formada por n repeticiones 
 * 
 */
    public static String separador(int numR) {
        String s = "-";
        return s.repeat(numR);
    }
    
}
