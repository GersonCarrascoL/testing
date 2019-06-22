package pe.edu.unmsm.quipucamayoc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pe.edu.unmsm.quipucamayoc.model.ComprobanteModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleNotaCredito;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotaCreditoModel;
import pe.edu.unmsm.quipucamayoc.model.TipoNotaCredito;
import pe.edu.unmsm.quipucamayoc.persistence.NotaCreditoPersistence;
import pe.edu.unmsm.quipucamayoc.service.ComprobantePagoService;
import pe.edu.unmsm.quipucamayoc.service.NotaCreditoService;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Service
public class NotaCreditoServiceImpl implements NotaCreditoService {

	@Autowired
	NotaCreditoPersistence notaCreditoPersistence;
	@Autowired
	private ComprobantePagoService comprobantePagoService;

	private static Logger logger = Logger.getLogger(NotaCreditoServiceImpl.class);

	@Override
	public List<TipoNotaCredito> getTipoNotaCreditoBoleta() {
		return notaCreditoPersistence.getTipoNotaCreditoBoleta();
	}

	@Override
	public List<TipoNotaCredito> getTipoNotaCreditoFactura() {
		return notaCreditoPersistence.getTipoNotaCreditoFactura();
	}

	@Override
	public List<ComprobantePagoModel> getComprobantesParaNotas(String codDependencia, Integer tipoComprobante,Integer numeroComprobante) {
		return notaCreditoPersistence.getComprobantesParaNotas(codDependencia, tipoComprobante,numeroComprobante);
	}

	@Override
	public List<DetalleFacturaModel> getDetalleComprobante(String numeroDocumento, Integer tipo) {
		return notaCreditoPersistence.getDetalleComprobante(numeroDocumento, tipo);
	}

	@Override
	public ComprobantePagoModel getInformacionComprobante(String codDependencia, Integer tipoComprobante) {
		return notaCreditoPersistence.getInformacionComprobante(codDependencia, tipoComprobante);
	}

	@Override
	public void registrarNotaCredito(NotaCreditoModel notaCredito) {
		notaCredito.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
		notaCreditoPersistence.registrarNotaCredito(notaCredito);
	}

	@Override
	public void registrarNotaCreditoDetalle(NotaCreditoModel notaCredito, DetalleNotaCredito detalle) {
		notaCreditoPersistence.registrarNotaCreditoDetalle(notaCredito, detalle);
	}

	@Override
	public EstadoModel enviarFacturador(NotaCreditoModel notaCredito) {
		EstadoModel estado=new EstadoModel();	
		
		ComprobanteModel comprobante = null;
		try{
		comprobante=comprobantePagoService.comprobanteNotaCredito(notaCredito.getTipoComprobante(),
				notaCredito.getCodEstab(), notaCredito.getSerie());
		}catch(NullPointerException e){		
			estado.setComprobante(0);
			estado.setObservacion("No se encontro el comprobante");
		}
		if(comprobante!=null)
			estado=generarArchivosFacturador(comprobante);
		
		return estado;
	}

	private EstadoModel generarArchivosFacturador(ComprobanteModel comprobante) {
		EstadoModel estado= new EstadoModel();
		String cabecera = "20148092282-07-" + comprobante.getDocumentoComprobante() + ".NOT";
		String detalle = "20148092282-07-" + comprobante.getDocumentoComprobante() + ".DET";
		String leyenda = "20148092282-07-" + comprobante.getDocumentoComprobante() + ".LEY";
		PrintWriter pwc = null;
		PrintWriter pwd = null;
		PrintWriter pwl = null;
		try {
			FileOutputStream foc = new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + cabecera);
			FileOutputStream fod = new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + detalle);
			FileOutputStream fol = new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + leyenda);
			pwc = new PrintWriter(foc);
			pwd = new PrintWriter(fod);
			pwl = new PrintWriter(fol);
			DecimalFormat f = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));
			pwc.print(comprobante.getEmision() + "|");
			pwc.print(comprobante.getTipoNota() + "|");
			pwc.print(comprobante.getMotivo() + "|");
			pwc.print(comprobante.getTipoAsocSunat() + "|");
			pwc.print(comprobante.getDocumentoAsociado() + "|");
			switch (comprobante.getTipoDocumento()) {
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
				pwc.print("-|");
				break;
			}
			pwc.print(comprobante.getnDocumento() + "|");
			pwc.print(StringEscapeUtils.escapeXml(StringEscapeUtils.unescapeXml(comprobante.getNombre())) + "|");
			if ("S/".equals(comprobante.getDetalle().get(0).getMoneda()))
				pwc.print("PEN|");
			else if ("$".equals(comprobante.getDetalle().get(0).getMoneda()))
				pwc.print("USD|");

			pwc.print("0.00|");
			pwc.print(comprobante.getGravada() + "|");
			pwc.print(comprobante.getInafecta() + "|");
			pwc.print(comprobante.getExonerada() + "|");
			pwc.print(f.format(comprobante.getIgv()) + "|");
			pwc.print("0.00|");
			pwc.print("0.00|");
			pwc.print(f.format(comprobante.getTotal()));
			pwc.close();
			boolean bandera = true;
			String vacio = "";
			for (DetalleModel i : comprobante.getDetalle()) {
				if (bandera) {
					bandera = false;
				} else {
					pwd.println("");
				}
				pwd.print(i.getUniMedida() + "|");
				pwd.print(i.getCantidad() + "|");
				pwd.print(i.getItem() + "|");
				if (i.getCodSunat() == null) {
					pwd.print("|");
				} else {
					pwd.print(i.getCodSunat() + "|");
				}
				pwd.print(i.getDescripcion() + "|");
				pwd.print(i.getPrecioU() + "|");
				pwd.print("0.00|");
				pwd.print(f.format(i.getIgvU()) + "|");
				pwd.print(i.getCodTipoIgv() + "|");
				pwd.print("0.00|");
				pwd.print("03|");
				pwd.print(new BigDecimal(vacio + Math.round(i.getPrecioU() * 100) / 100.00)
						.add(new BigDecimal(vacio + Math.round(i.getIgvU() * 100) / 100.00)) + "|");
				pwd.print(f.format(i.getPrecioT() + i.getIgvT()));
			}
			pwd.close();
			pwl.print("1000|" + comprobante.getMontoLetras());
			pwl.close();
			estado.setComprobante(1);
			estado.setObservacion("Se ha generado correctamente los archivos para la nota de credito");
		} catch (FileNotFoundException e) {
			logger.fatal("Error en lectura y escritura de archivos para la nota de credito", e);
			estado.setComprobante(0);
			estado.setObservacion("Error en lectura y escritura de archivos para la nota de credito");
		}
		return estado;
	}
}
