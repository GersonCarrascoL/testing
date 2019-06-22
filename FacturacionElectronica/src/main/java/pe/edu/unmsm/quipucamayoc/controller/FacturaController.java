
package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.edu.unmsm.quipucamayoc.model.BancoModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FormaPagoModel;
import pe.edu.unmsm.quipucamayoc.model.PrecioCambioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;
import pe.edu.unmsm.quipucamayoc.service.FacturaService;

@Controller
@RequestMapping("/rest/FacturaController")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/ESGSC")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public FacturaModel eSGSC(@RequestBody FacturaModel item) {
		return facturaService.ingresar(item);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/ECGSC")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public FacturaModel eCGSC(@RequestBody FacturaModel item) {
		return facturaService.ingresar(item);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarBancos")
	@ResponseBody
	public List<BancoModel> listarBancos() {
		return facturaService.listarBancos();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarFormasPago")
	@ResponseBody
	public List<FormaPagoModel> listarFormasPago() {
		return facturaService.listarFormasPago();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarUnidades")
	@ResponseBody
	public List<ComprobanteUsuarioModel> listarUnidades() {
		return facturaService.listarUnidades(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarUnidadesTicket")
	@ResponseBody
	public List<ComprobanteUsuarioModel> listarUnidadesTicket() {
		return facturaService.listarUnidadesTicket(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/precioCambio")
	@ResponseBody
	public PrecioCambioModel precioCambio() {
		return facturaService.precioCambio();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/unidad/{codigo}")
	@ResponseBody
	public UnidadModel unidad(@PathVariable(value = "codigo") String codigo) {
		return facturaService.unidad(codigo);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarTipoImpuesto/{tipo}")
	@ResponseBody
	public List<TipoImpuestoModel> listarTipoImpuesto(@PathVariable(value = "tipo") String tipo) {
		return facturaService.listarTipoImpuesto(tipo);
	}

}
