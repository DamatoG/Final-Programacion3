package org.utn.frbb.model;

import org.utn.frbb.util.LogToFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Ronda {
    private int nro_ronda = 1;
    private Jugador jugadorPrimero;
    private Jugador jugadorSegundo;
    private Personaje pj_primero;
    private Personaje pj_segundo;
    private Jugador ganador_ronda;
    int rondas_ataque = 7;
    LogToFile log;

    public Ronda(Jugador jugadorPrimero, Jugador jugadorSegundo, int nro_ronda, LogToFile log) {
        this.jugadorPrimero = jugadorPrimero;
        this.jugadorSegundo = jugadorSegundo;
        this.pj_primero = sortear_personajes_vivos(jugadorPrimero);
        this.pj_segundo = sortear_personajes_vivos(jugadorSegundo);
        this.log = log;
    }




    public int getNro_ronda() {
        return nro_ronda;
    }

    public Personaje getPj_primero() {
        return pj_primero;
    }

    public void setPj_primero(Personaje pj_primero) {
        this.pj_primero = pj_primero;
    }

    public Personaje getPj_segundo() {
        return pj_segundo;
    }

    public void setPj_segundo(Personaje pj_segundo) {
        this.pj_segundo = pj_segundo;
    }

    public Jugador getGanador_ronda() {
        return ganador_ronda;
    }

    public void setGanador_ronda(Jugador ganador_ronda) {
        this.ganador_ronda = ganador_ronda;
    }

    public int getRondas_ataque() {
        return rondas_ataque;
    }

    public void setRondas_ataque(int rondas_ataque) {
        this.rondas_ataque = rondas_ataque;
    }


    public Personaje sortear_personajes_vivos(Jugador j){
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

    public void msj_pj_sorteado() throws IOException {
        log.escribirArchivoYMostrarPorConsola("El personaje sorteado de " + jugadorPrimero.getNombre() + " es " + pj_primero.toString());
        //System.out.println("El personaje sorteado de " + jugadorPrimero.getNombre() + " es " + pj_primero.toString());
        //System.out.println("El personaje sorteado de " + jugadorSegundo.getNombre() + " es " + pj_segundo.toString());
        log.escribirArchivoYMostrarPorConsola("El personaje sorteado de " + jugadorSegundo.getNombre() + " es " + pj_segundo.toString());
    }

    public void iniciar_ronda() throws IOException {

        //mientras el numero de ronda sea mayor a 1 (comienza en 7) y los dos personajes esten vivos se ejecuta la pelea
            while (rondas_ataque >= 1 && ambosPersonajesEstanVivos()) {

                ataque(pj_primero, pj_segundo);

                //si el segundo personaje esta muerto termina la ronda
                if (!pj_segundo.isVivo()) {
                    pj_primero.setVivo(false);
                   //System.out.println(pj_segundo.getNombre() + " ha muerto.");
                    log.escribirArchivoYMostrarPorConsola(pj_segundo.getNombre() + " ha muerto.");
                    this.ganador_ronda = jugadorPrimero;
                    } //si el segundo personaje esta vivo ataca:
                else {
                    ataque(pj_segundo, pj_primero);

                    //si el primer personaje esta muerto termina la ronda
                    if (!pj_primero.isVivo()) {
                        pj_primero.setVivo(false);
                        //System.out.println(pj_primero.getNombre() + " ha muerto.");
                        log.escribirArchivoYMostrarPorConsola(pj_primero.getNombre() + " ha muerto.");
                        this.ganador_ronda = jugadorSegundo;
                    }
                }
                rondas_ataque = rondas_ataque - 1;
            }
    }

    public boolean ambosPersonajesEstanVivos(){
        return pj_primero.isVivo() && pj_segundo.isVivo();
    }



    public Jugador getGanador() {
        if (!pj_primero.isVivo() && pj_segundo.isVivo()) {
            return jugadorSegundo;
        } else if (pj_primero.isVivo() && !pj_segundo.isVivo()) {
            return jugadorPrimero;
        }
        return null;  // Ambos jugadores han perdido un personaje
    }


    //Dado dos personajes (atacante y defensor), se ataca y se muestra el mensaje de resultado
    public void ataque(Personaje atacante, Personaje defensor) throws IOException {
        double ataqueAtaacnte = atacante.dañoProvocado(defensor.poderDefensa());
        defensor.actualizarSalud(ataqueAtaacnte);

//        System.out.println(atacante.getNombre() + " ataca a " + defensor.getNombre() + " y le quita " + ataqueAtaacnte + ". " +
//                defensor.getNombre() + " queda con " + defensor.getSalud() + " de salud");

        log.escribirArchivoYMostrarPorConsola(atacante.getNombre() + " ataca a " + defensor.getNombre() + " y le quita " + ataqueAtaacnte + ". " +
                defensor.getNombre() + " queda con " + defensor.getSalud() + " de salud");

    }
}

