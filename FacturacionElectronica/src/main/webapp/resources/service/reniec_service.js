
app.factory('reniecService', ['$http', '$q', function($http, $q){

	return {

		 consultaDNI: function(dni) {
				var deferred = $q.defer();
				 $http.get('rest/ValidationController/reniec/'+dni)
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
