app.factory('catCodProductoService', ['$http', '$q', function($http, $q){

	return {
		
		listarCatCodProductos: function() {
			var deferred = $q.defer();
			 $http.get('rest/CatCodProductoController/listarCatCodProductos')
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