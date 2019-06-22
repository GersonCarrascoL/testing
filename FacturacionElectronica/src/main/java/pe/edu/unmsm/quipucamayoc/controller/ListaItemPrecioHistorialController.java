package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioHistorialModel;
import pe.edu.unmsm.quipucamayoc.service.ListaItemPrecioHistorialService;

@Controller
@RequestMapping("/rest/ListaPrecioHistorialController")
public class ListaItemPrecioHistorialController {

	@Autowired private ListaItemPrecioHistorialService lpHistorialService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/historialItem/{udId}/{codItem}/")
    @ResponseBody
    public List<ListaItemPrecioHistorialModel> listarPreciosHistorialXDependencia(@PathVariable(value = "udId") Integer udId, @PathVariable(value = "codItem") String codItem){
        return lpHistorialService.listarHistorialXItem(udId, codItem);
    }
	
}
