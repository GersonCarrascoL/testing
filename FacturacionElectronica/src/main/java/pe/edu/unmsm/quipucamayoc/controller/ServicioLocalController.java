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

import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;
import pe.edu.unmsm.quipucamayoc.model.ServicioLocalModel;
import pe.edu.unmsm.quipucamayoc.service.ServicioLocalService;

@Controller
@RequestMapping("/rest/ServicioLocalController")
public class ServicioLocalController {

	@Autowired private ServicioLocalService servicioLocalService;
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getLocales/{ud_id_adm}")
    @ResponseBody
    public List<ServicioLocalModel> listarLocalesServicios(@PathVariable(value = "ud_id_adm") Integer udIdAdm){
		return servicioLocalService.getLocalesServicios(udIdAdm);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json", value = "/registrarLocal/")
    @ResponseBody
    public ResponseEntity<Integer> registrarLocal(@RequestBody ServicioLocalModel local){
		servicioLocalService.registrarLocal(local);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(local.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: EL NOMBRE DEL LOCAL YA EXISTE, -3: OTRO ERROR: 
		return new ResponseEntity<>(local.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/updateLocal/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarLocal(@RequestBody ServicioLocalModel local){
		servicioLocalService.actualizarLocal(local);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(local.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, 	-3: OTRO ERROR
		return new ResponseEntity<>(local.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteLocal/{idMaquinaLocal}")
    public ResponseEntity<Integer> deleteLocal(@PathVariable(value = "idMaquinaLocal") Integer idMaquinaLocal) {
		
		ServicioLocalModel local = new ServicioLocalModel();
		local.setIdMaquinaLocal(idMaquinaLocal);				
		servicioLocalService.deleteLocal(local);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(local.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL ITEM NO ESTA REGISTRADO,  -3: OTRO ERROR
		return new ResponseEntity<>(local.getStatus().intValue(), hs);
    }
}
