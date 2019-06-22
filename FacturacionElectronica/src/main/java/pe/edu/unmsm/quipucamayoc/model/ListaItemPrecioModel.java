package pe.edu.unmsm.quipucamayoc.model;

public class ListaItemPrecioModel extends ListaItemModel{
	
	private Integer anio;
	private Integer codTipoIgv;	
	private String nombreCatalogo;	
	private String descrDetraccion;	
	private Integer idBienCata;
	private String descCategoriaBien;
	private String tipoItem;
	
	
	public String getTipoItem() {
		return tipoItem;
	}
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getCodTipoIgv() {
		return codTipoIgv;
	}
	public void setCodTipoIgv(Integer codTipoIgv) {
		this.codTipoIgv = codTipoIgv;
	}
	public String getNombreCatalogo() {
		return nombreCatalogo;
	}
	public void setNombreCatalogo(String nombreCatalogo) {
		this.nombreCatalogo = nombreCatalogo;
	}
	public String getDescrDetraccion() {
		return descrDetraccion;
	}
	public void setDescrDetraccion(String descrDetraccion) {
		this.descrDetraccion = descrDetraccion;
	}
	public Integer getIdBienCata() {
		return idBienCata;
	}
	public void setIdBienCata(Integer idBienCata) {
		this.idBienCata = idBienCata;
	}
	public String getDescCategoriaBien() {
		return descCategoriaBien;
	}
	public void setDescCategoriaBien(String descCategoriaBien) {
		this.descCategoriaBien = descCategoriaBien;
	}
		
	
		
}
