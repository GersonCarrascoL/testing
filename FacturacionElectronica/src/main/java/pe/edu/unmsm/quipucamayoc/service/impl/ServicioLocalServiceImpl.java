package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;
import pe.edu.unmsm.quipucamayoc.model.ServicioLocalModel;
import pe.edu.unmsm.quipucamayoc.persistence.ServicioLocalPersistence;
import pe.edu.unmsm.quipucamayoc.service.ServicioLocalService;
@Service
public class ServicioLocalServiceImpl implements ServicioLocalService{
	
	@Autowired private ServicioLocalPersistence servicioLocalPersistence;
	
	@Override
	public List<ServicioLocalModel> getLocalesServicios(Integer udIdAdm){
		return servicioLocalPersistence.getLocalesServicios(udIdAdm);
	}
	
	@Override
	public void registrarLocal(ServicioLocalModel local){
		servicioLocalPersistence.registrarLocal(local);
	}
	@Override
	public void actualizarLocal(ServicioLocalModel local){
		servicioLocalPersistence.actualizarLocal(local);
	}
	@Override
	public void deleteLocal(ServicioLocalModel local){
		servicioLocalPersistence.deleteLocal(local);
	}
	
}
