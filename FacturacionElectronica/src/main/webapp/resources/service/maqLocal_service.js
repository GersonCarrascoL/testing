
app.factory('maqLocalService', ['$http', '$q', function($http, $q){
	
	return {
		getMaqLocalesTodos: function(){
			return $http.get('rest/getMaqLocalesTodos')	
					.then(
							function(response){
								return {data:response.data};
							}, 
							function(errResponse){
								return $q.reject(errResponse);
							}
					);
	    },
	    
	    getMaqLocalesXudId: function(udId){
			return $http.get('rest/getMaqLocales/'+udId)	
					.then(
							function(response){
								return {data:response.data};
							}, 
							function(errResponse){
								return $q.reject(errResponse);
							}
					);
	    }
	    
	};

}]);
