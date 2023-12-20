package org.utn.frbb.model;

import org.utn.frbb.util.GenerarAtributosPjAleatorio;
import org.utn.frbb.util.LogToFile;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Partida {
    private Jugador j1;
    private Jugador j2;
    private String ganador;
    private Jugador ganador_ultima_ronda;
    private int rondas = 3;
    private Ronda ronda;
    private LogToFile log;
    private boolean automatico;

    public boolean isAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    public Partida(LogToFile log) {
        this.log = log;
    }

    public void setJ2(Jugador j2) {
        this.j2 = j2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Jugador getGanador_ultima_ronda() {
        return ganador_ultima_ronda;
    }

    public void setGanador_ultima_ronda(Jugador ganador_ultima_ronda) {
        this.ganador_ultima_ronda = ganador_ultima_ronda;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public void asignar_pjs() throws IOException {
        int count = 1;
        //mientras las lista de los personajes de los jugadores sea menor a 3:
        while (j1.getPersonajes().size() <3 && j2.getPersonajes().size() <3) {



            //Creo personaje
            log.escribirArchivoYMostrarPorConsola("Creando personaje "+ count +" a Jugador 1");

            Personaje pj_random1 = new Personaje();

            if (isAutomatico()){
                pj_random1.setNombre(GenerarAtributosPjAleatorio.generarNombreAleatorio());
            } else {
                log.escribirArchivoYMostrarPorConsola(j1.getNombre() + " ingrese el nombre del personaje " + count);
                Scanner sc = new Scanner(System.in);
                String nombre_pj1 = sc.nextLine();
                pj_random1.setNombre(nombre_pj1);
            }


            //Asigno atributos velocidad; destreza; fuerza; nivel; armadura de manera aleatoria
            Random random = new Random();
            pj_random1.setVelocidad(random.nextInt(10)+1);
            pj_random1.setDestreza(random.nextInt(5)+1);
            pj_random1.setFuerza(random.nextInt(10)+1);
            pj_random1.setNivel(random.nextInt(10)+1);
            pj_random1.setArmadura(random.nextInt(10)+1);
            pj_random1.setRaza(GenerarAtributosPjAleatorio.asignarRazaAleatorio());

            //lo añado a la lista del jugador
            log.escribirArchivoYMostrarPorConsola(pj_random1.getNombre());
            j1.asignar_personaje(pj_random1);
            log.escribirArchivoYMostrarPorConsola("Creando personaje "+ count +" a Jugador 2");

            Personaje pj_random2 =  new Personaje();
            if (isAutomatico()){
                pj_random2.setNombre(GenerarAtributosPjAleatorio.generarNombreAleatorio());
            } else {
                log.escribirArchivoYMostrarPorConsola(j2.getNombre() + " ingrese el nombre del personaje " + count);
                Scanner sc = new Scanner(System.in);
                String nombre_pj2 = sc.nextLine();
                pj_random2.setNombre(nombre_pj2);
            }
            //idm j2
            pj_random2.setVelocidad(random.nextInt(10)+1);
            pj_random2.setDestreza(random.nextInt(5)+1);
            pj_random2.setFuerza(random.nextInt(10)+1);
            pj_random2.setNivel(random.nextInt(10)+1);
            pj_random2.setArmadura(random.nextInt(10)+1);
            pj_random2.setRaza(GenerarAtributosPjAleatorio.asignarRazaAleatorio());
            log.escribirArchivoYMostrarPorConsola(pj_random2.getNombre());
            j2.asignar_personaje(pj_random2);

            count = count + 1;
        }
        }

        public void mostrar_personajes(Jugador j) throws IOException {

            log.escribirArchivoYMostrarPorConsola("Personajes de "+ j.getNombre());
            for (Personaje p: j.getPersonajes()){

                log.escribirArchivoYMostrarPorConsola(p.toString());
            }
        }


    public void iniciar_partida() throws IOException {
        Jugador jugadorPrimeroEnAtacar;
        Jugador jugadorSegundoEnAtacar;

        // Sorteo del jugador que comienza atacando en la primera ronda
        if (new Random().nextBoolean()) {
            jugadorPrimeroEnAtacar = j1;
            jugadorSegundoEnAtacar = j2;
        } else {
            jugadorPrimeroEnAtacar = j2;
            jugadorSegundoEnAtacar = j1;
        }

        int numero_ronda = 1;
        Jugador ganadorRonda = null;

        while (jugadorPrimeroEnAtacar.algunPersonajeVivo() && jugadorSegundoEnAtacar.algunPersonajeVivo()) {
            // Crear una nueva r con los personajes sorteados

            Ronda r = new Ronda(jugadorPrimeroEnAtacar, jugadorSegundoEnAtacar,numero_ronda, log);


            log.escribirArchivoYMostrarPorConsola("\n------------------------" +
                    "\n-------Ronda " + numero_ronda+"---------" +
                    "\n------------------------\n\n");

            if (ganadorRonda != null && numero_ronda >1){

                log.escribirArchivoYMostrarPorConsola("Comienza atacando el jugador " + jugadorPrimeroEnAtacar.getNombre() +" por perder la ronda anterior");
            } else {

                log.escribirArchivoYMostrarPorConsola("El sistema sorteó al jugador " + jugadorPrimeroEnAtacar.getNombre() + " para iniciar la ronda numero "+ numero_ronda);
            }

            r.msj_pj_sorteado();
            r.iniciar_ronda();

            // Determinar el próximo jugador que comenzará a atacar
            ganadorRonda = r.getGanador();

            if (ganadorRonda == null) {
                // Ningun jugador ha perdido un personaje, sortear nuevamente
                jugadorPrimeroEnAtacar = (new Random().nextBoolean()) ? j1 : j2;
            } else {
                // El jugador ganador comienza a atacar en la próxima ronda
                jugadorPrimeroEnAtacar = (ganadorRonda == j1)? j2:j1;
                log.escribirArchivoYMostrarPorConsola("EL jugador " + ganadorRonda.getNombre() + " ha ganado la ronda numero " + numero_ronda + "\n");

            }
            jugadorSegundoEnAtacar = (jugadorPrimeroEnAtacar == j1) ? j2 : j1;
            numero_ronda+=1;
        }

        log.escribirArchivoYMostrarPorConsola("\"------------------FELICITACIONES------------------\n" + "El jugador " + ganadorRonda.getNombre() + " ha ganado la pardida \n\n");
        log.escribirArchivoYMostrarPorConsola("------------------La partida ha terminado.------------------\n\n");
    }


}
