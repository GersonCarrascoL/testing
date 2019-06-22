app.factory('mantenimientoTicketService',['$http','$q',function($http,$q){
	
	return {
		
		getComprobantesTicket: function(udId,fecIni,fecFin){
			var deferred=$q.defer();
			$http.post('rest/ComprobanteTicketController/obtenerComprobanteTicket/',
				{
					'udId'				: udId,
					'tipo'   			: 1,
					'fechaInicial'    	: fecIni,
					'fechaFinal'    	: fecFin
				}
			).success(function (data){
				deferred.resolve({data:data});
				console.log(data);
			})
			.error(function(msg){
				deferred.reject(msg);
			});
			return deferred.promise;
		},
		emitirTicket: function(datosCompTicket){
			var deferred=$q.defer();
			$http.post('rest/ComprobanteTicketController/insertarTicket',
					datosCompTicket
			)
			.success(function (data){
				deferred.resolve({data:data});
				//console.log(data);
			})
			.error(function(msg){
				deferred.reject(msg);
				//console.log("badTicket")
			});
			return deferred.promise;
		},
		anularTicket: function(codEstabAnexo,correlativo){
			var deferred=$q.defer();
			$http.put('rest/ComprobanteTicketController/anularComprobanteTicket/codEstabAnexo/'+codEstabAnexo+'/correlativo/'+correlativo)
			.success(function(data) { 
			       deferred.resolve({ data: data });
				   })
				.error(function(msg) {
				           deferred.reject(msg);
				   });
				return deferred.promise;
		}
	};
	
}]);