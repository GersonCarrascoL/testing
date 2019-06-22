
package pe.edu.unmsm.quipucamayoc.model;

public class DetalleModel extends DetallePModel{
	
	private Integer id;
	private Integer detraccion;
	private String establecimiento;
	private String serie;
	private String item;
	private String codSunat;
	private String uniMedida;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDetraccion() {
		return detraccion;
	}
	public void setDetraccion(Integer detraccion) {
		this.detraccion = detraccion;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCodSunat() {
		return codSunat;
	}
	public void setCodSunat(String codSunat) {
		this.codSunat = codSunat;
	}
	public String getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

}