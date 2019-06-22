package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosHistorialModel;
import pe.edu.unmsm.quipucamayoc.persistence.ListaServiciosHistorialPersistence;
import pe.edu.unmsm.quipucamayoc.service.ListaServiciosHistorialService;

@Service
public class ListaServiciosHistorialServiceImpl implements ListaServiciosHistorialService{
	
	@Autowired private ListaServiciosHistorialPersistence listaServiciosHistorialPersistence;
	
	@Override
	public List<ListaServiciosHistorialModel> getListaServicioHistorial(Integer udId, String coItem){
		return listaServiciosHistorialPersistence.getListaServicioHistorial(udId, coItem);
	}

}
