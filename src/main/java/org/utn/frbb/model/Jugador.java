package org.utn.frbb.model;

import java.util.ArrayList;

public class Jugador {

    private ArrayList<Personaje> personajes = new ArrayList<>();
    private String nombre;
    private int partidas_ganadas;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidas_ganadas() {
        return partidas_ganadas;
    }

    public void setPartidas_ganadas(int partidas_ganadas) {
        this.partidas_ganadas = partidas_ganadas;
    }

    public void asignar_personaje(Personaje pj){
        personajes.add(pj);
    };

    public boolean algunPersonajeVivo() {
        for (Personaje personaje : this.getPersonajes()) {
            if (personaje.isVivo()) {
                return true;
            }
        }
        return false;
    }


}
