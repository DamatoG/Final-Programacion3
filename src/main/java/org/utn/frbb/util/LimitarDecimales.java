package org.utn.frbb.util;

import java.text.DecimalFormat;

public class LimitarDecimales {

    public static double limitarADosDecimales (double valor){
        DecimalFormat formato = new DecimalFormat("#.##");
        // Formatear el valor utilizando el patrón
        String valorFormateado = formato.format(valor);
        // Convertir el valor formateado de nuevo a double


        return ((tieneMasDeDosDecimales(valor))? Double.parseDouble(valorFormateado) : valor);
    }

    public static boolean tieneMasDeDosDecimales(double numero) {
        // Convierte el número a cadena y obtiene la parte decimal
        String cadena = Double.toString(Math.abs(numero));
        String parteDecimal = cadena.contains(".") ? cadena.split("\\.")[1] : "";


        return parteDecimal.length() > 2;
    }

}
