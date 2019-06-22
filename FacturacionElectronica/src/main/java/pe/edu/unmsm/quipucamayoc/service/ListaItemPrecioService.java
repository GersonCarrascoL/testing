package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioModel;

public interface ListaItemPrecioService {
	public List<ListaItemPrecioModel> listaItemPrecio();
	public List<ListaItemPrecioModel> listaItemYConceptoXDependencia(String codigoDependencia);
	public List<ListaItemPrecioModel> listaItemPrecioXDependencia(String codigoDependencia);
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentral(Integer udIdAdministrativa);
	public List<ListaItemPrecioModel> getListaPreciosXAdmiCentralHabilitados(Integer udIdAdministrativa);
	public void registrarItem(ListaItemPrecioModel item);
	public void actualizarEstado(Integer udId, String codItem, Integer estado);
	public void actualizarItem(ListaItemPrecioModel item);
	public List<ListaItemPrecioModel> listaItemXDependenciaHabilitados(String codigoDependencia);
	public void deleteItemPrecio(ListaItemPrecioModel item);
}
