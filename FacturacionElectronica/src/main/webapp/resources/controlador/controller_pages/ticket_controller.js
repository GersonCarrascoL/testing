app.controller("ticket_controller",function($scope, $http, $q, $timeout, $route, $filter, facturacion, utilCharsService, maqLocalService,mantenimientoTicketService){
	$scope.modalOptions = {
            headerColorValue: 0,  // 0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
            closeButtonText: 'No',
            actionButtonText: 'Si',
            headerText: 'Confirmar',
            bodyText: 'Realizar ésta acción?',
            data: {},
            action: function(){}              
    };
	$scope.numDocCliente=null;
	var urlBoleta='rest/BoletaController/insertarBoletaS';
	var urlTicket='rest/ComprobanteTicketController/insertarTicket';
	var urlFacturador=null;
	$http.get("rest/SistemaController/sistema").success(function(data){
		urlFacturador=data;
 		cad1=['rest/BoletaController/insertarBoletaS',data,"rest/ComprobantePagoController/estado"];
 	});	
	
    $scope.seleccionarCliente=function(){
    	$scope.numDocCliente=$scope.tablaCliente[0].ruc;
    	$scope.razonCliente=$scope.tablaCliente[0].razonSocial;
    	$scope.direccionCliente=$scope.tablaCliente[0].direccion;
    	$scope.visibilidadClientes=true;
    	$scope.visibilidadCliente=false;
    	$scope.nombreTemp=null;
    };
    $scope.selCliente=function(){
    	if($scope.selectedRowCliente!=null){
 		   var codigoMatricula=$scope.selectedRowCliente.codigoMatricula;
 		   if(codigoMatricula!=null){
 			    $scope.numDocCliente = $scope.selectedRowCliente.numDoc;
 		    	$scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
 		    	$scope.razonCliente = $scope.selectedRowCliente.nomCompleto;
 		    	$scope.direccionCliente = $scope.selectedRowCliente.direccion;
 		    	$scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
 		   }else{
 			   var codigoNumDoc=$scope.selectedRowCliente.numDoc;
 			   if(codigoNumDoc!=null){	 
 				   var cesantia=$scope.selectedRowCliente.cesantia;
 				   if(cesantia==null){
 					    $scope.numDocCliente = $scope.selectedRowCliente.numDoc;
 		 		    	$scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
 		 		    	$scope.razonCliente = $scope.selectedRowCliente.nomCompleto;
 		 		    	$scope.direccionCliente = $scope.selectedRowCliente.direccion;
 		 		    	$scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
 				   }else{
 					    $scope.numDocCliente = $scope.selectedRowCliente.numDoc;
		 		    	$scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
		 		    	$scope.razonCliente = $scope.selectedRowCliente.nombre.trim()+' '+$scope.selectedRowCliente.paterno.trim()+' '+$scope.selectedRowCliente.materno.trim();
		 		    	$scope.direccionCliente = ($scope.selectedRowCliente.domicilio==null || $scope.selectedRowCliente.domicilio=="")?(" "):($scope.selectedRowCliente.domicilio);
		 		    	$scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
 				   }
 			   }   
 		   }  
 	    }
    	$scope.visibilidadClientes=true;
    	$scope.visibilidadCliente=false;
    	$scope.estadoBotonAceptar=false;
    	$("#buttonAceptarCliente").prop('disabled', true);
  	   	$('#clienteBuscar').modal('hide');
    };
    $('#clienteBuscar').on('hidden.bs.modal', function (e) {
        var noData = $("#dtNoData_SinRuc").val().trim();
        var regExito = $("#regSinRUC_exito").val().trim();
        var direc = $("#direccionClieHidden").val().trim();
        var numDoc = $("#numDocClieHidden").val().trim();
        var nombreDoc = $("#nombreDocClieHidden").val().trim();
        var nombCompleto = $("#nombCompletoClieHidden").val().trim();
        if(noData == 'true' && regExito == 'true'){ 
            $scope.$apply(function () {
                $scope.selectedRowCliente = {};
                $scope.selectedRowCliente.numDoc = numDoc;
                $scope.selectedRowCliente.nomCompleto = nombCompleto;
                $scope.selectedRowCliente.direccion = direc;
                $scope.selectedRowCliente.nombreDoc = nombreDoc;
        		$scope.selectedRowCliente.codigoMatricula = null;
        		$scope.selectedRowCliente.cesantia = null;
        		$scope.numDocCliente = $scope.selectedRowCliente.numDoc;
	 		    $scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
	 		    $scope.razonCliente = $scope.selectedRowCliente.nomCompleto;
	 		    $scope.direccionCliente = $scope.selectedRowCliente.direccion;
	 		    $scope.visibilidadClientes=true;
	 	    	$scope.visibilidadCliente=false;
	 	    	$scope.estadoBotonAceptar=false;
            });
        }
    });
    $("#datoSinRuc").keyup(function(){
    	$("#buttonAceptarCliente").prop('disabled', true);
    	$("#mensajeVerifica").hide();  	
    });
    $scope.verPDF=function(establecimiento,correlativo) {  
    	window.open("rest/ReporteController/generarPDF/"+establecimiento+"/"+correlativo+"/", "", "width=400,height=600");                            
    };   
    
	$http.get("rest/ComprobanteTicketController/listarUnidadesTicket")
    .success(function(value) {
    	//console.log("perro")
    	//console.log(value[0])
    	$scope.unidades=value;
    	$scope.unidadElegida=value[0];
    	console.log("unidadElegida")
    	console.log($scope.unidadElegida)
    	$scope.unidad=$scope.unidadElegida.descripcion;
    	$scope.facultad=$scope.unidadElegida.facultad;
    	$scope.nEstablecimiento=$scope.unidadElegida.nEstablecimiento;
    	$scope.nSerie=$scope.unidadElegida.ultTicket;
    	});
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
    $scope.comprobanteTicket={
    		anexo				: $scope.nEstablecimiento,
			nomCorto			: $scope.nombCortoLocal,
			total    			: $scope.total,
			formaPago    		: $scope.formaPago,
			moneda   			: $scope.moneda,
			importeOperacion  	: $scope.importem,
			subtotal   			: $scope.sub,
			igv    				: $scope.igv,
			unidad   			: $scope.unidad,
			facultad    		: $scope.facultad,
			serieFabMaq			: $scope.serieFabMaq,
			nroAutoMaq			: $scope.nroAutorizacion,
			direccion			: $scope.direccion,
			detalle				: $scope.contenido
    }
    $scope.abrirModalConfirm_emitirTicket = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        
        $scope.modalOptions.headerText = 'Emitir';
    	$scope.modalOptions.bodyText = 'Está a punto de Emitir un nuevo Ticket. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.procesar

        $('#modalConfirm').modal({show: true});
    };
    $scope.procesar=function(){
    	
    	for(var i in $scope.contenido){
    		if($scope.contenido[i].cantidad==null || $scope.contenido[i].cantidad==""){
    			mensaje("El articulo "+(parseInt(i)+1)+" de la lista no posee cantidad");
    			return false;
    		}
    		if($scope.contenido[i].cantidad<=0){
    			mensaje("El articulo "+(parseInt(i)+1)+" de la lista debe ser positivo");
    			return false;
    		}
    		if($scope.contenido[i].precioU<=0 || $scope.contenido[i].precioU=="" || $scope.contenido[i].precioU==null){
    			mensaje("El articulo "+(parseInt(i)+1)+" de la lista debe tener un precio positivo");
    			return false;
    		}
    	}
    	if($scope.contenido.length==0){
    		mensaje("No se ha añadido ningun producto");
    	}else{
    		console.log($scope.nSerie)
    		if($scope.nSerie==null || $scope.nSerie==""){
    			console.log("q prro")
    			mensaje("Ingrese el número de serie");
    		}else{
    			insertar();
				$scope.cargando.valor=true;
    		}
    	}
    };
    function insertar() {
    	$scope.cargando.valor=true;  
    	var promise = mantenimientoTicketService.emitirTicket({
    		anexo				: $scope.nEstablecimiento,
			nomCorto			: $scope.nombCortoLocal,
			total    			: $scope.total,
			formaPago    		: $scope.formaPago,
			moneda   			: $scope.moneda,
			importeOperacion  	: $scope.importem,
			subtotal   			: $scope.sub,
			igv    				: $scope.igv,
			unidad   			: $scope.unidad,
			facultad    		: $scope.facultad,
			serieFabMaq			: $scope.serieFabMaq,
			nroAutoMaq			: $scope.nroAutorizacion,
			direccion			: $scope.direccion,
			detalle				: $scope.contenido
    });
        promise.then(
                           function(respLista) {
                        	 console.log("respuesta Lista")
                             console.log(respLista.data);
                        	 mensaje('Ticket',"Su Ticket ha sido emitido correctamente",1);
                        	 $scope.cargando.valor=false;
                        	 $scope.verPDF(respLista.data.anexo,respLista.data.correlativo);
                        	 $timeout(function() {
                        		 $route.reload();
    			    		 },1000);                           },
                           function(errorLista){
                        	   console.log("errorLista")
                        	   console.log(errorLista);
                        	   
                           }
        );
//    	facturacion.postDirectiva(urlTicket,{
//			'anexo'				: $scope.nEstablecimiento,
//			'nomCorto'			: $scope.nombCortoLocal,
//			'total'    			: $scope.total,
//			'formaPago'    		: $scope.formaPago,
//			'moneda'   			: $scope.moneda,
//			'importeOperacion'  : $scope.importem,
//			'subtotal'   		: $scope.sub,
//			'igv'    			: $scope.igv,
//			'unidad'   			: $scope.unidades[0].descripcion,
//			'facultad'    		: $scope.unidades[0].facultad,
//			'serieFabMaq'		: $scope.serieFabMaq,
//			'nroAutoMaq'		: $scope.nroAutorizacion,
//			'direccion'			: $scope.direccion,
//			'detalle'			: $scope.contenido
//        }).then(
//	   	          function(resp) {
//	   	        	  console.log("bien")
//	   	        	  console.log(resp);
//	   	        	  if(resp!=null){
//	   	        		window.open("rest/ReporteController/generarPDF/"+resp.establecimiento+"/"+resp.serie+"/"+resp.tipo+"/"+false+"/", "", "width=800,height=600");
//	   	        		if($scope.unidadElegida.sunat =='1'){
//								obj2={
//										'num_ruc'	: '20148092282',
//										'tip_doc'   : '03',
//										'num_doc'	: 'B'+resp.establecimiento+"-"+resp.serie
//					            };
//								return facturacion.postDirectiva(urlFacturador,obj2);
//		   	        	  }else{
//		   	        		  if($scope.unidadElegida.sunat =='0'){
//		   	        			mensaje('Facturador',"Deberá enviar a la sunat en la opción Mantenimiento de Comprobantes.",2); 	 
//		   	        			$scope.cargando.valor=false;
//		   	        		  }
//		   	        	  }		   	        		  
//	   	        	  }else{
//	   	        		$scope.cargando.valor=false;
//	   	        		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
//						$timeout(function() {
//			    			$route.reload();
//			    		  },1000);
//	   	        	  }
//	   	        		
//   	        		
//	   	          }).then(function(resp2) {
//	   	        	  if(resp2!=null){
//							if(resp2.tipo=='03' || resp2.tipo=='04')
//								mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1);  	    
//							else							
//								mensaje('Facturador',"El comprobante NO se ha enviado a la SUNAT.",2);    
//	   	        	  }
//						$scope.cargando.valor=false;
//						$timeout(function() {
//			    			$route.reload();
//			    		  },1000);
//	   	          }).catch(function() {
//	   	        	  		if($scope.unidadElegida.sunat =='1')
//	   	        	  		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
//		   	        	  	$scope.cargando.valor=false;
//							$timeout(function() {
//				    			$route.reload();
//				    		  },1000);
//	 	          });  
	}
    
});