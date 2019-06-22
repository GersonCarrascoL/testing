package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.UnidadMedidaModel;
import pe.edu.unmsm.quipucamayoc.service.UnidadMedidaService;

@Controller
@RequestMapping("/rest/UnidadMedida")
public class UnidadMedidaController {
	
	@Autowired private UnidadMedidaService unidadMedidadService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/listar")
	@ResponseBody
	public List<UnidadMedidaModel> listarUnidadesMedida() {
		return unidadMedidadService.getUnidadesMedida();
	}

}
