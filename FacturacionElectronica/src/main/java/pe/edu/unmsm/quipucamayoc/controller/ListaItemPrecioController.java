package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pe.edu.unmsm.quipucamayoc.model.ListaItemPrecioModel;
import pe.edu.unmsm.quipucamayoc.model.ListaServiciosModel;
import pe.edu.unmsm.quipucamayoc.service.ListaItemPrecioService;

@Controller
@RequestMapping("/rest/ListaItemPrecioController")
public class ListaItemPrecioController {

	@Autowired private ListaItemPrecioService listaItemPrecioService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/listaItemPrecio")
	@ResponseBody
	public List<ListaItemPrecioModel> listar() {
		return listaItemPrecioService.listaItemPrecio();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/{codigoDependencia}")
    @ResponseBody
    public List<ListaItemPrecioModel> listarPreciosXDependencia(@PathVariable(value = "codigoDependencia") String codigoDependencia){
        return listaItemPrecioService.listaItemPrecioXDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/habilitados/{codigoDependencia}")
    @ResponseBody
    public List<ListaItemPrecioModel> listarPreciosXDependenciaHabilitados(@PathVariable(value = "codigoDependencia") String codigoDependencia){
        return listaItemPrecioService.listaItemXDependenciaHabilitados(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXAdmiCentral/{udIdAdministrativa}")
    @ResponseBody
    public List<ListaItemPrecioModel> listarPreciosXAdmiCentral(@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa){
        return listaItemPrecioService.getListaPreciosXAdmiCentral(udIdAdministrativa);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXAdmiCentral/habilitados/{udIdAdministrativa}")
    @ResponseBody
    public List<ListaItemPrecioModel> listarServiciosXAdmiCentralHabilitados(@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa){
        return listaItemPrecioService.getListaPreciosXAdmiCentralHabilitados(udIdAdministrativa);
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = STR_APPLICATION_JSON,value = "/registrarItem/")
    @ResponseBody
    public ResponseEntity<Integer> registrarOcurrencia(@RequestBody ListaItemPrecioModel item){
			listaItemPrecioService.registrarItem(item);
            HttpStatus hs = HttpStatus.CONFLICT;
    		if(item.getStatus().intValue()==1){ 
    			hs = HttpStatus.CREATED;
    		}
    		return new ResponseEntity<>(item.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/udId/{udId}/codItem/{codItem}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = "udId") int udId, @PathVariable(value = "codItem") String codItem, @PathVariable(value = "estado") Integer estado){
            listaItemPrecioService.actualizarEstado(udId, codItem, estado);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateItem/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarItem(@RequestBody ListaItemPrecioModel item){
            listaItemPrecioService.actualizarItem(item);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listaYConceptoXDependencia/{codigoDependencia}")
    @ResponseBody
    public List<ListaItemPrecioModel> listarPreciosYConceptoXDependencia(@PathVariable(value = "codigoDependencia") String codigoDependencia){
        return listaItemPrecioService.listaItemYConceptoXDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteItemPrecio/{udId}/{codItem}")
    public ResponseEntity<Integer> deleteItemPrecio(@PathVariable(value = "udId") Integer udId, @PathVariable(value = "codItem") String codItem) {		
		ListaItemPrecioModel item = new ListaItemPrecioModel();
		item.setUdId(udId);
		item.setCodigo(codItem);
		listaItemPrecioService.deleteItemPrecio(item);
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(item.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		return new ResponseEntity<>(item.getStatus().intValue(), hs);
    }
}
