package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioHistorialModel;

public interface ListaItemPrecioHistorialService {

	public List<ListaItemPrecioHistorialModel> listarHistorialXItem(Integer udId, String codItem);
}
