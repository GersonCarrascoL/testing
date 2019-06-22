package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.MaqLocalModel;

public interface MaqLocalService {
	
	public List<MaqLocalModel> getLocalesTodos();
	public List<MaqLocalModel> getLocalesXUdId(Integer udId);

}
