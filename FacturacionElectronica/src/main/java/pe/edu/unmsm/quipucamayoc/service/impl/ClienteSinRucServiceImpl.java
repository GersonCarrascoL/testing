package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ClienteSinRucModel;
import pe.edu.unmsm.quipucamayoc.persistence.ClienteSinRucPersistence;
import pe.edu.unmsm.quipucamayoc.service.ClienteSinRucService;

@Service
public class ClienteSinRucServiceImpl implements ClienteSinRucService {

	@Autowired private ClienteSinRucPersistence clienteSinRucPersistence;
	
	@Override
	public List<ClienteSinRucModel> listarClientesSinRuc() {
		return clienteSinRucPersistence.listar();
	}
	
	@Override
	public List<ClienteSinRucModel> listarClientesXTipoDocNumDoc(Integer tipoDocumen, String numDocumen){
		return clienteSinRucPersistence.getClienteXNumDocumentoYTipoTodos(tipoDocumen, numDocumen);
	}
	
	@Override
	public List<ClienteSinRucModel> listarClientesXTipoDocNumDoc(Integer tipoDocumen, String numDocumen, Integer estado){
		return clienteSinRucPersistence.getClienteXNumDocumentoYTipoxEstado(tipoDocumen, numDocumen, estado);
	}
	
	@Override
	public List<ClienteSinRucModel> listarClientesXNombreCompleto(String nombreCompleto){
		return clienteSinRucPersistence.getClienteXNombreCompletoTodos(nombreCompleto);
	}
	
	@Override
	public List<ClienteSinRucModel> listarClientesXNombreCompleto(String nombreCompleto, Integer estado){
		return clienteSinRucPersistence.getClienteXNombreCompletoxEstado(nombreCompleto, estado);
	}
	
	@Override
	public void registrarCliente(ClienteSinRucModel cliente){
		clienteSinRucPersistence.registrarClienteSinRUC(cliente);
	}
	@Override
	public void registrarClienteExtra(ClienteSinRucModel cliente){
		clienteSinRucPersistence.registrarClienteSinRUCExtra(cliente);
	}
	@Override
	public void actualizarEstado(Integer idCliente, Integer estado){
		clienteSinRucPersistence.actualizarEstado(idCliente, estado);
	}
	@Override
	public void actualizarCliente(ClienteSinRucModel cliente){
		clienteSinRucPersistence.actualizarCliente(cliente);
	}

}
