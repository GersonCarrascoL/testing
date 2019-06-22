package pe.edu.unmsm.quipucamayoc.model;

import java.util.List;


public class DependenciaModel {

	private Integer idUnidad;
	private String codUnidad;
	private String descripcion;
	private String codPadre;
	private String namePadre;
	private Integer nivel;
	private String nEstablecimiento;
	private String nSerie;
	private String numUnidad; //codigo de 3 digitos de la escuela o unidad
	private Integer estadoCodUnidad;
	private Integer estSolCodUnidad; // estado solicitud codigo unidad
	private List<DependenciaModel> unidades;
	
	
	public String getCodPadre() {
		return codPadre;
	}
	public void setCodPadre(String codPadre) {
		this.codPadre = codPadre;
	}
	public String getNamePadre() {
		return namePadre;
	}
	public void setNamePadre(String namePadre) {
		this.namePadre = namePadre;
	}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getnEstablecimiento() {
		return nEstablecimiento;
	}
	public void setnEstablecimiento(String nEstablecimiento) {
		this.nEstablecimiento = nEstablecimiento;
	}
	public String getnSerie() {
		return nSerie;
	}
	public void setnSerie(String nSerie) {
		this.nSerie = nSerie;
	}
	public String getNumUnidad() {
		return numUnidad;
	}
	public void setNumUnidad(String numUnidad) {
		this.numUnidad = numUnidad;
	}
	public Integer getEstadoCodUnidad() {
		return estadoCodUnidad;
	}
	public void setEstadoCodUnidad(Integer estadoCodUnidad) {
		this.estadoCodUnidad = estadoCodUnidad;
	}
	public Integer getEstSolCodUnidad() {
		return estSolCodUnidad;
	}
	public void setEstSolCodUnidad(Integer estSolCodUnidad) {
		this.estSolCodUnidad = estSolCodUnidad;
	}
	public List<DependenciaModel> getUnidades() {
		return unidades;
	}
	public void setUnidades(List<DependenciaModel> unidades) {
		this.unidades = unidades;
	}
		

}
