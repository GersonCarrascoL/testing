package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.AlumnoSUMModel;

public interface AlumnoSUMService {
	
	public List<AlumnoSUMModel> listarAlumnosXTipoDocNumDoc(Integer tipoDocumen, String numDocumen);
	public List<AlumnoSUMModel> listarAlumnosXNombreCompleto(String nombreCompleto);
	public List<AlumnoSUMModel> listarAlumnosXCodigoMatricula(String codigoMatricula);

}
