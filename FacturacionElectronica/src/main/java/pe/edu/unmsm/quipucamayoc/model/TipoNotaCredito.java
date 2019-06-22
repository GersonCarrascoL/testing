package pe.edu.unmsm.quipucamayoc.model;

public class TipoNotaCredito {
	private String idTipo;
	private String descripcion;
	private char boleta;
	private char factura;
	private char est;
	private String detalle;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public char getBoleta() {
		return boleta;
	}
	public void setBoleta(char boleta) {
		this.boleta = boleta;
	}
	public char getFactura() {
		return factura;
	}
	public void setFactura(char factura) {
		this.factura = factura;
	}
	public char getEst() {
		return est;
	}
	public void setEst(char est) {
		this.est = est;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}
	
}
