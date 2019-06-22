package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ServicioLocalModel;

public interface ServicioLocalService {

	public List<ServicioLocalModel> getLocalesServicios(Integer udIdAdm);
	public void registrarLocal(ServicioLocalModel local);
	public void actualizarLocal(ServicioLocalModel local);
	public void deleteLocal(ServicioLocalModel local);
}
