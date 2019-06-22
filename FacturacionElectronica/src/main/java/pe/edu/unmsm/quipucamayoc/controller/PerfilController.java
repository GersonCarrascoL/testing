package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.PerfilModel;
import pe.edu.unmsm.quipucamayoc.service.PerfilService;

@Controller
@RequestMapping("/rest/Perfil")
public class PerfilController {
	
	@Autowired private PerfilService perfilService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/tiposPerfil")
	@ResponseBody
	public List<PerfilModel> listarTiposPerfil() {
		return perfilService.listarTiposPerfil();
	}
}
