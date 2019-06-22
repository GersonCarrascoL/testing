
package pe.edu.unmsm.quipucamayoc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.persistence.BoletaPersistence;
import pe.edu.unmsm.quipucamayoc.service.BoletaService;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Service
public class BoletaServiceImpl extends FacturacionServiceImpl implements BoletaService {

	@Autowired
	private BoletaPersistence boletaPersistence;
	private static Logger logger = Logger.getLogger(FacturaServiceImpl.class);

	@Override
	public FacturaModel insertarBoletaS(FacturaModel item) {
		try {
			int detraccion = escribir(item);
			boletaPersistence.insertarBoletaEfectivoS(item,
					SecurityContextHolder.getContext().getAuthentication().getName(), detraccion);
			generarTxt(item, Constantes.RUC_BOLETA, "01");
			detallar(item);
			return item;
		} catch (Exception e) {
			logger.error("No se logro insertar la boleta: ", e);
			return item;
		}
	}

}
