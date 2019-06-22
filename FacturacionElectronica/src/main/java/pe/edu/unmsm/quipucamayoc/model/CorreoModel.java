package pe.edu.unmsm.quipucamayoc.model;

public class CorreoModel {
	private String correoDestino;
	private String establecimiento;
	private String serie;
	private Integer tipo;
	private String documento;
	
	public String getCorreoDestino() {
		return correoDestino;
	}
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
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
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}
