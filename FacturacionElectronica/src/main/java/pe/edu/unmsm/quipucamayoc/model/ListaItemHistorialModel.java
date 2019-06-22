package pe.edu.unmsm.quipucamayoc.model;

public class ListaItemHistorialModel {

	private Integer idHist;
	private Integer udId;
	private String codItem;
	private Double precio;
	private Integer moneda;
	private String monedaSimb;
	private String fecha;
	private String usuario;
	private String unidadMedidaCod;
	private String numResolucion;
	private String nameTipoResolucion;
		
	public String getNumResolucion() {
		return numResolucion;
	}
	public void setNumResolucion(String numResolucion) {
		this.numResolucion = numResolucion;
	}
	public String getNameTipoResolucion() {
		return nameTipoResolucion;
	}
	public void setNameTipoResolucion(String nameTipoResolucion) {
		this.nameTipoResolucion = nameTipoResolucion;
	}
	public Integer getIdHist() {
		return idHist;
	}
	public void setIdHist(Integer idHist) {
		this.idHist = idHist;
	}
	public Integer getUdId() {
		return udId;
	}
	public void setUdId(Integer udId) {
		this.udId = udId;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getMoneda() {
		return moneda;
	}
	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}
	public String getMonedaSimb() {
		return monedaSimb;
	}
	public void setMonedaSimb(String monedaSimb) {
		this.monedaSimb = monedaSimb;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUnidadMedidaCod() {
		return unidadMedidaCod;
	}
	public void setUnidadMedidaCod(String unidadMedidaCod) {
		this.unidadMedidaCod = unidadMedidaCod;
	}
	
	
}
