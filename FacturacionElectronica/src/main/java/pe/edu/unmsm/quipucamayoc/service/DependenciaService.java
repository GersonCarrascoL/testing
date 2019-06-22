package pe.edu.unmsm.quipucamayoc.service;


import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.*;

public interface DependenciaService {
	
	public DependenciaModel dependenciasPorUsuarioYPerfil(String dependencia,String perfil);
	public DependenciaModel dependenciasPorUsuarioYPerfilXTeso();
	public DependenciaModel nombrePadre(String codHijo);
	public List<DependenciaModel>  dependenciasBase();
	public List<UnidadModel> facultadesYDependencias();
	public List<DependenciaModel> unidadesXTeso();
	

}
