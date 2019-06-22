package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.ListaServiciosHistorialModel;
import pe.edu.unmsm.quipucamayoc.service.ListaServiciosHistorialService;

@Controller
@RequestMapping("/rest/ListaServicioHistorialController")
public class ListaServicioHistorialController {
	
	@Autowired private ListaServiciosHistorialService listaServiciosHistorialService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/historialServicio/{udId}/{codItem}/")
    @ResponseBody
    public List<ListaServiciosHistorialModel> listarServicioHistorialXDependencia(@PathVariable(value = "udId") Integer udId, @PathVariable(value = "codItem") String codItem){
        return listaServiciosHistorialService.getListaServicioHistorial(udId, codItem);
    }

}
