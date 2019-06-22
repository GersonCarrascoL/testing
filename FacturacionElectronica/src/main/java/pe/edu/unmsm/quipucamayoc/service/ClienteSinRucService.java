package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.ClienteSinRucModel;

public interface ClienteSinRucService {
	public List<ClienteSinRucModel> listarClientesSinRuc();
	public List<ClienteSinRucModel> listarClientesXTipoDocNumDoc(Integer tipoDocumen, String numDocumen);
	public List<ClienteSinRucModel> listarClientesXTipoDocNumDoc(Integer tipoDocumen, String numDocumen, Integer estado);
	public List<ClienteSinRucModel> listarClientesXNombreCompleto(String nombreCompleto);
	public List<ClienteSinRucModel> listarClientesXNombreCompleto(String nombreCompleto, Integer estado);
	public void registrarCliente(ClienteSinRucModel cliente);
	public void registrarClienteExtra(ClienteSinRucModel cliente);
	public void actualizarEstado(Integer idCliente, Integer estado);
	public void actualizarCliente(ClienteSinRucModel cliente);
}
