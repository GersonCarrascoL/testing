package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.edu.unmsm.quipucamayoc.model.ListaServiciosModel;
import pe.edu.unmsm.quipucamayoc.service.ListaServiciosService;

@Controller
@RequestMapping("/rest/ListaServiciosController")
public class ListaServiciosController {
	@Autowired private ListaServiciosService listaServiciosService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/{codigoDependencia}")
    @ResponseBody
    public List<ListaServiciosModel> listarServiciosXDependencia(@PathVariable(value = "codigoDependencia") String codigoDependencia){
        return listaServiciosService.getListaServiciosXDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/habilitados/{codigoDependencia}")
    @ResponseBody
    public List<ListaServiciosModel> listarServiciosXDependenciaHabilitados(@PathVariable(value = "codigoDependencia") String codigoDependencia){
        return listaServiciosService.getListaServiciosXDependenciaHabilitados(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXAdmiCentral/{udIdAdministrativa}")
    @ResponseBody
    public List<ListaServiciosModel> listarServiciosXAdmiCentral(@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa){
        return listaServiciosService.getListaServiciosXAdmiCentral(udIdAdministrativa);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXAdmiCentral/habilitados/{udIdAdministrativa}")
    @ResponseBody
    public List<ListaServiciosModel> listarServiciosXAdmiCentralHabilitados(@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa){
        return listaServiciosService.getListaServiciosXAdmiCentralHabilitados(udIdAdministrativa);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarServicio/")
    @ResponseBody
    public ResponseEntity<Integer> registrarServicio(@RequestBody ListaServiciosModel item){
		listaServiciosService.registrarServicio(item);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(item.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		//2: ERROR AL REGISTRAR
		return new ResponseEntity<>(item.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/udId/{udId}/codItem/{codItem}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = "udId") int udId, @PathVariable(value = "codItem") String codItem, @PathVariable(value = "estado") Integer estado){
		listaServiciosService.actualizarEstado(udId, codItem, estado);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateServicio/")
    @ResponseBody
    public ResponseEntity<String> actualizarServicio(@RequestBody ListaServiciosModel servicio){
		listaServiciosService.actualizarServicio(servicio);
		if(servicio.getStatus().intValue()==1){
			return new ResponseEntity<>(HttpStatus.OK);		
		}else{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteServicio/{udId}/{codItem}")
    public ResponseEntity<Integer> deleteServicio(@PathVariable(value = "udId") Integer udId, @PathVariable(value = "codItem") String codItem) {		
		ListaServiciosModel item = new ListaServiciosModel();
		item.setUdId(udId);
		item.setCodigo(codItem);		
		listaServiciosService.deleteItemServicio(item);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(item.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		//-1: EL ITEM NO ESTA REGISTRADO,  -2: EL ITEM ESTA SIENDO USADO, NO SE PERMITIRA BORRAR  -4: OTRO ERROR
		return new ResponseEntity<>(item.getStatus().intValue(), hs);
    }
	
}
