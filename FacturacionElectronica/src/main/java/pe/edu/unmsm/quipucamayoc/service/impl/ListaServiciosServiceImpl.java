package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosModel;
import pe.edu.unmsm.quipucamayoc.persistence.ListaServicioPersistence;
import pe.edu.unmsm.quipucamayoc.service.ListaServiciosService;

@Service
public class ListaServiciosServiceImpl implements ListaServiciosService{
	
	@Autowired private ListaServicioPersistence listaServicioPersistence;
	
	@Override
	public List<ListaServiciosModel> getListaServiciosXDependencia(String codigoDependencia){
		return listaServicioPersistence.getListaServiciosXDependencia(codigoDependencia);
	}
	@Override
	public List<ListaServiciosModel> getListaServiciosXDependenciaHabilitados(String codigoDependencia){
		return listaServicioPersistence.getListaServiciosXDependenciaHabilitados(codigoDependencia);
	}
	@Override
	public void registrarServicio(ListaServiciosModel item){
		listaServicioPersistence.registrarServicio(item);
	}
	
	@Override
	public void deleteItemServicio(ListaServiciosModel item){
		listaServicioPersistence.deleteItemServicio(item);
	}
	
	@Override
	public void actualizarEstado(Integer udId, String codItem, Integer estado){
		listaServicioPersistence.actualizarEstado(udId, codItem, estado);
	}
	
	@Override
	public void actualizarServicio(ListaServiciosModel servicio){
		listaServicioPersistence.actualizarServicio(servicio);
	}
	
	@Override
	public List<ListaServiciosModel> getListaServiciosXAdmiCentral(Integer udIdAdministrativa){
		return listaServicioPersistence.getListaServiciosXAdmiCentral(udIdAdministrativa);
	}
	
	@Override
	public List<ListaServiciosModel> getListaServiciosXAdmiCentralHabilitados(Integer udIdAdministrativa){
		return listaServicioPersistence.getListaServiciosXAdmiCentralHabilitados(udIdAdministrativa);
	}

}
