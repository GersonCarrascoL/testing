package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.TipoConceptoPagoModel;
import pe.edu.unmsm.quipucamayoc.persistence.TipoConceptoPagoPersistence;
import pe.edu.unmsm.quipucamayoc.service.TipoConceptoPagoService;

@Service
public class TipoConceptoPagoServiceImpl implements TipoConceptoPagoService {
	
	@Autowired private TipoConceptoPagoPersistence tipoConceptoPagoPersistence;
	
	@Override
	public List<TipoConceptoPagoModel> getTiposConceptoPago(){
		return tipoConceptoPagoPersistence.listarTiposConceptoPago();
	}

}
