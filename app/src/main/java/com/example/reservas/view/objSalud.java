package com.example.reservas.view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class objSalud {

    private Long dni;
    private String ayn;
    private String fnac;
    private String edad;
    private String sexo;
    private String domicilio;
    private String celular;
    private String mail;
    private String profesion;
    private String ocupacion;
    private String gsanguineo;
    private String factor;
    private String altura;
    private String peso;
    private String expcab;
    private String expdur;
    private String emerayn;
    private String emerdom;
    private String emerparen;
    private String emertelefono;
    private String datosseguromedico;
    private String afeccionresp;
    private String cualafeccionresp;
    private String anginas;
    private String asma;
    private String otrasrespiratorio;
    private String coag;
    private String hemoragia;
    private String probpres;
    private String otrascirc;
    private String prbgastr;
    private String cualprobgast;
    private String otrosdigestivo;
    private String cardiopatia;
    private String soplo;
    private String tension;
    private String pulso;
    private String medalerg;
    private String cualmed;
    private String lescintura;
    private String cualcintura;
    private String leshombro;
    private String cualhombro;
    private String diabetes;
    private String lescabeza;
    private String cirugia;
    private String cualcigia;
    private String otroalergia;
    private String sufreeenfermedad;
    private String cualenfermedad;
    private String epilepcia;
    private String convulcion;
    private String queprovcomb;
    private String friolenta;
    private String embarazo;
    private String meses;
    private String tratmedico;
    private String cualtratamiento;
    private String fobias;
    private String probmusc;
    private String cualesmusculres;
    private String tomamed;
    private String cualesmedicamentos;


    private String regimen;
    private String alimento;


    public objSalud(Long dni, String ayn, String fnac, String edad, String sexo, String domicilio, String celular, String mail, String profesion, String ocupacion, String gsanguineo, String factor, String altura, String peso, String expcab, String expdur, String emerayn, String emerdom, String emerparen, String emertelefono, String datosseguromedico, String afeccionresp, String cualafeccionresp, String anginas, String asma, String otrasrespiratorio, String coag, String hemoragia, String probpres, String otrascirc, String prbgastr, String cualprobgast, String otrosdigestivo, String cardiopatia, String soplo, String tension, String pulso, String medalerg, String cualmed, String lescintura, String cualcintura, String leshombro, String cualhombro, String diabetes, String lescabeza, String cirugia, String cualcigia, String otroalergia, String sufreenfermedad, String cualenfermedad, String epilepcia, String convulcion, String queprovcomb, String friolenta, String embarazo, String meses, String tratmedico, String cualtratamiento, String fobias, String probmusc, String cualesmusculres, String tomamed, String cualesmedicamentos, String regimen, String alimento) {

        this.dni = dni;
        this.ayn = ayn;
        this.fnac = fnac;
        this.edad = edad;
        this.sexo = sexo;
        this.domicilio = domicilio;
        this.celular = celular;
        this.mail = mail;
        this.profesion = profesion;
        this.ocupacion = ocupacion;
        this.gsanguineo = gsanguineo;
        this.factor = factor;
        this.altura = altura;
        this.peso = peso;
        this.expcab = expcab;
        this.expdur = expdur;
        this.emerayn = emerayn;
        this.emerdom = emerdom;
        this.emerparen = emerparen;
        this.emertelefono = emertelefono;
        this.datosseguromedico = datosseguromedico;
        this.afeccionresp = afeccionresp;
        this.cualafeccionresp = cualafeccionresp;
        this.anginas = anginas;
        this.asma = asma;
        this.otrasrespiratorio = otrasrespiratorio;
        this.coag = coag;
        this.hemoragia = hemoragia;
        this.probpres = probpres;
        this.otrascirc = otrascirc;
        this.prbgastr = prbgastr;
        this.cualprobgast = cualprobgast;
        this.otrosdigestivo = otrosdigestivo;
        this.cardiopatia = cardiopatia;
        this.soplo = soplo;
        this.tension = tension;
        this.pulso = pulso;
        this.medalerg = medalerg;
        this.cualmed = cualmed;
        this.lescintura = lescintura;
        this.cualcintura = cualcintura;
        this.leshombro = leshombro;
        this.cualhombro = cualhombro;
        this.diabetes = diabetes;
        this.lescabeza = lescabeza;
        this.cirugia = cirugia;
        this.cualcigia = cualcigia;
        this.otroalergia = otroalergia;
        this.sufreeenfermedad = sufreenfermedad;
        this.cualenfermedad = cualenfermedad;
        this.epilepcia = epilepcia;
        this.convulcion = convulcion;
        this.queprovcomb = queprovcomb;
        this.friolenta = friolenta;
        this.embarazo = embarazo;
        this.meses = meses;
        this.tratmedico = tratmedico;
        this.cualtratamiento = cualtratamiento;
        this.fobias = fobias;
        this.probmusc = probmusc;
        this.cualesmusculres = cualesmusculres;
        this.tomamed = tomamed;
        this.cualesmedicamentos = cualesmedicamentos;
        this.regimen=regimen;
        this.alimento=alimento;





    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getAyn() {
        return ayn;
    }

    public void setAyn(String ayn) {
        this.ayn = ayn;
    }

    public String getFnac()   {



        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = format1.parse(fnac);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = format2.format(fecha);


        return fechaFormateada;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getGsanguineo() {
        return gsanguineo;
    }

    public void setGsanguineo(String gsanguineo) {
        this.gsanguineo = gsanguineo;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getExpcab() {
        return expcab;
    }

    public void setExpcab(String expcab) {
        this.expcab = expcab;
    }

    public String getExpdur() {
        return expdur;
    }

    public void setExpdur(String expdur) {
        this.expdur = expdur;
    }

    public String getEmerayn() {
        return emerayn;
    }

    public void setEmerayn(String emerayn) {
        this.emerayn = emerayn;
    }

    public String getEmerdom() {
        return emerdom;
    }

    public void setEmerdom(String emerdom) {
        this.emerdom = emerdom;
    }

    public String getEmerparen() {
        return emerparen;
    }

    public void setEmerparen(String emerparen) {
        this.emerparen = emerparen;
    }

    public String getEmertelefono() {
        return emertelefono;
    }

    public void setEmertelefono(String emertelefono) {
        this.emertelefono = emertelefono;
    }

    public String getDatosseguromedico() {
        return datosseguromedico;
    }

    public void setDatosseguromedico(String datosseguromedico) {
        this.datosseguromedico = datosseguromedico;
    }

    public String getAfeccionresp() {
        return afeccionresp;
    }

    public void setAfeccionresp(String afeccionresp) {
        this.afeccionresp = afeccionresp;
    }

    public String getCualafeccionresp() {
        return cualafeccionresp;
    }

    public void setCualafeccionresp(String cualafeccionresp) {
        this.cualafeccionresp = cualafeccionresp;
    }

    public String getAnginas() {
        return anginas;
    }

    public void setAnginas(String anginas) {
        this.anginas = anginas;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getOtrasrespiratorio() {
        return otrasrespiratorio;
    }

    public void setOtrasrespiratorio(String otrasrespiratorio) {
        this.otrasrespiratorio = otrasrespiratorio;
    }

    public String getCoag() {
        return coag;
    }

    public void setCoag(String coag) {
        this.coag = coag;
    }

    public String getHemoragia() {
        return hemoragia;
    }

    public void setHemoragia(String hemoragia) {
        this.hemoragia = hemoragia;
    }

    public String getProbpres() {
        return probpres;
    }

    public void setProbpres(String probpres) {
        this.probpres = probpres;
    }

    public String getOtrascirc() {
        return otrascirc;
    }

    public void setOtrascirc(String otrascirc) {
        this.otrascirc = otrascirc;
    }

    public String getPrbgastr() {
        return prbgastr;
    }

    public void setPrbgastr(String prbgastr) {
        this.prbgastr = prbgastr;
    }

    public String getCualprobgast() {
        return cualprobgast;
    }

    public void setCualprobgast(String cualprobgast) {
        this.cualprobgast = cualprobgast;
    }

    public String getOtrosdigestivo() {
        return otrosdigestivo;
    }

    public void setOtrosdigestivo(String otrosdigestivo) {
        this.otrosdigestivo = otrosdigestivo;
    }

    public String getCardiopatia() {
        return cardiopatia;
    }

    public void setCardiopatia(String cardiopatia) {
        this.cardiopatia = cardiopatia;
    }

    public String getSoplo() {
        return soplo;
    }

    public void setSoplo(String soplo) {
        this.soplo = soplo;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getMedalerg() {
        return medalerg;
    }

    public void setMedalerg(String medalerg) {
        this.medalerg = medalerg;
    }

    public String getCualmed() {
        return cualmed;
    }

    public void setCualmed(String cualmed) {
        this.cualmed = cualmed;
    }

    public String getLescintura() {
        return lescintura;
    }

    public void setLescintura(String lescintura) {
        this.lescintura = lescintura;
    }

    public String getCualcintura() {
        return cualcintura;
    }

    public void setCualcintura(String cualcintura) {
        this.cualcintura = cualcintura;
    }

    public String getLeshombro() {
        return leshombro;
    }

    public void setLeshombro(String leshombro) {
        this.leshombro = leshombro;
    }

    public String getCualhombro() {
        return cualhombro;
    }

    public void setCualhombro(String cualhombro) {
        this.cualhombro = cualhombro;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getLescabeza() {
        return lescabeza;
    }

    public void setLescabeza(String lescabeza) {
        this.lescabeza = lescabeza;
    }

    public String getCirugia() {
        return cirugia;
    }

    public void setCirugia(String cirugia) {
        this.cirugia = cirugia;
    }

    public String getCualcigia() {
        return cualcigia;
    }

    public void setCualcigia(String cualcigia) {
        this.cualcigia = cualcigia;
    }

    public String getOtroalergia() {
        return otroalergia;
    }

    public void setOtroalergia(String otroalergia) {
        this.otroalergia = otroalergia;
    }

    public String getSufreeenfermedad() {
        return sufreeenfermedad;
    }

    public void setSufreeenfermedad(String sufreeenfermedad) {
        this.sufreeenfermedad = sufreeenfermedad;
    }

    public String getCualenfermedad() {
        return cualenfermedad;
    }

    public void setCualenfermedad(String cualenfermedad) {
        this.cualenfermedad = cualenfermedad;
    }

    public String getEpilepcia() {
        return epilepcia;
    }

    public void setEpilepcia(String epilepcia) {
        this.epilepcia = epilepcia;
    }

    public String getConvulcion() {
        return convulcion;
    }

    public void setConvulcion(String convulcion) {
        this.convulcion = convulcion;
    }

    public String getQueprovcomb() {
        return queprovcomb;
    }

    public void setQueprovcomb(String queprovcomb) {
        this.queprovcomb = queprovcomb;
    }

    public String getFriolenta() {
        return friolenta;
    }

    public void setFriolenta(String friolenta) {
        this.friolenta = friolenta;
    }

    public String getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }

    public String getMeses() {
        return meses;
    }

    public void setMeses(String meses) {
        this.meses = meses;
    }

    public String getTratmedico() {
        return tratmedico;
    }

    public void setTratmedico(String tratmedico) {
        this.tratmedico = tratmedico;
    }

    public String getCualtratamiento() {
        return cualtratamiento;
    }

    public void setCualtratamiento(String cualtratamiento) {
        this.cualtratamiento = cualtratamiento;
    }

    public String getFobias() {
        return fobias;
    }

    public void setFobias(String fobias) {
        this.fobias = fobias;
    }

    public String getProbmusc() {
        return probmusc;
    }

    public void setProbmusc(String probmusc) {
        this.probmusc = probmusc;
    }

    public String getCualesmusculres() {
        return cualesmusculres;
    }

    public void setCualesmusculres(String cualesmusculres) {
        this.cualesmusculres = cualesmusculres;
    }

    public String getTomamed() {
        return tomamed;
    }

    public void setTomamed(String tomamed) {
        this.tomamed = tomamed;
    }

    public String getCualesmedicamentos() {
        return cualesmedicamentos;
    }

    public void setCualesmedicamentos(String cualesmedicamentos) {
        this.cualesmedicamentos = cualesmedicamentos;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

}
