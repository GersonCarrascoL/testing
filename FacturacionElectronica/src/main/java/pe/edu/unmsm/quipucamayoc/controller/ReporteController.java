package pe.edu.unmsm.quipucamayoc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.unmsm.quipucamayoc.model.CorreoModel;
import pe.edu.unmsm.quipucamayoc.service.ReporteService;
import pe.edu.unmsm.quipucamayoc.util.DJCorreoTexto;

@Controller
@RequestMapping("/rest/ReporteController")
public class ReporteController {
	
    @Autowired
    private ReporteService reporteService;
	
    @RequestMapping(method = RequestMethod.POST, value ="/mostrarReporteFactura/{establecimiento}/{serie}")
    public void mostrarReporteFactura(HttpServletResponse response,@PathVariable(value = "establecimiento") String establecimiento,@PathVariable(value = "serie") String serie) {
		//
    }
	
    @RequestMapping(method = RequestMethod.POST, value ="/mostrarReporteBoleta/{maquina}/{nTicket}")
    public void mostrarReporteBoleta(HttpServletResponse response,@PathVariable(value = "maquina") String maquina,@PathVariable(value = "nTicket") String nTicket) {
		//
    }
	
    @RequestMapping(method = RequestMethod.GET, value ="/generarPDF/{establecimiento}/{serie}/{tipo}/{doblePagina}")
    public void generarPDF(HttpServletResponse response,@PathVariable(value = "establecimiento") String establecimiento,@PathVariable(value = "serie") String serie,@PathVariable(value = "tipo") Integer tipo,@PathVariable(value = "doblePagina") Boolean doblePagina) {
    	reporteService.generarPDF(response, establecimiento, serie, tipo,doblePagina);
    }
    
    @RequestMapping(method = RequestMethod.GET, value ="/generarPDF/{establecimiento}/{correlativo}")
    public void generarPDFTicket(HttpServletResponse response,@PathVariable(value = "establecimiento") String establecimiento,@PathVariable(value = "correlativo") String correlativo) {
    	reporteService.generarPDFTicket(response, establecimiento, correlativo);
    }
    
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json",value = "/enviarCorreo")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Integer enviarCorreo(@RequestBody CorreoModel comprobante) {
    	try{
		DJCorreoTexto correo= new DJCorreoTexto();
		correo.enviarCompromisoPagoCorreo(comprobante.getCorreoDestino(), "FACTURACION ELECTRONICA UNMSM", "Sr(a) se le adjunta el comprobante asociado a su pago", reporteService.returnPDF(comprobante.getEstablecimiento(), comprobante.getSerie(), comprobante.getTipo()));		
			return 1;		
    	}catch(Exception e){
    		return 0;
    	}
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteServiciosFacu/{usuario}/{udCodEstablecimiento}/")
	public void generarReporteServiciosFacu(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario,
			@PathVariable(value = "udCodEstablecimiento") String udCodEstablecimiento ) throws IOException {
		reporteService.mostrarReporteServiciosFacu(response, usuario, udCodEstablecimiento);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteServiciosTeso/{usuario}/{udIdAdministrativa}/")
	public void generarReporteServiciosTeso(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario,
			@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa ) throws IOException {
		reporteService.mostrarReporteServiciosTeso(response, usuario, udIdAdministrativa);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteBienesFacu/{usuario}/{udCodEstablecimiento}/")
	public void generarReporteBienesFacu(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario,
			@PathVariable(value = "udCodEstablecimiento") String udCodEstablecimiento ) throws IOException {
		reporteService.mostrarReporteBienesFacu(response, usuario, udCodEstablecimiento);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/generarReporteBienesTeso/{usuario}/{udIdAdministrativa}/")
	public void generarReporteBienesTeso(HttpServletResponse response,
			@PathVariable(value = "usuario") String usuario,
			@PathVariable(value = "udIdAdministrativa") Integer udIdAdministrativa ) throws IOException {
		reporteService.mostrarReporteBienesTeso(response, usuario, udIdAdministrativa);
	}
	   
}