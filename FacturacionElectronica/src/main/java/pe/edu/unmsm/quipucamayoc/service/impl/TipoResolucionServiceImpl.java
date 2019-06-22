package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.TipoResolucionModel;
import pe.edu.unmsm.quipucamayoc.persistence.TipoResolucionPersistence;
import pe.edu.unmsm.quipucamayoc.service.TipoResolucionService;

@Service 
public class TipoResolucionServiceImpl implements TipoResolucionService {
	
	@Autowired private TipoResolucionPersistence tipoResolucionPersistence;
	
	@Override
	public List<TipoResolucionModel> listarTiposResoluciones(){
		return tipoResolucionPersistence.listarTiposResoluciones();
	}

}
