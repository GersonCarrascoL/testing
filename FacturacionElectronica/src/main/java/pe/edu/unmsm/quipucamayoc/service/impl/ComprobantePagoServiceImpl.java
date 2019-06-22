package pe.edu.unmsm.quipucamayoc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.BandFactModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.CorreoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleModel;
import pe.edu.unmsm.quipucamayoc.model.EstablecimientoBoletaYFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotificationModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFecha;
import pe.edu.unmsm.quipucamayoc.persistence.ComprobantePagoPersistence;
import pe.edu.unmsm.quipucamayoc.persistence.FacturaPersistence;
import pe.edu.unmsm.quipucamayoc.service.ComprobantePagoService;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Service
public class ComprobantePagoServiceImpl implements ComprobantePagoService {

	@Autowired
	private FacturaPersistence facturaPersistence;
	@Autowired
	private ComprobantePagoPersistence comprobantePagoPersistence;
	private static final int ONCE = 11;
	private static final String CERO_AM = " 00:00:00";
	private static final String DOCE_PM = " 23:59:59";
	private PrintWriter pwl=null;
	@Override
	public List<ComprobantePagoModel> comprobantePago(RangoFecha rango, String usuario) {
		rango.setFechaInicial(rango.getFechaInicial() + CERO_AM);
		rango.setFechaFinal(rango.getFechaFinal() + DOCE_PM);
		return comprobantePagoPersistence.comprobantePago(rango, usuario);
	}
	
	@Override
	public List<ComprobantePagoModel> comprobantePagoAdminCaja(RangoFecha rango, String usuario) {
		rango.setFechaInicial(rango.getFechaInicial() + CERO_AM);
		rango.setFechaFinal(rango.getFechaFinal() + DOCE_PM);
		return comprobantePagoPersistence.comprobantePagoAdminCaja(rango, usuario);
	}

	@Override
	public List<ComprobantePagoModel> getNotasDeCredito(RangoFecha rango, String usuario) {
		rango.setFechaInicial(rango.getFechaInicial() + CERO_AM);
		rango.setFechaFinal(rango.getFechaFinal() + DOCE_PM);
		return comprobantePagoPersistence.getNotasDeCredito(rango, usuario);
	}
	
	@Override
	public List<ComprobantePagoModel> getNotasDeCreditoAdminCaja(RangoFecha rango, String usuario) {
		rango.setFechaInicial(rango.getFechaInicial() + CERO_AM);
		rango.setFechaFinal(rango.getFechaFinal() + DOCE_PM);
		return comprobantePagoPersistence.getNotasDeCreditoAdminCaja(rango, usuario);
	}
	
	@Override
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientosXFacultad(String usuario){
		return comprobantePagoPersistence.listarEstablecimientosXFacultad(usuario);
	}

	@Override
	public void modificar(ComprobantePagoModel item) {
		comprobantePagoPersistence.modificar(item);
	}

	@Override
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFacturaxUsuario(String usuario) {
		return comprobantePagoPersistence.listarEstablecimientoBoletaYFacturaxUsuario(usuario);
	}

	@Override
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFactura() {
		return comprobantePagoPersistence.listarEstablecimientoBoletaYFactura();
	}

