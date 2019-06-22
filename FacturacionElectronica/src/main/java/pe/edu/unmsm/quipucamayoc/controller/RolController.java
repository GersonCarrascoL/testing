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

import pe.edu.unmsm.quipucamayoc.model.RolModel;
import pe.edu.unmsm.quipucamayoc.service.RolService;

@Controller
@RequestMapping("/rest/RolController") 
public class RolController {
	
@Autowired private RolService rolService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/roles/{cod_dependencia}")
    @ResponseBody
    public List<RolModel> listarRoles(@PathVariable(value = "cod_dependencia") String dependencia){
		return rolService.listarRoles(dependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/rolxDni/{dni}")
    @ResponseBody
    public List<RolModel> listarRolDni(@PathVariable(value = "dni") String dni){
		return rolService.listarRolDni(dni);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/id_hist/{id_hist}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = "id_hist") Integer id_hist, @PathVariable(value = "estado") Integer estado){
		rolService.actualizarEstado(id_hist, estado);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json", value = "/registrarUsuarioRol")
    @ResponseBody        
    public ResponseEntity<Integer> registrarUsuarioRol(@RequestBody RolModel usuarioRol){
		rolService.registrarUsuarioRol(usuarioRol);
		HttpStatus hs = HttpStatus.CONFLICT;
		//System.out.print(usuarioRol.getStatus());
		if(usuarioRol.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, -3: OTRO ERROR: 
		return new ResponseEntity<>(usuarioRol.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/updateUsuarioRol")
    @ResponseBody
    public ResponseEntity<Integer> actualizarUsuarioRol(@RequestBody RolModel usuarioRol){
		rolService.actualizarUsuarioRol(usuarioRol);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(usuarioRol.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL DNI YA EXISTE, 	-3: OTRO ERROR
		return new ResponseEntity<>(usuarioRol.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteUsuarioRol/{idHist}")
    public ResponseEntity<Integer> deleteUsuarioRol(@PathVariable(value = "idHist") Integer idHist) {
		
		RolModel usuarioRol = new RolModel();
		usuarioRol.setId_hist(idHist);
		rolService.deleteUsuarioRol(usuarioRol);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(usuarioRol.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL ITEM NO ESTA REGISTRADO,  -3: OTRO ERROR
		return new ResponseEntity<>(usuarioRol.getStatus().intValue(), hs);
    }
	
}
