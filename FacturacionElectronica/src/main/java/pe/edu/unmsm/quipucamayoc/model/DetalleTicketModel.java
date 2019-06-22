package pe.edu.unmsm.quipucamayoc.model;

public class DetalleTicketModel extends DetallePModel{
	private String estableAnexo;
	private String nombCorto;
	private String serieFabMaq;
	
	public String getEstableAnexo() {
		return estableAnexo;
	}
	public void setEstableAnexo(String estableAnexo) {
		this.estableAnexo = estableAnexo;
	}
	public String getNombCorto() {
		return nombCorto;
	}
	public void setNombCorto(String nombCorto) {
		this.nombCorto = nombCorto;
	}
	public String getSerieFabMaq() {
		return serieFabMaq;
	}
	public void setSerieFabMaq(String serieFabMaq) {
		this.serieFabMaq = serieFabMaq;
	}
	
}
