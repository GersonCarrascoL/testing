package pe.edu.unmsm.quipucamayoc.util;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import java.io.ByteArrayOutputStream;

public final class ReportExporter {

	private ReportExporter() {

	}

	public static void exportToXLS(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		/*
		 * Existe una clase especializada para distintos tipos de formatos en
		 * este caso el JRXlsExporter se encarga de los formatos xls
		 */
		JRXlsExporter exporter = new JRXlsExporter();

		/*
		 * Parametros generales para escribir la instancia del JaserPrint al
		 * baos
		 */
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		/*
		 * Parametros especificos de un reporte Excel, consultar la
		 * documentacion de Jasper para una descripcion de estos parametros.
		 */
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		exporter.exportReport();
	}

	public static void exportToPDF(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();
	}
}
