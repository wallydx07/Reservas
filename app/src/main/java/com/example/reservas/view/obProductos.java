package com.example.reservas.view;

import java.io.Serializable;

public class obProductos implements Serializable {
    String Nombre;
    String Precio;
    String tipo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public obProductos(String nombre, String precio, String tipo) {
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

    public String getPrecio() {

        return Precio;
    }

    public void setPrecio(String precio) {
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
