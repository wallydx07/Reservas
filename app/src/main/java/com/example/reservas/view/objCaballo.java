package com.example.reservas.view;

import java.io.Serializable;

public class objCaballo implements Serializable {
    String nombre;
    String precio;

    public objCaballo(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
