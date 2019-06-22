/**
 * 
 */

app.factory('listaServHistorialService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarHistorialXServicio: function(id_dependencia, codItem) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaServicioHistorialController/historialServicio/'+id_dependencia+'/'+codItem+'/')
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
