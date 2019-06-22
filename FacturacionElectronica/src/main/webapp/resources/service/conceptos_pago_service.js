
app.factory('conceptosPagoService', ['$http', '$q', function($http, $q){

	return {
		
		listarConceptosPagoXDependencia: function(codigoDependencia) {
			var deferred = $q.defer();
			 $http.get('rest/conceptoUnidadController/listarXDependencia/'+codigoDependencia)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(data) {
			       deferred.reject(data);
			   });

			 return deferred.promise;			 
		 },
		 
		 listarConceptosPagoXDependenciaHabilitados: function(codigoDependencia) {
				var deferred = $q.defer();
				 $http.get('rest/conceptoUnidadController/listarXDependencia/'+codigoDependencia+'/estado/1')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				   })
				 .error(function(data) {
				       deferred.reject(data);
				   });

				 return deferred.promise;			 
		 },
			 
		listarConceptosPagoXUnaDependencia: function(codigoDependencia) {
					var deferred = $q.defer();
					 $http.get('rest/conceptoUnidadController/listarXUnaDependencia/'+codigoDependencia+'/')
					 .success(function(data) { 
					       deferred.resolve({data: data});
					   })
					 .error(function(data) {
					       deferred.reject(data);
					   });

					 return deferred.promise;			 
		},
		
		listarConceptosPagoHabilidatosSoloXUnaDep: function(udIdDependencia) {
			var deferred = $q.defer();
			 $http.get('rest/conceptoUnidadController/listarHabilitadosXUnaDep/'+udIdDependencia+'/')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(data) {
			       deferred.reject(data);
			   });

			 return deferred.promise;			 
		},
			 
		listarConceptosPagoPendientesXdep: function(codigoDependencia) {
				var deferred = $q.defer();
				$http.get('rest/conceptoUnidadController/listarPendientesXdep/'+codigoDependencia)
				.success(function(data) { 
					       deferred.resolve({data: data});
					})
				.error(function(data) {
					       deferred.reject(data);
					});

				return deferred.promise;			 
		},
		
		listarConceptosPagoPendientesTodos: function() {
				var deferred = $q.defer();
				$http.get('rest/conceptoUnidadController/listarPendientesTodos')
				.success(function(data) { 
					       deferred.resolve({data: data});
					})
				.error(function(data) {
					       deferred.reject(data);
					});
	
				return deferred.promise;			 
		},
		
		listarConceptosPagoTodos: function() {
			var deferred = $q.defer();
			$http.get('rest/conceptoUnidadController/listarTodos')
			.success(function(data) { 
				       deferred.resolve({data: data});
				})
			.error(function(data) {
				       deferred.reject(data);
				});

			return deferred.promise;			 
		},
		 
		 createConceptoUnidad: function(concepto){
			    var deferred = $q.defer();
				$http.post('rest/conceptoUnidadController/registrarConceptoPagoUnidad/', concepto)
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });

			 return deferred.promise;
	     },
	     
	     updateEstado: function(idCPU, estado){
	    	    var deferred = $q.defer();
				$http.put('rest/conceptoUnidadController/updateEstado/idCPago/'+idCPU+'/estado/'+estado)
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      updateConceptoPago: function(concepto){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/conceptoUnidadController/updateConcepto/', concepto)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      volverEnviarConcepto: function(concepto){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/conceptoUnidadController/volverEnviarConcepto/', concepto)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      aprobarConcepto: function(idCPU){
	    	    var deferred = $q.defer();
				$http.put('rest/conceptoUnidadController/aprobarConcepto/idCPago/'+idCPU+'/')
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      rechazarConcepto: function(idCPU, motivoRechazo){
	    	    var deferred = $q.defer();
				$http.put('rest/conceptoUnidadController/rechazarConcepto/'+idCPU+'/'+motivoRechazo+'/')
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	     
	      listarBancos: function() {
				var deferred = $q.defer();
				$http.get('rest/FacturaController/listarBancos')
				.success(function(data) { 
					       deferred.resolve({data: data});
					})
				.error(function(data) {
					       deferred.reject(data);
					});
	
				return deferred.promise;			 
	      },
	      
	      buscarConceptosMaestros: function(nombre, codigo, udIdPadre){
	    	  var deferred = $q.defer();
				$http.get('rest/conceptoMaestroController/buscar/'+nombre+'/'+codigo+'/'+udIdPadre+'/')
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      createConceptoUnidadMaestra: function(concepto){
			    var deferred = $q.defer();
				$http.post('rest/conceptoUnidadController/registrarConceptoUnidadMatriz/', concepto)
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });
				return deferred.promise;
	      },
	      
	      deleteConceptoUnidad: function(idCPU, idCPAGO){
			    var deferred = $q.defer();
				$http.delete('rest/conceptoUnidadController/deleteConceptoUnidad/'+idCPU+'/'+idCPAGO)
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });
				return deferred.promise;
	      },
	      
	      downloadReporteConceptosPago: function(udCodUnidad, nameUnidadFac){
			    var deferred = $q.defer();
				$http.post('rest/conceptoUnidadController/reporteConceptoPago/'+udCodUnidad+'/'+nameUnidadFac+'/')
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });

			 return deferred.promise;
	     },
	     
	     downloadReporteConceptosPagoAdminCentral: function(udIdAministrativa, nameUnidadFac){
			    var deferred = $q.defer();
				$http.post('rest/conceptoUnidadController/reporteConceptoPago/'+udIdAministrativa+'/'+nameUnidadFac+'/')
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });

			 return deferred.promise;
	     },
	     
	     existeConceptoPago: function(idCpago, udId) {
				var deferred = $q.defer();
				 $http.get('rest/conceptoUnidadController/existeConceptoPago/'+idCpago+'/'+udId+'/')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				   })
				 .error(function(data) {
				       deferred.reject(data);
				   });

				 return deferred.promise;			 
	     }
		
	};

}]);
