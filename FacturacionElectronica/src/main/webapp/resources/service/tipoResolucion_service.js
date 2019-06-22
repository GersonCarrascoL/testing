
app.factory('tipoResolucion', ['$http', '$q', function($http, $q){

	return {
		
		listarTiposResolucion: function() {
			var deferred = $q.defer();
			 $http.get('rest/TipoResolucionController/getDataTipoResoluciones')
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
