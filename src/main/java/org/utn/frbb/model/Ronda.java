package org.utn.frbb.model;

public class Ronda {
    int nro_ronda = 1;
    Personaje pj_j1;
    Personaje pj_j2;
    Jugador ganador_ronda;
    int rondas_ataque = 7;

    public int getNro_ronda() {
        return nro_ronda;
    }

    public void setNro_ronda(int nro_ronda) {
        this.nro_ronda = nro_ronda;
    }

    public Personaje getPj_j1() {
        return pj_j1;
    }

    public void setPj_j1(Personaje pj_j1) {
        this.pj_j1 = pj_j1;
    }

    public Personaje getPj_j2() {
        return pj_j2;
    }

    public void setPj_j2(Personaje pj_j2) {
        this.pj_j2 = pj_j2;
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
}
