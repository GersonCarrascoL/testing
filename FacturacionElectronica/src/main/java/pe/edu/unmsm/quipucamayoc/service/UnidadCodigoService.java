package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;


import pe.edu.unmsm.quipucamayoc.model.UnidadCodigoModel;

public interface UnidadCodigoService {
	
	public List<UnidadCodigoModel> unidadesCodigoXDependencia(String udIdPadreRegistrado);
	public List<UnidadCodigoModel> unidadesCodigoXDep(String udIdPadreRegistrado, String codUnidad);
	public void registrarUnidadCodigo(UnidadCodigoModel unidad);
	public void actualizarUnidadCodigo(UnidadCodigoModel unidad);
	public void deleteUnidadCodigo(UnidadCodigoModel unidad);
	public List<UnidadCodigoModel> codigoUnidadesTodos();
	public void volverEnviarUnidadCodigo(UnidadCodigoModel unidad);
	public void aprobarUnidadCodigo(Integer udId);
	public void rechazarUnidadCodigo(Integer udId, String motivoRechazo);

}
