
package pe.edu.unmsm.quipucamayoc.model;

public class EstadoModel {
	
	private Integer comprobante;
	private String establecimiento;
	private String serie;
	private String descripcion;
	private String observacion;
	private String tipo;
	
	public Integer getComprobante() {
		return comprobante;
	}
	public void setComprobante(Integer comprobante) {
		this.comprobante = comprobante;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "EstadoModel [comprobante=" + comprobante + ", establecimiento=" + establecimiento + ", serie=" + serie
				+ ", descripcion=" + descripcion + ", observacion=" + observacion + ", tipo=" + tipo + "]";
	}

}