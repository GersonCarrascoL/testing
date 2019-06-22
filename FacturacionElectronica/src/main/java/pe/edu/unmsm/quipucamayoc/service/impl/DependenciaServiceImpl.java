package pe.edu.unmsm.quipucamayoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unmsm.quipucamayoc.model.DependenciaModel;
import pe.edu.unmsm.quipucamayoc.model.UnidadModel;
import pe.edu.unmsm.quipucamayoc.persistence.DependenciaPersistence;
import pe.edu.unmsm.quipucamayoc.service.DependenciaService;



@Service 
public class DependenciaServiceImpl  implements DependenciaService{

	@Autowired
	private DependenciaPersistence dependenciaPersistence;
	
	@Override
	public List<DependenciaModel> dependenciasBase(){
		return dependenciaPersistence.unidadesBase();
	}
	
	@Override
	public List<DependenciaModel> unidadesXTeso(){
		return dependenciaPersistence.unidadesXTeso();
	}

	@Override
	public DependenciaModel dependenciasPorUsuarioYPerfil(String dependencia, String perfil) {
		    List<DependenciaModel> nvl1 = new ArrayList();
	        List<DependenciaModel> nvl2 = new ArrayList();
	        List<DependenciaModel> nvl3 = new ArrayList();
	        List<DependenciaModel> nvl4 = new ArrayList();
	        List<DependenciaModel> nvl5 = new ArrayList();
	        List<DependenciaModel> nvl6 = new ArrayList();
	        List<DependenciaModel> nvl7 = new ArrayList();
	        
	        int numCaracteresDep = dependencia.length();

	        DependenciaModel unmsm = new DependenciaModel();
	        unmsm.setDescripcion("UNMSM");

	        if("CXC_TESO".equals(perfil) || "CXC_CAJA_RECAUD_TESO".equals(perfil)){
	            unmsm.setUnidades(nvl1);
	            for (DependenciaModel unidad : dependenciaPersistence.unidades()) {

	                if (unidad.getNivel() == 1) {
	                    nvl1.add(unidad);
	                    nvl2 = new ArrayList();
	                    unidad.setUnidades(nvl2);
	                } else if (unidad.getNivel() == 2) {
	                    nvl2.add(unidad);
	                    nvl3 = new ArrayList();
	                    unidad.setUnidades(nvl3);
	                } else if (unidad.getNivel() == 3) {
	                    nvl3.add(unidad);
	                    nvl4 = new ArrayList();
	                    unidad.setUnidades(nvl4);
	                } else if (unidad.getNivel() == 4) {
	                    nvl4.add(unidad);
	                    nvl5 = new ArrayList();
	                    unidad.setUnidades(nvl5);
	                }else  if (unidad.getNivel() == 5)    {
	                    nvl5.add(unidad);
	                    nvl6 = new ArrayList();
	                    unidad.setUnidades(nvl6);
	                }else    if (unidad.getNivel() == 6)   {
	                    nvl6.add(unidad);
	                    nvl7 = new ArrayList();
	                    unidad.setUnidades(nvl7);
	                }  else    if (unidad.getNivel() == 7)  {
	                    nvl7.add(unidad);
	                }

	            }

	        }
	        
	        if("CXC_FACULTAD".equals(perfil)  || "CXC_CLIENTE".equals(perfil) || "CXC_SOLIC_CONCEP".equals(perfil) ){
	            unmsm.setUnidades(nvl2);
	            for (DependenciaModel unidad : dependenciaPersistence.unidades()) {

	                if (unidad.getCodUnidad().length() >= numCaracteresDep) {
	                    if ((unidad.getCodUnidad().substring(0, numCaracteresDep)).equals(dependencia)) {

	                        if (unidad.getNivel() == 2) {
	                            nvl2.add(unidad);
	                            nvl3 = new ArrayList();
	                            unidad.setUnidades(nvl3);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl2);
	                            }
	                        } else if (unidad.getNivel() == 3) {
	                            nvl3.add(unidad);
	                            nvl4 = new ArrayList();
	                            unidad.setUnidades(nvl4);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl3);
	                            }
	                        } else if (unidad.getNivel() == 4) {
	                            nvl4.add(unidad);
	                            nvl5 = new ArrayList();
	                            unidad.setUnidades(nvl5);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl4);
	                            }
	                        } else if (unidad.getNivel() == 5) {
	                            nvl5.add(unidad);
	                            nvl6 = new ArrayList();
	                            unidad.setUnidades(nvl6);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl5);
	                            }
	                        } else if (unidad.getNivel() == 6) {
	                            nvl6.add(unidad);
	                            nvl7 = new ArrayList();
	                            unidad.setUnidades(nvl7);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl6);
	                            }
	                        } else if (unidad.getNivel() == 7) {
	                            nvl7.add(unidad);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl7);
	                            }
	                        }

	                    }
	                }
	            }

	        }
	        
	        if("CXC_CAJA_RECAUD".equals(perfil) || "CXC_ADMIN_CAJAS".equals(perfil) || "EMISOR_DE_TICKETS".equals(perfil)){

	            unmsm.setUnidades(nvl3);
	            for (DependenciaModel unidad : dependenciaPersistence.unidades()) {
	            	//F06080502, F0608050207, ...
	                if (unidad.getCodUnidad().length() >= numCaracteresDep) { 
	                    if ((unidad.getCodUnidad().substring(0, numCaracteresDep)).equals(dependencia)) {

	                    	if (unidad.getNivel() == 2) {
	                            nvl2.add(unidad);
	                            nvl3 = new ArrayList();
	                            unidad.setUnidades(nvl3);
	                            if(unidad.getCodUnidad().equals(dependencia)) {
	                            	unmsm.setUnidades(nvl2);
	                            }
	                        }
	                    	else if (unidad.getNivel() == 3) {
		                            nvl3.add(unidad);
		                            nvl4 = new ArrayList();
		                            unidad.setUnidades(nvl4);
		                            if(unidad.getCodUnidad().equals(dependencia)) {
		                            	unmsm.setUnidades(nvl3);
		                            }
	                    	}	                        	                    	
		                    if (unidad.getNivel() == 4) {
		                            nvl4.add(unidad);
		                            nvl5 = new ArrayList();
		                            unidad.setUnidades(nvl5);
		                            if(unidad.getCodUnidad().equals(dependencia)) {
		                            	unmsm.setUnidades(nvl4);
		                            }
		                    } else if (unidad.getNivel() == 5) {
		                            nvl5.add(unidad);
		                            nvl6 = new ArrayList();
		                            unidad.setUnidades(nvl6);
		                            if(unidad.getCodUnidad().equals(dependencia)) {
		                            	unmsm.setUnidades(nvl5);
		                            }
		                    } else if (unidad.getNivel() == 6) {
		                            nvl6.add(unidad);
		                            nvl7 = new ArrayList();
		                            unidad.setUnidades(nvl7);
		                            if(unidad.getCodUnidad().equals(dependencia)) {
		                            	unmsm.setUnidades(nvl6);
		                            }
		                    } else if (unidad.getNivel() == 7) {
		                        	nvl7.add(unidad);
		                        	if(unidad.getCodUnidad().equals(dependencia)) {
		                        		unmsm.setUnidades(nvl7);
		                        	}
		                    }
	                    }
	                }
	            }
	        }
	        
	        return unmsm;	
	}

	@Override
	public DependenciaModel dependenciasPorUsuarioYPerfilXTeso() {
		    List<DependenciaModel> nvl1 = new ArrayList();
	        List<DependenciaModel> nvl2 = new ArrayList();
	        List<DependenciaModel> nvl3 = new ArrayList();
	        List<DependenciaModel> nvl4 = new ArrayList();
	        List<DependenciaModel> nvl5 = new ArrayList();
	        List<DependenciaModel> nvl6 = new ArrayList();
	        List<DependenciaModel> nvl7 = new ArrayList();
	        
	        DependenciaModel unmsm = new DependenciaModel();
	        unmsm.setDescripcion("UNMSM");

	            unmsm.setUnidades(nvl1);
	            for (DependenciaModel unidad : dependenciaPersistence.unidadesXTeso()) {

	                if (unidad.getNivel() == 1) {
	                    nvl1.add(unidad);
	                    nvl2 = new ArrayList();
	                    unidad.setUnidades(nvl2);
	                } else if (unidad.getNivel() == 2) {
	                    nvl2.add(unidad);
	                    nvl3 = new ArrayList();
	                    unidad.setUnidades(nvl3);
	                } else if (unidad.getNivel() == 3) {
	                    nvl3.add(unidad);
	                    nvl4 = new ArrayList();
	                    unidad.setUnidades(nvl4);
	                } else if (unidad.getNivel() == 4) {
	                    nvl4.add(unidad);
	                    nvl5 = new ArrayList();
	                    unidad.setUnidades(nvl5);

	                }else  if (unidad.getNivel() == 5)    {

	                    nvl5.add(unidad);
	                    nvl6 = new ArrayList();
	                    unidad.setUnidades(nvl6);

	                }else    if (unidad.getNivel() == 6)   {

	                    nvl6.add(unidad);
	                    nvl7 = new ArrayList();
	                    unidad.setUnidades(nvl7);

	                }  else    if (unidad.getNivel() == 7)  {

	                    nvl7.add(unidad);

	                }
	            }
	        return unmsm;	
	}	

	@Override
	public DependenciaModel nombrePadre(String codHijo) {
		String primeraLetra=codHijo.substring(0,1);
		DependenciaModel objAux;
			
		if("F".equals(primeraLetra)){
			objAux=dependenciaPersistence.nombrePadreFacultad(codHijo);				
		}else{			
			objAux=dependenciaPersistence.nombrePadreDependencia(codHijo);				
		}
		
		return objAux;		
	}

	@Override
	public List<UnidadModel> facultadesYDependencias(){
		return dependenciaPersistence.facultadesYDependencias();
	}
	
	
	

}
