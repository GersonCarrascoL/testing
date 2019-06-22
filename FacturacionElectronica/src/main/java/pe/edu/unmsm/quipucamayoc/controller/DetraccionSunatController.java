package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.DetraccionSunatModel;
import pe.edu.unmsm.quipucamayoc.service.DetraccionSunatService;

@Controller
@RequestMapping("/rest/DetraccionSunat")
public class DetraccionSunatController {

	@Autowired private DetraccionSunatService detraccionSunatService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/importeMinimoDet")
	@ResponseBody
	public List<DetraccionSunatModel> importeMinimoDetraccion() {
		return detraccionSunatService.importeMinimoDetraccion();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/porcentajeDetraccionUsoGeneral")
	@ResponseBody
	public List<DetraccionSunatModel> porcentajeDetraccionUsoGeneral() {
		return detraccionSunatService.porcentajeDetraccionUsoGeneral();
	}
}
