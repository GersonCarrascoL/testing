package pe.edu.unmsm.quipucamayoc.model;


public class ClienteRucModelSunat{
	
	private String ruc;
	private String razonSocial;
	private String direccion;
	private String nombreComercial;
	private String email;
	private String telf;
	private String fechaNacimiento;
	private String sexo;
	private String sexoDescr;
	private String tipoPersona; // 01:Persona Natural  02: Persona Juridica
	private String tipoPersonaDesc;
	private String codUbigeo;
	private String nombreUbigeo; // nombre distrito
	private String nombDepartamento;
	private String nombProvincia;	
	private String estadoSunat; // 1: ACTIVO, 0: (BAJA DEFINITIVA, BAJA PROV. DE OFICIO, SUSPENSION TEMPORAL)
	private String estadoSunatDescr;  // ACTIVO, BAJA DEFINITIVA, BAJA PROVISIONAL DE OFICIO, SUSPENSION TEMPORAL
	private String condicionSunat;  // 1: HABIDO, 0: (NO HALLADO, NO HABIDO, PENDIENTE)
	private String condicionSunatDescr; // HABIDO, NO HALLADO, NO HABIDO, PENDIENTE
	//para comprobar errores en bd o errores en el servicio consulta ruc sunat
	private Integer status; //1: activo y habido,  2: no activo o no habido,  0: no se encuentra el ruc, -1: servicio caido o falla
	
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelf() {
		return telf;
	}
	public void setTelf(String telf) {
		this.telf = telf;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSexoDescr() {
		return sexoDescr;
	}
	public void setSexoDescr(String sexoDescr) {
		this.sexoDescr = sexoDescr;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getTipoPersonaDesc() {
		return tipoPersonaDesc;
	}
	public void setTipoPersonaDesc(String tipoPersonaDesc) {
		this.tipoPersonaDesc = tipoPersonaDesc;
	}
	public String getCodUbigeo() {
		return codUbigeo;
	}
	public void setCodUbigeo(String codUbigeo) {
		this.codUbigeo = codUbigeo;
	}
	public String getNombreUbigeo() {
		return nombreUbigeo;
	}
	public void setNombreUbigeo(String nombreUbigeo) {
		this.nombreUbigeo = nombreUbigeo;
	}
	public String getNombDepartamento() {
		return nombDepartamento;
	}
	public void setNombDepartamento(String nombDepartamento) {
		this.nombDepartamento = nombDepartamento;
	}
	public String getNombProvincia() {
		return nombProvincia;
	}
	public void setNombProvincia(String nombProvincia) {
		this.nombProvincia = nombProvincia;
	}
	public String getEstadoSunat() {
		return estadoSunat;
	}
	public void setEstadoSunat(String estadoSunat) {
		this.estadoSunat = estadoSunat;
	}
	public String getEstadoSunatDescr() {
		return estadoSunatDescr;
	}
	public void setEstadoSunatDescr(String estadoSunatDescr) {
		this.estadoSunatDescr = estadoSunatDescr;
	}
	public String getCondicionSunat() {
		return condicionSunat;
	}
	public void setCondicionSunat(String condicionSunat) {
		this.condicionSunat = condicionSunat;
	}
	public String getCondicionSunatDescr() {
		return condicionSunatDescr;
	}
	public void setCondicionSunatDescr(String condicionSunatDescr) {
		this.condicionSunatDescr = condicionSunatDescr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
		

}
