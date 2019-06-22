package pe.edu.unmsm.quipucamayoc.bean;

import java.util.List;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.MonedaModel;
import pe.edu.unmsm.quipucamayoc.model.PrecioCambioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;

public class ComprobanteBean {
	private String urlFacturador;
	private List<ComprobanteUsuarioModel> unidad;
	private List<TipoImpuestoModel> igv;
	private List<MonedaModel> moneda;
	private PrecioCambioModel precioCambio;
	public String getUrlFacturador() {
		return urlFacturador;
	}
	public void setUrlFacturador(String urlFacturador) {
		this.urlFacturador = urlFacturador;
	}
	public List<ComprobanteUsuarioModel> getUnidad() {
		return unidad;
	}
	public void setUnidad(List<ComprobanteUsuarioModel> unidad) {
		this.unidad = unidad;
	}
	public List<TipoImpuestoModel> getIgv() {
		return igv;
	}
	public void setIgv(List<TipoImpuestoModel> igv) {
		this.igv = igv;
	}
	public List<MonedaModel> getMoneda() {
		return moneda;
	}
	public void setMoneda(List<MonedaModel> moneda) {
		this.moneda = moneda;
	}
	public PrecioCambioModel getPrecioCambio() {
		return precioCambio;
	}
	public void setPrecioCambio(PrecioCambioModel precioCambio) {
		this.precioCambio = precioCambio;
	}
		
}
