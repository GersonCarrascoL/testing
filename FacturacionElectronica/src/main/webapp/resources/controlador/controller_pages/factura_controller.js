app.controller("factura_controller",function($scope, $http,$timeout,$route, $filter, maestroClientesService, sunatService, facturacion){
    $scope.rucCliente=null;
    var urlFacturador=null;
//	$http.get("rest/SistemaController/sistema").success(function(data){
//		urlFacturador=data;
//		cad1=["rest/FacturaController/ESGSC",data,"rest/ComprobantePagoController/estado"];
//		cad2=["rest/FacturaController/ECGSC",data,"rest/ComprobantePagoController/estado"];
// 	});

    $scope.selCliente=function(){
    	 $scope.loadValidandoRuc = true;
    	 $scope.dataSunat = {};     	
    	 var ruc = $scope.selectedRowCliente.ruc;
    	 $("#modalValidandoRuc .modal-dialog").css({width:'300px'});
    	 $("#modalValidandoRuc").modal({show:'true'});
    	 var promise = sunatService.consultaRUC_validar(ruc);
    	 promise.then(
    			 function(respItem) {
    				 $scope.dataSunat = respItem.data;
    				 $scope.loadValidandoRuc = false;
    				 
    				 if($scope.dataSunat.status == 0){
    					 $("#modalValidandoRuc .modal-dialog").css({width:'350px'});
    				 }else{
    					 $("#modalValidandoRuc .modal-dialog").css({width:'400px'});
    				 }
    			 },
    			 function(errorItem) {
    				 console.error('Error while updating estado : ' + errorItem);
    				 $scope.loadValidandoRuc = false;
    				 $("#modalValidandoRuc .modal-dialog").css({width:'400px'});
    			 }
    	 );
     };
     $scope.aceptarClienteValidado = function(){
     	if($scope.dataSunat.status==-1){
     		$scope.loading = true;
     		$scope.rucCliente = $scope.selectedRowCliente.ruc;
         	$scope.razonCliente = $scope.selectedRowCliente.razonSocial;
         	$scope.direccionCliente = $scope.selectedRowCliente.direccion;
         	$scope.codUbigeoCliente = $scope.selectedRowCliente.codUbigeo;
         	$scope.visibilidadClientes = true;
         	$scope.visibilidadCliente = false;
         	$scope.estadoBotonAceptar = false;
         	$("#buttonAceptarCliente").prop('disabled', true);
         	$("#modalValidandoRuc").modal('toggle');
         	$('#clienteBuscar').modal('hide');
         	$scope.loading = false;
     	}
     	else if($scope.dataSunat.status==1){
     	   $scope.loading = true;
  	       if( !( $scope.selectedRowCliente.razonSocial.toUpperCase()===$scope.dataSunat.razonSocial.toUpperCase() &&
  	    		$scope.selectedRowCliente.direccion.toUpperCase()===$scope.dataSunat.direccion.toUpperCase() &&
  	    		$scope.selectedRowCliente.codUbigeo.toUpperCase()===$scope.dataSunat.codUbigeo.toUpperCase() )
  	    		   ){
  	    	  var promise = maestroClientesService.updateClienteRuc_extra($scope.dataSunat);
  	          promise.then(
  	                       function(respItem) {
 	 	                        $scope.rucCliente = $scope.dataSunat.ruc;
 	 	                     	$scope.razonCliente = $scope.dataSunat.razonSocial;
 	 	                     	$scope.direccionCliente = $scope.dataSunat.direccion;
 	 	                     	$scope.codUbigeoCliente = $scope.dataSunat.codUbigeo;
 	 	                     	$scope.visibilidadClientes = true;
 	 	                     	$scope.visibilidadCliente = false;
 	 	                     	$scope.estadoBotonAceptar = false;
 	 	                     	$("#buttonAceptarCliente").prop('disabled', true);
 	 	                     	$("#modalValidandoRuc").modal('toggle');
 	 	                    	$('#clienteBuscar').modal('hide');
 	 	                    	$scope.loading = false;
  	                       },
  	                       function(errorItem) {
  	                           console.error('Error while updating cLIENTE : ' + errorItem);
  	                           $scope.loading = false;
  	                       }
  	           );
  	       }
  	       else{
  	    	   	$scope.rucCliente = $scope.dataSunat.ruc;
             	$scope.razonCliente = $scope.dataSunat.razonSocial;
             	$scope.direccionCliente = $scope.dataSunat.direccion;
             	$scope.codUbigeoCliente = $scope.dataSunat.codUbigeo;
             	$scope.visibilidadClientes = true;
             	$scope.visibilidadCliente = false;
             	$scope.estadoBotonAceptar = false;
             	$("#buttonAceptarCliente").prop('disabled', true);
             	$("#modalValidandoRuc").modal('toggle');
             	$('#clienteBuscar').modal('hide');
             	$scope.loading = false;
  	       }
  	   }
    };
    $('#clienteBuscar').on('hidden.bs.modal', function (e) {
        if(maestroClientesService.getNoData() && maestroClientesService.getRegExito()){
        	var clieSunat = maestroClientesService.getCliente();
        	
            $scope.$apply(function () {
                $scope.selectedRowCliente = {};
                $scope.selectedRowCliente.ruc = clieSunat.numDoc;
                $scope.selectedRowCliente.razonSocial = clieSunat.nombreCompleto;
                $scope.selectedRowCliente.direccion = clieSunat.direccion;
                $scope.selectedRowCliente.codUbigeo = clieSunat.codUbigeo;
                
                $scope.rucCliente=$scope.selectedRowCliente.ruc;
                $scope.razonCliente=$scope.selectedRowCliente.razonSocial;
                $scope.direccionCliente=$scope.selectedRowCliente.direccion;
                $scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
                $scope.visibilidadClientes=true;
                $scope.visibilidadCliente=false;
                $scope.estadoBotonAceptar=false;
            });
        }
    });
    $("#datoConRuc").keyup(function(){
    	$("#buttonAceptarCliente").prop('disabled', true);
    	$("#mensajeVerifica").hide();
    });
//    $http.get("rest/FacturaController/listarUnidades")
//	.success(function(value) {
//    	$scope.unidades=value;
//    	$scope.unidadElegida=value[0];
//    	$scope.nEstablecimiento=$scope.unidadElegida.nEstablecimiento;
//    	$scope.nSerie=$scope.unidadElegida.ultFact;
//    });
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
    		if($scope.rucCliente==null || $scope.rucCliente==""){
				mensaje("Ingrese el ruc del cliente");
			}else{
				if($scope.guia==null || $scope.guia==""){
					caso(4);
				}else{
					caso(6);
				}
			}
    	}
    };
    
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
    function caso(i){
    	$scope.cargando.valor=true;
    	var urlFactura=null;
    	switch (i) {
    	case 4: urlFactura='rest/FacturaController/ESGSC'; break;
    	case 5: urlFactura='rest/FacturaController/ECGSC'; break;
    	default: break;
    	}
    	facturacion.postDirectiva(urlFactura,{
			'anio'    			: parseInt($filter('date')(new Date(),'yyyy')),
			'mes'    			: parseInt($filter('date')(new Date(),'MM')),
			'emision'			: $filter('date')(new Date(),'yyyy-MM-dd'),
			'tipo'    			: 2,
			'establecimiento'	: $scope.nEstablecimiento,
			'serie'    			: $scope.nSerie,
			'fecha'				: $filter('date')(new Date(),'dd/MM/yyyy H:mm:ss'),
			'documento'    		: $scope.rucCliente,
			'nombre'   			: $scope.razonCliente,
			'direccion'    		: $scope.direccionCliente,
			'sub'   			: $scope.sub,
			'igv'    			: $scope.igv,
			'total'    			: $scope.total,
			'detalle'			: $scope.contenido,
			'formaPago'    		: $scope.formaPago,
			'moneda'   			: $scope.moneda,
			'importe'    		: $scope.importem,
			'unidad'   			: $scope.unidades[0].descripcion,
			'facultad'    		: $scope.unidades[0].facultad,
			'estado'    		: '00',
			'estUso'    		: 1,
			'tipoDoc'    		: 'RUC',
			'codUbigeoCliente'	: $scope.codUbigeoCliente,
			'gravada'    		: $scope.gravada,
			'exonerada'    		: $scope.exonerada,
			'inafecta'			: $scope.inafecta,
			'observacion'		: $scope.observacion
        }).then(
	   	          function(resp) {
	   	        	  if(resp!=null){
	   	        		window.open("rest/ReporteController/generarPDF/"+resp.establecimiento+"/"+resp.serie+"/"+resp.tipo+"/"+false+"/", "", "width=800,height=600");
	   	        		if($scope.unidadElegida.sunat =='1'){
								obj2={
										'num_ruc'	: '20148092282',
										'tip_doc'   : '01',
										'num_doc'	: 'F'+resp.establecimiento+"-"+resp.serie
					            };
								return facturacion.postDirectiva(urlFacturador,obj2);
		   	        	  }else{
		   	        		  if($scope.unidadElegida.sunat =='0'){
		   	        			mensaje('Facturador',"Deberá enviar a la sunat en la opción Mantenimiento de Comprobantes.",2); 	 
		   	        			$scope.cargando.valor=false;
		   	        		  }
		   	        	  }		   	        		  
	   	        	  }else{
	   	        		$scope.cargando.valor=false;
	   	        		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
						$timeout(function() {
			    			$route.reload();
			    		  },1000);
	   	        	  }
	   	        		
   	        		
	   	          }).then(function(resp2) {
	   	        	  if(resp2!=null){
							if(resp2.tipo=='03' || resp2.tipo=='04')
								mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1);  	    
							else							
								mensaje('Facturador',"El comprobante NO se ha enviado a la SUNAT.",2);    
	   	        	  }
						$scope.cargando.valor=false;
						$timeout(function() {
			    			$route.reload();
			    		  },1000);
	   	          }).catch(function() {
	   	        	  		if($scope.unidadElegida.sunat =='1')
	   	        	  		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
		   	        	  	$scope.cargando.valor=false;
							$timeout(function() {
				    			$route.reload();
				    		  },1000);
	 	          });
