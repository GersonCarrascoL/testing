package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.TipoResolucionModel;
import pe.edu.unmsm.quipucamayoc.service.TipoResolucionService;

@Controller
@RequestMapping("/rest/TipoResolucionController")
public class TipoResolucionController {
	
	@Autowired private TipoResolucionService tipoResolucionService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getDataTipoResoluciones")
    @ResponseBody
    public List<TipoResolucionModel> listarTipoResoluciones(){
		return tipoResolucionService.listarTiposResoluciones();
    }

}
