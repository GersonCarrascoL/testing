
package pe.edu.unmsm.quipucamayoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.unmsm.quipucamayoc.bean.CabeceraBean;
import pe.edu.unmsm.quipucamayoc.service.ComprobantePagoService;
import pe.edu.unmsm.quipucamayoc.service.TipoCambioService;
import sun.font.FontUtilities;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("/rest/SistemaController")
public class SistemaController {
	
	@Autowired
	private ComprobantePagoService comprobantePagoService;	
	@Autowired
	private TipoCambioService tipoCambioService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain", value = "/sistema")
	@ResponseBody
	public String sistema() {
		if(FontUtilities.isWindows){
			return "http://localhost:8080/FacturadorSunat/enviarFactura.htm";
		}else{
			return "http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/enviarFactura.htm";
		}
	}
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain", value = "/sistemaConsulta")
	@ResponseBody
	public String sistemaConsulta() {
		if(FontUtilities.isWindows){
			return "http://localhost:8080/FacturadorSunat/consultarComprobante.htm";
		}else{
			return "http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/consultarComprobante.htm";
		}
	}	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "/getCabecera")
	@ResponseBody
	public CabeceraBean getCabecera(@RequestBody Integer ud_id) {
		 CabeceraBean cabecera= new CabeceraBean();
		 cabecera.setNotificacion(comprobantePagoService.notificacionComprobantes(ud_id));
		 cabecera.setTipoCambio(tipoCambioService.getTipoCambioActual());
		 return cabecera;
		 
	}
}
