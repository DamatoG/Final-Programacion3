package org.utn.frbb;

import org.utn.frbb.model.Jugador;
import org.utn.frbb.model.Partida;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {
        //INICIO PARTIDA

        Scanner lectura = new Scanner(System.in);

        //Creo objeto partida
        Partida partida = new Partida();
        System.out.println("\n-------------------------------------------------------------" +
                "\n-------BIENVENIDOS AL MEJOR JUEGO DE ROLES DE LA UTN---------" +
                "\n-------------------------------------------------------------\n");

        //INGRESO NOMBRES JUGADORES
        System.out.println("Ingrese el nombre del jugador 1: ");
        Jugador j1 = new Jugador(lectura.next());
        partida.setJ1(j1);
        System.out.println("Ingrese el nombre del jugador 2: ");
        Jugador j2 = new Jugador(lectura.next());
        partida.setJ2(j2);

        System.out.println("\nEl duelo del dia de hoy será entre " + partida.getJ1().getNombre() + " VS " + partida.getJ2().getNombre());



        //GENERAR Y ASIGNAR PERSONAJES A JUGADORES
        System.out.println("\n--------------------------------\n|   SORTEANDO PERSONAJES....   |\n--------------------------------\n");
        partida.asignar_pjs();

        System.out.println("\n\n\n----------PERSONAJES CREADOS----------\n\n\n");
        partida.mostrar_personajes(partida.getJ1());
        partida.mostrar_personajes(partida.getJ2());

        System.out.println("\n--------------------------------\n|   Que comience el juego....   |\n--------------------------------\n");

//        partida.getJ1().getPersonajes().get(1).setVivo(false);
//        partida.getJ1().getPersonajes().get(2).setVivo(false);
//
//        System.out.println("personaje sorteado j1: " + partida.sortear_personajes(j1).toString());
//        System.out.println("personaje sorteado j1: " + partida.sortear_personajes(j1).toString());
//        System.out.println("personaje sorteado j1: " + partida.sortear_personajes(j1).toString());

    }

}
