/**
 * 
 */

app.factory('unidadMedidaService', ['$http', '$q', function($http, $q){

	return {
		
		listarUnidadesMedida: function() {
			var deferred = $q.defer();
			 $http.get('rest/UnidadMedida/listar')
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
