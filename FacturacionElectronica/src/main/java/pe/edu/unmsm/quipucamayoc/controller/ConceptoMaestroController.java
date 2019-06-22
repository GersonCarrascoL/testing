package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.ConceptoMaestroModel;
import pe.edu.unmsm.quipucamayoc.service.ConceptoMaestroService;

@Controller
@RequestMapping("/rest/conceptoMaestroController")
public class ConceptoMaestroController {

	@Autowired private ConceptoMaestroService conceptoMaestroService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/listarMaestros")
    @ResponseBody
    public List<ConceptoMaestroModel> listarConceptosPagoMaestros(){
        return conceptoMaestroService.conceptosPagosMaestro();
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/buscar/{nombre}/{codigo}/{udIdPadre}/")
    @ResponseBody
    public List<ConceptoMaestroModel> buscarConceptosPagoMaestros(@PathVariable(value = "nombre") String nombre, @PathVariable(value = "codigo") String codigo, @PathVariable(value = "udIdPadre") String udIdPadre){
        return conceptoMaestroService.conceptosPagosXnombreCodigo(nombre, codigo, udIdPadre);
    }
	
}
