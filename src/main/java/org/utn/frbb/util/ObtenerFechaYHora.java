package org.utn.frbb.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ObtenerFechaYHora {

    public String fechaYHoraActual(){
        LocalDateTime fechaYHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaYHoraFormateada = fechaYHoraActual.format(formato);

        return fechaYHoraFormateada;
    }
}
