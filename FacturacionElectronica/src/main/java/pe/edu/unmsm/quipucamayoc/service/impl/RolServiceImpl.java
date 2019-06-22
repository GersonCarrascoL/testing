package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.RolModel;
import pe.edu.unmsm.quipucamayoc.persistence.RolPersistence;
import pe.edu.unmsm.quipucamayoc.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired private RolPersistence rolPersistence;
	
	@Override
	public List<RolModel> listarRoles(String dependencia){
		return rolPersistence.getRolXdependencia(dependencia);
	}
	
	@Override
	public List<RolModel> listarRolDni(String dni) {
		return rolPersistence.listarRolDni(dni);
	}
	
	@Override
	public void registrarUsuarioRol(RolModel usuarioRol){
		rolPersistence.registrarUsuarioRol(usuarioRol);
	}
	@Override
	public void actualizarUsuarioRol(RolModel usuarioRol){
		rolPersistence.actualizarUsuarioRol(usuarioRol);
	}
	
	@Override
	public void deleteUsuarioRol(RolModel usuarioRol){
		rolPersistence.deleteUsuarioRol(usuarioRol);
	}
	@Override
	public void actualizarEstado(Integer id_hist, Integer estado){
		rolPersistence.actualizarEstado(id_hist, estado);
	}

}
