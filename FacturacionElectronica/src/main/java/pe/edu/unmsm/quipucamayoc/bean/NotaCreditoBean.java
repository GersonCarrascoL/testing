package pe.edu.unmsm.quipucamayoc.bean;

import java.util.List;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;
import pe.edu.unmsm.quipucamayoc.model.TipoNotaCredito;

public class NotaCreditoBean {
	
	private String urlFacturador;
	private List<ComprobanteUsuarioModel> unidad;
	private List<TipoImpuestoModel> igv;
	private List<TipoNotaCredito> tipoNotaCredito;
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
	public List<TipoNotaCredito> getTipoNotaCredito() {
		return tipoNotaCredito;
	}
	public void setTipoNotaCredito(List<TipoNotaCredito> tipoNotaCredito) {
		this.tipoNotaCredito = tipoNotaCredito;
	}
		
}
