package pe.edu.unmsm.quipucamayoc.model;

public class ServicioCategoriaModel {
	
	private Integer idServCata;
	private Integer udIdCategoria;
	private String descCategoriaServ;
	private String filterCategoriaServ;
	private Integer status;
	
	public Integer getIdServCata() {
		return idServCata;
	}
	public void setIdServCata(Integer idServCata) {
		this.idServCata = idServCata;
	}
	public Integer getUdIdCategoria() {
		return udIdCategoria;
	}
	public void setUdIdCategoria(Integer udIdCategoria) {
		this.udIdCategoria = udIdCategoria;
	}
	public String getDescCategoriaServ() {
		return descCategoriaServ;
	}
	public void setDescCategoriaServ(String descCategoriaServ) {
		this.descCategoriaServ = descCategoriaServ;
	}
	public String getFilterCategoriaServ() {
		return filterCategoriaServ;
	}
	public void setFilterCategoriaServ(String filterCategoriaServ) {
		this.filterCategoriaServ = filterCategoriaServ;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}
