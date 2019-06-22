package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.TipoCambioModel;
import pe.edu.unmsm.quipucamayoc.service.TipoCambioService;

@Controller
@RequestMapping("/rest/TipoCambioController")
public class TipoCambioController {

	@Autowired
	private TipoCambioService tipoCambioService;

	public TipoCambioService getTipoCambioService() {
		return tipoCambioService;
	}

	public void setTipoCambioService(TipoCambioService tipoCambioService) {
		this.tipoCambioService = tipoCambioService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/tipoCambioActual")
	@ResponseBody
	public List<TipoCambioModel> getTipoCambioActual() {
		return tipoCambioService.getTipoCambioActual();
	}

}
