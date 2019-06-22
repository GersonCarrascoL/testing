package pe.edu.unmsm.quipucamayoc.model;

public class TipoConceptoPagoModel {
	
	private Integer idTipoCpago;
    private String codTipoCpago;
    private String nombre;
    private String descr;
	private Integer est;
	public Integer getIdTipoCpago() {
		return idTipoCpago;
	}
	public void setIdTipoCpago(Integer idTipoCpago) {
		this.idTipoCpago = idTipoCpago;
	}
	public String getCodTipoCpago() {
		return codTipoCpago;
	}
	public void setCodTipoCpago(String codTipoCpago) {
		this.codTipoCpago = codTipoCpago;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getEst() {
		return est;
	}
	public void setEst(Integer est) {
		this.est = est;
	}
	
}
