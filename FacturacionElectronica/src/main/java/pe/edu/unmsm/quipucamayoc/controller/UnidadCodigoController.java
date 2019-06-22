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

import pe.edu.unmsm.quipucamayoc.model.UnidadCodigoModel;
import pe.edu.unmsm.quipucamayoc.service.UnidadCodigoService;

@Controller
@RequestMapping("/rest/UnidadCodigo")
public class UnidadCodigoController {
	
	@Autowired private UnidadCodigoService unidadCodigoService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/unidadesRegXDependencia/{udId_padreRegistrado}")
	@ResponseBody
	public List<UnidadCodigoModel> getUnidadesRegXDependenciaPadre(@PathVariable(value = "udId_padreRegistrado") String udIdPadreRegistrado) {
		return unidadCodigoService.unidadesCodigoXDependencia(udIdPadreRegistrado);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/udIdPadre/{udIdPadreReg}/codUnidad/{codUnidad}/")
	@ResponseBody
	public List<UnidadCodigoModel> getUnidadesRegXDep(@PathVariable(value = "udIdPadreReg") String udIdPadreReg, @PathVariable(value = "codUnidad") String codUnidad) {
		return unidadCodigoService.unidadesCodigoXDep(udIdPadreReg, codUnidad);
	}
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarUnidadCodigo/")
    @ResponseBody
    public ResponseEntity<Integer> registrarUnidadCodigo(@RequestBody UnidadCodigoModel unidad){
		unidadCodigoService.registrarUnidadCodigo(unidad);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(unidad.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: LA UNIDAD YA TIENE UN CODIGO ASIGNADO, -2: CODIGO REPETIDO  -4: OTRO ERROR
		return new ResponseEntity<>(unidad.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteUnidadCodigo/{ud_id}/{codigo_unidad}")
    public ResponseEntity<Integer> deleteUnidadCodigo(@PathVariable(value = "ud_id") Integer udId, @PathVariable(value = "codigo_unidad") String codigoUnidad) {
		UnidadCodigoModel unidad = new UnidadCodigoModel();
		unidad.setUdIdUnidad(udId);
		unidad.setNumUnidad(codigoUnidad);
		unidadCodigoService.deleteUnidadCodigo(unidad);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(unidad.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: CODIGO DE UNIDAD NO EXISTE,  -4: OTRO ERROR
		return new ResponseEntity<>(unidad.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateUnidadCodigo/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarUnidadCodigo(@RequestBody UnidadCodigoModel unidad){
		unidadCodigoService.actualizarUnidadCodigo(unidad);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(unidad.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: COD UNIDAD REPETIDO,  -4: OTRO ERROR
		return new ResponseEntity<>(unidad.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/unidadesRegTodos")
	@ResponseBody
	public List<UnidadCodigoModel> getUnidadesRegTodos() {
		return unidadCodigoService.codigoUnidadesTodos();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/aprobarUnidRec/udId/{ud_id}/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void aprobarUnidRec(@PathVariable(value = "ud_id") Integer udId){
		unidadCodigoService.aprobarUnidadCodigo(udId);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/rechazarUnidRec/{ud_id}/{motivoRechazo}/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void rechazarUnidRec(@PathVariable(value = "ud_id") Integer udId, @PathVariable(value = "motivoRechazo") String motivoRechazo){
		unidadCodigoService.rechazarUnidadCodigo(udId, motivoRechazo);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/volverEnviarUnidRec/")
    @ResponseBody
    public ResponseEntity<Integer> volverEnviarUnidRec(@RequestBody UnidadCodigoModel unidad){
		unidadCodigoService.volverEnviarUnidadCodigo(unidad);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(unidad.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: COD UNIDAD REPETIDO,  -4: OTRO ERROR
		return new ResponseEntity<>(unidad.getStatus().intValue(), hs);
    }

}
