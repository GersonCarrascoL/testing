package pe.edu.unmsm.quipucamayoc.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.ServidorModel;
import pe.edu.unmsm.quipucamayoc.service.ServidorService;

@Controller
@RequestMapping("/rest/servidor")
public class ServidorController {

	@Autowired private ServidorService servidorService;
	
	static final String STR_APPLICATION_JSON = "application/json";

	@RequestMapping(method = RequestMethod.POST, produces = STR_APPLICATION_JSON, value= "/nombre")
    @ResponseBody
    public List<ServidorModel> listarXNombreCompleto(@RequestBody String nombreCompleto){
		return servidorService.listarXNombres(nombreCompleto);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/tipoDoc/{tipoDoc}/numDoc/{numDoc}")
    @ResponseBody
    public List<ServidorModel> listarXTipoDocYNumDoc(@PathVariable(value = "tipoDoc") Integer tipoDoc, @PathVariable(value = "numDoc") String numDoc){
		return new LinkedList<>();
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/numDoc/{numDoc}")
    @ResponseBody
    public List<ServidorModel> listarXNumDoc(@PathVariable(value = "numDoc") String numDoc){
		return servidorService.listarXNumDoc(numDoc);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/ruc/{ruc}")
    @ResponseBody
    public List<ServidorModel> listarXRUC(@PathVariable(value = "ruc") String ruc){
		return new LinkedList<>();
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/codTrabajador/{codTrabajador}")
    @ResponseBody
    public List<ServidorModel> listarXCodTrabajador(@PathVariable(value = "codTrabajador") String codTrabajador){
		return new LinkedList<>();
    }
}
