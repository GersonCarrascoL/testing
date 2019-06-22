package pe.edu.unmsm.quipucamayoc.model;

public class ServidorModel {
	
	private String codigo;
	private String numDoc;
	private String codAnt;
	private String abvEst;
	private String abvTipSer;
	private String paterno;
	private String materno;
	private String nombre;
	private String tipoServicio;
	private String cesantia; //dependencia o unidad en la q labora
	private String domicilio;
	private String estado;
	private Integer estadoTrabaActual;
	private Integer tipoDoc;
	private String nombreDoc;
	
	
	public String getAbvEst() {
		return abvEst;
	}
	public void setAbvEst(String abvEst) {
		this.abvEst = abvEst;
	}
	public String getAbvTipSer() {
		return abvTipSer;
	}
	public void setAbvTipSer(String abvTipSer) {
		this.abvTipSer = abvTipSer;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getCodAnt() {
		return codAnt;
	}
	public void setCodAnt(String codAnt) {
		this.codAnt = codAnt;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public String getCesantia() {
		return cesantia;
	}
	public void setCesantia(String cesantia) {
		this.cesantia = cesantia;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getEstadoTrabaActual() {
		return estadoTrabaActual;
	}
	public void setEstadoTrabaActual(Integer estadoTrabaActual) {
		this.estadoTrabaActual = estadoTrabaActual;
	}
	public Integer getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNombreDoc() {
		return nombreDoc;
	}
	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}
	
	
}