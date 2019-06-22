package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.persistence.MaquinaRegistradoraPersistence;
import pe.edu.unmsm.quipucamayoc.service.MaquinaRegistradoraService;


@Service
public class MaquinaRegistradoraServiceImpl implements MaquinaRegistradoraService {
	
	@Autowired private MaquinaRegistradoraPersistence maquinaRegistradoraPersistence;
	
	@Override
	public List<MaquinaRegistradoraModel> getMaquinaRegistradora(Integer udIdMaq){
		return maquinaRegistradoraPersistence.getMaquinaRegistradora(udIdMaq);
	}
	
	@Override
	public void registrarMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora){
		maquinaRegistradoraPersistence.registrarMaquinaRegistradora(maquinaRegistradora);
	}
	
	@Override
	public void actualizarMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora){
		maquinaRegistradoraPersistence.actualizarMaquinaRegistradora(maquinaRegistradora);
	}

	@Override
	public void deleteMaquinaRegistradora(MaquinaRegistradoraModel maquinaRegistradora) {
		maquinaRegistradoraPersistence.deleteMaquinaRegistradora(maquinaRegistradora);
	}
}
