
package pe.edu.unmsm.quipucamayoc.model;

public class ComprobanteUsuarioModel {

	private Integer idUnidad;
	private String nEstablecimiento;
	private String ultBole;
	private String ultFact;
	private String ultTicket;
	private String codUnidad;
	private String descripcion;
	private String usuario;
	private String facultad;
	private char sunat;
	
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}
	public String getCodUnidad() {
		return codUnidad;
	}
	public void setCodUnidad(String codUnidad) {
		this.codUnidad = codUnidad;
	}
	public String getnEstablecimiento() {
		return nEstablecimiento;
	}
	public void setnEstablecimiento(String nEstablecimiento) {
		this.nEstablecimiento = nEstablecimiento;
	}
	public String getUltBole() {
		return ultBole;
	}
	public void setUltBole(String ultBole) {
		this.ultBole = ultBole;
	}
	public String getUltFact() {
		return ultFact;
	}
	public void setUltFact(String ultFact) {
		this.ultFact = ultFact;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public char getSunat() {
		return sunat;
	}
	public void setSunat(char sunat) {
		this.sunat = sunat;
	}
	public String getUltTicket() {
		return ultTicket;
	}
	public void setUltTicket(String ultTicket) {
		this.ultTicket = ultTicket;
	}
	
}
