/**
 * 
 */

app.factory('ubigeoService', ['$http', '$q', function($http, $q){

	return {
		
		listarDataUbigeo: function() {
			var deferred = $q.defer();
			 $http.get('rest/UbigeoController/getDataUbigeo')
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
