package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;

public interface ServicioCategoriaService {
	
	public List<ServicioCategoriaModel> getCategoriasServicios(Integer udIddCat);
	public void registrarcategoria(ServicioCategoriaModel categoria);
	public void actualizarcategoria(ServicioCategoriaModel categoria);
	public void deleteCategoria(ServicioCategoriaModel categoria);

}
