package com.example.reservas.view;


import java.io.Serializable;
import java.util.List;

public class objHorario implements Serializable {
    String ID;
    String fecha;
    String horaInicio;
    String horaFin;
    String guia;
    String circuito;
    List<objReserva> Reservalist;


    public objHorario(String fecha, String horaInicio, String horaFin, String guia, String circuito, List<objReserva> reservalist) {
        this.ID = ID;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.guia = guia;
        this.circuito = circuito;
        Reservalist = reservalist;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public List<objReserva> getReservalist() {
        return Reservalist;
    }

    public void setReservalist(List<objReserva> reservalist) {
        Reservalist = reservalist;
    }
}
