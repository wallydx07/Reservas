package com.example.reservas.view;

public class objProducto {
    String Nombre;
    String Tipo;
    String Precio;
    boolean Disponible;

    public objProducto(String nombre, String tipo, String precio, boolean disponible) {
        Nombre = nombre;
        Tipo = tipo;
        Precio=precio;
        Disponible = disponible;
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
