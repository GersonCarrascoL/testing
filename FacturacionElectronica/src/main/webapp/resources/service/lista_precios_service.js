/**
 * 
 */

app.factory('listaPreciosService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarPreciosXDependencia: function(codigoDependencia) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaItemPrecioController/listarXDependencia/'+codigoDependencia)
			 .success(function(data) { 
			       deferred.resolve({
			              data: data,
			    	   	  title: data.title,
			              cost: data.price});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 listarItemsXDependenciaHabilitados: function(codigoDependencia) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaItemPrecioController/listarXDependencia/habilitados/'+codigoDependencia)
			 .success(function(data) { 
			       deferred.resolve({
			              data: data,
			    	   	  title: data.title,
			              cost: data.price});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 listarPreciosXAdmiCentral: function(udIdAdministrativa) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaItemPrecioController/listarXAdmiCentral/'+udIdAdministrativa)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 listarPreciosXAdmiCentralHabilitados: function(udIdAdministrativa) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaItemPrecioController/listarXAdmiCentral/habilitados/'+udIdAdministrativa)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			           deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
 
		 createItem: function(item){
			    var deferred = $q.defer();
				$http.post('rest/ListaItemPrecioController/registrarItem/', item)
				.success(function(data) { 
			       deferred.resolve({data: data});
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      updateEstado: function(udId, codItem, estado){
	    	    var deferred = $q.defer();
				$http.put('rest/ListaItemPrecioController/updateEstado/udId/'+udId+'/codItem/'+codItem+'/estado/'+estado)
				.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      updateItem: function(item){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ListaItemPrecioController/updateItem/', item)
	    	    .success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      deleteItemPrecio: function(udId, codItem){
			    var deferred = $q.defer();
				$http.delete('rest/ListaItemPrecioController/deleteItemPrecio/'+udId+'/'+codItem)
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
