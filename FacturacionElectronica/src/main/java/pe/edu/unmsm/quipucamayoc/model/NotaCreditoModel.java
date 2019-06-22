package pe.edu.unmsm.quipucamayoc.model;

import java.util.List;

public class NotaCreditoModel {
	private String tipo;
	private String motivo;
	private String numeroDocumentoAsociado;
	private String codEstab;
	private String serie;
	private Integer tipoComprobante;
	private List<DetalleNotaCredito> listaDetalle;
	private String usuario;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNumeroDocumentoAsociado() {
		return numeroDocumentoAsociado;
	}
	public void setNumeroDocumentoAsociado(String numeroDocumentoAsociado) {
		this.numeroDocumentoAsociado = numeroDocumentoAsociado;
	}
	public String getCodEstab() {
		return codEstab;
	}
	public void setCodEstab(String codEstab) {
		this.codEstab = codEstab;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(Integer tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public List<DetalleNotaCredito> getListaDetalle() {
		return listaDetalle;
	}
	public void setListaDetalle(List<DetalleNotaCredito> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
