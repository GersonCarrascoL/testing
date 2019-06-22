
package pe.edu.unmsm.quipucamayoc.model;

public class EstablecimientoBoletaYFacturaModel {

	private Integer udId;
	private Integer estado;
	private String unidad;
	private String codEst;
	private String ultFact;
	private String ultBole;
	private String direccion;
	private String telefono;
	private String correo;
	private char sunat;

	public Integer getUdId() {
		return udId;
	}

	public void setUdId(Integer udId) {
		this.udId = udId;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getCodEst() {
		return codEst;
	}

	public void setCodEst(String codEst) {
		this.codEst = codEst;
	}

	public String getUltFact() {
		return ultFact;
	}

	public void setUltFact(String ultFact) {
		this.ultFact = ultFact;
	}

	public String getUltBole() {
		return ultBole;
	}

	public void setUltBole(String ultBole) {
		this.ultBole = ultBole;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public char getSunat() {
		return sunat;
	}

	public void setSunat(char sunat) {
		this.sunat = sunat;
	}

}