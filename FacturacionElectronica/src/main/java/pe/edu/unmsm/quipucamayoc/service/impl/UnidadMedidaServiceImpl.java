package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.UnidadMedidaModel;
import pe.edu.unmsm.quipucamayoc.persistence.UnidadMedidaPersistence;
import pe.edu.unmsm.quipucamayoc.service.UnidadMedidaService;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService{
	
	@Autowired private UnidadMedidaPersistence unidadMedidaPersistence;
	
	@Override
	public List<UnidadMedidaModel> getUnidadesMedida(){
		return unidadMedidaPersistence.listarUnidadesMedida();		
	}

}
