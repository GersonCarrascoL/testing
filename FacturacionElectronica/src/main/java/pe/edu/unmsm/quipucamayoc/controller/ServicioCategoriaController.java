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
import pe.edu.unmsm.quipucamayoc.service.ServicioCategoriaService;

@Controller
@RequestMapping("/rest/ServicioCategoriaController")
public class ServicioCategoriaController {
	
	@Autowired private ServicioCategoriaService servicioCategoriaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getCategorias/{ud_id_cat}")
    @ResponseBody
    public List<ServicioCategoriaModel> listarCategoriasServicios(@PathVariable(value = "ud_id_cat") Integer udIdCat){
		return servicioCategoriaService.getCategoriasServicios(udIdCat);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json", value = "/registrarCategoria/")
    @ResponseBody
    public ResponseEntity<Integer> registrarCategoria(@RequestBody ServicioCategoriaModel categoria){
		servicioCategoriaService.registrarcategoria(categoria);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, -3: OTRO ERROR: 
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/updateCategoria/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarCategoria(@RequestBody ServicioCategoriaModel categoria){
		servicioCategoriaService.actualizarcategoria(categoria);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, 	-3: OTRO ERROR
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteCategoria/{idServCata}")
    public ResponseEntity<Integer> deleteCategoria(@PathVariable(value = "idServCata") Integer idServCata) {
		
		ServicioCategoriaModel categoria = new ServicioCategoriaModel();
		categoria.setIdServCata(idServCata);				
		servicioCategoriaService.deleteCategoria(categoria);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL ITEM NO ESTA REGISTRADO,  -3: OTRO ERROR
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }

}
