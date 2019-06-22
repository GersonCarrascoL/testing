package pe.edu.unmsm.quipucamayoc.model;

public class ClienteRucModel extends ClienteRucModelSunat{

	private String nombre;
	private String apPat;
	private String apMat;

	private String email2;
	private String fechaReg;
		
	private Integer estado;
	private int posicion;
	private String usuCrea;
	
	private String codNivel1; //codigo departamento
	private String codNivel2; //codigo provincia
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApPat() {
		return apPat;
	}
	public void setApPat(String apPat) {
		this.apPat = apPat;
	}
	public String getApMat() {
		return apMat;
	}
	public void setApMat(String apMat) {
		this.apMat = apMat;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getUsuCrea() {
		return usuCrea;
	}
	public void setUsuCrea(String usuCrea) {
		this.usuCrea = usuCrea;
	}
	public String getCodNivel1() {
		return codNivel1;
	}
	public void setCodNivel1(String codNivel1) {
		this.codNivel1 = codNivel1;
	}
	public String getCodNivel2() {
		return codNivel2;
	}
	public void setCodNivel2(String codNivel2) {
		this.codNivel2 = codNivel2;
	}
	

}
