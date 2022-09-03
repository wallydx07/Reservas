package com.example.reservas.view;

import java.io.Serializable;
import java.util.List;

public class objReserva implements Serializable {
    String ID;
    String fecha;
    String horaInicio;
    String horaFin;
    String correo;
    String telefono;
    String hospedaje;
    String usuario;
    String guia;
    String circuito;
    String UrlBuenaSalud;
    String UrlDNI;
    String pendiente;
    String deposito;
    List<objPersona> Personalist;
    List<obProductos> Caballolist;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUrlBuenaSalud() {
        return UrlBuenaSalud;
    }

    public void setUrlBuenaSalud(String urlBuenaSalud) {
        UrlBuenaSalud = urlBuenaSalud;
    }

    public String getUrlDNI() {
        return UrlDNI;
    }

    public void setUrlDNI(String urlDNI) {
        UrlDNI = urlDNI;
    }

    public objReserva(String fecha, String horaInicio, String horaFin, String correo, String telefono, String hospedaje, String usuario, String guia, String circuito, List<objPersona> personalist, List<obProductos> caballolist,String pendiente,String deposito) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.correo = correo;
        this.telefono = telefono;
        this.hospedaje = hospedaje;
        this.usuario = usuario;
        this.guia = guia;
        this.deposito=deposito;
        this.pendiente=pendiente;
        this.circuito = circuito;
        Personalist = personalist;
        Caballolist = caballolist;
    }

    public objReserva() {

    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String Deposito) {
        deposito = Deposito;
    }

    public String nombreTitular() {
        objPersona titular = Personalist.get(0);
        String nombrexd = titular.getNombre();
        return nombrexd;

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
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHospedaje() {
        return hospedaje;
    }

    public void setHospedaje(String hospedaje) {
        this.hospedaje = hospedaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public List<objPersona> getPersonalist() {
        return Personalist;
    }

    public void setPersonalist(List<objPersona> personalist) {
        Personalist = personalist;
    }

    public List<obProductos> getCaballolist() {
        return Caballolist;
    }

    public void setCaballolist(List<obProductos> caballolist) {
        Caballolist = caballolist;
    }
}