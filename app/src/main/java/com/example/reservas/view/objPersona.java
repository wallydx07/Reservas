package com.example.reservas.view;

import java.io.Serializable;

public class objPersona implements Serializable {
    String nombre;
    String dni;
    String fechaN;
    String Tipo;
    String caballo;
    String obs;

    public objPersona(String nombre, String dni, String fechaN, String tipo, String caballo,String obs) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaN = fechaN;
        this.Tipo = tipo;
        this.caballo=caballo;
        this.obs=obs;
    }



    public String getCaballo() {
        return caballo;
    }

    public void setCaballo(String caballo) {
        this.caballo = caballo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN= fechaN;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
