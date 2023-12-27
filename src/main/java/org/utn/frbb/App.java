package org.utn.frbb;

import org.utn.frbb.model.Jugador;
import org.utn.frbb.model.Partida;
import org.utn.frbb.util.LogToFile;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App
{
    public static void main( String[] args ) throws IOException {

        Scanner lectura = new Scanner(System.in);

        System.out.println("\n-------------------------------------------------------------" +
                "\n-------BIENVENIDOS AL MEJOR JUEGO DE ROLES DE LA UTN---------" +
                "\n-------------------------------------------------------------\n");

        int seleccion = 0;
        do {
            try {
            //Menu
            System.out.println("MENÚ PRINCIPAL:\n"
                    + "1. Iniciar partida con personajes aleatorios automaticamente\n"
                    + "2. Iniciar partida con personajes custom \n"
                    + "3. Leer archivo de ultimas partidas\n"
                    + "4. Borrar archivo logs\n"
                    + "5. Salir\n");

            seleccion = lectura.nextInt();


                //SALIR DEL PROGRAMA
                if (seleccion == 5) {
                    System.out.println("Hasta pronto. Has salido del juego");
                }
                //BORRAR ARCHIVOS
                else if (seleccion == 4) {

                    File carpeta_logs = new File("logs_partidas");
                    File[] archivos_logs = carpeta_logs.listFiles();
                    if (archivos_logs != null && archivos_logs.length >0){
                        System.out.println("Selecciona el archivo que desea borrar");
                        int count = 0;
                        for (File archivo : archivos_logs) {
                            count += 1;
                            System.out.println(count + ". " + archivo.getName());

                        }
                        int seleccion_archivo = lectura.nextInt();
                        if (seleccion_archivo >= 1 && seleccion_archivo <= count) {
                            System.out.println(count);
                            int confirmar_eliminacion;

                            System.out.println("Seguro que desea eliminiar el archivo? \n" +
                                    "1. SI\n 2. NO\n");

                            confirmar_eliminacion = lectura.nextInt();
                            switch (confirmar_eliminacion) {
                                case 1:
//                                    System.out.println(archivos_logs[seleccion_archivo - 1].getName());
//                                    archivos_logs[seleccion_archivo - 1].delete();
//                                    break;
                                try {
                                    System.out.println(archivos_logs[seleccion_archivo - 1].getName());

                                    if (archivos_logs[seleccion_archivo - 1].delete()) {
                                        System.out.println("Archivo eliminado con éxito.");
                                    } else {
                                        System.out.println("No se pudo eliminar el archivo.");
                                    }
                                } catch (SecurityException e) {
                                    System.out.println("Error de permisos al intentar eliminar el archivo: " + e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Error al intentar eliminar el archivo: " + e.getMessage());
                                }
                                break;
                                case 2:
                                    // secuencia de sentencias.
                                    break;
                            }

                        } else {
                            System.out.println("El archivo no existe");
                        }

                    } else {
                        System.out.println("No hay archivos disponibles. Debe iniciar una partida y automaticamente se guardará el log.");
                    }
                    // listar logs con numero
                    //si el numero es alguno de los expuestos
                    //borrar archivo
                    //si no mostrar error
                }
                //LEER ARCHIVOS LOGS
                else if (seleccion == 3) {

                    File carpeta_logs = new File("logs_partidas");
                    File[] archivos_logs = carpeta_logs.listFiles();

                    if (archivos_logs != null && archivos_logs.length >0) {
                        System.out.println("Selecciona el archivo que desea abrir");
                        int count = 1;
                        for (File archivo : archivos_logs) {
                            System.out.println(count + ". " + archivo.getName());
                            count += 1;
                        }
                        int seleccion_archivo = lectura.nextInt();
                        if (seleccion_archivo >= 1 && seleccion_archivo <= archivos_logs.length) {
                            Scanner scanner = new Scanner(archivos_logs[seleccion_archivo - 1]);
                            while (scanner.hasNextLine()) {
                                String linea = scanner.nextLine();
                                System.out.println(linea);
                            }
                        }else {
                            System.out.println("El archivo no existe\n\n");
                        };

                    } else {
                        System.out.println("No hay archivos disponibles\n\n");
                    }
                }
                //JUGAR PARTIDAS
                else if (seleccion == 2 || seleccion == 1) {
                    //INICIAR CON EL LOG EN ARCHIVO
                    LogToFile log_partida = new LogToFile();
                    Partida partida = new Partida(log_partida);
                    if (seleccion == 1){
                        partida.setAutomatico(true);
                    } else if (seleccion == 2) {
                        partida.setAutomatico(false);
                    }


                    log_partida.crearArchivoLogNuevaPartida();

                    log_partida.escribirArchivoYMostrarPorConsola("\n-------------------------------------------------------------" +
                            "\n-------BIENVENIDOS AL MEJOR JUEGO DE ROLES DE LA UTN---------" +
                            "\n-------------------------------------------------------------\n");

                    //INGRESO NOMBRES JUGADORES
                    //System.out.println("Ingrese el nombre del jugador 1: ");
                    log_partida.escribirArchivoYMostrarPorConsola("Ingrese el nombre del jugador 1: ");
                    Jugador j1 = new Jugador(lectura.next());
                    log_partida.escribirArchivoYMostrarPorConsola(j1.getNombre());
                    partida.setJ1(j1);
                    //System.out.println("Ingrese el nombre del jugador 2: ");
                    log_partida.escribirArchivoYMostrarPorConsola("Ingrese el nombre del jugador 2: ");
                    Jugador j2 = new Jugador(lectura.next());
                    log_partida.escribirArchivoYMostrarPorConsola(j2.getNombre());
                    partida.setJ2(j2);


                    //System.out.println("\nEl duelo del dia de hoy será entre " + partida.getJ1().getNombre() + " VS " + partida.getJ2().getNombre());
                    log_partida.escribirArchivoYMostrarPorConsola("\nEl duelo del dia de hoy será entre " + partida.getJ1().getNombre() + " VS " + partida.getJ2().getNombre());



                    //GENERAR Y ASIGNAR PERSONAJES A JUGADORES
                    //System.out.println("\n--------------------------------\n|   SORTEANDO PERSONAJES....   |\n--------------------------------\n");
                    log_partida.escribirArchivoYMostrarPorConsola("\n--------------------------------\n|   SORTEANDO PERSONAJES....   |\n--------------------------------\n");
                    partida.asignar_pjs();
                    //System.out.println("\n\n\n----------PERSONAJES CREADOS----------\n\n\n");
                    log_partida.escribirArchivoYMostrarPorConsola("\n\n\n----------PERSONAJES CREADOS----------\n\n\n");
                    partida.mostrar_personajes(partida.getJ1());
                    partida.mostrar_personajes(partida.getJ2());
                    //System.out.println("\n--------------------------------\n|   Que comience el juego....   |\n--------------------------------\n");
                    log_partida.escribirArchivoYMostrarPorConsola("\n--------------------------------\n|   Que comience el juego....   |\n--------------------------------\n");
                    partida.iniciar_partida();

                }
                //OPCION DISTINTA ENTRE 1 Y 5
                else {
                    System.out.println("Selecciona una opción válida (1-5)\n\n");
                }
                //MANEJO DE ERROR POR INGRESAR DATO DISTINTO DE ENTERO
                } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un NUMERO entre 1 y 5\n\n");
                lectura.nextLine(); // Limpiar el buffer del scanner


            }
            }while ( seleccion != 5);
        } 

        }

    


