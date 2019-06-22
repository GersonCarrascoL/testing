package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.DetraccionSunatModel;

public interface DetraccionSunatService {
	
	public List<DetraccionSunatModel> importeMinimoDetraccion();
	public List<DetraccionSunatModel> porcentajeDetraccionUsoGeneral();

}
