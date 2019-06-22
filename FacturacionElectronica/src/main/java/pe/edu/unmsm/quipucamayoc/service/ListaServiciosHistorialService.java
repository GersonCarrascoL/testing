package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosHistorialModel;


public interface ListaServiciosHistorialService {
	
	public List<ListaServiciosHistorialModel> getListaServicioHistorial(Integer udId, String codItem);

}
