package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.TipoConceptoPagoModel;
import pe.edu.unmsm.quipucamayoc.service.TipoConceptoPagoService;

@Controller
@RequestMapping("/rest/TiposConcepto")
public class TipoConceptoPagoController {
	
	@Autowired private TipoConceptoPagoService tipoConceptoPagoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/listar")
	@ResponseBody
	public List<TipoConceptoPagoModel> listarMonedas() {
		return tipoConceptoPagoService.getTiposConceptoPago();
	}

}
