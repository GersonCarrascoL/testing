
app.factory('comprobantesPagoService', ['$http', '$q', function($http, $q){

	return {
		
		 getComprobantesPago: function() {
			var deferred = $q.defer();
			 $http.post('rest/ComprobantePagoController/obtenerComprobantePago', params)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			  })
			 .error(function(msg) {
			       deferred.reject(msg);
			  });
			 return deferred.promise;
		 },
		 
		 getNotasDeCredito: function(params) {
			var deferred = $q.defer();
			 $http.post('rest/ComprobantePagoController/obtenerNotasDeCredito', params)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			  })
			  .error(function(msg) {
			       deferred.reject(msg);
			  });
			 return deferred.promise;
		 },
		 getNotasDeCreditoAdminCaja: function(params) {
			var deferred = $q.defer();
			$http.post('rest/ComprobantePagoController/obtenerNotasDeCreditoAdminCaja', params)
			.success(function(data) { 
				deferred.resolve({data: data});
			})
			.error(function(msg) {
				deferred.reject(msg);
			});
			return deferred.promise;
		 },
		 enviarCorreo: function(params) {
				var deferred = $q.defer();
				 $http.post('rest/ReporteController/enviarCorreo', params)
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				  .error(function(msg) {
				       deferred.reject(msg);
				  });
				 return deferred.promise;
		},
		 getCorreoCliente: function(params) {
				var deferred = $q.defer();
				 $http.post('rest/ComprobantePagoController/obtenerCorreoCliente', params)
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				  .error(function(msg) {
				       deferred.reject(msg);
				  });
				 return deferred.promise;
		},
		 getNotificacion: function(ud_id) {
				var deferred = $q.defer();
				 $http.post('rest/ComprobantePagoController/getNotificacion',ud_id)
				 .success(function(data) { 
				       deferred.resolve({data: data});
				  })
				 .error(function(msg) {
				       deferred.reject(msg);
				  });
				 return deferred.promise;
		},
		getEstablecimientosXFacultad: function() {
			var deferred = $q.defer();
			$http.post('rest/ComprobantePagoController/listarEstablecimientosXFacultad')
			.success(function(data) { 
					 deferred.resolve({data: data});
			})
			.error(function(msg) {
					 deferred.reject(msg);
			});
			return deferred.promise;
		}
		
	};

}]);
