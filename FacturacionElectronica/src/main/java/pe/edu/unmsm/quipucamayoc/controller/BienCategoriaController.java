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

import pe.edu.unmsm.quipucamayoc.model.BienCategoriaModel;
import pe.edu.unmsm.quipucamayoc.service.BienCategoriaService;

@Controller
@RequestMapping("/rest/BienCategoriaController")
public class BienCategoriaController {
	
	@Autowired private BienCategoriaService bienCategoriaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getCategorias/{ud_id_cat}")
    @ResponseBody
    public List<BienCategoriaModel> listarCategoriasServicios(@PathVariable(value = "ud_id_cat") Integer udIdCat){
		return bienCategoriaService.getCategoriasBienes(udIdCat);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json", value = "/registrarCategoria/")
    @ResponseBody
    public ResponseEntity<Integer> registrarCategoria(@RequestBody BienCategoriaModel categoria){
		bienCategoriaService.registrarcategoria(categoria);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/updateCategoria/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarCategoria(@RequestBody BienCategoriaModel categoria){
		bienCategoriaService.actualizarcategoria(categoria);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteCategoria/{idBienCata}")
    public ResponseEntity<Integer> deleteCategoria(@PathVariable(value = "idBienCata") Integer idBienCata) {		
		BienCategoriaModel categoria = new BienCategoriaModel();
		categoria.setIdBienCata(idBienCata);				
		bienCategoriaService.deleteCategoria(categoria);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(categoria.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		return new ResponseEntity<>(categoria.getStatus().intValue(), hs);
    }
	
	

}
