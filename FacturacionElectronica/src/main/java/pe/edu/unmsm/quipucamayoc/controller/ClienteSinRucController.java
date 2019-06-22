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

import pe.edu.unmsm.quipucamayoc.model.ClienteSinRucModel;
import pe.edu.unmsm.quipucamayoc.service.ClienteSinRucService;

@Controller
@RequestMapping("/rest/ClienteSinRuc")
public class ClienteSinRucController {

	@Autowired private ClienteSinRucService clienteSinRucService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/listaClienteSinRuc")
	@ResponseBody
	public List<ClienteSinRucModel> listar() {
		return clienteSinRucService.listarClientesSinRuc();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/tipoDoc/{tipoDoc}/numDoc/{numDoc}")
    @ResponseBody
    public List<ClienteSinRucModel> listClientesXTipoDocNumDocTodos(@PathVariable(value = "tipoDoc") Integer tipoDoc, @PathVariable(value = "numDoc") String numDoc){
		return clienteSinRucService.listarClientesXTipoDocNumDoc(tipoDoc, numDoc);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/tipoDoc/{tipoDoc}/numDoc/{numDoc}/estado/{estado}")
    @ResponseBody
    public List<ClienteSinRucModel> listClientesXTipoDocNumDocXEstado(@PathVariable(value = "tipoDoc") Integer tipoDoc, @PathVariable(value = "numDoc") String numDoc, @PathVariable(value = "estado") Integer estado){
		return clienteSinRucService.listarClientesXTipoDocNumDoc(tipoDoc, numDoc, estado);
    }

	@RequestMapping(method = RequestMethod.POST, produces = STR_APPLICATION_JSON, value= "/nombreCompletoTodos")
    @ResponseBody
    public List<ClienteSinRucModel> listClientesXNombreCompletoTodos(@RequestBody String nomCompleto){
		return clienteSinRucService.listarClientesXNombreCompleto(nomCompleto);
    }

	@RequestMapping(method = RequestMethod.POST, produces = STR_APPLICATION_JSON, value= "/nombreCompletoHabilitados")
    @ResponseBody
    public List<ClienteSinRucModel> listClientesXNombreCompletoXEstado(@RequestBody String nomCompleto){
		return clienteSinRucService.listarClientesXNombreCompleto(nomCompleto, 1);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarCliente/")
    @ResponseBody
    public ResponseEntity<Integer> registrarCliente(@RequestBody ClienteSinRucModel cliente){
		clienteSinRucService.registrarCliente(cliente);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(cliente.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: NUMERO DE DOCUMENTO REPETIDO,  -2: OTRO ERROR
		return new ResponseEntity<>(cliente.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarCliente_extra/")
    @ResponseBody
    public ResponseEntity<Integer> registrarClienteExtra(@RequestBody ClienteSinRucModel cliente){
		clienteSinRucService.registrarClienteExtra(cliente);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(cliente.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: NUMERO DE DOCUMENTO REPETIDO,  -2: OTRO ERROR
		return new ResponseEntity<>(cliente.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/idCliente/{idCliente}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = "idCliente") Integer idCliente, @PathVariable(value = "estado") Integer estado){
		clienteSinRucService.actualizarEstado(idCliente, estado);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateCliente/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarCliente(@RequestBody ClienteSinRucModel cliente){
		clienteSinRucService.actualizarCliente(cliente);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(cliente.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: NUMERO DE DOCUMENTO REPETIDO,  -2: OTRO ERROR
		return new ResponseEntity<>(cliente.getStatus().intValue(), hs);

    }
	
	
}
