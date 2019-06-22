package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.unmsm.quipucamayoc.model.ComprobanteTicketModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleTicketModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.model.MaqLocalModel;
import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFecha;
import pe.edu.unmsm.quipucamayoc.model.RangoFechaDependenciaModel;
import pe.edu.unmsm.quipucamayoc.service.ComprobanteTicketService;

@Controller
@RequestMapping("/rest/ComprobanteTicketController")
public class ComprobanteTicketController {
	
	@Autowired 
	private ComprobanteTicketService comprobanteTicketService;
	//private static Logger logger = Logger.getLogger(ComprobantePagoController.class);
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerComprobanteTicket")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<ComprobanteTicketModel> comprobanteTicket( @RequestBody RangoFechaDependenciaModel rango) {
		return comprobanteTicketService.comprobanteTicket(rango);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value= "/getMaqRegistradora/{udId}")
    @ResponseBody
    public List<MaquinaRegistradoraModel> getMaqRegistradora(@PathVariable(value = "udId") Integer udId){
		return comprobanteTicketService.getMaqRegistradora(udId);
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json", value = "/insertarTicket")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ComprobanteTicketModel insertarTicket(@RequestBody ComprobanteTicketModel item) {
		return comprobanteTicketService.insertarTicket(item);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/anularComprobanteTicket/codEstabAnexo/{codEstabAnexo}/correlativo/{correlativo}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void anularTicket(@PathVariable(value = "codEstabAnexo") String codEstabAnexo, @PathVariable(value = "correlativo") String correlativo){
		comprobanteTicketService.anularTicket(codEstabAnexo, correlativo);
    }
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarUnidadesTicket")
	@ResponseBody
	public List<ComprobanteUsuarioModel> listarUnidadesTicket() {
		return comprobanteTicketService.listarUnidadesTicket(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}


