package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ConceptoUnidadModel;
import pe.edu.unmsm.quipucamayoc.persistence.ConceptoUnidadPersistence;
import pe.edu.unmsm.quipucamayoc.service.ConceptoUnidadService;

@Service
public class ConceptoUnidadServiceImpl implements ConceptoUnidadService{
	
	@Autowired private ConceptoUnidadPersistence conceptoPagoPersistence;
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoXDependencia(String codigoDependencia){
		String codDep = codigoDependencia.concat("%");
		return conceptoPagoPersistence.conceptosPagoXDependencia(codDep);
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaXEstado(String codigoDependencia, Integer estado){
		String codDep = codigoDependencia.concat("%");
		return conceptoPagoPersistence.conceptosPagoXDependenciaXEstado(codDep, estado);
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoXUnaDependencia(String codigoDependencia){
		return conceptoPagoPersistence.conceptosPagoXUnaDependencia(codigoDependencia);
	}
	
	@Override
	public List<ConceptoUnidadModel> listarConceptosPagoHabilitadosXUnaDep(Integer udIdDependencia){
		return conceptoPagoPersistence.listarConceptosPagoHabilitadosXUnaDep(udIdDependencia);
	}
	
	@Override
	public void registrarConceptoPagoUnidad(ConceptoUnidadModel concepto){
		conceptoPagoPersistence.registrarConceptoPagoUnidad(concepto);
	}
	
	@Override
	public void actualizarEstado(Integer idCPago, Integer estado){
		conceptoPagoPersistence.actualizarEstado(idCPago, estado);
	}
	
	@Override
	public void actualizarConceptoPago(ConceptoUnidadModel concepto){
		conceptoPagoPersistence.actualizarConceptoPago(concepto);
	}
	
	@Override
	public void volverEnviarConcepto(ConceptoUnidadModel concepto){
		conceptoPagoPersistence.volverEnviarConcepto(concepto);
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitados(String codigoDependencia) {
		//concatena % para usar like
		String codDep = codigoDependencia.concat("%");
		return conceptoPagoPersistence.conceptosPagoXDependenciaHabilitados(codDep);
	}

	@Override
	public List<ConceptoUnidadModel> conceptosPagoXDependenciaHabilitadosMoneda(String codigoDependencia, String codigoMoneda) {
		List<ConceptoUnidadModel> conceptos=conceptoPagoPersistence.conceptosPagoXDependenciaHabilitadosMoneda(codigoDependencia,codigoMoneda);
		for(int i=0; i<conceptos.size();i++){
			ConceptoUnidadModel concepto=conceptos.get(i);
			concepto.setConcatenacionBusqueda(concepto.getCodigoUnidad()+"-"+concepto.getCodCPago()+"  "+concepto.getConcepto());
		}
		return conceptos;
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoPendientesXDependencia(String codigoDependencia){
		// concatena % para usar like
		String codDep = codigoDependencia.concat("%");
		return conceptoPagoPersistence.conceptosPagoPendientesXDependencia(codDep);
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoPendientesTodos(){
		return conceptoPagoPersistence.conceptosPagoPendientesTodos();
	}
	
	@Override
	public List<ConceptoUnidadModel> conceptosPagoTodos(){
		return conceptoPagoPersistence.conceptosPagoTodos();
	}
	
	@Override
	public void aprobarConcepto(Integer idCPago){
		conceptoPagoPersistence.aprobarConceptoPago(idCPago);
	}
	
	@Override
	public void rechazarConcepto(Integer idCPago, String motivoRechazo){
		conceptoPagoPersistence.rechazarConceptoPago(idCPago, motivoRechazo);
	}
	
	@Override
	public void registrarConceptoUnidadMatriz(ConceptoUnidadModel conceptoPago){
		conceptoPagoPersistence.registrarConceptoPagoUnidadMatriz(conceptoPago);
	}
	
	@Override
	public void deleteConceptoUnidad(ConceptoUnidadModel conceptoPago){
		conceptoPagoPersistence.deleteConceptoUnidad(conceptoPago);
	}
	
	@Override
	public List<ConceptoUnidadModel> existeConceptoPago(Integer idCP, Integer udId){
		return conceptoPagoPersistence.existeConceptoPago(idCP, udId);
	}

}
