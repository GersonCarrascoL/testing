package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.UbigeoModel;
import pe.edu.unmsm.quipucamayoc.persistence.UbigeoPersistence;
import pe.edu.unmsm.quipucamayoc.service.UbigeoService;

@Service
public class UbigeoServiceImpl implements UbigeoService{
	
	@Autowired private UbigeoPersistence ubigeoPersistence;
	
	@Override
	public List<UbigeoModel> listarDataUbigeo(){
		return ubigeoPersistence.listarDataUbigeo();
	}

}
