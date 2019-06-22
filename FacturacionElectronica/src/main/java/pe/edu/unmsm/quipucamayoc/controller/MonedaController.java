package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.MonedaModel;
import pe.edu.unmsm.quipucamayoc.service.MonedaService;

@Controller
@RequestMapping("/rest/MonedaController")
public class MonedaController {
	
	@Autowired private MonedaService monedaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/listarMonedas")
	@ResponseBody
	public List<MonedaModel> listarMonedas() {
		return monedaService.obtieneMonedasActivas();
	}

}
