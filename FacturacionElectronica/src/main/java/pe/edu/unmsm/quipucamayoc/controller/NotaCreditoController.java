package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.unmsm.quipucamayoc.model.ComprobantePagoModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleFacturaModel;
import pe.edu.unmsm.quipucamayoc.model.DetalleNotaCredito;
import pe.edu.unmsm.quipucamayoc.model.EstadoModel;
import pe.edu.unmsm.quipucamayoc.model.NotaCreditoModel;
import pe.edu.unmsm.quipucamayoc.model.TipoNotaCredito;
import pe.edu.unmsm.quipucamayoc.service.NotaCreditoService;

@Controller
@RequestMapping("/rest/notaCredito")
public class NotaCreditoController {
	
	@Autowired
	NotaCreditoService notaCreditoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getTipoNotaCreditoBoleta")
	@ResponseBody
	public List<TipoNotaCredito> getTipoNotaCreditoBoleta() {
		return notaCreditoService.getTipoNotaCreditoBoleta();
	}
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getTipoNotaCreditoFactura")
	@ResponseBody
	public List<TipoNotaCredito> getTipoNotaCreditoFactura() {
		return notaCreditoService.getTipoNotaCreditoFactura();
	}
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getComprobantesParaNotas/{cod_dependencia}/{tipo}/{numeroComprobante}")
	@ResponseBody
	public List<ComprobantePagoModel> getComprobantesParaNotas(@PathVariable(value = "cod_dependencia") String codDependencia,@PathVariable(value = "tipo") Integer tipo,@PathVariable(value = "numeroComprobante") Integer numeroComprobante) {
		return notaCreditoService.getComprobantesParaNotas(codDependencia, tipo,numeroComprobante);
	}	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getDetalleComprobante/{numero_documento}/{tipo}")
	@ResponseBody
	public List<DetalleFacturaModel> getDetalleComprobante(@PathVariable(value = "numero_documento") String numeroDocumento,@PathVariable(value = "tipo") Integer tipo) {
		return notaCreditoService.getDetalleComprobante(numeroDocumento, tipo);
	}	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/getInformacionComprobante/{numero_documento}/{tipo}")
	@ResponseBody
	public ComprobantePagoModel getInformacionComprobante(@PathVariable(value = "numero_documento") String numeroDocumento,@PathVariable(value = "tipo") Integer tipo) {
		return notaCreditoService.getInformacionComprobante(numeroDocumento, tipo);
	}
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json",value = "/registrarNotaCredito")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EstadoModel registrarNotaCreditoDetalle(@RequestBody NotaCreditoModel notaCredito){
			EstadoModel estadoModel = new EstadoModel();
			try{
				notaCreditoService.registrarNotaCredito(notaCredito);
				if(notaCredito.getSerie()!=null && !notaCredito.getSerie().equals("")){
					for(DetalleNotaCredito detalle:notaCredito.getListaDetalle()){
						notaCreditoService.registrarNotaCreditoDetalle(notaCredito, detalle);
					}
				}
				estadoModel.setComprobante(1);
			}catch(Exception e){
				estadoModel.setComprobante(0);
				estadoModel.setObservacion("Ha ocurrido un error en la creacion de nota de credito");
			}
			if(estadoModel.getComprobante()!=0){
				estadoModel=notaCreditoService.enviarFacturador(notaCredito);
				if(estadoModel.getComprobante()!=0){
					estadoModel.setEstablecimiento(notaCredito.getCodEstab());
					estadoModel.setSerie(notaCredito.getSerie());
					estadoModel.setTipo(Integer.toString(notaCredito.getTipoComprobante()));
				}				
			}
			return estadoModel;		
    } 	
	@RequestMapping(method = RequestMethod.POST , consumes = "application/json",value = "/generarArchivosComprobante")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EstadoModel generarArchivosComprobante(@RequestBody ComprobantePagoModel comprobante){	
			NotaCreditoModel notaCredito= new NotaCreditoModel();
			notaCredito.setTipoComprobante(comprobante.getTipo());
			notaCredito.setCodEstab(comprobante.getCodEst());
			notaCredito.setSerie(comprobante.getSerie());
			EstadoModel estado=notaCreditoService.enviarFacturador(notaCredito);
			return estado;
		
    } 
}
