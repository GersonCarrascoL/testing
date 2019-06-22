
package pe.edu.unmsm.quipucamayoc.service;

import java.util.List;
import pe.edu.unmsm.quipucamayoc.model.BancoModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteUsuarioModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FormaPagoModel;
import pe.edu.unmsm.quipucamayoc.model.PrecioCambioModel;
import pe.edu.unmsm.quipucamayoc.model.TipoImpuestoModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;

public interface FacturaService {

    FacturaModel ingresar(FacturaModel item);
    PrecioCambioModel precioCambio();
    UnidadModel unidad(String udCod);
    List<BancoModel> listarBancos();
    List<FormaPagoModel> listarFormasPago();
    List<ComprobanteUsuarioModel> listarUnidades(String correo);
    List<ComprobanteUsuarioModel> listarUnidadesTicket(String correo);
    List<TipoImpuestoModel> listarTipoImpuesto(String tipo);
    
}
