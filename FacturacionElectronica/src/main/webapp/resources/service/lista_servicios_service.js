app.factory('listaServiciosService', ['$http', '$q', function($http, $q){

	return {
	 
		 listarServiciosXDependencia: function(codigoDependencia) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaServiciosController/listarXDependencia/'+codigoDependencia)
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
		 
		 listarServiciosXDependenciaHabilitados: function(codigoDependencia) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaServiciosController/listarXDependencia/habilitados/'+codigoDependencia)
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
		 
		 listarServiciosXAdmiCentral: function(udIdAdministrativa) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaServiciosController/listarXAdmiCentral/'+udIdAdministrativa)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
		 
		 listarServiciosXAdmiCentralHabilitados: function(udIdAdministrativa) {
			 var deferred = $q.defer();
			 $http.get('rest/ListaServiciosController/listarXAdmiCentral/habilitados/'+udIdAdministrativa)
			 .success(function(data) { 
			       deferred.resolve({data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		 },
 
		 createService: function(item){
			    var deferred = $q.defer();
				$http.post('rest/ListaServiciosController/registrarServicio/', item)
				.success(function(data) { 
			         deferred.resolve({data: data});
				   })
				.error(function(msg) {
				     deferred.reject(msg);
				   });

			 return deferred.promise;
	      },
	      
	      updateServicio: function(item){
	    	  var deferred = $q.defer();
	    	  $http.put('rest/ListaServiciosController/updateServicio/', item)
	    	    .success(function(data) { 
			         deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				     deferred.reject(msg);
				   });
			  return deferred.promise;		
	      },
	      
	      updateEstado: function(udId, codItem, estado){
	    	    var deferred = $q.defer();
				$http.put('rest/ListaServiciosController/updateEstado/udId/'+udId+'/codItem/'+codItem+'/estado/'+estado)
				.success(function(data) { 
			          deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				      deferred.reject(msg);
				   });
				return deferred.promise;
	      },
	      
	      deleteServicio: function(udId, codItem){
			    var deferred = $q.defer();
				$http.delete('rest/ListaServiciosController/deleteServicio/'+udId+'/'+codItem)
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
