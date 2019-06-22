package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.SunatCatalogoN15Model;
import pe.edu.unmsm.quipucamayoc.model.TipoOperacionIGVModel;
import pe.edu.unmsm.quipucamayoc.service.SunatCatalogoN15Service;
import pe.edu.unmsm.quipucamayoc.service.SunatGeneralService;

@Controller
@RequestMapping("/rest/SunatCatalogosController")
public class SunatCatalogosController {
	
	@Autowired private SunatCatalogoN15Service sunatCatalogoN15Service;
	@Autowired private SunatGeneralService sunatGeneralService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/catN15/listarCatDetracciones")
	@ResponseBody
	public List<SunatCatalogoN15Model> listarCataDetracciones() {
		return sunatCatalogoN15Service.listarDataDetracciones();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/catN15/codUsoGeneralDetraccion")
	@ResponseBody
	public List<SunatCatalogoN15Model> codigoUsoGeneralCatalogoDetraccion() {
		return sunatCatalogoN15Service.getCodGeneralCatalogoDetraccion();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/cataGeneral/tiposOperacionIgv")
	@ResponseBody
	public List<TipoOperacionIGVModel> listarTiposOperacionesIGV() {
		return sunatGeneralService.listarTiposOperacionIgv();
	}

}
