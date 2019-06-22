package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.DetraccionSunatModel;
import pe.edu.unmsm.quipucamayoc.persistence.DetraccionSunatPersistence;
import pe.edu.unmsm.quipucamayoc.service.DetraccionSunatService;

@Service
public class DetraccionSunatServiceImpl implements DetraccionSunatService{

	@Autowired private DetraccionSunatPersistence detraccionSunatPersistence;
	
	@Override
	public List<DetraccionSunatModel> importeMinimoDetraccion(){
		return detraccionSunatPersistence.importeMinimoDetraccion();
	}
	
	@Override
	public List<DetraccionSunatModel> porcentajeDetraccionUsoGeneral(){
		return detraccionSunatPersistence.porcentajeDetraccionUsoGeneral();
	}
}
