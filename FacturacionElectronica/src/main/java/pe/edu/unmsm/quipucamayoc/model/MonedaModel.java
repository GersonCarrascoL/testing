package pe.edu.unmsm.quipucamayoc.model;

public class MonedaModel {
	
	private int idMoneda;	
	private String monedaDesc;
	private String monedaSimb;
	private int estado;
	
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public String getMonedaDesc() {
		return monedaDesc;
	}
	public void setMonedaDesc(String monedaDesc) {
		this.monedaDesc = monedaDesc;
	}
	public String getMonedaSimb() {
		return monedaSimb;
	}
	public void setMonedaSimb(String monedaSimb) {
		this.monedaSimb = monedaSimb;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
