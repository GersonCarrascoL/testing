package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.CatalogoCodigoProducto;
import pe.edu.unmsm.quipucamayoc.persistence.CatalogoCodigoProductoPersistence;
import pe.edu.unmsm.quipucamayoc.service.CatalogoCodigoProductoService;

@Service
public class CatalogoCodigoProductoServiceImpl implements CatalogoCodigoProductoService{
	
	@Autowired private CatalogoCodigoProductoPersistence catalogoCodigoProductoPersistence;
	
	@Override
	public List<CatalogoCodigoProducto> listarCatalogoCodigoProductos(){
		return catalogoCodigoProductoPersistence.listarCatalogoCodigoProductos();
	}	

}
