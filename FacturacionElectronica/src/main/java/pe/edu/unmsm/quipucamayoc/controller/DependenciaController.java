package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.unmsm.quipucamayoc.model.DependenciaModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;
import pe.edu.unmsm.quipucamayoc.service.DependenciaService;

@Controller
@RequestMapping("/rest/Dependencia")
public class DependenciaController {
	
	@Autowired private DependenciaService dependenciaService;
	
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/listarDependencias")
	@ResponseBody
	public List<DependenciaModel> listarDependenciasBase() {
		return dependenciaService.dependenciasBase();	
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/dependenciasUserPerfil/{cod_dependencia}/{cod_perfil}")
	@ResponseBody
	public DependenciaModel dependenciasPorUsuarioYPerfil(@PathVariable(value = "cod_dependencia") String dependencia,@PathVariable(value = "cod_perfil") String perfil) {
		return dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/dependenciasUserPerfilXTeso/")
	@ResponseBody
	public DependenciaModel dependenciasPorUsuarioYPerfilXTeso() {
		return dependenciaService.dependenciasPorUsuarioYPerfilXTeso();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/nombrePadre/{cod_hijo}")
	@ResponseBody
	public DependenciaModel nombrePadre(@PathVariable(value = "cod_hijo") String codHijo) {
		return dependenciaService.nombrePadre(codHijo);	
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value = "/listarFacultadesNivel2")
	@ResponseBody
	public List<UnidadModel> getFacultadesYUnidadesNivel2() {
		return dependenciaService.facultadesYDependencias();
	
	}
}
