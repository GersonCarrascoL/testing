
app.factory('sunatService', ['$http', '$q', function($http, $q){

	return {
		
		/*************************** DETRACCION SUNAT *****************************/
		detraccionImporteMinimoActual: function() {
			var deferred = $q.defer();
			 $http.get('rest/DetraccionSunat/importeMinimoDet')
			 .success(function(data) { 
			       deferred.resolve({data: data});
			  })
			 .error(function(msg) {
			           deferred.reject(msg);
			  });

			 return deferred.promise;
		 },
		 
		 porcentajeDetraccionUsoGeneral: function() {
				var deferred = $q.defer();
				 $http.get('rest/DetraccionSunat/porcentajeDetraccionUsoGeneral')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		 },
		 
		 /******************************************************************************/
		 
		 tipoCambioActual: function() {
				var deferred = $q.defer();
				 $http.get('rest/TipoCambioController/tipoCambioActual')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		 },
		 
		 consultaRUC: function(ruc) {
				var deferred = $q.defer();
				 $http.get('rest/ValidationController/sunat/'+ruc)
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		 },
		   
		 consultaRUC_validar: function(ruc) {
				var deferred = $q.defer();
				 $http.get('rest/ValidationController/sunat/valida/'+ruc)
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		 },
		  
		  /*************************** CATALOGO NUMERO 15 *****************************/
		  dataCataDetracciones: function(ruc) {
				var deferred = $q.defer();
				 $http.get('rest/SunatCatalogosController/catN15/listarCatDetracciones')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		  },
		  
		  codigoDetraccionUsoGeneral: function(ruc) {
				var deferred = $q.defer();
				 $http.get('rest/SunatCatalogosController/catN15/codUsoGeneralDetraccion')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				           deferred.reject(msg);
				  });

				 return deferred.promise;
		  },
		  
		  /******************************************************************************/
		  
		  /*************************** CATALOGOS GENERAL *****************************/
		  dataTiposOperacionIGV: function() {
				var deferred = $q.defer();
				 $http.get('rest/SunatCatalogosController/cataGeneral/tiposOperacionIgv')
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				        deferred.reject(msg);
				  });

				 return deferred.promise;
		  }
		  
		  /******************************************************************************/
			 
		
	};

}]);
