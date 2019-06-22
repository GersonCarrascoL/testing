package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.MonedaModel;
import pe.edu.unmsm.quipucamayoc.persistence.MonedaPersistence;
import pe.edu.unmsm.quipucamayoc.service.MonedaService;

@Service
public class MonedaServiceImpl implements MonedaService {
	
	@Autowired private MonedaPersistence monedaPersistence;
	
	@Override
	public List<MonedaModel> obtieneMonedasActivas(){
		return monedaPersistence.listaMonedas();
	}
	

}
