package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosModel;

public interface ListaServiciosService {
	
	public List<ListaServiciosModel> getListaServiciosXDependencia(String codigoDependencia);
	public List<ListaServiciosModel> getListaServiciosXDependenciaHabilitados(String codigoDependencia);
	public List<ListaServiciosModel> getListaServiciosXAdmiCentral(Integer udIdAdministrativa);
	public List<ListaServiciosModel> getListaServiciosXAdmiCentralHabilitados(Integer udIdAdministrativa);
	public void registrarServicio(ListaServiciosModel item);
	public void actualizarServicio(ListaServiciosModel servicio);
	public void actualizarEstado(Integer udId, String codItem, Integer estado);
	public void deleteItemServicio(ListaServiciosModel item);

}