//    	switch (i) {
//			case 4:
//				facturacion.facturar(cad1,'01','F',$scope.cargando,{
//					'anio'    			: parseInt($filter('date')(new Date(),'yyyy')),
//					'mes'    			: parseInt($filter('date')(new Date(),'MM')),
//					'emision'			: $filter('date')(new Date(),'yyyy-MM-dd'),
//					'tipo'    			: 2,
//					'establecimiento'	: $scope.nEstablecimiento,
//					'serie'    			: $scope.nSerie,
//					'fecha'				: $filter('date')(new Date(),'dd/MM/yyyy H:mm:ss'),
//					'documento'    		: $scope.rucCliente,
//					'nombre'   			: $scope.razonCliente,
//					'direccion'    		: $scope.direccionCliente,
//					'sub'   			: $scope.sub,
//					'igv'    			: $scope.igv,
//					'total'    			: $scope.total,
//					'detalle'			: $scope.contenido,
//					'formaPago'    		: $scope.formaPago,
//					'moneda'   			: $scope.moneda,
//					'importe'    		: $scope.importem,
//					'unidad'   			: $scope.unidades[0].descripcion,
//					'facultad'    		: $scope.unidades[0].facultad,
//					'estado'    		: '00',
//					'estUso'    		: 1,
//					'tipoDoc'    		: 'RUC',
//					'codUbigeoCliente'	: $scope.codUbigeoCliente,
//					'gravada'    		: $scope.gravada,
//					'exonerada'    		: $scope.exonerada,
//					'inafecta'			: $scope.inafecta,
//					'observacion'		: $scope.observacion
//                },$scope.unidadElegida.sunat);
//				break;
//			case 6:
//				facturacion.facturar(cad2,'01','F',$scope.cargando,{
//					'anio'    			: $filter('date')(new Date(),'yyyy'),
//					'mes'    			: $filter('date')(new Date(),'MM'),
//					'emision'			: $filter('date')(new Date(),'yyyy-MM-dd'),
//					'tipo'    			: 2,
//					'establecimiento'	: $scope.nEstablecimiento,
//					'serie'    			: $scope.nSerie,
//					'fecha'				: $filter('date')(new Date(),'dd/MM/yyyy H:mm:ss'),
//					'documento'    		: $scope.rucCliente,
//					'nombre'   			: $scope.razonCliente,
//					'direccion'    		: $scope.direccionCliente,
//					'guia'    			: $scope.guia,
//					'sub'   			: $scope.sub,
//					'igv'    			: $scope.igv,
//					'total'    			: $scope.total,
//					'detalle'			: $scope.contenido,
//					'formaPago'    		: $scope.formaPago,
//					'moneda'   			: $scope.moneda,
//					'importe'    		: $scope.importem,
//					'unidad'   			: $scope.unidades[0].descripcion,
//					'facultad'    		: $scope.unidades[0].facultad,
//					'estado'    		: '00',
//					'estUso'    		: 1,
//					'tipoDoc'    		: 'RUC',
//					'codUbigeoCliente'	: $scope.codUbigeoCliente,
//					'gravada'    		: $scope.gravada,
//					'exonerada'    		: $scope.exonerada,
//					'inafecta'			: $scope.inafecta,
//					'observacion'		: $scope.observacion
//                },$scope.unidadElegida.sunat);
//				break;
//				default:
//		}
    }
});