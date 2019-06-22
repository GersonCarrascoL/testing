package pe.edu.unmsm.quipucamayoc.service;

import java.io.FileNotFoundException;
import java.util.List;

import pe.edu.unmsm.quipucamayoc.model.BandFactModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.CorreoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleModel;
import pe.edu.unmsm.quipucamayoc.model.EstablecimientoBoletaYFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotificationModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFecha;

public interface ComprobantePagoService {
	List<ComprobantePagoModel> comprobantePago(RangoFecha rango, String usuario);
	List<ComprobantePagoModel> comprobantePagoAdminCaja(RangoFecha rango, String usuario);

	List<ComprobantePagoModel> getNotasDeCredito(RangoFecha rango, String usuario);
	List<ComprobantePagoModel> getNotasDeCreditoAdminCaja(RangoFecha rango, String usuario);
	
	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientosXFacultad(String usuario);

	void modificar(ComprobantePagoModel item);

	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFacturaxUsuario(String usuario);

	List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFactura();

	void modificarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item);

	void borrarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item);

	void modificarEstadoEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item);

	void insertarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item);

	void estado(EstadoModel estado);

	ComprobanteModel comprobante(Integer tipo, String establecimiento, String serie);

	List<DetalleModel> detalle(String temp);

	void reenviar(EstadoModel estado) throws FileNotFoundException;

	ComprobanteModel comprobanteNotaCredito(Integer tipo, String establecimiento, String serie);
	
	CorreoModel obtenerCorreoComprobante(CorreoModel correo);
	
	void modificarEstadoSunat(BandFactModel bandfact);
	
	List<NotificationModel> notificacionComprobantes(Integer ud_id);
}
