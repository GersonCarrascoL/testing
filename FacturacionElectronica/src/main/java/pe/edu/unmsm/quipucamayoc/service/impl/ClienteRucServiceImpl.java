package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ClienteRucModel;
import pe.edu.unmsm.quipucamayoc.persistence.ClienteRucPersistence;
import pe.edu.unmsm.quipucamayoc.service.ClienteRucService;

@Service
public class ClienteRucServiceImpl implements ClienteRucService {

	@Autowired private ClienteRucPersistence clienteRucPersistence;

	@Override
	public ClienteRucModel buscarRuc(String id) {
		return clienteRucPersistence.buscarRuc(id);
	}

	@Override
	public List<ClienteRucModel> buscarRazon(String id) {
 		String rs = "%";
		rs = rs.concat(id); 
		rs = rs.concat("%");
		return clienteRucPersistence.buscarRazon(rs);
	}
	
	@Override
	public List<ClienteRucModel> buscarXRuc(String ruc){
		return clienteRucPersistence.buscarxRUCTodos(ruc);
	}
	
	@Override
	public List<ClienteRucModel> buscarXRuc(String ruc, Integer estado){
		return clienteRucPersistence.buscarxRUCxEstado(ruc, estado);
	}
	
	@Override
	public List<ClienteRucModel> buscarXRazonSocial(String razonSocial){
		return clienteRucPersistence.buscarRazonSocialTodos(razonSocial);
	}
	
	@Override
	public List<ClienteRucModel> buscarXRazonSocial(String razonSocial, Integer estado){
		return clienteRucPersistence.buscarRazonSocialxEstado(razonSocial, estado);
	}
	
	@Override
	public void registrarClienteRUC(ClienteRucModel cliente){
		clienteRucPersistence.registrarClienteRUC(cliente);
	}
	
	@Override
	public void actualizarEstado(String ruc, Integer estado){
		clienteRucPersistence.actualizarEstado(ruc, estado);
	}
	
	@Override
	public void actualizarCliente(ClienteRucModel cliente){
		clienteRucPersistence.actualizarClienteRUC(cliente);
	}
	
	@Override
	public void registrarClienteRUCExtra(ClienteRucModel cliente){
		clienteRucPersistence.registrarClienteRUCExtra(cliente);
	}
	
	@Override
	public void actualizarClienteExtra(ClienteRucModel cliente){
		clienteRucPersistence.actualizarClienteRUCExtra(cliente);
	}

}
