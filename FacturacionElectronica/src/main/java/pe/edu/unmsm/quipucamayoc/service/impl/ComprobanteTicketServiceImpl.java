package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ComprobanteTicketModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleTicketModel;
import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFechaDependenciaModel;
import pe.edu.unmsm.quipucamayoc.persistence.ComprobanteTicketPersistence;
import pe.edu.unmsm.quipucamayoc.service.ComprobanteTicketService;
@Service
public class ComprobanteTicketServiceImpl implements ComprobanteTicketService{
	
	@Autowired
	private ComprobanteTicketPersistence comprobanteTicketPersistence;
	private static Logger logger = Logger.getLogger(ComprobanteTicketServiceImpl.class);
	private static final String CERO_AM = " 00:00:00";
	private static final String DOCE_PM = " 23:59:59";
	
    @Override
	public List<ComprobanteTicketModel> comprobanteTicket(RangoFechaDependenciaModel rango) {
    	rango.setFechaInicial(rango.getFechaInicial() + CERO_AM);
		rango.setFechaFinal(rango.getFechaFinal() + DOCE_PM);
    	return comprobanteTicketPersistence.comprobanteTicket(rango);
	}
	
    @Override
	public List<MaquinaRegistradoraModel> getMaqRegistradora(Integer udId) {
		return comprobanteTicketPersistence.getMaqRegistradora(udId);
	}
    
    @Override
	public ComprobanteTicketModel insertarTicket(ComprobanteTicketModel item) {
		try {
			comprobanteTicketPersistence.insertarTicket(item,
					SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println("correcto");
			detallar(item);
			return item;
		} catch (Exception e) {
			logger.error("No se logro insertar la boleta: ", e);
			System.out.println(e.getMessage());
			return item;
		}
	}
    
    protected void detallar(ComprobanteTicketModel item) {
		for (DetalleFacturaModel i : item.getDetalle()) {
			comprobanteTicketPersistence.ingresarDetalle(item.getAnexo(),item.getNomCorto(), item.getCorrelativo(),item.getSerieFabMaq(), i);
		}
	}
    
    @Override
	public void anularTicket(String codEstabAnexo,String correlativo){
    	comprobanteTicketPersistence.anularTicket(codEstabAnexo, correlativo);
	}
    
    @Override
	public List<ComprobanteUsuarioModel> listarUnidadesTicket(String correo) {
		return comprobanteTicketPersistence.listarUnidadesTicket(correo);
	}
	

}
