/**
 * 
 */

app.factory('monedaService', ['$http', '$q', function($http, $q){

	return {
		
		listarMonedasActivas: function() {
			var deferred = $q.defer();
			 $http.get('rest/MonedaController/listarMonedas')
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