	@Override
	public void modificarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoPersistence.modificarEstablecimientoBoletaYFactura(item);
	}

	@Override
	public void borrarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoPersistence.borrarEstablecimientoBoletaYFactura(item);
	}

	@Override
	public void modificarEstadoEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoPersistence.modificarEstadoEstablecimientoBoletaYFactura(item);
	}

	@Override
	public void insertarEstablecimientoBoletaYFactura(EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoPersistence.insertarEstablecimientoBoletaYFactura(item);
	}

	@Override
	public void estado(EstadoModel estado) {
		try {
			comprobantePagoPersistence.estado(estado);
		} catch (Exception e) {
			System.out.println("el error es: "+e);
		}
	}

	@Override
	public ComprobanteModel comprobante(Integer tipo, String establecimiento, String serie) {
		return comprobantePagoPersistence.comprobante(tipo, establecimiento, serie);
	}

	@Override
	public List<DetalleModel> detalle(String temp) {
		return comprobantePagoPersistence.detalle(temp);
	}

	private void rCero(PrintWriter pwc, int i) {
		for (int j = 0; j < i; j++) {
			pwc.print("0.00|");
		}
	}

	@Override
	public void reenviar(EstadoModel estado) throws FileNotFoundException {
		String cabecera;
		String detalle;
		String leyenda;
		ComprobanteModel item = comprobantePagoPersistence.comprobante(estado.getComprobante(),
				estado.getEstablecimiento(), estado.getSerie());
		if (item.getTipo() == 2) {
			cabecera = Constantes.RUC_FACTURA + item.getEstablecimiento() + "-" + item.getSerie() + ".CAB";
			detalle = Constantes.RUC_FACTURA + item.getEstablecimiento() + "-" + item.getSerie() + ".DET";
			leyenda = Constantes.RUC_FACTURA + item.getEstablecimiento() + "-" + item.getSerie() + ".LEY";
		} else {
			cabecera = Constantes.RUC_BOLETA + item.getEstablecimiento() + "-" + item.getSerie() + ".CAB";
			detalle = Constantes.RUC_BOLETA + item.getEstablecimiento() + "-" + item.getSerie() + ".DET";
			leyenda = Constantes.RUC_BOLETA + item.getEstablecimiento() + "-" + item.getSerie() + ".LEY";
		}
		PrintWriter pwc = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + cabecera));
		PrintWriter pwd = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + detalle));
		pwl = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + leyenda));
		DecimalFormat f = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		pwc.print("01|");
		pwc.print(item.getEmision().substring(0, item.getEmision().length() - ONCE) + "|");
		pwc.print(item.getEstablecimiento() + "|");
		switch (item.getTipoDocumento()) {
		case "DNI":
			pwc.print("1|");
			break;
		case "CARNÉ DE EXTRANJERIA":
			pwc.print("4|");
			break;
		case "RUC":
			pwc.print("6|");
			break;
		case "PASAPORTE":
			pwc.print("7|");
			break;
		case "OTROS":
			pwc.print("0|");
			break;
		default:
		}
		pwc.print(item.getnDocumento() + "|");
		pwc.print(StringEscapeUtils.escapeXml(StringEscapeUtils.unescapeXml(item.getNombre())) + "|");
		pwc.print("$".equals(item.getDetalle().get(0).getMoneda()) ? "USD|" : "PEN|");
		rCero(pwc, Constantes.TRES);
		pwc.print(f.format(item.getGravada()) + "|");
		pwc.print(f.format(item.getInafecta()) + "|");
		pwc.print(f.format(item.getExonerada()) + "|");
		pwc.print(f.format(item.getIgv()) + "|");
		rCero(pwc, 2);
		pwc.print(f.format(item.getTotal()));
		pwc.close();
		boolean bandera = true;
		for (DetalleModel i : item.getDetalle()) {
			if (bandera) {
				bandera = false;
			} else {
				pwd.println("");
			}
			pwd.print(i.getUniMedida() + "|");
			pwd.print(i.getCantidad() + "|");
			pwd.print(i.getItem() + "|");
			pwd.print("|");
			pwd.print(i.getDescripcion() + "|");
			pwd.print(i.getPrecioU() + "|");
			rCero(pwd, 1);
			pwd.print(f.format(i.getIgvU()) + "|");
			pwd.print(i.getCodTipoIgv()+"|");
			rCero(pwd, 1);
			pwd.print("03|");
			pwd.print(new BigDecimal(Double.toString(Math.round(i.getPrecioU() * Constantes.I_CIEN) / Constantes.D_CIEN))
					.add(new BigDecimal(Double.toString(Math.round(i.getIgvU() * Constantes.I_CIEN) / Constantes.D_CIEN))) + "|");
			pwd.print(f.format(i.getPrecioT() + i.getIgvT()));
		}
		pwd.close();
		//recorrer(item);
		pwl.print("1000|" + item.getMontoLetras());
		pwl.close();

	}

	private void recorrer(ComprobanteModel item) {
		double monto = "$".equals(item.getDetalle().get(0).getMoneda()) ? Constantes.DETRACCION
				/ facturaPersistence.precioCambio(new java.sql.Date(new Date().getTime()).toString()).getPrecioCambio()
				: Constantes.DETRACCION;
		if (item.getTotal() > monto) {
			for (DetalleModel i : item.getDetalle()) {
				if (i.getDetraccion() != 0) {
					pwl.println(i.getDetraccion() + "|" + i.getDescripcion());
				}
			}
		}
	}

	@Override
	public ComprobanteModel comprobanteNotaCredito(Integer tipo, String establecimiento, String serie) {
		return comprobantePagoPersistence.comprobanteNotaCredito(tipo, establecimiento, serie);
	}

	@Override
	public CorreoModel obtenerCorreoComprobante(CorreoModel correo) {		
		return comprobantePagoPersistence.obtenerCorreoComprobante(correo);
	}

	@Override
	public void modificarEstadoSunat(BandFactModel bandfact) {
		if(bandfact.getInd_situ().equals("0001"))
			bandfact.setInd_situ("03");
		else
			if(bandfact.getInd_situ().equals("0002"))
				bandfact.setInd_situ("05");
			
		comprobantePagoPersistence.modificarEstadoSunat(bandfact);
		
	}

	@Override
	public List<NotificationModel> notificacionComprobantes(Integer ud_id) {		
		return comprobantePagoPersistence.notificacionComprobantes(ud_id);
	}

}
