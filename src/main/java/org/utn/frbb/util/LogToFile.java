package org.utn.frbb.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogToFile {

    private String nombre_archivo;

    public LogToFile(){
        this.nombre_archivo = "Partida-"+ new ObtenerFechaYHora().fechaYHoraActual().replace(":", "_") + ".txt";
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public void crearArchivoLogNuevaPartida() throws IOException {



        // Construir la ruta del archivo utilizando Path
        Path rutaArchivo = Paths.get("C:", "Users", "Usuario", "Documents", "TUP", "UTN", "3ER CUATRIMESTRE", "PROGRAMACION3", "final-progra3", "logs_partidas", this.nombre_archivo);
        // Crear directorios si no existen
        rutaArchivo.toFile().getParentFile().mkdirs();

        File myObj = rutaArchivo.toFile();

        if (myObj.createNewFile()) {
            System.out.println("Archivo creado: " + myObj.getName() + " en " + myObj.getPath());
            FileWriter myWriter = new FileWriter(myObj.getPath() , true);
            myWriter.write("INICIO PARTIDA" + "\n");

            myWriter.flush();
            myWriter.close();


        } else {
            System.out.println("El archivo ya existe");
        }
    }

    public void escribirArchivoYMostrarPorConsola(String mensaje) throws IOException {

        Path rutaArchivo = Paths.get("C:", "Users", "Usuario", "Documents", "TUP", "UTN", "3ER CUATRIMESTRE", "PROGRAMACION3", "final-progra3", "logs_partidas", nombre_archivo);
        File myObj = rutaArchivo.toFile();

        FileWriter myWriter = new FileWriter(myObj.getPath() , true);
        myWriter.write(mensaje + "\n");
        myWriter.flush();
        myWriter.close();

        System.out.println(mensaje);
    }
}
