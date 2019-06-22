
package pe.edu.unmsm.quipucamayoc.model;

public class DetalleFacturaModel extends DetallePModel {

	private Integer poseeDetraccion;
	private Integer idDetComp;
	private String codigo;
	private String codProductoSUNAT;
	private String codDetraccion;
	private String nUnidadMedida;
	private Double porcentDetraccion;
	private String descTipoIgv;

	public Integer getPoseeDetraccion() {
		return poseeDetraccion;
	}

	public void setPoseeDetraccion(Integer poseeDetraccion) {
		this.poseeDetraccion = poseeDetraccion;
	}

	public Integer getIdDetComp() {
		return idDetComp;
	}

	public void setIdDetComp(Integer idDetComp) {
		this.idDetComp = idDetComp;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodProductoSUNAT() {
		return codProductoSUNAT;
	}

	public void setCodProductoSUNAT(String codProductoSUNAT) {
		this.codProductoSUNAT = codProductoSUNAT;
	}

	public String getCodDetraccion() {
		return codDetraccion;
	}

	public void setCodDetraccion(String codDetraccion) {
		this.codDetraccion = codDetraccion;
	}

	public String getnUnidadMedida() {
		return nUnidadMedida;
	}

	public void setnUnidadMedida(String nUnidadMedida) {
		this.nUnidadMedida = nUnidadMedida;
	}

	public Double getPorcentDetraccion() {
		return porcentDetraccion;
	}

	public void setPorcentDetraccion(Double porcentDetraccion) {
		this.porcentDetraccion = porcentDetraccion;
	}

	public String getDescTipoIgv() {
		return descTipoIgv;
	}

	public void setDescTipoIgv(String descTipoIgv) {
		this.descTipoIgv = descTipoIgv;
	}

}
