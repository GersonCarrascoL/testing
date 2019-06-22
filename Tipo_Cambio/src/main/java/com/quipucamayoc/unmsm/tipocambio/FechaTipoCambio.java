package com.quipucamayoc.unmsm.tipocambio;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FechaTipoCambio {

    private int dia, mes, anio;
    private SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    private String fecha = null;

    public FechaTipoCambio() {

    }

    public FechaTipoCambio(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String toString() {
        return getDia() + "/" + getMes() + "/" + getAnio();
    }

    public String toDate() {
        try {
            fecha = fechaFormato.format(fechaFormato.parse(toString()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fecha;
    }
}
