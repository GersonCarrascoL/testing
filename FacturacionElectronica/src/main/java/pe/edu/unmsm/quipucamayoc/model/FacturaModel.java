
package pe.edu.unmsm.quipucamayoc.model;

import java.util.List;

public class FacturaModel extends ComprobanteP1Model {

	private String emision;
	private String establecimiento;
	private Double sub;
	private Double cambio;
	private String fecha;
	private String documento;
	private String importe;
	private String tipoDoc;
	private String codUbigeoCliente;
	private String observacion;
	private String nomCortoLocal;
	private List<DetalleFacturaModel> detalle;

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEmision() {
		return emision;
	}

	public void setEmision(String emision) {
		this.emision = emision;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Double getSub() {
		return sub;
	}

	public void setSub(Double sub) {
		this.sub = sub;
	}

	public Double getCambio() {
		return cambio;
	}

	public void setCambio(Double cambio) {
		this.cambio = cambio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getCodUbigeoCliente() {
		return codUbigeoCliente;
	}

	public void setCodUbigeoCliente(String codUbigeoCliente) {
		this.codUbigeoCliente = codUbigeoCliente;
	}

	public List<DetalleFacturaModel> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleFacturaModel> detalle) {
		this.detalle = detalle;
	}

	public String getNomCortoLocal() {
		return nomCortoLocal;
	}

	public void setNomCortoLocal(String nomCortoLocal) {
		this.nomCortoLocal = nomCortoLocal;
	}

}