package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ComprobanteTicketModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.MaquinaRegistradoraModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFechaDependenciaModel;

public interface ComprobanteTicketService {
	
	public List<ComprobanteTicketModel> comprobanteTicket(RangoFechaDependenciaModel rango);
	public List<MaquinaRegistradoraModel> getMaqRegistradora(Integer udId);
	public ComprobanteTicketModel insertarTicket(ComprobanteTicketModel item);
	List<ComprobanteUsuarioModel> listarUnidadesTicket(String correo);
	public void anularTicket(String codEstabAnexo,String correlativo);
}
