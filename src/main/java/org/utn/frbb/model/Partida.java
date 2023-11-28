package org.utn.frbb.model;

import org.utn.frbb.util.GenerarAtributosPjAleatorio;

import java.util.ArrayList;
import java.util.Random;

public class Partida {
    Jugador j1;
    Jugador j2;
    String ganador;
    Jugador ganador_ultima_ronda;
    int rondas = 3;
    Ronda ronda;

    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public Jugador getJ2() {
        return j2;
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

    public void asignar_pjs(){
        int count = 1;
        //mientras las lista de los personajes de los jugadores sea menor a 3:
        while (j1.getPersonajes().size() <3 && j2.getPersonajes().size() <3) {

            //Creo personaje
            System.out.println("Creando personaje "+ count +" a Jugador 1");

            Personaje pj_random1 = new Personaje(GenerarAtributosPjAleatorio.generarNombreAleatorio());
            //Asigno atributos velocidad; destreza; fuerza; nivel; armadura de manera aleatoria
            Random random = new Random();
            pj_random1.setVelocidad(random.nextInt(10));
            pj_random1.setDestreza(random.nextInt(5));
            pj_random1.setFuerza(random.nextInt(10));
            pj_random1.setNivel(random.nextInt(10));
            pj_random1.setArmadura(random.nextInt(10));
            pj_random1.setRaza(GenerarAtributosPjAleatorio.asignarRazaAleatorio());

            //lo añado a la lista del jugador
            System.out.println(pj_random1.getNombre());
            j1.asignar_personaje(pj_random1);


            System.out.println("Creando personaje "+ count +" a Jugador 2");
            //idm j2
            Personaje pj_random2 =  new Personaje(GenerarAtributosPjAleatorio.generarNombreAleatorio());
            pj_random2.setVelocidad(random.nextInt(10));
            pj_random2.setDestreza(random.nextInt(5));
            pj_random2.setFuerza(random.nextInt(10));
            pj_random2.setNivel(random.nextInt(10));
            pj_random2.setArmadura(random.nextInt(10));
            pj_random2.setRaza(GenerarAtributosPjAleatorio.asignarRazaAleatorio());
            System.out.println(pj_random2.getNombre());
            j2.asignar_personaje(pj_random2);

            count = count + 1;
        }
        }

        public void mostrar_personajes(Jugador j){
            System.out.println("Personajes de "+ j.getNombre());
            for (Personaje p: j.getPersonajes()){
                System.out.println(p.toString());

            }
        }

        public Personaje sortear_personajes(Jugador j){
            ArrayList<Personaje> personajes_vivos = new ArrayList<>();

            for(Personaje pj: j.getPersonajes()){
                if(pj.isVivo()){
                    personajes_vivos.add(pj);
                }
            }

            Random random = new Random();
            int indice = random.nextInt(personajes_vivos.size());
            return personajes_vivos.get(indice);
        }


}
