package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.TipoOperacionIGVModel;
import pe.edu.unmsm.quipucamayoc.persistence.SunatGeneralPersistence;
import pe.edu.unmsm.quipucamayoc.service.SunatGeneralService;

@Service
public class SunatGeneralServiceImpl implements SunatGeneralService{
	
	@Autowired private SunatGeneralPersistence sunatGeneralPersistence;
	
	@Override
	public List<TipoOperacionIGVModel> listarTiposOperacionIgv(){
		return sunatGeneralPersistence.listarTiposOperacionIgv();
	}
}
