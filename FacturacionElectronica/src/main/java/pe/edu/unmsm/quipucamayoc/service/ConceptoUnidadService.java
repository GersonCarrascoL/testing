package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;


import pe.edu.unmsm.quipucamayoc.model.ConceptoUnidadModel;

public interface ConceptoUnidadService {
	
	public List<ConceptoUnidadModel> conceptosPagoXDependencia(String codigoDependencia);
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaXEstado(String codigoDependencia, Integer estado);
	public List<ConceptoUnidadModel> conceptosPagoXUnaDependencia(String codigoDependencia);
	public List<ConceptoUnidadModel> listarConceptosPagoHabilitadosXUnaDep(Integer udIdDependencia);
	public void registrarConceptoPagoUnidad(ConceptoUnidadModel concepto);
	public void actualizarEstado(Integer idCPago, Integer estado);
	public void actualizarConceptoPago(ConceptoUnidadModel concepto);
	public void volverEnviarConcepto(ConceptoUnidadModel concepto);
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitados(String codigoDependencia);
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitadosMoneda(String codigoDependencia,String codigoMoneda);
	public List<ConceptoUnidadModel> conceptosPagoPendientesXDependencia(String codigoDependencia);
	public List<ConceptoUnidadModel> conceptosPagoPendientesTodos();
	public List<ConceptoUnidadModel> conceptosPagoTodos();
	public void aprobarConcepto(Integer idCPago);
	public void rechazarConcepto(Integer idCPago, String motivoRechazo);
	public void registrarConceptoUnidadMatriz(ConceptoUnidadModel conceptoPago);	
	public void deleteConceptoUnidad(ConceptoUnidadModel conceptoPago);
	public List<ConceptoUnidadModel> existeConceptoPago(Integer idCP, Integer udId);

}
