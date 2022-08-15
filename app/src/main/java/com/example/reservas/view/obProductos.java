package com.example.reservas.view;

public class obProductos {
    String Nombre;
    int Precio;
    String tipo;

    public obProductos(String nombre, int precio, String tipo) {
        Nombre = nombre;
        Precio = precio;
        this.tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
