package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioModel;
import pe.edu.unmsm.quipucamayoc.persistence.ListaItemPrecioPersistence;
import pe.edu.unmsm.quipucamayoc.service.ListaItemPrecioService;

@Service
public class ListaItemPrecioServiceImpl implements ListaItemPrecioService {
	
	@Autowired private ListaItemPrecioPersistence listaItemPrecioPersistence;

	@Override
	public List<ListaItemPrecioModel> listaItemPrecio() {
		return listaItemPrecioPersistence.items();
	}
	
	@Override
	public List<ListaItemPrecioModel> listaItemYConceptoXDependencia(String codigoDependencia){
		return listaItemPrecioPersistence.getListaItemYConceptoXDependencia(codigoDependencia);
	}
	
	@Override
	public List<ListaItemPrecioModel> listaItemPrecioXDependencia(String codigoDependencia){
		return listaItemPrecioPersistence.getListaItemPrecioXDependencia(codigoDependencia);
	}
	@Override
	public List<ListaItemPrecioModel> listaItemXDependenciaHabilitados(String codigoDependencia){
		return listaItemPrecioPersistence.getItemsPrecioXDependenciaHabilitados(codigoDependencia);
	}
	
	@Override
	public void registrarItem(ListaItemPrecioModel item){
		listaItemPrecioPersistence.registrarItem(item);
	}
	
	@Override
	public void actualizarEstado(Integer udId, String codItem, Integer estado){
		listaItemPrecioPersistence.actualizarEstado(udId, codItem, estado);
	}
	
	@Override
	public void actualizarItem(ListaItemPrecioModel item){
		listaItemPrecioPersistence.actualizarItem(item);
	}
	
	@Override
	public void deleteItemPrecio(ListaItemPrecioModel item){
		listaItemPrecioPersistence.deleteItemPrecio(item);
	}
	
	@Override
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentral(Integer udIdAdministrativa){
		return listaItemPrecioPersistence.getListaPreciosXAdmiCentral(udIdAdministrativa);
	}
	
	@Override
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentralHabilitados(Integer udIdAdministrativa){
		return listaItemPrecioPersistence.getListaPreciosXAdmiCentralHabilitados(udIdAdministrativa);
	}

}
