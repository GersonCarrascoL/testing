package pe.edu.unmsm.quipucamayoc.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Component
public class ReportDownloader {

	@Autowired
	private DriverManagerDataSource dataSource;

	private static Logger logger = Logger.getLogger(ReportDownloader.class);

	public void downloadPDF(HttpServletResponse response, String reportUrl, String filename,
			Map<String,Object> params,Boolean doblePagina) {
		try {
			InputStream reportStream = new FileInputStream(reportUrl);
			JasperDesign jd = JRXmlLoader.load(reportStream);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			Connection connection = dataSource.getConnection();
			JasperPrint jp = JasperFillManager.fillReport(jr, params, connection);
			jp.setLocaleCode("en_US");	
			if(jp!=null && doblePagina){
				List lista = jp.getPages();
				int pages=lista.size();
				for(int i=0;i<pages;i++){
	                JRPrintPage pagina = (JRPrintPage) lista.get(i);
	                jp.addPage(pagina);					
				}                                 
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ReportExporter.exportToPDF(jp, baos);
			response.setHeader("Content-Disposition", "filename=" + filename);
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			writeReportToResponseStream(response, baos);
			connection.close();	
		} catch (JRException e) {
			logger.fatal("Error en el JRE downloadPDF", e);
		} catch (SQLException e) {
			logger.fatal("Error en SQL", e);
		} catch (FileNotFoundException e) {
			logger.fatal("Error en lectura y escritura de archivos de reporte PDF", e);
		}
	}

	public void downloadXLS(HttpServletResponse response, String reportUrl, String filename,
			Map<String,Object> params) {
		try {
			InputStream reportStream = new FileInputStream(reportUrl);
			JasperDesign jd = JRXmlLoader.load(reportStream);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			Connection connection = dataSource.getConnection();
			JasperPrint jp = JasperFillManager.fillReport(jr, params, connection);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ReportExporter.exportToXLS(jp, baos);
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			writeReportToResponseStream(response, baos);
			connection.close();	
		} catch (JRException e) {
			logger.fatal("Error en el JRE", e);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			logger.fatal("Error en SQL", e);
		} catch (FileNotFoundException e) {
			logger.fatal("Error en lectura y escritura de archivos de reporte XLS", e);
		}
	}

	public void exportPDF(HttpServletResponse response, String reportUrl, String filename,
			Map<String,Object> params) {
		try {
			InputStream reportStream = new FileInputStream(reportUrl);
			JasperDesign jd = JRXmlLoader.load(reportStream);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			Connection connection = dataSource.getConnection();
			JasperPrint jp = JasperFillManager.fillReport(jr, params, connection);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ReportExporter.exportToPDF(jp, baos);
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			writeReportToResponseStream(response, baos);
			connection.close();	
		} catch (JRException e) {
			logger.fatal("Error en el JRE downloadPDF", e);
		} catch (SQLException e) {
			logger.fatal("Error en SQL", e);
		} catch (FileNotFoundException e) {
			logger.fatal("Error en lectura y escritura de archivos de reporte PDF", e);
		}
	}

	private void writeReportToResponseStream(HttpServletResponse response, ByteArrayOutputStream baos) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			baos.writeTo(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			logger.fatal("Error en escribir", e);
		}
	}
	public byte[] returnPDF(String reportUrl,Map<String,Object> params){
		byte[] reporte=null;	
		try {
			InputStream reportStream = new FileInputStream(reportUrl);
			JasperDesign jd = JRXmlLoader.load(reportStream);
			JasperReport jr = JasperCompileManager.compileReport(jd);
			Connection connection = dataSource.getConnection();
			JasperPrint jp = JasperFillManager.fillReport(jr, params, connection);
			reporte = JasperExportManager.exportReportToPdf(jp);
			connection.close();				
		} catch (JRException e) {
			logger.fatal("Error en el JRE downloadPDF", e);
		} catch (SQLException e) {
			logger.fatal("Error en SQL", e);
		} catch (FileNotFoundException e) {
			logger.fatal("Error en lectura y escritura de archivos de reporte PDF", e);
		}
		return reporte;
	}
}
