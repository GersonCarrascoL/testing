package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.DocumentoIdentidadModel;
import pe.edu.unmsm.quipucamayoc.persistence.DocumentoIdentidadPersistence;
import pe.edu.unmsm.quipucamayoc.service.DocumentoIdentidadService;

@Service
public class DocumentoIdentidadServiceImpl implements DocumentoIdentidadService {
	
	@Autowired private DocumentoIdentidadPersistence documentoIdentidadPersistence;
	
	@Override
	public List<DocumentoIdentidadModel> listarTiposDocIdentidad(){
		return documentoIdentidadPersistence.listarTiposDocIdentidad();
	}

}
