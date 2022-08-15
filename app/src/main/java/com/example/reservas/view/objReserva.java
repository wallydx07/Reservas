package com.example.reservas.view;

import java.util.List;

public class objReserva {
    String fecha;
    String horaInicio;
    String horaFin;
    String correo;
    String Telefono;
    String Hospedaje;
    String usuario;
    List<objGuia> guialist;
    List<obProductos> circuitolist;
    List<objPersona> Personalist;
    List<objCaballo> Caballolist;

    public objReserva(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getHospedaje() {
        return Hospedaje;
    }

    public void setHospedaje(String hospedaje) {
        Hospedaje = hospedaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<objGuia> getGuialist() {
        return guialist;
    }

    public void setGuialist(List<objGuia> guialist) {
        this.guialist = guialist;
    }

    public List<obProductos> getCircuitolist() {
        return circuitolist;
    }

    public void setCircuitolist(List<obProductos> circuitolist) {
        this.circuitolist = circuitolist;
    }

    public List<objPersona> getPersonalist() {
        return Personalist;
    }

    public void setPersonalist(List<objPersona> personalist) {
        Personalist = personalist;
    }

    public List<objCaballo> getCaballolist() {
        return Caballolist;
    }

    public void setCaballolist(List<objCaballo> caballolist) {
        Caballolist = caballolist;
    }
}
