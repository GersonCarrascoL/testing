package pe.edu.unmsm.quipucamayoc.model;

/*
 * Codigos de elementos adicionales a la factura y/o boleta de venta
 * SUNAT
 */

public class SunatCatalogoN15Model {

	private String codCatalogo;
	private String descr;
	private String leyenda;
	private String grupo;
	
	public String getCodCatalogo() {
		return codCatalogo;
	}
	public void setCodCatalogo(String codCatalogo) {
		this.codCatalogo = codCatalogo;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	
}
