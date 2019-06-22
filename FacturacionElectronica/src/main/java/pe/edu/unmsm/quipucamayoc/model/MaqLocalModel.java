package pe.edu.unmsm.quipucamayoc.model;

public class MaqLocalModel {
	private Integer idMaqLocal;
	private Integer udId;
	private Integer udIdAdministrativa;	
	private String descLocal;
	private String nombCorto;
	private String direccion;
	private String codigoEstab;
	
	public Integer getIdMaqLocal() {
		return idMaqLocal;
	}
	public void setIdMaqLocal(Integer idMaqLocal) {
		this.idMaqLocal = idMaqLocal;
	}
	public Integer getUdIdAdministrativa() {
		return udIdAdministrativa;
	}
	public void setUdIdAdministrativa(Integer udIdAdministrativa) {
		this.udIdAdministrativa = udIdAdministrativa;
	}
	public Integer getUdId() {
		return udId;
	}
	public void setUdId(Integer udId) {
		this.udId = udId;
	}
	public String getDescLocal() {
		return descLocal;
	}
	public void setDescLocal(String descLocal) {
		this.descLocal = descLocal;
	}
	public String getNombCorto() {
		return nombCorto;
	}
	public void setNombCorto(String nombCorto) {
		this.nombCorto = nombCorto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodigoEstab() {
		return codigoEstab;
	}
	public void setCodigoEstab(String codigoEstab) {
		this.codigoEstab = codigoEstab;
	}
	
		
}
