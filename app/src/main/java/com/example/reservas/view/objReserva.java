package com.example.reservas.view;

import java.io.Serializable;
import java.util.List;

public class objReserva implements Serializable {
    private String ID;
    private String correo;
    private String telefono;
    private  String hospedaje;
    private String usuario;
    private String pendiente;
    private String deposito;
    private String procedencia;
    private String total;
     List<objPersona> Personalist;


    public objReserva(String correo, String telefono, String hospedaje, String usuario, List<objPersona> personalist,String pendiente,String deposito, String procedencia) {
        this.correo = correo;
        this.telefono = telefono;
        this.hospedaje = hospedaje;
        this.usuario = usuario;
        this.deposito=deposito;
        this.pendiente=pendiente;
        this.Personalist = personalist;
        this.procedencia=procedencia;
    }

    public objReserva() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<objPersona> getPersonalist() {
        return Personalist;
    }

    public void setPersonalist(List<objPersona> personalist) {
        Personalist = personalist;
    }


}