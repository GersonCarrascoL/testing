/**
 * 
 */

app.factory('maestroClientesService', ['$http', '$q', function($http, $q){
	
	var dataClie = {
        cliente: {},
        tipoCliente:'', //RUC, SINRUC, SUM, SERVIDOR
        noData: false,
        regExito: false        
    };
	
	return {
		
		getCliente: function() {
            return dataClie.cliente;
        },
        setCliente: function(cliente) {
        	dataClie.cliente = cliente;
        },
        getTipoCliente: function() {
            return dataClie.tipoCliente;
        },
        setTipoCliente: function(tipoCliente) {
        	dataClie.tipoCliente = tipoCliente;
        },
        getNoData: function() {
        	return dataClie.noData;
        },
        setNoData: function(noData) {
        	dataClie.noData = noData;
        },
        getRegExito: function() {
        	return dataClie.regExito;
        },
        setRegExito: function(regExito) {
        	dataClie.regExito = regExito;
        },
        clearData: function() {
        	dataClie = {
	        	  cliente: {},
	        	  tipoCliente:'',
	        	  noData: false,
	        	  regExito: false        
        	};
        },
        
		/******************************************* INCIO CLIENTES  CON RUC **************************************/
		 buscarClienteRUC_ruc: function(ruc) {
			 var deferred = $q.defer();
			 $http.get('rest/ClienteRucController/byruc/'+ruc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarClienteRUC_rucHabilitados: function(ruc) {
			 var deferred = $q.defer();
			 $http.get('rest/ClienteRucController/byruc/'+ruc+'/estado/1')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarClienteRUC_razonSocial: function(razonSocial) {
			 var deferred = $q.defer();
			 $http.post('rest/ClienteRucController/razonSocialTodos', razonSocial)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;			
		 },

		 buscarClienteRUC_razonSocialHabilitados: function(razonSocial) {
			 var deferred = $q.defer();
			 $http.post('rest/ClienteRucController/razonSocialHabilitados', razonSocial)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;			
		 },
		 
		 createClienteRUC: function(cliente){
			    var deferred = $q.defer();
				$http.post('rest/ClienteRucController/registrarClienteRUC/', cliente)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      createClienteRUC_extra: function(cliente){
			    var deferred = $q.defer();
				$http.post('rest/ClienteRucController/registrarClienteRUC_extra/', cliente)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      updateEstadoRuc: function(ruc, estado){
	    	    var deferred = $q.defer();
				$http.put('rest/ClienteRucController/updateEstado/ruc/'+ruc+'/estado/'+estado)
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      updateClienteRuc: function(cliente){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ClienteRucController/updateCliente/', cliente)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      updateClienteRuc_extra: function(cliente){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ClienteRucController/updateClienteValidado/', cliente)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
		 /******************************************* FIN CLIENTES  CON RUC **************************************/
		 
		 
		 /******************************************* INICIO CLIENTES  SIN RUC **************************************/
		 buscarClienteXTipoDoc_numDoc: function(tipoDoc, numDoc) {
			 var deferred = $q.defer();
			 $http.get('rest/ClienteSinRuc/tipoDoc/'+tipoDoc+'/numDoc/'+numDoc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarClienteXTipoDoc_numDocHabilitados: function(tipoDoc, numDoc) {
			 var deferred = $q.defer();
			 $http.get('rest/ClienteSinRuc/tipoDoc/'+tipoDoc+'/numDoc/'+numDoc+'/estado/1')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarClienteXNombreCompleto: function(nomCompleto) {
			 var deferred = $q.defer();
			 $http.post('rest/ClienteSinRuc/nombreCompletoTodos', nomCompleto)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarClienteXNombreCompletoHabilitados: function(nomCompleto) {
			 var deferred = $q.defer();
			 $http.post('rest/ClienteSinRuc/nombreCompletoHabilitados', nomCompleto)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 createClienteSinRUC: function(cliente){
			    var deferred = $q.defer();
				$http.post('rest/ClienteSinRuc/registrarCliente/', cliente)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      createClienteSinRUC_extra: function(cliente){
			    var deferred = $q.defer();
				$http.post('rest/ClienteSinRuc/registrarCliente_extra/', cliente)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      updateEstadoSinRuc: function(idCliente, estado){
	    	    var deferred = $q.defer();
				$http.put('rest/ClienteSinRuc/updateEstado/idCliente/'+idCliente+'/estado/'+estado)
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      updateClienteSinRuc: function(cliente){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ClienteSinRuc/updateCliente/', cliente)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
		 /******************************************* FIN CLIENTES  SIN RUC **************************************/
		 
		 
		 /******************************************* INICIO  ALUMNO **************************************/
		 buscarAlumnoXTipoDoc_numDoc: function(tipoDoc, numDoc) {
			 var deferred = $q.defer();
			 $http.get('rest/AlumnoSum/tipoDoc/'+tipoDoc+'/numDoc/'+numDoc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;	
		 },
		 
		 buscarAlumnoXNombreCompleto: function(nomCompleto) {
			 var deferred = $q.defer();
			 $http.post('rest/AlumnoSum/nombreCompleto',nomCompleto)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarAlumnoXcodMatricula: function(codMatricula) {
			 var deferred = $q.defer();
			 $http.get('rest/AlumnoSum/codMatricula/'+codMatricula)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;				 
		 },
		 
		 /******************************************* FIN  ALUMNO **************************************/

		 
		 /******************************************* INICIO  SERVIDORES **************************************/
		 buscarServidorXNombreCompleto: function(nomCompleto) {
			 var deferred = $q.defer();
			 $http.post('rest/servidor/nombre', nomCompleto)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 buscarServidorXTipoDoc_numDoc: function(tipoDoc, numDoc) {
			 var deferred = $q.defer();
			 $http.get('rest/servidor/tipoDoc/'+tipoDoc+'/numDoc/'+numDoc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;	
		 },
		 
		 buscarServidorXNumDoc: function(numDoc) {
			 var deferred = $q.defer();
			 $http.get('rest/servidor/numDoc/'+numDoc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;	
		 },
		 
		 buscarServidorXruc: function(ruc) {
			 var deferred = $q.defer();
			 $http.get('rest/servidor/ruc/'+ruc)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
 
		 buscarServidorXcodTrabajador: function(codTrabajador) {
			 var deferred = $q.defer();
			 $http.get('rest/servidor/codTrabajador/'+codTrabajador)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;				 
		 } 
		 /******************************************* FIN  SERVIDORES **************************************/

	};
    
}]);
