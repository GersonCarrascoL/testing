package pe.edu.unmsm.quipucamayoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.UsuarioModel;
import pe.edu.unmsm.quipucamayoc.persistence.UsuarioPersistence;
import pe.edu.unmsm.quipucamayoc.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioPersistence usuarioPersistence;

	@Override
	public UsuarioModel datosDeUsuario(String userName) {

		return usuarioPersistence.datosDeUsuario(userName);
	}

	public UsuarioPersistence getUsuarioPersistence() {
		return usuarioPersistence;
	}

	public void setUsuarioPersistence(UsuarioPersistence usuarioPersistence) {
		this.usuarioPersistence = usuarioPersistence;
	}

}
