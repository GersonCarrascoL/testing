package pe.edu.unmsm.quipucamayoc.model;

public class ConceptoUnidadModel {
	private Integer idCPU;   // id concepto_unidad
	private Integer idCPago; // id concepto pago
	private String codCPago; // codigo concepto 3 digitos
	private String concepto; //nombre concepto
	private String descr;    //descripcion breve concepto
	private Integer idTipoCpago;
	private String tcpDescr; //descripcion del tipo de concepto de pago
	private Double monto;
	private Integer estado;
	private String tIngClasif;
	private Integer idMoneda;
	private Integer facturable; //1: facturable obligatorio,  0: No obligatorio
	private Integer igv; //1: aplicable,  0: No aplicable
	private String resolRectoral;
	
	private Integer udId;
	private String udCod;
	private String udDesc;
	private String udDescPadre;
	private Integer udIdPadre;
	private String monedaDesc;
	private String monedaSimb;
	private String codBanco;
	private String nombreBanco;
	
	private Integer poseeDetraccion;
	private String codDetraccion;
	private String descrDetraccion;
	private double porcentDetraccion;
	
	private Integer status;
	private String codigoUnidad; //3 digitos
	private String codigoConcepto6digitos; //6 digitos ejemplo: 016-175
	private String fechaReg;
	private String fechaModif;
	private Integer estEdit;
	private String fechaAprobado;
	private String fechaRechazo;
	private String regMonth;
	private String regYear;
	private Integer estadoSolicitud;
	private String udIdSol; ///ud_id del solicitante
	private String observaciones;
	private String motivoRechazo;
	private Integer udIdAdministrativa;
	private String errorMsg;
	private Integer estadoSolicitudMaestro; //estado de solicitud para un concepto maestro
	
	private String concatenacionBusqueda;
	private Integer contador;
	private String readableName;
	
	public String getReadableName() {
		return readableName;
	}
	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}
	public Integer getIdCPU() {
		return idCPU;
	}
	public void setIdCPU(Integer idCPU) {
		this.idCPU = idCPU;
	}
	public Integer getIdCPago() {
		return idCPago;
	}
	public void setIdCPago(Integer idCPago) {
		this.idCPago = idCPago;
	}
	public String getCodCPago() {
		return codCPago;
	}
	public void setCodCPago(String codCPago) {
		this.codCPago = codCPago;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getIdTipoCpago() {
		return idTipoCpago;
	}
	public void setIdTipoCpago(Integer idTipoCpago) {
		this.idTipoCpago = idTipoCpago;
	}
	public String getTcpDescr() {
		return tcpDescr;
	}
	public void setTcpDescr(String tcpDescr) {
		this.tcpDescr = tcpDescr;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String gettIngClasif() {
		return tIngClasif;
	}
	public void settIngClasif(String tIngClasif) {
		this.tIngClasif = tIngClasif;
	}
	public Integer getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}
	public Integer getFacturable() {
		return facturable;
	}
	public void setFacturable(Integer facturable) {
		this.facturable = facturable;
	}
	public Integer getIgv() {
		return igv;
	}
	public void setIgv(Integer igv) {
		this.igv = igv;
	}
	public String getResolRectoral() {
		return resolRectoral;
	}
	public void setResolRectoral(String resolRectoral) {
		this.resolRectoral = resolRectoral;
	}
	public Integer getUdId() {
		return udId;
	}
	public void setUdId(Integer udId) {
		this.udId = udId;
	}
	public String getUdCod() {
		return udCod;
	}
	public void setUdCod(String udCod) {
		this.udCod = udCod;
	}
	public String getUdDesc() {
		return udDesc;
	}
	public void setUdDesc(String udDesc) {
		this.udDesc = udDesc;
	}
	public String getUdDescPadre() {
		return udDescPadre;
	}
	public void setUdDescPadre(String udDescPadre) {
		this.udDescPadre = udDescPadre;
	}
	public Integer getUdIdPadre() {
		return udIdPadre;
	}
	public void setUdIdPadre(Integer udIdPadre) {
		this.udIdPadre = udIdPadre;
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
	public String getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	public String getNombreBanco() {
		return nombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}
	public Integer getPoseeDetraccion() {
		return poseeDetraccion;
	}
	public void setPoseeDetraccion(Integer poseeDetraccion) {
		this.poseeDetraccion = poseeDetraccion;
	}
	public String getCodDetraccion() {
		return codDetraccion;
	}
	public void setCodDetraccion(String codDetraccion) {
		this.codDetraccion = codDetraccion;
	}
	public String getDescrDetraccion() {
		return descrDetraccion;
	}
	public void setDescrDetraccion(String descrDetraccion) {
		this.descrDetraccion = descrDetraccion;
	}
	public double getPorcentDetraccion() {
		return porcentDetraccion;
	}
	public void setPorcentDetraccion(double porcentDetraccion) {
		this.porcentDetraccion = porcentDetraccion;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCodigoUnidad() {
		return codigoUnidad;
	}
	public void setCodigoUnidad(String codigoUnidad) {
		this.codigoUnidad = codigoUnidad;
	}
	public String getCodigoConcepto6digitos() {
		return codigoConcepto6digitos;
	}
	public void setCodigoConcepto6digitos(String codigoConcepto6digitos) {
		this.codigoConcepto6digitos = codigoConcepto6digitos;
	}
	public String getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}
	public String getFechaModif() {
		return fechaModif;
	}
	public void setFechaModif(String fechaModif) {
		this.fechaModif = fechaModif;
	}
	public Integer getEstEdit() {
		return estEdit;
	}
	public void setEstEdit(Integer estEdit) {
		this.estEdit = estEdit;
	}
	public String getFechaAprobado() {
		return fechaAprobado;
	}
	public void setFechaAprobado(String fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}
	public String getFechaRechazo() {
		return fechaRechazo;
	}
	public void setFechaRechazo(String fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	public String getRegMonth() {
		return regMonth;
	}
	public void setRegMonth(String regMonth) {
		this.regMonth = regMonth;
	}
	public String getRegYear() {
		return regYear;
	}
	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}
	public Integer getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(Integer estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public String getUdIdSol() {
		return udIdSol;
	}
	public void setUdIdSol(String udIdSol) {
		this.udIdSol = udIdSol;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public Integer getUdIdAdministrativa() {
		return udIdAdministrativa;
	}
	public void setUdIdAdministrativa(Integer udIdAdministrativa) {
		this.udIdAdministrativa = udIdAdministrativa;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getEstadoSolicitudMaestro() {
		return estadoSolicitudMaestro;
	}
	public void setEstadoSolicitudMaestro(Integer estadoSolicitudMaestro) {
		this.estadoSolicitudMaestro = estadoSolicitudMaestro;
	}
	public String getConcatenacionBusqueda() {
		return concatenacionBusqueda;
	}
	public void setConcatenacionBusqueda(String concatenacionBusqueda) {
		this.concatenacionBusqueda = concatenacionBusqueda;
	}
	public Integer getContador() {
		return contador;
	}
	public void setContador(Integer contador) {
		this.contador = contador;
	}		
	
	
}
