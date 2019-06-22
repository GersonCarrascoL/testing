app.factory('listaRolesService', ['$http', '$q', function($http, $q){
	
	return {
		
		listarRolesxDependencia:function(codDependencia){
			
			var deferred = $q.defer();
			
			$http.get('rest/RolController/roles/'+codDependencia)
			.success(function(data) {
			     console.log(data);
				deferred.resolve({
			              data: data});
			   })
			 .error(function(msg) {
			       deferred.reject(msg);
			   });

			 return deferred.promise;
		},
		
		createUsuarioRol: function(usuario){
			console.log(usuario);
		    var deferred = $q.defer();
			$http.post('rest/RolController/registrarUsuarioRol/', usuario)
			.success(function(data) { 
		       deferred.resolve({data: data});
			   })
			.error(function(msg) {
			           deferred.reject(msg);
			   });

		 return deferred.promise;
      },
      
      updateEstadoUsuRol: function(id_hist, estado){
  	    var deferred = $q.defer();
			$http.put('rest/RolController/updateEstado/id_hist/'+id_hist+'/estado/'+estado)
			.success(function(data) { 
		       deferred.resolve({ data: data });
			   })
			.error(function(msg) {
			           deferred.reject(msg);
			   });
			return deferred.promise;
      },
    
      updateUsuarioRol: function(usuario){
    	  var deferred = $q.defer();
    	  $http.put('rest/RolController/updateUsuarioRol/', usuario)
    	    .success(function(data) { 
		       deferred.resolve({ data: data });
			   })
			.error(function(msg) {
			           deferred.reject(msg);
			   });
		  return deferred.promise;		
      },
      
      deleteUsuarioRol: function(id_hist){
  		var deferred = $q.defer();
    	  $http.delete('rest/RolController/deleteUsuarioRol/'+id_hist)
    	    .success(function(data) { 
  		       deferred.resolve({ data: data });
  			   })
  			.error(function(msg) {
  			           deferred.reject(msg);
  			   });
  		  return deferred.promise;
  	},
  	
  	buscarRolxDni: function(dni){
  		var deferred = $q.defer();
    	  $http.get('rest/RolController/rolxDni/'+dni)
    	    .success(function(data) { 
  		       deferred.resolve({ data: data });
  			   })
  			.error(function(msg) {
  			           deferred.reject(msg);
  			   });
  		  return deferred.promise;
  	}
      
	}
	
}])