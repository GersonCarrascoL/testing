package pe.edu.unmsm.quipucamayoc.model;

public class MaquinaRegistradoraModel {
	private Integer codigoFacultad;
	private String codigoEstablecimiento;
	private String serieFabricacion;
	private String marca;
	private String modelo;
	private String numeroAutorizacion;
	//fecha de Registro
	private Integer idMaquinaRegistradora;
	//fecha de Modificacion
	private Integer idMaqLocal;
	private String nombCortoLocal;
	private String direccion;
	private Integer status;
	private String fechaSistema;
	
	
	public Integer getCodigoFacultad() {
		return codigoFacultad;
	}
	public void setCodigoFacultad(Integer codigoFacultad) {
		this.codigoFacultad = codigoFacultad;
	}
	public String getCodigoEstablecimiento() {
		return codigoEstablecimiento;
	}
	public void setCodigoEstablecimiento(String codigoEstablecimiento) {
		this.codigoEstablecimiento = codigoEstablecimiento;
	}
	public String getSerieFabricacion() {
		return serieFabricacion;
	}
	public void setSerieFabricacion(String serieFabricacion) {
		this.serieFabricacion = serieFabricacion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}
	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}
	public Integer getIdMaquinaRegistradora() {
		return idMaquinaRegistradora;
	}
	public void setIdMaquinaRegistradora(Integer idMaquinaRegistradora) {
		this.idMaquinaRegistradora = idMaquinaRegistradora;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIdMaqLocal() {
		return idMaqLocal;
	}
	public void setIdMaqLocal(Integer idMaqLocal) {
		this.idMaqLocal = idMaqLocal;
	}
	public String getFechaSistema() {
		return fechaSistema;
	}
	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}
	public String getNombCortoLocal() {
		return nombCortoLocal;
	}
	public void setNombCortoLocal(String nombCortoLocal) {
		this.nombCortoLocal = nombCortoLocal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
