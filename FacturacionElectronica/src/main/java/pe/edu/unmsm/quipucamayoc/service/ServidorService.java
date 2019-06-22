package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ServidorModel;

public interface ServidorService {

	public List<ServidorModel> listarXNombres(String nombre);
	public List<ServidorModel> listarXRuc(String ruc);
	public List<ServidorModel> listarXTipoDocYNumDoc(Integer tipoDoc, String numDoc);
	public List<ServidorModel> listarXNumDoc(String numDoc);
	public List<ServidorModel> listarXcodTrabajador(String codTrabajador);
}
