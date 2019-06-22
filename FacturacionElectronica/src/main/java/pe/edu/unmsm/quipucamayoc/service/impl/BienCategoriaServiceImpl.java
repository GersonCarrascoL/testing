package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.BienCategoriaModel;
import pe.edu.unmsm.quipucamayoc.persistence.BienCategoriaPersistence;
import pe.edu.unmsm.quipucamayoc.service.BienCategoriaService;

@Service
public class BienCategoriaServiceImpl implements BienCategoriaService{	
	
	@Autowired private BienCategoriaPersistence bienCategoriaPersistence;
	
	@Override
	public List<BienCategoriaModel> getCategoriasBienes(Integer udIdCat){
		return bienCategoriaPersistence.getCategoriasBienes(udIdCat);
	}
	
	@Override
	public void registrarcategoria(BienCategoriaModel categoria){
		bienCategoriaPersistence.registrarcategoria(categoria);
	}
	
	@Override
	public void actualizarcategoria(BienCategoriaModel categoria){
		bienCategoriaPersistence.actualizarcategoria(categoria);
	}
	
	@Override
	public void deleteCategoria(BienCategoriaModel categoria){
		bienCategoriaPersistence.deleteCategoria(categoria);
	}


}
