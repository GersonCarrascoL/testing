package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ClienteRucModel;

public interface ClienteRucService {
	public ClienteRucModel buscarRuc(String id);
	public List<ClienteRucModel> buscarRazon(String id);
	public List<ClienteRucModel> buscarXRuc(String ruc);
	public List<ClienteRucModel> buscarXRuc(String ruc, Integer estado);
	public List<ClienteRucModel> buscarXRazonSocial(String razonSocial);
	public List<ClienteRucModel> buscarXRazonSocial(String razonSocial,Integer estado);
	public void registrarClienteRUC(ClienteRucModel cliente);
	public void registrarClienteRUCExtra(ClienteRucModel cliente);
	public void actualizarEstado(String ruc, Integer estado);
	public void actualizarCliente(ClienteRucModel cliente);
	public void actualizarClienteExtra(ClienteRucModel cliente);
}
