package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.service.ReporteService;
import pe.edu.unmsm.quipucamayoc.util.ReportDownloader;

@Service
public class ReporteServiceImpl implements ReporteService {
	public static final String XLS = ".xls";
	public static final String PDF = ".pdf";
	public static final String REPORT_LOCALE = "REPORT_LOCALE";
	public static final String USUARIO = "usuario";
	public static final String COD_UNIDAD = "codUnidad";
	public static final String UD_COD_ESTABLECIMIENTO = "udCodEstablecimiento";
	public static final String UD_ID_ADMINISTRATIVA = "udIdAdministrativa";
	public static final String LOGO_QUIPU = "logo_quipu";
	public static final String LOGO_ANULADO = "anulado";
	public static final String LOGO = "logo";
	public static final String REPORTE_VENTAS = "Reporte_Ventas_";
	public static final String RUTA_LOGO = "/reportes/EscudoUNMSM.png";
	public static final String RUTA_LOGO_QUIPU = "/reportes/logo_quipu.png";
	public static final String REPORTE_SERVICIOS = "Reporte_Servicios_";
	public static final String REPORTE_BIENES = "Reporte_Bienes_";
	public static final String RUTA_LOGO_ANULADO = "/reportes/anulado.png";
	
	@Autowired
	private ReportDownloader reportDownloader;

	@Autowired
	private ServletContext context;

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void downloadReporteConceptoPago(HttpServletResponse response, String udCodUnidad) {
		String rutaReporte = context.getRealPath("/reportes/ReporteConceptoPago.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("udCodUnidad", udCodUnidad);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		String nombreReporte = "Reporte_Conceptos_Pago" + PDF;
		reportDownloader.downloadPDF(response, rutaReporte, nombreReporte, params,false);
	}

	@Override
	public void downloadReporteConceptoPagoAdmiCentral(HttpServletResponse response, String udIdAministrativa) {
		String rutaReporte = context.getRealPath("/reportes/ReporteConceptoPagoAdmiCentral.jrxml");
		Map<String, Object> params = new HashMap<>();
		params.put("udIdAministrativa", udIdAministrativa);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		String nombreReporte = "Reporte_Conceptos_Pago" + PDF;
		reportDownloader.downloadPDF(response, rutaReporte, nombreReporte, params,false);
	}

	@Override
	public void generarPDF(HttpServletResponse response, String establecimiento, String serie, Integer tipo,Boolean doblePagina) {
		String valor = context.getRealPath("/reportes") + "\\";
		Map<String, Object> params = new HashMap<>();
		params.put("establecimiento", establecimiento);
		params.put("serie", serie);
		params.put("SUBREPORT_DIR", valor);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		String rutaLogoAnulado = context.getRealPath(RUTA_LOGO_ANULADO);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		params.put(LOGO_ANULADO, rutaLogoAnulado);
		if (tipo == 1) {
			reportDownloader.downloadPDF(response, context.getRealPath("/reportes/boleta.jrxml"),
					"cxc_CP-20148092282-03-B" + establecimiento + "-" + serie + PDF, params,doblePagina);
		} else {
			if (tipo == 2) {
				reportDownloader.downloadPDF(response, context.getRealPath("/reportes/factura.jrxml"),
						"cxc_CP-20148092282-01-F" + establecimiento + "-" + serie + PDF, params,doblePagina);
			} else if (tipo == 4) {
				params.put("tipo", tipo);
				reportDownloader.downloadPDF(response, context.getRealPath("/reportes/notaCredito.jrxml"),
						"cxc_CP-20148092282-07-F" + establecimiento + "-" + serie + PDF, params,doblePagina);
			} else if (tipo == 5) {
				params.put("tipo", tipo);
				reportDownloader.downloadPDF(response, context.getRealPath("/reportes/notaCredito.jrxml"),
						"cxc_CP-20148092282-07-B" + establecimiento + "-" + serie + PDF, params,doblePagina);
			}
		}
	}
	
	@Override
	public void generarPDFTicket(HttpServletResponse response, String establecimiento, String correlativo) {
		String valor = context.getRealPath("/reportes") + "\\";
		Map<String, Object> params = new HashMap<>();
		params.put("establecimiento", establecimiento);
		params.put("correlativo", correlativo);
		params.put("SUBREPORT_DIR", valor);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		reportDownloader.downloadPDF(response, context.getRealPath("/reportes/ticket.jrxml"),
				"cxc_CP-20148092282-03-T" + establecimiento + "-" + correlativo + PDF, params,false);
	}

	@Override
	public void mostrarReporteXMes(HttpServletResponse response, String usuario, Integer tipoArchivo, String codUnidad,
			Integer anio, Integer mes) {
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(COD_UNIDAD, codUnidad);
		params.put("anio", anio);
		params.put("mes", mes);
		params.put(REPORT_LOCALE, Locale.ENGLISH);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		if (tipoArchivo == 1) {
			String rutaReportePDF = context.getRealPath("/reportes/reporteVentasPDF.jrxml");
			String nombreReportePDF = REPORTE_VENTAS + codUnidad + "_" + anio + "_" + mes + PDF;
			reportDownloader.exportPDF(response, rutaReportePDF, nombreReportePDF, params);
		} else if (tipoArchivo == 2) {
			String rutaReporteXLS = context.getRealPath("/reportes/reporteVentasXLS.jrxml");
			String nombreReporteXLS = REPORTE_VENTAS + codUnidad + "_" + anio + "_" + mes + XLS;
			reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);
		}
	}

