package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.SunatCatalogoN15Model;
import pe.edu.unmsm.quipucamayoc.persistence.SunatCatalogoN15Persistence;
import pe.edu.unmsm.quipucamayoc.service.SunatCatalogoN15Service;

@Service
public class SunatCatalogoN15ServiceImpl implements SunatCatalogoN15Service {
	
	@Autowired private SunatCatalogoN15Persistence sunatCatalogoN15Persistence;
	
	@Override
	public List<SunatCatalogoN15Model> listarDataDetracciones(){
		return sunatCatalogoN15Persistence.listarDataDetracciones();
	}
	
	@Override
	public List<SunatCatalogoN15Model> getCodGeneralCatalogoDetraccion(){
		return sunatCatalogoN15Persistence.getCodGeneralCatalogoDetraccion();
	}

}
