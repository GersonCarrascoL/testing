package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.RolModel;

public interface RolService {
	
	public List<RolModel> listarRoles(String dependencia);
	public List<RolModel> listarRolDni(String dni);
	public void registrarUsuarioRol(RolModel usuarioRol);
	public void actualizarUsuarioRol(RolModel usuarioRol);
	public void deleteUsuarioRol(RolModel usuarioRol);
	public void actualizarEstado(Integer id_hist, Integer estado);
	
}
