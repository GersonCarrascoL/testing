package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ConceptoMaestroModel;
import pe.edu.unmsm.quipucamayoc.persistence.ConceptoMaestroPersistence;
import pe.edu.unmsm.quipucamayoc.service.ConceptoMaestroService;

@Service
public class ConceptoMaestroServiceImpl implements ConceptoMaestroService{

	@Autowired private ConceptoMaestroPersistence conceptoMaestroPersistence;
	
	@Override
	public List<ConceptoMaestroModel> conceptosPagosMaestro(){
		return conceptoMaestroPersistence.conceptosPagoMaestro();
	}
	
	@Override
	public List<ConceptoMaestroModel> conceptosPagosXnombreCodigo(String nombre, String codigo, String udIdDepPadre){
		return conceptoMaestroPersistence.conceptosPagoXnombreCodigo(nombre, codigo, udIdDepPadre);
	}
}
