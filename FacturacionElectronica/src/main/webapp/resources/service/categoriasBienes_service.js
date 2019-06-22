
app.factory('categoriasBienesService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarCategoriasXUnidadBase: function(udId) {
			 var deferred = $q.defer();
			 $http.get('rest/BienCategoriaController/getCategorias/'+udId)
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
				$http.post('rest/BienCategoriaController/registrarCategoria/', categoria)
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
	    	  $http.put('rest/BienCategoriaController/updateCategoria/', categoria)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      deleteCategory: function(idBienCata){
			    var deferred = $q.defer();
				$http.delete('rest/BienCategoriaController/deleteCategoria/'+idBienCata)
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
