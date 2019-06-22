package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.EstablecimientoTicketModel;
import pe.edu.unmsm.quipucamayoc.persistence.EstablecimientoTicketPersistence;
import pe.edu.unmsm.quipucamayoc.service.EstablecimientoTicketService;
@Service
public class EstablecimientoTicketServiceImpl implements EstablecimientoTicketService{
	
	@Autowired private EstablecimientoTicketPersistence establecimientoTicketPersistence;
	
	@Override
	public List<EstablecimientoTicketModel> listarEstablecimientoTicket(){
		return establecimientoTicketPersistence.listarEstablecimientoTicket();
	}
}
