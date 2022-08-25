package com.example.reservas.view;

import java.io.Serializable;

public class objCaballo implements Serializable {
    String nombre;
    int precio;

    public objCaballo(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
