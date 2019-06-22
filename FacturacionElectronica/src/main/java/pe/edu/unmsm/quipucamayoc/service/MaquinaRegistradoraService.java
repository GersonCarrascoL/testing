package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;


public interface MaquinaRegistradoraService {
	
	public List<MaquinaRegistradoraModel> getMaquinaRegistradora(Integer udIddMaq);
	public void registrarMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora);
	public void actualizarMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora);
	public void	deleteMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora);
}
