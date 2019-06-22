/**
 * 
 */

app.factory('listapHistorialService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarHistorialXitem: function(id_dependencia, codItem) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaPrecioHistorialController/historialItem/'+id_dependencia+'/'+codItem+'/')
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