	@Override
	public void mostrarReporteXIntervalo(HttpServletResponse response, String usuario, Integer tipoArchivo,
			String codUnidad, String fechaInicial, String fechaFinal) {
		String fechaInicio = fechaInicial.replace('-', '/');
		String fechaFin = fechaFinal.replace('-', '/');
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(COD_UNIDAD, codUnidad);
		params.put("fecha_inicial", fechaInicio);
		params.put("fecha_final", fechaFin);
		params.put(REPORT_LOCALE, Locale.ENGLISH);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		if (tipoArchivo == 1) {
			String rutaReportePDF = context.getRealPath("/reportes/reporteVentasxIPDF.jrxml");
			String nombreReportePDF = REPORTE_VENTAS + codUnidad + "_" + fechaInicio + "_" + fechaFin + PDF;
			reportDownloader.exportPDF(response, rutaReportePDF, nombreReportePDF, params);
		} else if (tipoArchivo == 2) {
			String rutaReporteXLS = context.getRealPath("/reportes/reporteVentasxIXLS.jrxml");
			String nombreReporteXLS = REPORTE_VENTAS + codUnidad + "_" + fechaInicio + "_" + fechaFin + XLS;
			reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);
		}
	}

	@Override
	public void mostrarReporteGeneralXMes(HttpServletResponse response, String usuario, Integer tipoArchivo,
			String codUnidad, Integer anio, Integer mes) {
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(COD_UNIDAD, codUnidad);
		params.put("anio", anio);
		params.put("mes", mes);
		params.put(REPORT_LOCALE, Locale.ENGLISH);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		if (tipoArchivo == 2) {
			String rutaReporteXLS = context.getRealPath("/reportes/reporteVentasGeneralXLS.jrxml");
			String nombreReporteXLS = REPORTE_VENTAS + codUnidad+"_" + anio + "_" + mes + XLS;
			reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);
		}
	}
	
	@Override
	public void mostrarReporteServiciosFacu(HttpServletResponse response, String usuario, String udCodEstablecimiento){
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(UD_COD_ESTABLECIMIENTO, udCodEstablecimiento);
		params.put(REPORT_LOCALE, Locale.ENGLISH);		
		String rutaReporteXLS = context.getRealPath("/reportes/ReporteDeServiciosFacu.jrxml");
		String nombreReporteXLS = REPORTE_SERVICIOS + udCodEstablecimiento + "_" + XLS;
		reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);	
	}
	@Override
	public void mostrarReporteServiciosTeso(HttpServletResponse response, String usuario, Integer udIdAdministrativa){
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(UD_ID_ADMINISTRATIVA, udIdAdministrativa);
		params.put(REPORT_LOCALE, Locale.ENGLISH);		
		String rutaReporteXLS = context.getRealPath("/reportes/ReporteDeServiciosTeso.jrxml");
		String nombreReporteXLS = REPORTE_SERVICIOS + udIdAdministrativa + "_" + XLS;
		reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);	
	}
	@Override
	public void mostrarReporteBienesFacu(HttpServletResponse response, String usuario, String udCodEstablecimiento){
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(UD_COD_ESTABLECIMIENTO, udCodEstablecimiento);
		params.put(REPORT_LOCALE, Locale.ENGLISH);		
		String rutaReporteXLS = context.getRealPath("/reportes/ReporteDeBienesFacu.jrxml");
		String nombreReporteXLS = REPORTE_BIENES + udCodEstablecimiento + "_" + XLS;
		reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);
	}
	@Override
	public void mostrarReporteBienesTeso(HttpServletResponse response, String usuario, Integer udIdAdministrativa){
		Map<String, Object> params = new HashMap<>();
		params.put(USUARIO, usuario);
		params.put(UD_ID_ADMINISTRATIVA, udIdAdministrativa);
		params.put(REPORT_LOCALE, Locale.ENGLISH);		
		String rutaReporteXLS = context.getRealPath("/reportes/ReporteDeBienesTeso.jrxml");
		String nombreReporteXLS = REPORTE_BIENES + udIdAdministrativa + "_" + XLS;
		reportDownloader.downloadXLS(response, rutaReporteXLS, nombreReporteXLS, params);
	}

	@Override
	public byte[] returnPDF(String establecimiento, String serie, Integer tipo) {
		String valor = context.getRealPath("/reportes") + "\\";
		Map<String, Object> params = new HashMap<>();
		params.put("establecimiento", establecimiento);
		params.put("serie", serie);
		params.put("SUBREPORT_DIR", valor);
		String rutaLogo = context.getRealPath(RUTA_LOGO);
		String rutaLogoQuipu = context.getRealPath(RUTA_LOGO_QUIPU);
		params.put(LOGO, rutaLogo);
		params.put(LOGO_QUIPU, rutaLogoQuipu);
		byte[] pdf=null;
		try{
			if (tipo == 1) 
				pdf=reportDownloader.returnPDF(context.getRealPath("/reportes/boleta.jrxml"),params); 
			else {
				if (tipo == 2) 
					pdf=reportDownloader.returnPDF(context.getRealPath("/reportes/factura.jrxml"),params);
				else if (tipo == 4) {
					params.put("tipo", tipo);
					pdf=reportDownloader.returnPDF(context.getRealPath("/reportes/notaCredito.jrxml"),params);
				} else if (tipo == 5) {
					params.put("tipo", tipo);
					pdf=reportDownloader.returnPDF(context.getRealPath("/reportes/notaCredito.jrxml"),params);
				}
			}			
		}catch(Exception e){
			System.out.println("error reporte: "+e.getMessage());
		}
		return pdf;
	}
}
