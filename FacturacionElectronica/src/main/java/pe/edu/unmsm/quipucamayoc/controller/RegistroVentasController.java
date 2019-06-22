package pe.edu.unmsm.quipucamayoc.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.edu.unmsm.quipucamayoc.service.ReporteService;

@Controller
@RequestMapping("/rest/RegistroVenta")
public class RegistroVentasController {

	@Autowired
	private ReporteService reporteService;

	public ReporteService getReporteService() {
		return reporteService;
	}

	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteRegistroVentasXMes/{usuario}/{tipo_archivo}/{cod_dependencia}/{anio}/{mes}")
	public void generarReporteRegistroVentasXMes(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario, @PathVariable(value = "tipo_archivo") Integer tipoArchivo,
			@PathVariable(value = "cod_dependencia") String codDependencia, @PathVariable(value = "anio") Integer anio,
			@PathVariable(value = "mes") Integer mes) throws IOException {
		reporteService.mostrarReporteXMes(response, usuario, tipoArchivo, codDependencia, anio, mes);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteRegistroVentasXIntervalo/{usuario}/{tipo_archivo}/{cod_dependencia}/{fecha_inicial}/{fecha_final}")
	public void generarReporteRegistroVentasXIntervalo(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario, @PathVariable(value = "tipo_archivo") Integer tipoArchivo,
			@PathVariable(value = "cod_dependencia") String codDependencia,
			@PathVariable(value = "fecha_inicial") String fechaInicial,
			@PathVariable(value = "fecha_final") String fechaFinal) throws IOException {
		reporteService.mostrarReporteXIntervalo(response, usuario, tipoArchivo, codDependencia, fechaInicial,
				fechaFinal);

	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteRegistroVentasGeneralXMes/{usuario}/{tipo_archivo}/{cod_dependencia}/{anio}/{mes}")
	public void generarReporteRegistroVentasGeneralXMes(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario, @PathVariable(value = "tipo_archivo") Integer tipoArchivo,
			@PathVariable(value = "cod_dependencia") String codDependencia, @PathVariable(value = "anio") Integer anio,
			@PathVariable(value = "mes") Integer mes) throws IOException {
		reporteService.mostrarReporteGeneralXMes(response, usuario, tipoArchivo, codDependencia, anio, mes);

	}
}
