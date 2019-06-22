
package pe.edu.unmsm.quipucamayoc.model;

public class DetallePModel {
	
	private Double cantidad;
	private String descripcion;
	private Double precioU;
	private Double precioT;
	private Integer tipo;
	private String moneda;
	private Double igvU;
	private Double igvT;
	private Integer codTipoIgv;

	
	
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Integer getCodTipoIgv() {
		return codTipoIgv;
	}
	public void setCodTipoIgv(Integer codTipoIgv) {
		this.codTipoIgv = codTipoIgv;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Double getPrecioU() {
		return precioU;
	}
	public void setPrecioU(Double precioU) {
		this.precioU = precioU;
	}
	public Double getPrecioT() {
		return precioT;
	}
	public void setPrecioT(Double precioT) {
		this.precioT = precioT;
	}
	public Double getIgvU() {
		return igvU;
	}
	public void setIgvU(Double igvU) {
		this.igvU = igvU;
	}
	public Double getIgvT() {
		return igvT;
	}
	public void setIgvT(Double igvT) {
		this.igvT = igvT;
	}

}