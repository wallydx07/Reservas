package com.example.reservas.view;

public class objPersona {
    String nombre;
    String dni;
    String fechaN;
    String Tipo;

    public objPersona(String nombre, String dni, String fechaN, String tipo) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaN = fechaN;
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
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
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
