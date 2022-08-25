package com.example.reservas.view;

import java.io.Serializable;

public class objPersona implements Serializable {
    String nombre;
    String dni;
    String fechaNacimiento;
    String Tipo;

    public objPersona(String nombre, String dni, String fechaN, String tipo) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaN;
        Tipo = tipo;
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
        return fechaNacimiento;
    }

    public void setFechaN(String fechaN) {
        this.fechaNacimiento = fechaNacimiento;
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
