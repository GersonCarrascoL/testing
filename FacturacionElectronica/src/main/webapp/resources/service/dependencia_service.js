/**
 * 
 */

app.factory('dependenciaService', ['$http', '$q', function($http, $q){

	return {
		
		listarDependenciasBase: function() {
				return $http.get('rest/Dependencia/listarDependencias')
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while fetching dependencias');
									return $q.reject(errResponse);
								}
						);
		},
		
		dependenciasPorUsuarioYPerfil: function(dependencia, perfil) {
			var deferred = $q.defer();
			 $http.get('rest/Dependencia/dependenciasUserPerfil/'+dependencia+'/'+perfil)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;			 
		 },
		 
		 dependenciasPorUsuarioYPerfilXTeso: function() {
				var deferred = $q.defer();
				 $http.get('rest/Dependencia/dependenciasUserPerfilXTeso/')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				   })
				 .error(function(msg) {
				           deferred.reject(msg);
				   });

				 return deferred.promise;			 
			 },
		
		dependenciasNombrePadre: function(codigoNodoSeleccionado) {
			var deferred = $q.defer();
			 $http.get('rest/Dependencia/nombrePadre/'+codigoNodoSeleccionado)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;			 
		},
		
		listarFacultadesYUnidadesPadre: function() {
			var deferred = $q.defer();
			 $http.get('rest/Dependencia/listarFacultadesNivel2')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		},
		
		/*********************************** UNIDAD CODIGO *************************************/
		listarUnidadesCodigoXDependencia: function(udIdPadre) {
			var deferred = $q.defer();
			 $http.get('rest/UnidadCodigo/unidadesRegXDependencia/'+udIdPadre)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;		
		},
		
		buscarUnidadesCodigoXDep: function(udIdPadre, codUnidad) {
			var deferred = $q.defer();
			 $http.get('rest/UnidadCodigo/udIdPadre/'+udIdPadre+'/codUnidad/'+codUnidad+'/')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;			
		},
		
		createUnidadCodigo: function(unidad){
		    var deferred = $q.defer();
			$http.post('rest/UnidadCodigo/registrarUnidadCodigo/', unidad)
			.success(function(data) { 
		        deferred.resolve({data: data});
			   })
			.error(function(data) {
			    deferred.reject(data);
			   });

			return deferred.promise;
		},
		
		updateUnidadCodigo: function(unidad){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/UnidadCodigo/updateUnidadCodigo/', unidad)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	    },
		
		deleteUnidadCodigo: function(udId, codigo_unidad){
		    var deferred = $q.defer();
			$http.delete('rest/UnidadCodigo/deleteUnidadCodigo/'+udId+'/'+codigo_unidad)
			.success(function(data) { 
		        deferred.resolve({data: data});
			   })
			.error(function(data) {
			    deferred.reject(data);
			   });
			return deferred.promise;
		},
	    
	    listarUnidadesCodigoTodos: function() {
			var deferred = $q.defer();
			 $http.get('rest/UnidadCodigo/unidadesRegTodos')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;		
		},
		
		volverEnviarUnidadCodigo: function(unidad){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/UnidadCodigo/volverEnviarUnidRec/', unidad)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	    },
	    
	    aprobarUnidadCod: function(udId){
    	    var deferred = $q.defer();
			$http.put('rest/UnidadCodigo/aprobarUnidRec/udId/'+udId+'/')
			.success(function(data) { 
		       deferred.resolve({ data: data });
			   })
			.error(function(msg) {
			           deferred.reject(msg);
			   });
			return deferred.promise;
      },
      
      rechazarUnidadCod: function(udId, motivoRechazo){
    	    var deferred = $q.defer();
			$http.put('rest/UnidadCodigo/rechazarUnidRec/'+udId+'/'+motivoRechazo+'/')
			.success(function(data) { 
		       deferred.resolve({ data: data });
			   })
			.error(function(msg) {
			           deferred.reject(msg);
			   });
			return deferred.promise;
      }
		
		
		/**************************************************************************************/
	
		
		
	};

}]);
