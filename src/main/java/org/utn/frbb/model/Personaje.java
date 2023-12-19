package org.utn.frbb.model;

import org.utn.frbb.util.GenerarAtributosPjAleatorio;

import java.time.LocalDate;
import java.util.Random;


public class Personaje {

    private String nombre;
    private String apodo;
    private Raza raza;
    private LocalDate fecha_nac;
    private double salud;
    private int velocidad;
    private int destreza;
    private int fuerza;
    private int nivel;
    private int armadura;
    private boolean vivo;

    public Personaje() {
    }

    public Personaje( String nombre) {
        this.nombre = nombre;
        this.vivo=true;
        this.salud = 100.0;
}

    public Personaje(String nombre, String apodo, Raza raza, LocalDate fecha_nac, int velocidad_ataque, int destreza, int fuerza, int nivel, int armadura) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.raza = raza;
        this.fecha_nac = fecha_nac;
        this.salud = 100.0;
        this.velocidad = velocidad_ataque;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.nivel = nivel;
        this.armadura = armadura;
        this.vivo=true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreAleatorio (){
        this.nombre = GenerarAtributosPjAleatorio.generarNombreAleatorio();
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public double getSalud() {
        return salud;
    }

    public void setSalud(double salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int poder_disparo(){
        return this.destreza * this.fuerza * this.nivel;
    }

    public boolean isVivo() {
        this.vivo=(getSalud()>0)?true:false;
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int efectividad_disparo (){
        Random r = new Random();
        int ed = r.nextInt(100);
        return ed;
    }

    public int valor_ataque(){
        return poder_disparo() * efectividad_disparo();
    }

    public int poderDefensa(){
        return this.armadura * this.velocidad;
    }

    public void actualizarSalud(double daño_recibido){
        setSalud(getSalud()-daño_recibido);
    }

    public double dañoProvocado(int poder_defensa) {
        double daño;

        if (this.raza.equals("humano")) {
            //daño = 1.0;
            daño = 0.1;
        } else if (this.raza.equals("elfo")) {
            //daño = 1.05;
            daño = 0.15;
        } else {
            //daño = 1.1;
            daño = 0.2;
        }

        return ((((valor_ataque()*efectividad_disparo())-poder_defensa)/500)*daño);
    }

    @Override
    public String toString() {
        return  "Nombre: '" + nombre + '\'' +
                ", \nApodo: '" + apodo + '\'' +
                ", \nRaza: " + raza +
                ", \nFecha_nac: " + fecha_nac +
                ", \nSalud: " + salud +
                ", \nVelocidad: " + velocidad +
                ", \nDestreza: " + destreza +
                ", \nFuerza: " + fuerza +
                ", \nNivel: " + nivel +
                ", \nArmadura: " + armadura +
                ", \nVivo: " + vivo +
                "\n";
    }
}
