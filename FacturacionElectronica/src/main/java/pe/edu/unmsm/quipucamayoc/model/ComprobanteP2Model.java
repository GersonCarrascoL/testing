
package pe.edu.unmsm.quipucamayoc.model;

public class ComprobanteP2Model extends ComprobanteP1Model{

	private Double subTotal;
	private Double pCambio;
	private String usuario;
	private String motivo;
	private String tipoNota;
	
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getpCambio() {
		return pCambio;
	}
	public void setpCambio(Double pCambio) {
		this.pCambio = pCambio;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}
	
}