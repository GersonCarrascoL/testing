package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ConceptoMaestroModel;

public interface ConceptoMaestroService {

	public List<ConceptoMaestroModel> conceptosPagosMaestro();
	public List<ConceptoMaestroModel> conceptosPagosXnombreCodigo(String nombre, String codigo, String udIdDepPadre);
}
