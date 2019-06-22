
package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.BancoModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FormaPagoModel;
import pe.edu.unmsm.quipucamayoc.model.PrecioCambioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;
import pe.edu.unmsm.quipucamayoc.service.FacturaService;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Service
public class FacturaServiceImpl extends FacturacionServiceImpl implements FacturaService{

	private static Logger logger = Logger.getLogger(FacturaServiceImpl.class);

	@Override
	public FacturaModel ingresar(FacturaModel item) {
		try {
			int detraccion = escribir(item);
			facturaPersistence.ingresar(item, SecurityContextHolder.getContext().getAuthentication().getName(),
					detraccion);
			generarTxt(item,Constantes.RUC_FACTURA,"01");
			detallar(item);
			return item;
		} catch (Exception e) {
			logger.error("No se logro insertar la factura: ", e);
			return item;
		}
	}


	@Override
	public List<BancoModel> listarBancos() {
		return facturaPersistence.listarBancos();
	}

	@Override
	public List<FormaPagoModel> listarFormasPago() {
		return facturaPersistence.listarFormasPago();
	}

	@Override
	public List<ComprobanteUsuarioModel> listarUnidades(String correo) {
		return facturaPersistence.listarUnidades(correo);
	}
	
	@Override
	public List<ComprobanteUsuarioModel> listarUnidadesTicket(String correo) {
		return facturaPersistence.listarUnidadesTicket(correo);
	}
	

	@Override
	public PrecioCambioModel precioCambio() {
		return facturaPersistence.precioCambio(new java.sql.Date(new Date().getTime()).toString());
	}

	@Override
	public UnidadModel unidad(String udCod) {
		return facturaPersistence.unidad(udCod);
	}

	@Override
	public List<TipoImpuestoModel> listarTipoImpuesto(String tipo) {
		return facturaPersistence.listarTipoImpuesto(tipo);
	}

}
