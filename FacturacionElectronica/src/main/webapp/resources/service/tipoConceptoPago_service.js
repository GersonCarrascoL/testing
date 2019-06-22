/**
 * 
 */

app.factory('tipoConceptoPagoService', ['$http', '$q', function($http, $q){

	return {
		
		listarTiposConceptosActivos: function() {
					var deferred = $q.defer();
					 $http.get('rest/TiposConcepto/listar')
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
