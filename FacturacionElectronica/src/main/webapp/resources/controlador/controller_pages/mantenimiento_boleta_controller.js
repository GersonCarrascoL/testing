app.controller("mantenimiento_boleta_controller",function($scope, $http, facturacion){
	var sistema;var sistemaConsulta;
    $scope.estados=[{id:"",title:"Todos"},
                    {id:'0',title:"Enviado y Aceptado SUNAT"},
                    {id:'1',title:"Sin enviar SUNAT"},
                    {id:'2',title:"Enviado y Anulado SUNAT"}];
	$http.get("rest/SistemaController/sistema").success(function(data){
 		sistema=data;
 	});
	$http.get("rest/SistemaController/sistemaConsulta").success(function(data){
		sistemaConsulta=data;
 	});	
	$scope.download=function(a,b){
    	$scope.url="http://quipucamayoc.unmsm.edu.pe/bajada/cxc_CP-20148092282-03-B"+a+"-"+b+".pdf";
    	$scope.abrirModal("#descargarPDF");
    };
    $scope.cambio=function(c,i){
    	$scope.cargando.valor=true;
    	var banderaEnvio=false;
    	var documento={'num_ruc': '20148092282','tip_doc' : '03','num_doc'	: 'B'+c.codEst+"-"+c.serie};
		facturacion.postDirectiva(sistemaConsulta,documento).then(
	   	          function(resp) {	 
	   	        	  if(resp.status=="0001" || resp.status=="0002"){
	   	        		  	documento={'num_ruc': '20148092282','tip_doc' : '03','num_doc'	: 'B'+c.codEst+"-"+c.serie,'ind_situ':resp.status};
	   	        		  	$scope.mensajePositivo('Facturador',resp.Message,'resources/assets/img/notification/succes.png');
	   	        		  	return facturacion.postDirectiva('rest/ComprobantePagoController/modificarEstadoSunat',documento);
	   	        	  }else{
	   	        		  	banderaEnvio=true;
				   	        $scope.mensajeCambio("#confirmacion","Enviar","¿Seguro que desea enviar la Boleta "+c.codEst+"-"+c.serie+"?");
				   	     	$scope.cTemp=c;
				   	     	$scope.indice=i;
			   	        	$scope.cargando.valor=false;
	   	        	  }
	   	          }).then(function(resp2) {
	   	        	  if(!banderaEnvio) 
	   	        		  $scope.tableParams.data[i].enviar='0';
		   	        	$scope.cargando.valor=false;     
	   	          }).catch(function() {
		   	        	  	$scope.cargando.valor=false;
	 	          });    	
    };
    $scope.cambiarColor=function(c,i){
    	$scope.cargando.valor=true;
    	var boleta={'comprobante': 1,'establecimiento' : c.codEst,'serie'	: c.serie};
    	var documento={'num_ruc': '20148092282','tip_doc' : '03','num_doc'	: 'B'+c.codEst+"-"+c.serie};
    	if(c.id=='00' || c.id=='01' || c.id=='12'){
	    		facturacion.post('rest/ComprobantePagoController/reenviar',boleta).then(
		   	          function(resp) { 
		   	        	return facturacion.post(sistema,documento);
		   	          },
	     	          function(error) {
		   	        	$scope.mensajePositivo('Facturador','Ocurrió un error en la operación','resources/assets/img/notification/peligro.png');
	     	          }
	    		).then(
	  		          function(resp) { 
	  		        	  	if(resp.tipo=='03' || resp.tipo=='04'){
		  		        	  	$scope.tableParams.data[i].enviar='0';	  
				   	        	$scope.mensajePositivo('Facturador','El comprobante se ha enviado a la SUNAT','resources/assets/img/notification/succes.png'); 
	  		        	  	}else
	  		        	  		$scope.mensajePositivo('Facturador','El comprobante NO se ha enviado a la SUNAT','resources/assets/img/notification/peligro.png');

			   	        	$scope.cargando.valor=false;     	
			   	          },
			   	          function(error) {
			   	        	$scope.cargando.valor=false;
			   	        	$scope.mensajePositivo('Facturador','Ocurrió un error en la operación','resources/assets/img/notification/peligro.png');
			   	          }
	  	         );
    	}else{
	    		facturacion.post(sistema,documento).then(
	  	   	          function(resp) { 
	  		        	  	if(resp.tipo=='03' || resp.tipo=='04'){
	  		        	  		$scope.tableParams.data[i].enviar='0';
	  		        	  		$scope.mensajePositivo('Facturador','El comprobante se ha enviado a la SUNAT','resources/assets/img/notification/succes.png');  
	  		        	  	}else
	  		        	  		$scope.mensajePositivo('Facturador','El comprobante NO se ha enviado a la SUNAT','resources/assets/img/notification/peligro.png');
	  		        	  		
			   	        	$scope.cargando.valor=false;			   	        	
	  	   	          },
	       	          function(error) {
			   	        	$scope.cargando.valor=false;
			   	        	$scope.mensajePositivo('Facturador','Ups, no tenemos conexión con la Sunat.','resources/assets/img/notification/peligro.png');
	       	          }
				);
    	}
    };
});