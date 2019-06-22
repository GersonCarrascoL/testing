package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.SunatCatalogoN15Model;

public interface SunatCatalogoN15Service {
	
	public List<SunatCatalogoN15Model> listarDataDetracciones();
	public List<SunatCatalogoN15Model> getCodGeneralCatalogoDetraccion();

}
