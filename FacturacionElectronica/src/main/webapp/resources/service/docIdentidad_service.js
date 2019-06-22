/**
 * 
 */

app.factory('docIdentidadService', ['$http', '$q', function($http, $q){

	return {
		
		listardocIdentidadActivas: function() {
					var deferred = $q.defer();
					 $http.get('rest/DocumentoIdentidad/tiposDocIdentidad')
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
