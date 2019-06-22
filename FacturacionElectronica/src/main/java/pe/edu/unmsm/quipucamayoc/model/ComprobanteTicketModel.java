package pe.edu.unmsm.quipucamayoc.model;

import java.util.List;

public class ComprobanteTicketModel {
	private String correlativo;
	private String anexo;
	private String nomCorto;
	private Double total;
	private String formaPago;
	private String moneda;
	private String importeOperacion;
	private Double subtotal;
	private Double igv;
	private String unidad;
	private String facultad;
	private String serieFabMaq;
	private String nroAutoMaq;
	private String direccion;
	private String codEstab;
	private String fechaEmision;
	private String usuario;
	private Integer estado;
	private List<DetalleFacturaModel> detalle;
	
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getImporteOperacion() {
		return importeOperacion;
	}
	public void setImporteOperacion(String importeOperacion) {
		this.importeOperacion = importeOperacion;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getIgv() {
		return igv;
	}
	public void setIgv(Double igv) {
		this.igv = igv;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSerieFabMaq() {
		return serieFabMaq;
	}
	public void setSerieFabMaq(String serieFabMaq) {
		this.serieFabMaq = serieFabMaq;
	}
	public String getNroAutoMaq() {
		return nroAutoMaq;
	}
	public void setNroAutoMaq(String nroAutoMaq) {
		this.nroAutoMaq = nroAutoMaq;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNomCorto() {
		return nomCorto;
	}
	public void setNomCorto(String nomCorto) {
		this.nomCorto = nomCorto;
	}
	public List<DetalleFacturaModel> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleFacturaModel> detalle) {
		this.detalle = detalle;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getCodEstab() {
		return codEstab;
	}
	public void setCodEstab(String codEstab) {
		this.codEstab = codEstab;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
}
