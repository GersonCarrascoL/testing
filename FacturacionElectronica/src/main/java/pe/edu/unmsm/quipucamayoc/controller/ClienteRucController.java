package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.apache.poi.util.SystemOutLogger;
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

import pe.edu.unmsm.quipucamayoc.model.ClienteRucModel;
import pe.edu.unmsm.quipucamayoc.service.ClienteRucService;

@Controller
@RequestMapping("/rest/ClienteRucController")
public class ClienteRucController {

	@Autowired private ClienteRucService clienteRucService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/ruc/{id}")
    @ResponseBody
    public ClienteRucModel buscarRuc(@PathVariable(value = "id") String id){
		return clienteRucService.buscarRuc(id);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/byruc/{ruc}")
    @ResponseBody
    public List<ClienteRucModel> buscarXRUCTodos(@PathVariable(value = "ruc") String ruc){
		return clienteRucService.buscarXRuc(ruc);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/byruc/{ruc}/estado/{estado}")
    @ResponseBody
    public List<ClienteRucModel> buscarXRUCXEstado(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "estado") Integer estado){
		return clienteRucService.buscarXRuc(ruc, estado);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/razon/{id}")
    @ResponseBody
    public List<ClienteRucModel> buscarRazon(@PathVariable(value = "id") String id){
		return clienteRucService.buscarRazon(id);
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = STR_APPLICATION_JSON, value= "/razonSocialTodos")
    @ResponseBody
    public List<ClienteRucModel> buscarXRazonSocialTodos(@RequestBody String razonSocial){
		return clienteRucService.buscarXRazonSocial(razonSocial);
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = STR_APPLICATION_JSON, value= "/razonSocialHabilitados")
    @ResponseBody
    public List<ClienteRucModel> buscarXRazonSocialHabilitados(@RequestBody String razonSocial){
		return clienteRucService.buscarXRazonSocial(razonSocial, 1);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarClienteRUC/")
    @ResponseBody
    public ResponseEntity<Integer> registrarClienteRUC(@RequestBody ClienteRucModel cliente){
		clienteRucService.registrarClienteRUC(cliente);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(cliente.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: RUC REPETIDO,  -2: RAZON SOCIAL REPETIDA,  -4: OTRO ERROR
		return new ResponseEntity<>(cliente.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarClienteRUC_extra/")
    @ResponseBody
    public ResponseEntity<Integer> registrarClienteRUCextra(@RequestBody ClienteRucModel cliente){
		clienteRucService.registrarClienteRUCExtra(cliente);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(cliente.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		//-1: RUC REPETIDO,  -2: RAZON SOCIAL REPETIDA,  -4: OTRO ERROR
		return new ResponseEntity<>(cliente.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/ruc/{ruc}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = "ruc") String ruc, @PathVariable(value = "estado") Integer estado){
		clienteRucService.actualizarEstado(ruc, estado);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateCliente/")
    @ResponseBody
    public ResponseEntity<String> actualizarCliente(@RequestBody ClienteRucModel cliente){
		clienteRucService.actualizarCliente(cliente);
		if(cliente.getStatus().intValue()==1){
			return new ResponseEntity<>(HttpStatus.OK);		
		}else{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateClienteValidado/")
    @ResponseBody
    public ResponseEntity<String> actualizarClienteExtra(@RequestBody ClienteRucModel cliente){
		clienteRucService.actualizarClienteExtra(cliente);
		if(cliente.getStatus().intValue()==1){
			return new ResponseEntity<>(HttpStatus.OK);		
		}else{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    }
}
