package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ServidorModel;
import pe.edu.unmsm.quipucamayoc.persistence.ServidorPersistence;
import pe.edu.unmsm.quipucamayoc.service.ServidorService;

@Service
public class ServidorServiceImpl implements ServidorService{

	@Autowired private ServidorPersistence servidorPersistence;
	
	@Override
	public List<ServidorModel> listarXNombres(String nombre){
		return servidorPersistence.listarXNombres(nombre);
	}
	
	@Override
	public List<ServidorModel> listarXRuc(String ruc){
		return new ArrayList<>();
	}
	
	@Override
	public List<ServidorModel> listarXTipoDocYNumDoc(Integer tipoDoc, String numDoc){
		return new ArrayList<>();
	}
	
	@Override
	public List<ServidorModel> listarXNumDoc(String numDoc){
		return servidorPersistence.listarXNumDocumento(numDoc);
	}
	
	@Override
	public List<ServidorModel> listarXcodTrabajador(String codTrabajador){
		return new ArrayList<>();
	}
}
