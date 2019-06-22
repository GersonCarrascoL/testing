package pe.edu.unmsm.quipucamayoc.model;

public class RolModel {
	private Integer id_hist;
	private Integer ud_id;
	private String nombres;
	private String apellidos;
	private String email;
	private String perfil;
	private Integer codPerfil;
	private Integer tipoDoc;
	private String dni;
	private Integer estado;
	private Integer status;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Integer getCodPerfil() {
		return codPerfil;
	}
	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUd_id() {
		return ud_id;
	}
	public void setUd_id(Integer ud_id) {
		this.ud_id = ud_id;
	}
	public Integer getId_hist() {
		return id_hist;
	}
	public void setId_hist(Integer id_hist) {
		this.id_hist = id_hist;
	}
	public Integer getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	
	
}
