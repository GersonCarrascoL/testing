package pe.edu.unmsm.quipucamayoc.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 25/11/15
 * Time: 04:04 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReporteService {
	  
   public void generarPDF(HttpServletResponse response, String establecimiento, String serie, Integer tipo,Boolean doblePagina);
   public void generarPDFTicket(HttpServletResponse response, String establecimiento, String correlativo);
   public void downloadReporteConceptoPago(HttpServletResponse response, String udCodUnidad);
   public void downloadReporteConceptoPagoAdmiCentral(HttpServletResponse response, String udIdAministrativa);
   public void mostrarReporteXMes(HttpServletResponse response, String usuario, Integer tipoArchivo, String codUnidad, Integer anio, Integer mes);
   public void mostrarReporteXIntervalo(HttpServletResponse response, String usuario, Integer tipoArchivo, String codUnidad, String fechaInicial, String fechaFinal);
   public void mostrarReporteGeneralXMes(HttpServletResponse response, String usuario, Integer tipoArchivo, String codUnidad, Integer anio, Integer mes);
   public void mostrarReporteServiciosFacu(HttpServletResponse response, String usuario, String udCodEstablecimiento);
   public void mostrarReporteServiciosTeso(HttpServletResponse response, String usuario, Integer udIdAdministrativa);
   public void mostrarReporteBienesFacu(HttpServletResponse response, String usuario, String udCodEstablecimiento);
   public void mostrarReporteBienesTeso(HttpServletResponse response, String usuario, Integer udIdAdministrativa);
   public byte[] returnPDF(String establecimiento, String serie, Integer tipo);
}

