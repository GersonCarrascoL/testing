/**
 * 
 */

app.factory('perfilService', ['$http', '$q', function($http, $q){

	return {
		
		listarPerfilesActivos: function() {
					var deferred = $q.defer();
					 $http.get('rest/Perfil/tiposPerfil')
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