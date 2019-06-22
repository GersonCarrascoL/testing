package com.quipucamayoc.unmsm.tipocambio;

public class TipoCambio {

    private FechaTipoCambio fecha;
    private double tipoCompra, tipoVenta;

    public TipoCambio() {

    }

    public TipoCambio(FechaTipoCambio fecha, double tipoCompra, double tipoVenta) {
        this.fecha = fecha;
        this.tipoCompra = tipoCompra;
        this.tipoVenta = tipoVenta;
    }

    public FechaTipoCambio getFecha() {
        return fecha;
    }

    public void setFecha(FechaTipoCambio fecha) {
        this.fecha = fecha;
    }

    public double getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(double tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public double getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(double tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
}
