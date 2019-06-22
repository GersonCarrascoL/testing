package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.AlumnoSUMModel;
import pe.edu.unmsm.quipucamayoc.persistence.AlumnoSumPersistence;
import pe.edu.unmsm.quipucamayoc.service.AlumnoSUMService;

@Service
public class AlumnoSUMServiceImpl implements AlumnoSUMService{
	
	@Autowired private AlumnoSumPersistence alumnoSumPersistence;
	
	@Override
	public List<AlumnoSUMModel> listarAlumnosXTipoDocNumDoc(Integer tipoDocumen, String numDocumen){
		return alumnoSumPersistence.getAlumnoXtipoDocNumDoc(tipoDocumen, numDocumen);
	}
	@Override
	public List<AlumnoSUMModel> listarAlumnosXNombreCompleto(String nombreCompleto){
		return alumnoSumPersistence.getAlumnoXNombreCompleto(nombreCompleto);
	}
	@Override
	public List<AlumnoSUMModel> listarAlumnosXCodigoMatricula(String codigoMatricula){
		return alumnoSumPersistence.getAlumnoXcodigoMatricula(codigoMatricula);
	}

}
