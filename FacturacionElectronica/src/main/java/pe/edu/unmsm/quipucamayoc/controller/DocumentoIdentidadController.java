package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.DocumentoIdentidadModel;
import pe.edu.unmsm.quipucamayoc.service.DocumentoIdentidadService;

@Controller
@RequestMapping("/rest/DocumentoIdentidad")
public class DocumentoIdentidadController {
	
	@Autowired private DocumentoIdentidadService documentoIdentidadService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/tiposDocIdentidad")
	@ResponseBody
	public List<DocumentoIdentidadModel> listarTiposDocIdentidad() {
		return documentoIdentidadService.listarTiposDocIdentidad();
	}

}
