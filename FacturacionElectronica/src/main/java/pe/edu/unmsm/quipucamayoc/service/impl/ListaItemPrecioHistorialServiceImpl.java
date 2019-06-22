package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioHistorialModel;
import pe.edu.unmsm.quipucamayoc.persistence.ListaItemPrecioHistorialPersistence;
import pe.edu.unmsm.quipucamayoc.service.ListaItemPrecioHistorialService;

@Service
public class ListaItemPrecioHistorialServiceImpl implements ListaItemPrecioHistorialService{
	
	@Autowired private ListaItemPrecioHistorialPersistence lpHistorialPersistence;
	
	@Override
	public List<ListaItemPrecioHistorialModel> listarHistorialXItem(Integer udId, String codItem){
		return lpHistorialPersistence.getListaItemHistorialItem(udId, codItem);
	}

}
