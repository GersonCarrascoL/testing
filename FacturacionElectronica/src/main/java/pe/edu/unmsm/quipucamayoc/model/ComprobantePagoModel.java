
package pe.edu.unmsm.quipucamayoc.model;

public class ComprobantePagoModel extends ComprobanteP2Model{

	private String fechaEmision;
	private String numeroDocumento;
	private String importeOperacion;
	private String tipoDoc;
	private String codEst;
	private String docIden;
	private String id;
	private String nombre2;
	private String enviar;
	private String estadoFacturadorDescr;
	private String estadoFacturador;
	private String notaAsoc;
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getImporteOperacion() {
		return importeOperacion;
	}
	public void setImporteOperacion(String importeOperacion) {
		this.importeOperacion = importeOperacion;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getCodEst() {
		return codEst;
	}
	public void setCodEst(String codEst) {
		this.codEst = codEst;
	}
	public String getDocIden() {
		return docIden;
	}
	public void setDocIden(String docIden) {
		this.docIden = docIden;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getEnviar() {
		return enviar;
	}
	public void setEnviar(String enviar) {
		this.enviar = enviar;
	}
	public String getEstadoFacturadorDescr() {
		return estadoFacturadorDescr;
	}
	public void setEstadoFacturadorDescr(String estadoFacturadorDescr) {
		this.estadoFacturadorDescr = estadoFacturadorDescr;
	}
	public String getEstadoFacturador() {
		return estadoFacturador;
	}
	public void setEstadoFacturador(String estadoFacturador) {
		this.estadoFacturador = estadoFacturador;
	}
	public String getNotaAsoc() {
		return notaAsoc;
	}
	public void setNotaAsoc(String notaAsoc) {
		this.notaAsoc = notaAsoc;
	}

}