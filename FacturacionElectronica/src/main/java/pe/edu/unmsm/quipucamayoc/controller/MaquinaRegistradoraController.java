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

import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.model.ServicioCategoriaModel;
import pe.edu.unmsm.quipucamayoc.service.MaquinaRegistradoraService;

@Controller
@RequestMapping("/rest/MaquinaRegistradoraController")

public class MaquinaRegistradoraController {
	
	@Autowired private MaquinaRegistradoraService maquinaRegistradoraService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getMaqReg/{ud_id_maq}")
    @ResponseBody
    public List<MaquinaRegistradoraModel> listarMaquinaRegistradora(@PathVariable(value = "ud_id_maq") Integer udIdMaq){
		return maquinaRegistradoraService.getMaquinaRegistradora(udIdMaq);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json", value = "/registrarMaquina/")
    @ResponseBody
    public ResponseEntity<Integer> registrarMaquinaRegistradora(@RequestBody MaquinaRegistradoraModel maquinaRegistradora){
		maquinaRegistradoraService.registrarMaquinaRegistradora(maquinaRegistradora);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(maquinaRegistradora.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, -3: OTRO ERROR: 
		return new ResponseEntity<>(maquinaRegistradora.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", value = "/updateMaqRegistradora/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarMaquinaRegistradora(@RequestBody MaquinaRegistradoraModel maqRegistradora){
		maquinaRegistradoraService.actualizarMaquinaRegistradora(maqRegistradora);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(maqRegistradora.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL NOMBRE DE CATEGORIA YA EXISTE, 	-3: OTRO ERROR
		return new ResponseEntity<>(maqRegistradora.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteMaqRegistradora/{idMaqReg}")
    public ResponseEntity<Integer> deleteMaquinaRegistradora(@PathVariable(value = "idMaqReg") Integer idMaqReg) {
		
		MaquinaRegistradoraModel maqRegistradora = new MaquinaRegistradoraModel();
		maqRegistradora.setIdMaquinaRegistradora(idMaqReg);				
		maquinaRegistradoraService.deleteMaquinaRegistradora(maqRegistradora);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(maqRegistradora.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: EL ITEM NO ESTA REGISTRADO,  -3: OTRO ERROR
		return new ResponseEntity<>(maqRegistradora.getStatus().intValue(), hs);
    }
}
