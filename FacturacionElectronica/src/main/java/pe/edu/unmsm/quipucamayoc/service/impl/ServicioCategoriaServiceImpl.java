package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;
import pe.edu.unmsm.quipucamayoc.persistence.ServicioCategoriaPersistence;
import pe.edu.unmsm.quipucamayoc.service.ServicioCategoriaService;

@Service
public class ServicioCategoriaServiceImpl implements ServicioCategoriaService{
	
	@Autowired private ServicioCategoriaPersistence servicioCategoriaPersistence;
	
	@Override
	public List<ServicioCategoriaModel> getCategoriasServicios(Integer udIdCat){
		return servicioCategoriaPersistence.getCategoriasServicios(udIdCat);
	}
	
	@Override
	public void registrarcategoria(ServicioCategoriaModel categoria){
		servicioCategoriaPersistence.registrarcategoria(categoria);
	}
	
	@Override
	public void actualizarcategoria(ServicioCategoriaModel categoria){
		servicioCategoriaPersistence.actualizarcategoria(categoria);
	}
	
	@Override
	public void deleteCategoria(ServicioCategoriaModel categoria){
		servicioCategoriaPersistence.deleteCategoria(categoria);
	}


}
