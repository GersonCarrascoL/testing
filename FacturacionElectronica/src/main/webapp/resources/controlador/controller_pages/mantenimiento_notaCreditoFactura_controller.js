app.controller('mantenimiento_notaCreditoFactura_controller', ['$scope', '$filter', 'ngTableParams', 'comprobantesPagoService', 'notaService','facturacion', function($scope, $filter, ngTableParams, comprobantesPagoService, notaService, facturacion) {
	
	$scope.VisibleResultTable = false;
	$scope.modalSpinnerShow = false;
	$scope.rolAdminCajasTxt = "CXC_ADMIN_CAJAS";
	$scope.filtroEstado = "";
    $scope.estados=[{id:"",title:"Todos"},
                    {id:'0',title:"Enviado y Aceptado SUNAT"},
                    {id:'1',title:"Sin enviar SUNAT"},
                    {id:'2',title:"Enviado y Anulado SUNAT"}];
    $scope.initCorreo=function(comprobante){
    	$scope.correoModel=null;
      	$scope.correoModel={'correoDestino':null,'documento':null,'establecimiento':comprobante.codEst,'serie':comprobante.serie,'tipo':comprobante.tipo};
    	
      	comprobantesPagoService.getCorreoCliente($scope.correoModel).then(
  	          function(resp) { 
  	        	if(resp.data!=''){
  	        	$scope.correoModel.correoDestino=resp.data.correoDestino;
  	        	$scope.correoModel.documento=resp.data.documento;
              	$('#enviarCorreo').modal("show");
  	        	}
  	          },
  	          function(error) {
  	        	  if(comprobante.tipo==5)
  	        		$scope.correoModel.documento="B"+comprobante.codEst+"-"+comprobante.serie;
  	        	  else 
  	        		  if(comprobante.tipo==4)
  	        			$scope.correoModel.documento="F"+comprobante.codEst+"-"+comprobante.serie;
  	        	  
  	        	$('#enviarCorreo').modal("show");  	        	  
  	          }
        );            	            	
    }
    $scope.enviarCorreo=function(){
    	$scope.modalSpinnerShow=true;
    	comprobantesPagoService.enviarCorreo($scope.correoModel).then(
    	          function(resp) { 
    	        	  if(resp.data==1)
    	        		  mensaje('Envio de correo','Se ha enviado correctamente el comprobante',1);
    	        	  else
    	        		  mensaje('Envio de correo','No se ha podido enviar el comprobante','resources/assets/img/notification/peligro.png',2);
    	        	  
    	        	  $scope.modalSpinnerShow=false;
    	        		  
    	          },
    	          function(error) {
    	        	  mensaje('Envio de correo','No se ha podido enviar el comprobante','resources/assets/img/notification/peligro.png',2);
    	        	  $scope.modalSpinnerShow=false;
    	          }
          ); 
    }      
    function mensaje(titulo,texto,tipo){
    	if(tipo==1){
	    	$.gritter.add({
				title: titulo,
				text: texto,
				image: 'resources/assets/img/notification/succes.png',
				sticky: false,
				time: 10000
			});	    		
    	}else{
    		if(tipo==2){
		    	$.gritter.add({
					title: titulo,
					text: texto,
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: 10000
				});		    			
    		}
    	}
    };
    
    function mensajeCambio(id,tituloMensaje,textoMensaje){
    	$scope.tituloMensaje=tituloMensaje;
    	$scope.textoMensaje=textoMensaje;
        $scope.abrirModal(id);
    };
    $scope.cambio=function(c,i){
    	$scope.modalSpinnerShow=true;
    	var banderaEnvio=false;
    	var documento={'num_ruc': '20148092282','tip_doc' : '07','num_doc'	: 'F'+c.codEst+"-"+c.serie};
		facturacion.postDirectiva($scope.urlConsultaFacturador,documento).then(
	   	          function(resp) { 
	   	        	  if(resp.status=="0001" || resp.status=="0002"){
	   	        		  	documento={'num_ruc': '20148092282','tip_doc' : '07','num_doc'	: 'F'+c.codEst+"-"+c.serie,'ind_situ':resp.status};	   	        		  	   	        		  
	   	        		  	mensaje('Facturador',resp.Message,1);	
	   	        		  	return facturacion.postDirectiva('rest/ComprobantePagoController/modificarEstadoSunat',documento);
	   	        	  }else{
	   	        		  	banderaEnvio=true;
	   	        	    	mensajeCambio("#confirmacion","Enviar","¿Seguro que desea enviar la Nota de Crédito F"+c.codEst+"-"+c.serie+"?");	   	        		  	
				   	     	$scope.cTemp=c;
				   	     	$scope.indice=i;
			   	        	$scope.modalSpinnerShow=false;
	   	        	  }
	   	          }).then(function(resp2) {
	   	        	  if(!banderaEnvio) 
	   	        		  $scope.tableParams.data[i].enviar='0';
		   	        	$scope.modalSpinnerShow=false;     
	   	          }).catch(function() {
	   	        	  	mensaje('Facturador','Ups. Tenemos un problema de conexión, intentelo mas tarde.',2);
 	        		  	$scope.modalSpinnerShow=false;
	 	          });     	
    };    
    $scope.abrirModal=function(opc){
    	$(opc).modal("show");
    };    
    $scope.deshabilitar=function(i) {
    	switch (i) {
			case '0':return true;
			case '1':return false;
			case '2':return true;
			default:
				return false;
    	}
    };
	
	function mostrar(data){
        $scope.listaComprobantes = data;
        if($scope.listaComprobantes==null || $scope.listaComprobantes==""){
            $scope.VisibleResultTable=false;
        }else{
            $scope.tableParams = new ngTableParams({filter: {enviar:""}}, { dataset: $scope.listaComprobantes});
            $scope.VisibleResultTable=true; 
        }
    };
	
	$scope.getComprobantesDePago = function(params){
		var promise;        
        if( $("#idPerfil").text()==$scope.rolAdminCajasTxt){
        	promise = comprobantesPagoService.getNotasDeCreditoAdminCaja(params);
        }else{
        	promise = comprobantesPagoService.getNotasDeCredito(params);
        }
        promise.then(
                           function(respLista) { 
                        	   mostrar(respLista.data);
                           },
                           function(errorLista){
                           }
                   );
	};  
	$scope.doblePagina=false;	
	$scope.verPDF=function(establecimiento,serie) {   	
        window.open("rest/ReporteController/generarPDF/"+establecimiento+"/"+serie+"/"+4+"/"+$scope.doblePagina+"/", "", "width=800,height=600");
    };
    
    $scope.consultar=function(){
    	$scope.getComprobantesDePago( {'tipo': 4, 'fechaInicial': $scope.fechaInicial, 'fechaFinal': $scope.fechaFinal});
    };
    
    $scope.detallar=function(c){
    	$scope.abrirModal("#detalle");
    	$scope.detalle=c;
    	$scope.detalle.fechaEmisionP=$scope.detalle.fechaEmision.substr(0,$scope.detalle.fechaEmision.length-2);
    };    
    
    $scope.volverAEnviar=function(c, i){
    	$scope.modalSpinnerShow = true;
    	$scope.documento={'num_ruc': '20148092282','tip_doc' : '07','num_doc'	: 'F'+c.codEst+"-"+c.serie};
    	if(c.estadoFacturador=='00' || c.estadoFacturador=='01'|| c.estadoFacturador=='12'){
    		notaService.generarArchivosAlFacturador(c).then(
	   	          function(resp) { 
	   	        	  if(resp.comprobante!=0){
	   	        		return notaService.enviarFacturador($scope.urlFacturador,$scope.documento);
	   	        	  }else
	   	        		mensaje('Nota de Credito',resp.observacion,2);	     	        	   	
	     	          },
	     	          function(error) {
	     	        	mensaje('Nota de Credito',"Ocurrió un error al crear los archivos",2);
	     	          }
           ).then(
		          function(resp) { 
		        	  	if(resp.tipo=='03' || resp.tipo=='04'){
		        	  		$scope.tableParams.data[i].enviar='0';	
		        	  		mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1); 
  		        	  	}else
  		        	  		mensaje('Facturador','El comprobante NO se ha enviado a la SUNAT',2);  	
		   	        	
		   	        	$scope.modalSpinnerShow = false;	        	
		   	          },
		   	          function(error) {
		   	        	$scope.modalSpinnerShow = false;
		   	        	mensaje('Facturador',"Ups, no tenemos conexión con la Sunat.",2);
		   	          }
    	         );
    	}else{
        	notaService.enviarFacturador($scope.urlFacturador,$scope.documento).then(
         	          function(resp) { 
  		        	  	if(resp.tipo=='03' || resp.tipo=='04'){
		        	  		$scope.tableParams.data[i].enviar='0';	
		        	  		mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1); 
  		        	  	}else
  		        	  		mensaje('Facturador','El comprobante NO se ha enviado a la SUNAT',2);  	
		   	        	
		   	        	$scope.modalSpinnerShow = false; 	        	
         	          },
         	          function(error) {
         	        	$scope.modalSpinnerShow = false;
         	        	mensaje('Facturador',"Ups, no tenemos conexión con la Sunat.",2);
         	          }
               ); 	
    	}         
    };
	
	$scope.init = function(){		
		var promise=notaService.getUrlFacturador();
        promise.then(
   	          function(resp) { 
   	        	  $scope.urlFacturador=resp;
   	          },
   	          function(error) {
   	          }
         ); 
		var promise=notaService.getUrlConsultaFacturador();
        promise.then(
   	          function(resp) { 
   	        	  $scope.urlConsultaFacturador=resp;
   	          },
   	          function(error) {
   	          }
         );        
        $scope.fechaInicial = $filter('date')(new Date(),'dd/MM/yyyy');
		$scope.fechaFinal = $filter('date')(new Date(),'dd/MM/yyyy');
		$scope.getComprobantesDePago( {'tipo': 4, 'fechaInicial': $scope.fechaInicial, 'fechaFinal': $scope.fechaFinal});		
		$("#panelResult").show();
	};
	
	$scope.init();
	
}]);