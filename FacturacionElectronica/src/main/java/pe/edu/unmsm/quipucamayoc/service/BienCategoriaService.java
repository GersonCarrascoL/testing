package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.BienCategoriaModel;

public interface BienCategoriaService {	
	public List<BienCategoriaModel> getCategoriasBienes(Integer udIdCat);
	public void registrarcategoria(BienCategoriaModel categoria);
	public void actualizarcategoria(BienCategoriaModel categoria);
	public void deleteCategoria(BienCategoriaModel categoria);

}
