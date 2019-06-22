package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleNotaCredito;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotaCreditoModel;
import pe.edu.unmsm.quipucamayoc.model.TipoNotaCredito;

public interface NotaCreditoService {
	public List<TipoNotaCredito> getTipoNotaCreditoBoleta();
	public List<TipoNotaCredito> getTipoNotaCreditoFactura();    
	public List<ComprobantePagoModel> getComprobantesParaNotas(String codDependencia, Integer tipoComprobante,Integer numeroComprobante);
	public List<DetalleFacturaModel> getDetalleComprobante(String numeroDocumento,Integer tipoComprobante); 
	public ComprobantePagoModel getInformacionComprobante(String codDependencia, Integer tipoComprobante);   
	public void registrarNotaCredito(NotaCreditoModel notaCredito);  
	public void registrarNotaCreditoDetalle(NotaCreditoModel notaCredito,DetalleNotaCredito detalle);  
	public EstadoModel enviarFacturador(NotaCreditoModel notaCredito);
}
