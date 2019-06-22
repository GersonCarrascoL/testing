
package pe.edu.unmsm.quipucamayoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.service.BoletaService;

@Controller
@RequestMapping("/rest/BoletaController")
public class BoletaController {

	@Autowired
	private BoletaService boletaService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json", value = "/insertarBoletaS")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public FacturaModel insertarBoletaS(@RequestBody FacturaModel item) {
		return boletaService.insertarBoletaS(item);
	}

}
