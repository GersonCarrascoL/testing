package pe.edu.unmsm.quipucamayoc.model;

public class DetalleNotaCredito {
	private Integer idDetalleAsociado;
	private Double cantidad;
	private Double precioUnitario;
	private String descripcion;
	
	public Integer getIdDetalleAsociado() {
		return idDetalleAsociado;
	}
	public void setIdDetalleAsociado(Integer idDetalleAsociado) {
		this.idDetalleAsociado = idDetalleAsociado;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
}
