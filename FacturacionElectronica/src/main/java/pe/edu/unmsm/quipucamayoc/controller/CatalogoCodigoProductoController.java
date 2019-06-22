package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.CatalogoCodigoProducto;
import pe.edu.unmsm.quipucamayoc.service.CatalogoCodigoProductoService;

@Controller
@RequestMapping("/rest/CatCodProductoController")
public class CatalogoCodigoProductoController {
	
	@Autowired private CatalogoCodigoProductoService catalogoCodigoProductoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/listarCatCodProductos")
	@ResponseBody
	public List<CatalogoCodigoProducto> listarCatCodProductos() {
		return catalogoCodigoProductoService.listarCatalogoCodigoProductos();
	}

}
