/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;

/**
 *
 * @author leonfocito
 */
public class Validaciones {

    public static boolean validaDni(String dni) {
        Pattern p = Pattern.compile("[XYxy]?[0-9]{1,9}[A-Za-z]");
        Matcher m = p.matcher(dni);
        return m.matches();
    }

    public static boolean compruebaEmail(String email) {
        return email.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$");
    }

    public static LocalDate formateaFecha(String fecha) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate f = null;
        try {
           f=LocalDate.parse(fecha, dateTimeFormatter);
           return f;
        } catch (Exception ex) {
            System.out.println("La fecha no tiene el formato correcto");
            
        }
        return f;
    }

}
