
package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.unmsm.quipucamayoc.bean.ComprobanteBean;
import pe.edu.unmsm.quipucamayoc.bean.NotaCreditoBean;
import pe.edu.unmsm.quipucamayoc.model.BandFactModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobanteModel;
import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.CorreoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleModel;
import pe.edu.unmsm.quipucamayoc.model.EstablecimientoBoletaYFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotificationModel;
import pe.edu.unmsm.quipucamayoc.model.RangoFecha;
import pe.edu.unmsm.quipucamayoc.service.ComprobantePagoService;
import pe.edu.unmsm.quipucamayoc.service.FacturaService;
import pe.edu.unmsm.quipucamayoc.service.MonedaService;
import pe.edu.unmsm.quipucamayoc.service.NotaCreditoService;
import sun.font.FontUtilities;

@Controller
@RequestMapping("/rest/ComprobantePagoController")
public class ComprobantePagoController {

	@Autowired
	private ComprobantePagoService comprobantePagoService;
	@Autowired 
	private MonedaService monedaService;
	@Autowired
	private FacturaService facturaService;
	@Autowired
	NotaCreditoService notaCreditoService;
	
	private static Logger logger = Logger.getLogger(ComprobantePagoController.class);

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerComprobantePago")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<ComprobantePagoModel> comprobantePago(@RequestBody RangoFecha rango) {
		return comprobantePagoService.comprobantePago(rango,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerComprobantePagoAdminCaja")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<ComprobantePagoModel> comprobantePagoAdminCaja(@RequestBody RangoFecha rango) {
		return comprobantePagoService.comprobantePagoAdminCaja(rango,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerNotasDeCredito")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<ComprobantePagoModel> listarNotasDeCredito(@RequestBody RangoFecha rango) {
		return comprobantePagoService.getNotasDeCredito(rango,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerNotasDeCreditoAdminCaja")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<ComprobantePagoModel> getNotasDeCreditoAdminCaja(@RequestBody RangoFecha rango) {
		return comprobantePagoService.getNotasDeCreditoAdminCaja(rango,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/modificar")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void modificar(@RequestBody ComprobantePagoModel item) {
		comprobantePagoService.modificar(item);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/insertarEstablecimientoBoletaYFactura")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void insertarEstablecimientoBoletaYFactura(@RequestBody EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoService.insertarEstablecimientoBoletaYFactura(item);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarEstablecimientoBoletaYFacturaxUsuario")
	@ResponseBody
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFacturaxUsuario() {
		return comprobantePagoService.listarEstablecimientoBoletaYFacturaxUsuario(
				SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarEstablecimientosXFacultad")
	@ResponseBody
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientosXFacultad() {
		return comprobantePagoService.listarEstablecimientosXFacultad(
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/listarEstablecimientoBoletaYFactura")
	@ResponseBody
	public List<EstablecimientoBoletaYFacturaModel> listarEstablecimientoBoletaYFactura() {
		return comprobantePagoService.listarEstablecimientoBoletaYFactura();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/modificarEstablecimientoBoletaYFactura")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void modificarEstablecimientoBoletaYFactura(@RequestBody EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoService.modificarEstablecimientoBoletaYFactura(item);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/borrarEstablecimientoBoletaYFactura")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void borrarEstablecimientoBoletaYFactura(@RequestBody EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoService.borrarEstablecimientoBoletaYFactura(item);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/modificarEstadoEstablecimientoBoletaYFactura")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void modificarEstadoEstablecimientoBoletaYFactura(@RequestBody EstablecimientoBoletaYFacturaModel item) {
		comprobantePagoService.modificarEstadoEstablecimientoBoletaYFactura(item);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/estado")
	@ResponseStatus(HttpStatus.CREATED)
	public void estado(@RequestBody EstadoModel estado) {
		comprobantePagoService.estado(estado);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/reenviar")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void reenviar(@RequestBody EstadoModel estado) {
		try {
			comprobantePagoService.reenviar(estado);
		} catch (Exception e) {
			logger.error("No se logro reenviar el comprobante: ", e);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/comprobante")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ComprobanteModel comprobante(@RequestParam("tipo") Integer tipo,
			@RequestParam("establecimiento") String establecimiento, @RequestParam("serie") String serie) {
		return comprobantePagoService.comprobante(tipo, establecimiento, serie);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/detalle")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<DetalleModel> detalle(@RequestParam("temp") String temp) {
		return comprobantePagoService.detalle(temp);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "/obtenerCorreoCliente")
	@ResponseBody
	public CorreoModel obtenerCorreoComprobante(@RequestBody CorreoModel correo) {
		return comprobantePagoService.obtenerCorreoComprobante(correo);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "/modificarEstadoSunat")
	@ResponseBody
	public void obtenerCorreoComprobante(@RequestBody BandFactModel comprobante) {
		comprobantePagoService.modificarEstadoSunat(comprobante);
	}
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = "/getNotificacion")
	@ResponseBody
	public List<NotificationModel> getNotificacion(@RequestBody Integer ud_id) {
		return comprobantePagoService.notificacionComprobantes(ud_id);
	}	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/inicioComprobante")
	@ResponseBody
	public ComprobanteBean inicioComprobante() {
		ComprobanteBean comprobante = new ComprobanteBean();
		comprobante.setIgv(facturaService.listarTipoImpuesto("IGV"));
		comprobante.setUnidad(facturaService.listarUnidades(SecurityContextHolder.getContext().getAuthentication().getName()));
		comprobante.setMoneda(monedaService.obtieneMonedasActivas());
		comprobante.setPrecioCambio(facturaService.precioCambio());
		if(FontUtilities.isWindows){
			comprobante.setUrlFacturador("http://localhost:8080/FacturadorSunat/enviarFactura.htm");
		}else{
			comprobante.setUrlFacturador("http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/enviarFactura.htm");
		}
		return comprobante;
	}
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/inicioNotaCreditoBoleta")
	@ResponseBody
	public NotaCreditoBean inicioNotaCreditoBoleta() {
		NotaCreditoBean notaCredito= new NotaCreditoBean();
		notaCredito.setIgv(facturaService.listarTipoImpuesto("IGV"));
		notaCredito.setUnidad(facturaService.listarUnidades(SecurityContextHolder.getContext().getAuthentication().getName()));
		notaCredito.setTipoNotaCredito(notaCreditoService.getTipoNotaCreditoBoleta());
		if(FontUtilities.isWindows){
			notaCredito.setUrlFacturador("http://localhost:8080/FacturadorSunat/enviarFactura.htm");
		}else{
			notaCredito.setUrlFacturador("http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/enviarFactura.htm");
		}
		return notaCredito;
	}
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/inicioNotaCreditoFactura")
	@ResponseBody
	public NotaCreditoBean inicioNotaCreditoFactura() {
		NotaCreditoBean notaCredito= new NotaCreditoBean();
		notaCredito.setIgv(facturaService.listarTipoImpuesto("IGV"));
		notaCredito.setUnidad(facturaService.listarUnidades(SecurityContextHolder.getContext().getAuthentication().getName()));
		notaCredito.setTipoNotaCredito(notaCreditoService.getTipoNotaCreditoFactura());
		if(FontUtilities.isWindows){
			notaCredito.setUrlFacturador("http://localhost:8080/FacturadorSunat/enviarFactura.htm");
		}else{
			notaCredito.setUrlFacturador("http://quipucamayoc.unmsm.edu.pe/FacturadorSunat/enviarFactura.htm");
		}
		return notaCredito;
	}	
}
