
app.factory('categoriasServiciosService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarCategoriasXUnidadBase: function(udId) {
			 var deferred = $q.defer();
			 $http.get('rest/ServicioCategoriaController/getCategorias/'+udId)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 createCategory: function(categoria){
			    var deferred = $q.defer();
				$http.post('rest/ServicioCategoriaController/registrarCategoria/', categoria)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      updateCategory: function(categoria){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ServicioCategoriaController/updateCategoria/', categoria)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      deleteCategory: function(idServCata){
			    var deferred = $q.defer();
				$http.delete('rest/ServicioCategoriaController/deleteCategoria/'+idServCata)
				.success(function(data) { 
			        deferred.resolve({data: data});
				   })
				.error(function(data) {
				    deferred.reject(data);
				   });
				return deferred.promise;
		  }
	     
		
	};

}]);
