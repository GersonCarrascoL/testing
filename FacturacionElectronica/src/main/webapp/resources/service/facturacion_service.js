app.service('facturacion', function($http,$timeout,$route, $q) {
	this.get=function(puntero,url) {
		$http.get(url).success(function(data) {
			puntero.valor=data;
		});
	};
	this.post=function(url,obj) {
		var d = $q.defer();
	    var p = d.promise;
	    $http.post(url,obj)
        .success(function(data) {
            d.resolve(data);
        })
        .error(function(err) {
        	$scope.cargando.valor=false;
            d.reject(err);
        });
	    return p;
	};
	this.postDirectiva=function(url,obj) {
		return $http.post(url, obj)
		.then(
				function(response){
					return response.data;
				}, 
				function(errResponse){
					 return $q.reject(errResponse);
				}
		);
	};		
	this.facturar=function(cad,tip,num,cargando,obj,banderaSunat){
		function metodo(cad1,obj1,est,ser,banderaSunat){
			var obj2;
			if(cad1.length!=0){
				$http.post(cad1[0],obj1).success(function (data) {
					switch (cad1.length) {
						case 1:
							cargando.valor=false;
							$timeout(function() {
				    			$route.reload();
				    		  },1000);
							break;
						case 2:
							if(banderaSunat=='1'){
								if(data.tipo=='03' || data.tipo=='04'){
										$.gritter.add({
											title: 'Facturador',
											text: 'El comprobante se ha enviado a la SUNAT',
											image: 'resources/assets/img/notification/succes.png',
											sticky: false,
											time: ''
										});								
								}else{
									$.gritter.add({
										title: 'Facturador',
										text: 'El comprobante NO se ha enviado a la SUNAT',
										image: 'resources/assets/img/notification/peligro.png',
										sticky: false,
										time: ''
									});									
								}
								obj2={
					            		'comprobante'		: obj.tipo,
					            		'establecimiento'	: est,
					            		'serie'   			: ser,
					            		'descripcion'		: data.descripcion,
					            		'observacion'		: data.observacion,
					            		'tipo'				: data.tipo}								
							}else{
								$.gritter.add({
									title: 'Facturador',
									text: 'Deberá enviar a la sunat en la opción Mantenimiento de Comprobantes.',
									image: 'resources/assets/img/notification/peligro.png',
									sticky: false,
									time: ''
								});									
							}

							break;
						case 3:
							window.open("rest/ReporteController/generarPDF/"+data.establecimiento+"/"+data.serie+"/"+obj.tipo+"/"+false+"/", "", "width=800,height=600");
							obj2={
									'num_ruc'	: '20148092282',
									'tip_doc'   : tip,
									'num_doc'	: num+data.establecimiento+"-"+data.serie
				            };
							break;
						default:
					}
					metodo(cad1.slice(1),obj2,data.establecimiento,data.serie,banderaSunat);
	        	}).error(function (data) {
					$.gritter.add({
						title: 'Facturador',
						text: 'Hubo un error en la conexión',
						image: 'resources/assets/img/notification/error.png',
						sticky: false,
						time: ''
					});
	        		cargando.valor=false;
					$timeout(function() {
		    			$route.reload();
		    		  },1000);
	        	});
			}
		}
		metodo(cad,obj);
	};
});