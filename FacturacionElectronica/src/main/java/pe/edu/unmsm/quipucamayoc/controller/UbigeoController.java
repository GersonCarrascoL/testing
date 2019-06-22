package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.UbigeoModel;
import pe.edu.unmsm.quipucamayoc.service.UbigeoService;

@Controller
@RequestMapping("/rest/UbigeoController")
public class UbigeoController {
	
	@Autowired private UbigeoService ubigeoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getDataUbigeo")
    @ResponseBody
    public List<UbigeoModel> listarDataUbigeo(){
		return ubigeoService.listarDataUbigeo();
    }

}
