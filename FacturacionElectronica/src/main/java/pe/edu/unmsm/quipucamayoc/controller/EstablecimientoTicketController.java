package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.model.EstablecimientoTicketModel;
import pe.edu.unmsm.quipucamayoc.service.EstablecimientoTicketService;

@Controller
@RequestMapping("/rest/EstablecimientoTicketController")
public class EstablecimientoTicketController {
	
	@Autowired private EstablecimientoTicketService establecimientoTicketService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarEstablecimientoTicket")
	@ResponseBody
	public List<EstablecimientoTicketModel> listarEstablecimientoTicket() {
		return establecimientoTicketService.listarEstablecimientoTicket();
	}
}
