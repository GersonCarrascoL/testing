package pe.edu.unmsm.quipucamayoc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import pe.edu.unmsm.quipucamayoc.model.ClienteRucModelSunat;
import pe.edu.unmsm.quipucamayoc.model.ReniecRELModel;
import pe.edu.unmsm.quipucamayoc.model.ReniecRequestModel;
import pe.edu.unmsm.quipucamayoc.model.SunatRequestModel;
import pe.edu.unmsm.quipucamayoc.util.Constantes;

@Controller
@RequestMapping("/rest/ValidationController")
public class ValidationController {

		private static Logger logger = Logger.getLogger(ValidationController.class);
		
		/**OBTIENE DATOS DE LA SUNAT**/	
		@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/sunat/{numruc}")
		@ResponseBody
		public ClienteRucModelSunat mostrarDatosSunat(@PathVariable(value = "numruc") String numruc) {
			final String uri = Constantes.CONSULTAWS_URL_BASE + "/sunat/consulta";
			ClienteRucModelSunat cliente = new ClienteRucModelSunat();
			
			try{
				String user = Constantes.LDAP_USER;
				String passw = Constantes.LDAP_PASSWORD;
				
			    SunatRequestModel req = new SunatRequestModel(numruc,user,passw);
			 
			    RestTemplate restTemplate = new RestTemplate();
			    cliente = restTemplate.postForObject(uri, req, ClienteRucModelSunat.class);
			}
			catch (Exception ex) {
				logger.fatal(ex);
				cliente.setStatus(-1);
            }
		    return cliente;
		}
		
		
		@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/sunat/valida/{numruc}")
		@ResponseBody
		public ClienteRucModelSunat mostrarRUCactivoHabido(@PathVariable(value = "numruc") String numruc) {
			final String uri = Constantes.CONSULTAWS_URL_BASE + "/sunat/valida";
			ClienteRucModelSunat cliente = new ClienteRucModelSunat();
			
			try{
				String user = Constantes.LDAP_USER;
				String passw = Constantes.LDAP_PASSWORD;
				
			    SunatRequestModel req = new SunatRequestModel(numruc,user,passw);
			 
			    RestTemplate restTemplate = new RestTemplate();
			    cliente = restTemplate.postForObject(uri, req, ClienteRucModelSunat.class);
			}
			catch (Exception ex) {
				logger.fatal(ex);
				cliente.setStatus(-1);
            }
		    return cliente;
		}
		
		
		@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/reniec/{num_dni}")
		@ResponseBody
		public ReniecRELModel mostrarDatosReniec2(@PathVariable(value="num_dni") String numDni){
			final String uri = Constantes.CONSULTAWS_URL_BASE + "/reniec/consulta";
			ReniecRELModel cliente = new ReniecRELModel();
			
			try{
				String user = Constantes.LDAP_USER;
				String passw = Constantes.LDAP_PASSWORD;
				
			    ReniecRequestModel req = new ReniecRequestModel(numDni,user,passw);
			 
			    RestTemplate restTemplate = new RestTemplate();
			    cliente = restTemplate.postForObject(uri, req, ReniecRELModel.class);
			}
			catch (Exception ex) {
				logger.fatal(ex);
				cliente.setStatus(-1);
				cliente.setMessage("El servicio consulta Reniec no se encuentra disponible");
            }
		    return cliente;
			
		}
		
		
}
