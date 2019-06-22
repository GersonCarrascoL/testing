package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.PerfilModel;
import pe.edu.unmsm.quipucamayoc.persistence.PerfilServicePersistence;
import pe.edu.unmsm.quipucamayoc.service.PerfilService;

@Service
public class PerfilServiceImp implements PerfilService{
	
	@Autowired private PerfilServicePersistence perfilServicePersistence;
	
	@Override
	public List<PerfilModel> listarTiposPerfil(){
		return perfilServicePersistence.listarTiposPerfil();
	}
}
