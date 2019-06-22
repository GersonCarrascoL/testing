
package pe.edu.unmsm.quipucamayoc.model;

import java.util.List;

public class ComprobanteModel extends ComprobanteP2Model{

	private String emision;
	private String establecimiento;
	private Integer detraccion;
	private String nDocumento;
	private String importe;
	private String tipoDocumento;
	private String ubigeo;
	private String montoLetras;
	private String documentoComprobante;
	private String documentoAsociado;
	private String tipoAsocSunat;
	private List<DetalleModel> detalle;
	
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
	public Integer getDetraccion() {
		return detraccion;
	}
	public void setDetraccion(Integer detraccion) {
		this.detraccion = detraccion;
	}
	public String getnDocumento() {
		return nDocumento;
	}
	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getMontoLetras() {
		return montoLetras;
	}
	public void setMontoLetras(String montoLetras) {
		this.montoLetras = montoLetras;
	}
	public String getDocumentoComprobante() {
		return documentoComprobante;
	}
	public void setDocumentoComprobante(String documentoComprobante) {
		this.documentoComprobante = documentoComprobante;
	}
	public String getDocumentoAsociado() {
		return documentoAsociado;
	}
	public void setDocumentoAsociado(String documentoAsociado) {
		this.documentoAsociado = documentoAsociado;
	}
	public String getTipoAsocSunat() {
		return tipoAsocSunat;
	}
	public void setTipoAsocSunat(String tipoAsocSunat) {
		this.tipoAsocSunat = tipoAsocSunat;
	}
	public List<DetalleModel> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleModel> detalle) {
		this.detalle = detalle;
	}
	
}