package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.UnidadCodigoModel;
import pe.edu.unmsm.quipucamayoc.persistence.UnidadCodigoPersistence;
import pe.edu.unmsm.quipucamayoc.service.UnidadCodigoService;

@Service
public class UnidadCodigoServiceImpl implements UnidadCodigoService{
	
	@Autowired private UnidadCodigoPersistence unidadCodigoPersistence;
	
	@Override
	public List<UnidadCodigoModel> unidadesCodigoXDependencia(String udIdPadreRegistrado){
		return unidadCodigoPersistence.unidadesCodigoXDependencia(udIdPadreRegistrado);
	}
	
	@Override
	public List<UnidadCodigoModel> unidadesCodigoXDep(String udIdPadreRegistrado, String codUnidad){
		return unidadCodigoPersistence.unidadesCodigoXDep(udIdPadreRegistrado, codUnidad);
	}
	
	@Override
	public void registrarUnidadCodigo(UnidadCodigoModel unidad){
		unidadCodigoPersistence.registrarUnidadCodigo(unidad);
	}
	@Override
	public void actualizarUnidadCodigo(UnidadCodigoModel unidad){
		unidadCodigoPersistence.actualizarUnidadCodigo(unidad);
	}
	
	@Override
	public void deleteUnidadCodigo(UnidadCodigoModel unidad){
		unidadCodigoPersistence.deleteUnidadCodigo(unidad);
	}
	
	@Override
	public List<UnidadCodigoModel> codigoUnidadesTodos(){
		return unidadCodigoPersistence.codigoUnidadesTodos();
	}
	
	@Override
	public void volverEnviarUnidadCodigo(UnidadCodigoModel unidad){
		unidadCodigoPersistence.volverEnviarUnidadCodigo(unidad);
	}
	
	@Override
	public void aprobarUnidadCodigo(Integer udId){
		unidadCodigoPersistence.aprobarUnidadCodigo(udId);
	}
	
	@Override
	public void rechazarUnidadCodigo(Integer udId, String motivoRechazo){
		unidadCodigoPersistence.rechazarUnidadCodigo(udId, motivoRechazo);
	}

}
