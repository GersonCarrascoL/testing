app.factory('notaService', ['$http', '$q', function($http, $q){
	return {
			getTipoNotaCreditoBoleta: function(){
				return $http.get('rest/notaCredito/getTipoNotaCreditoBoleta')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    
		    getTipoNotaCreditoFactura: function(){
				return $http.get('rest/notaCredito/getTipoNotaCreditoFactura')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },	
		    
		    getComprobantesParaNotas: function(cod_dependencia,tipo,numeroComprobante){
				return $http.get('rest/notaCredito/getComprobantesParaNotas/'+cod_dependencia+'/'+tipo+'/'+numeroComprobante)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    getEstablecimiento: function(){
				return $http.get('rest/FacturaController/listarUnidades/')	
						.then(
								function(response){
									return response.data[0];
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    getDetalleComprobante: function(numero_documento,tipo){
				return $http.get('rest/notaCredito/getDetalleComprobante/'+numero_documento+'/'+tipo)	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    getInformacionComprobante: function(numero_documento,tipo){
				return $http.get('rest/notaCredito/getInformacionComprobante/'+numero_documento+'/'+tipo)	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    getIgv: function(){
				return $http.get('rest/FacturaController/listarTipoImpuesto/IGV/')	
						.then(
								function(response){
									return response.data[0].monto;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    registrarNotaCredito: function(notaCredito){
				return $http.post('rest/notaCredito/registrarNotaCredito', notaCredito)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									 return $q.reject(errResponse);
								}
						);			
		    },
		    getUrlFacturador: function(){
				return $http.get('rest/SistemaController/sistema')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    getUrlConsultaFacturador: function(){
				return $http.get('rest/SistemaController/sistemaConsulta')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },		    
		    enviarFacturador: function(url,data){
				return $http.post(url,data)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									 return $q.reject(errResponse);
								}
						);			
		    },
		    generarArchivosAlFacturador: function(data){
				return $http.post('rest/notaCredito/generarArchivosComprobante',data)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									 return $q.reject(errResponse);
								}
						);			
		    },
		    geInitBoleta: function(){
				return $http.get('rest/ComprobantePagoController/inicioNotaCreditoBoleta/')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    },
		    geInitFactura: function(){
				return $http.get('rest/ComprobantePagoController/inicioNotaCreditoFactura/')	
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									return $q.reject(errResponse);
								}
						);
		    }		    
	};
}]);
