package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.AlumnoSUMModel;
import pe.edu.unmsm.quipucamayoc.service.AlumnoSUMService;

@Controller
@RequestMapping("/rest/AlumnoSum")
public class AlumnoSUMController {

	@Autowired private AlumnoSUMService alumnoSUMService;	
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/tipoDoc/{tipoDoc}/numDoc/{numDoc}")
    @ResponseBody
    public List<AlumnoSUMModel> listAlumnosXTipoDocNumDoc(@PathVariable(value = "tipoDoc") Integer tipoDoc, @PathVariable(value = "numDoc") String numDoc){
		return alumnoSUMService.listarAlumnosXTipoDocNumDoc(tipoDoc, numDoc);
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value= "/nombreCompleto")
    @ResponseBody
    public List<AlumnoSUMModel> listAlumnosXNombreCompleto(@RequestBody String nomCompleto){
		return alumnoSUMService.listarAlumnosXNombreCompleto(nomCompleto);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/codMatricula/{codMatricula}")
    @ResponseBody
    public List<AlumnoSUMModel> listAlumnosXCodigoMatricula(@PathVariable(value = "codMatricula") String codMatricula){
		return alumnoSUMService.listarAlumnosXCodigoMatricula(codMatricula);
    }
}
