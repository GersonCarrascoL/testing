package pe.edu.unmsm.quipucamayoc.model;

public class ReniecRELModel {
	private String codError;
	private String apPrimer;
	private String apSegundo;
	private String apCazada;
	private String nombres;
	private String estadoCivil;
	private String genero;
	private String codDepartamentoNac;
	private String codProvinciaNac;
	private String codDistritoNac;
	private String nombDepartamentoNac;
	private String nombProvinciaNac;
	private String nombDistritoNac;
	private String fechaNacimiento;
	private String fechaCaducidad;
	private String numDni;
	private Integer status; // 1: operacion correcta,  0: no se encuentra el dni  -1: servicio caido o falla,  -2: dni formato invalido, -3: usuario o constraseña invalida
	   // -4: dni cancelado en RUIPN,  -5: dni restringido en RUIPN,  -6: dni observado en RUIPN
	private String message;
	
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public String getApPrimer() {
		return apPrimer;
	}
	public void setApPrimer(String apPrimer) {
		this.apPrimer = apPrimer;
	}
	public String getApSegundo() {
		return apSegundo;
	}
	public void setApSegundo(String apSegundo) {
		this.apSegundo = apSegundo;
	}
	public String getApCazada() {
		return apCazada;
	}
	public void setApCazada(String apCazada) {
		this.apCazada = apCazada;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getCodDepartamentoNac() {
		return codDepartamentoNac;
	}
	public void setCodDepartamentoNac(String codDepartamentoNac) {
		this.codDepartamentoNac = codDepartamentoNac;
	}
	public String getCodProvinciaNac() {
		return codProvinciaNac;
	}
	public void setCodProvinciaNac(String codProvinciaNac) {
		this.codProvinciaNac = codProvinciaNac;
	}
	public String getCodDistritoNac() {
		return codDistritoNac;
	}
	public void setCodDistritoNac(String codDistritoNac) {
		this.codDistritoNac = codDistritoNac;
	}
	public String getNombDepartamentoNac() {
		return nombDepartamentoNac;
	}
	public void setNombDepartamentoNac(String nombDepartamentoNac) {
		this.nombDepartamentoNac = nombDepartamentoNac;
	}
	public String getNombProvinciaNac() {
		return nombProvinciaNac;
	}
	public void setNombProvinciaNac(String nombProvinciaNac) {
		this.nombProvinciaNac = nombProvinciaNac;
	}
	public String getNombDistritoNac() {
		return nombDistritoNac;
	}
	public void setNombDistritoNac(String nombDistritoNac) {
		this.nombDistritoNac = nombDistritoNac;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public String getNumDni() {
		return numDni;
	}
	public void setNumDni(String numDni) {
		this.numDni = numDni;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
