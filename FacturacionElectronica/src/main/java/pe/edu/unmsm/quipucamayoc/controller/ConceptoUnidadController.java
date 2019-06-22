package pe.edu.unmsm.quipucamayoc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pe.edu.unmsm.quipucamayoc.model.ConceptoUnidadModel;
import pe.edu.unmsm.quipucamayoc.service.ConceptoUnidadService;
import pe.edu.unmsm.quipucamayoc.service.ReporteService;

@Controller
@RequestMapping("/rest/conceptoUnidadController")
public class ConceptoUnidadController {
	
	@Autowired private ConceptoUnidadService conceptoUnidadService;
	@Autowired private ReporteService reporteService;
	
	static final String STR_COD_DEPENDENCIA = "codigoDependencia";
	static final String STR_ID_CPAGO = "idCPago";
	static final String STR_APPLICATION_JSON = "application/json";
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/{codigoDependencia}")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoXDependencia(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia){
        return conceptoUnidadService.conceptosPagoXDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/{codigoDependencia}/estado/{estado}")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoXDependenciaXEstado(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia, @PathVariable(value = "estado") Integer estado){
        return conceptoUnidadService.conceptosPagoXDependenciaXEstado(codigoDependencia, estado);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXUnaDependencia/{codigoDependencia}/")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoXUnaDependencia(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia){
        return conceptoUnidadService.conceptosPagoXUnaDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarHabilitadosXUnaDep/{udIdDependencia}/")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoHabilitadosXUnaDep(@PathVariable(value = "udIdDependencia") Integer udIdDependencia){
        return conceptoUnidadService.listarConceptosPagoHabilitadosXUnaDep(udIdDependencia);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarConceptoPagoUnidad/")
    @ResponseBody
    public ResponseEntity<Integer> registrarConceptoUnidad(@RequestBody ConceptoUnidadModel concepto){
		conceptoUnidadService.registrarConceptoPagoUnidad(concepto);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(concepto.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: UNIDAD NO EXISTE,  -2: COD CONCEPTO REPETIDO,  -3: OTRO ERROR
		return new ResponseEntity<>(concepto.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEstado/idCPago/{idCPago}/estado/{estado}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void actualizarEstado(@PathVariable(value = STR_ID_CPAGO) Integer idCPago, @PathVariable(value = "estado") Integer estado){
		conceptoUnidadService.actualizarEstado(idCPago, estado);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/updateConcepto/")
    @ResponseBody
    public ResponseEntity<Integer> actualizarConcepto(@RequestBody ConceptoUnidadModel concepto){
		conceptoUnidadService.actualizarConceptoPago(concepto);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(concepto.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: UNIDAD NO EXISTE,  -2: COD CONCEPTO REPETIDO,  -3: OTRO ERROR
		return new ResponseEntity<>(concepto.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.PUT, consumes = STR_APPLICATION_JSON, value = "/volverEnviarConcepto/")
    @ResponseBody
    public ResponseEntity<Integer> volverEnviarConcepto(@RequestBody ConceptoUnidadModel concepto){
		conceptoUnidadService.volverEnviarConcepto(concepto);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(concepto.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: UNIDAD NO EXISTE,  -2: COD CONCEPTO REPETIDO,  -3: OTRO ERROR
		return new ResponseEntity<>(concepto.getStatus().intValue(), hs);
    }

	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/habilitados/{codigoDependencia}")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoXDependenciaHabilitados(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia){
        return conceptoUnidadService.conceptosPagoXDependenciaHabilitados(codigoDependencia);
    }	
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarXDependencia/habilitadosxMoneda/{codigoDependencia}/{codigoMoneda}")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoXDependenciaHabilitadosMoneda(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia,@PathVariable(value = "codigoMoneda") String codigoMoneda){
        return conceptoUnidadService.conceptosPagoXDependenciaHabilitadosMoneda(codigoDependencia,codigoMoneda);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarPendientesXdep/{codigoDependencia}")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoPendientesXDependencia(@PathVariable(value = STR_COD_DEPENDENCIA) String codigoDependencia){
        return conceptoUnidadService.conceptosPagoPendientesXDependencia(codigoDependencia);
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarPendientesTodos")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoPendientesTodos(){
        return conceptoUnidadService.conceptosPagoPendientesTodos();
    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/listarTodos")
    @ResponseBody
    public List<ConceptoUnidadModel> listarConceptosPagoTodos(){
        return conceptoUnidadService.conceptosPagoTodos();
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/aprobarConcepto/idCPago/{idCPago}/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void aprobarConcepto(@PathVariable(value = STR_ID_CPAGO) Integer idCPago){
		conceptoUnidadService.aprobarConcepto(idCPago);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/rechazarConcepto/{idCPago}/{motivoRechazo}/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void rechazarConcepto(@PathVariable(value = STR_ID_CPAGO) Integer idCPago, @PathVariable(value = "motivoRechazo") String motivoRechazo){
		conceptoUnidadService.rechazarConcepto(idCPago, motivoRechazo);
    }
	
	@RequestMapping(method = RequestMethod.POST , consumes = STR_APPLICATION_JSON, value = "/registrarConceptoUnidadMatriz/")
    @ResponseBody
    public ResponseEntity<Integer> registrarConceptoUnidadMatriz(@RequestBody ConceptoUnidadModel concepto){
		conceptoUnidadService.registrarConceptoUnidadMatriz(concepto);
		HttpStatus hs = HttpStatus.CONFLICT;
		if(concepto.getStatus().intValue()==1){ 
			hs = HttpStatus.CREATED;
		}
		// -1: UNIDAD NO EXISTE,  -2: COD CONCEPTO REPETIDO,  -3: OTRO ERROR
		return new ResponseEntity<>(concepto.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteConceptoUnidad/{id_cu}/{id_cp}")
    public ResponseEntity<Integer> deleteConceptoPagoUnidad(@PathVariable(value = "id_cu") Integer idCu, @PathVariable(value = "id_cp") Integer idCp) {
		ConceptoUnidadModel concepto = new ConceptoUnidadModel();
		concepto.setIdCPU(idCu);
		concepto.setIdCPago(idCp);
		conceptoUnidadService.deleteConceptoUnidad(concepto);		
		HttpStatus hs = HttpStatus.NOT_FOUND;
		if(concepto.getStatus().intValue()==1){ 
			hs = HttpStatus.OK;
		}
		// -1: COD CONCEPTO_UNIDAD NO EXISTE,  -2: OTRO ERROR
		return new ResponseEntity<>(concepto.getStatus().intValue(), hs);
    }
	
	@RequestMapping(method = RequestMethod.POST, value ="/reporteConceptoPago/{udCodUnidad}/")
    public void downloadReporteConceptoPago(HttpServletResponse response, @PathVariable(value = "udCodUnidad") String udCodUnidad) {
        reporteService.downloadReporteConceptoPago(response, udCodUnidad);

    }
	
	@RequestMapping(method = RequestMethod.POST, value ="/reporteConceptoPago/adminCentral/{udIdAministrativa}/")
    public void downloadReporteConceptoPagoAdmiCentral(HttpServletResponse response, @PathVariable(value = "udIdAministrativa") String udIdAministrativa) {
        reporteService.downloadReporteConceptoPagoAdmiCentral(response, udIdAministrativa);

    }
	
	@RequestMapping(method = RequestMethod.GET, produces = STR_APPLICATION_JSON, value= "/existeConceptoPago/{idCPago}/{udId}/")
    @ResponseBody
    public List<ConceptoUnidadModel> existeConceptoPago(@PathVariable(value = STR_ID_CPAGO) Integer idCPago, @PathVariable(value = "udId") Integer udId){
        return conceptoUnidadService.existeConceptoPago(idCPago, udId);
    }
	
	
}
