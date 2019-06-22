
package pe.edu.unmsm.quipucamayoc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.FacturaModel;
import pe.edu.unmsm.quipucamayoc.persistence.FacturaPersistence;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Service
public class FacturacionServiceImpl {
	
	@Autowired
	protected FacturaPersistence facturaPersistence;
	private PrintWriter pwl = null;

	protected void generarTxt(FacturaModel item, String ruc, String n) throws FileNotFoundException {
		String cabecera = ruc + item.getEstablecimiento() + "-" + item.getSerie() + ".CAB";
		String detalle = ruc + item.getEstablecimiento() + "-" + item.getSerie() + ".DET";
		String leyenda = ruc + item.getEstablecimiento() + "-" + item.getSerie() + ".LEY";
		PrintWriter pwc = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + cabecera));
		PrintWriter pwd = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + detalle));
		pwl = new PrintWriter(new FileOutputStream(Constantes.CONSTANTE_RUTA_FACTURADOR + leyenda));
		DecimalFormat f = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.ENGLISH));
		pwc.print(n+"|");
		pwc.print(item.getEmision() + "|");
		pwc.print(item.getEstablecimiento() + "|");
		switch (item.getTipoDoc()) {
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
		pwc.print(item.getDocumento() + "|");
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
		for (DetalleFacturaModel i : item.getDetalle()) {
			if (bandera) {
				bandera = false;
			} else {
				pwd.println("");
			}
			pwd.print(i.getnUnidadMedida() + "|");
			pwd.print(i.getCantidad() + "|");
			pwd.print(i.getCodigo() + "|");
			pwd.print((i.getCodProductoSUNAT() == null) ? "|" : i.getCodProductoSUNAT() + "|");
			pwd.print(i.getDescripcion() + "|");
			pwd.print(new BigDecimal(Double.toString(Math.round(i.getPrecioU()  * Constantes.I_CIEN) / Constantes.D_CIEN))+ "|");
			rCero(pwd, 1);
			pwd.print(f.format(i.getIgvU()) + "|");
			pwd.print(i.getCodTipoIgv() + "|");
			rCero(pwd, 1);
			pwd.print("03|");
			pwd.print(new BigDecimal(Double.toString(Math.round(i.getPrecioU() * Constantes.I_CIEN) / Constantes.D_CIEN))
					.add(new BigDecimal(Double.toString(Math.round(i.getIgvU() * Constantes.I_CIEN) / Constantes.D_CIEN))) + "|");
			pwd.print(f.format(i.getPrecioT() + i.getIgvT()));
		}
		pwd.close();
		//recorrer(item);
	}

	protected void recorrer(FacturaModel item) {
		double monto = "$".equals(item.getDetalle().get(0).getMoneda()) ? Constantes.DETRACCION
				/ facturaPersistence.precioCambio(new java.sql.Date(new Date().getTime()).toString()).getPrecioCambio()
				: Constantes.DETRACCION;
		if (item.getTotal() > monto) {
			for (DetalleFacturaModel i : item.getDetalle()) {
				if (i.getPoseeDetraccion() == 1) {
					pwl.println(i.getCodDetraccion() + "|" + i.getDescripcion());
				}
			}
		}
	}

	protected void rCero(PrintWriter pwc, int i) {
		for (int j = 0; j < i; j++) {
			pwc.print("0.00|");
		}
	}

	protected int escribir(FacturaModel item) {
		int detraccion = 0;
		double monto = "$".equals(item.getDetalle().get(0).getMoneda()) ? Constantes.DETRACCION
				/ facturaPersistence.precioCambio(new java.sql.Date(new Date().getTime()).toString()).getPrecioCambio()
				: Constantes.DETRACCION;
		if (item.getTotal() > monto) {
			for (DetalleFacturaModel i : item.getDetalle()) {
				if (i.getPoseeDetraccion() == 1) {
					detraccion = Integer.parseInt(i.getCodDetraccion());
				}
			}
		}
		return detraccion;
	}

	protected void detallar(FacturaModel item) {
		for (DetalleFacturaModel i : item.getDetalle()) {
			if (i.getCodDetraccion() == null) {
				i.setCodDetraccion("0000");
			}
			facturaPersistence.ingresarDetalle(item.getEstablecimiento(), item.getSerie(), item.getTipo(), i);
		}
		montoLetras(item.getTipo(), item.getEstablecimiento(), item.getSerie());
	}

	protected void montoLetras(Integer tipo, String establecimiento, String serie) {
		pwl.print("1000|" + facturaPersistence.montoLetras(tipo, establecimiento, serie));
		pwl.close();
	}

	protected void moneda(FacturaModel item) {
		item.setImporte("SOLES".equals(item.getMoneda()) ? "S/ " + item.getImporte() : "$ " + item.getImporte());
	}
	
}
