app.controller("boleta_controller",function($scope, $http, $timeout, $route, $filter, facturacion, maestroClientesService, sunatService){
	$scope.numDocCliente=null;
	var urlBoleta='rest/BoletaController/insertarBoletaS';
//	$http.get("rest/SistemaController/sistema").success(function(data){
//		urlFacturador=data;
// 		cad1=['rest/BoletaController/insertarBoletaS',data,"rest/ComprobantePagoController/estado"];
// 	});

	$scope.cleanVariablesBoleta = function(){
		$scope.numDocCliente = null;
		$scope.visibilidadClientes = false;
		$scope.visibilidadCliente = false;
		$scope.tipoDocumCliente = null;
		$scope.razonCliente = '';
	    $scope.direccionCliente = '';
	    $scope.codUbigeoCliente = null;
				
		$scope.dataSunat = {};
		$scope.loadValidandoRuc = false;
    	$scope.unidadElegida = $scope.unidadInicial;
		
		$scope.formularioDocumento.$setPristine();
	};

	$scope.selCliente = function(){
		//validando reniec u otro
		if (maestroClientesService.getTipoCliente()=='RUC'){
			$scope.validaRucSunat();
		}else if(maestroClientesService.getTipoCliente()=='SINRUC' || maestroClientesService.getTipoCliente()=='SUM' || maestroClientesService.getTipoCliente()=='SERVIDOR'){
			$scope.aceptarClienteValidadoSINRUC();
		}		
	};
	$scope.validaRucSunat=function(){
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
    $scope.aceptarClienteValidadoRUC = function(){
     	if($scope.dataSunat.status==-1){
     		$scope.loading = true;
         	$scope.numDocCliente = $scope.selectedRowCliente.ruc;
		    $scope.tipoDocumCliente = 'RUC';
		    $scope.razonCliente = $scope.selectedRowCliente.razonSocial;
		    $scope.direccionCliente = $scope.selectedRowCliente.direccion;
		    $scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
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
 	 	                        $scope.numDocCliente = $scope.dataSunat.ruc;
 	 	                        $scope.tipoDocumCliente = 'RUC';
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
  	    	   	$scope.numDocCliente = $scope.dataSunat.ruc;
  	    	    $scope.tipoDocumCliente = 'RUC';
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
    $scope.aceptarClienteValidadoSINRUC=function(){
    	if($scope.selectedRowCliente!=null){
 		   var codigoMatricula=$scope.selectedRowCliente.codigoMatricula;
 		   if(codigoMatricula!=null){ //alumno unmsm
 			    $scope.numDocCliente = $scope.selectedRowCliente.numDoc;
 		    	$scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
 		    	$scope.razonCliente = $scope.selectedRowCliente.nomCompleto;
 		    	$scope.direccionCliente = $scope.selectedRowCliente.direccion;
 		    	$scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
 		   }else{
 			   var codigoNumDoc=$scope.selectedRowCliente.numDoc;
 			   if(codigoNumDoc!=null){
 				   var cesantia=$scope.selectedRowCliente.cesantia;
 				   if(cesantia==null){ //cliente sin ruc
 					    $scope.numDocCliente = $scope.selectedRowCliente.numDoc;
 		 		    	$scope.tipoDocumCliente = $scope.selectedRowCliente.nombreDoc;
 		 		    	$scope.razonCliente = $scope.selectedRowCliente.nomCompleto;
 		 		    	$scope.direccionCliente = $scope.selectedRowCliente.direccion;
 		 		    	$scope.codUbigeoCliente=$scope.selectedRowCliente.codUbigeo;
 				   }else{ // servidores
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
        if(maestroClientesService.getNoData() && maestroClientesService.getRegExito()){
        	var clie = maestroClientesService.getCliente();
        	
            $scope.$apply(function () {
                $scope.selectedRowCliente = {};
                $scope.selectedRowCliente.numDoc = clie.numDoc;
                $scope.selectedRowCliente.nomCompleto = clie.nombreCompleto;
                $scope.selectedRowCliente.direccion = clie.direccion;
                $scope.selectedRowCliente.nombreDoc = clie.nombreDoc;
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
    	$("#mensajeVerificaDNI").hide();  	
    });
    $("#datoConRuc").keyup(function(){
    	$("#buttonAceptarCliente").prop('disabled', true);
    	$("#mensajeVerifica").hide();
    });
    $scope.verPDF=function(establecimiento,serie) {   	
        window.open("rest/ReporteController/generarPDF/"+establecimiento+"/"+serie+"/"+2+"/"+false+"/", "", "width=800,height=600");
    };   
//	$http.get("rest/FacturaController/listarUnidades")
//    .success(function(value) {
//    	console.log("listar unidaddes")
//    	console.log(value)
//    	$scope.unidades=value;
//    	$scope.unidadElegida=value[0];
//    	$scope.nEstablecimiento=$scope.unidadElegida.nEstablecimiento;
//    	$scope.nSerie=$scope.unidadElegida.ultBole;
//    });

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
    		if($scope.nSerie==null || $scope.nSerie==""){
    			mensaje("Ingrese el número de serie");
    		}else{
    			if($scope.numDocCliente==null || $scope.numDocCliente==""){
    				mensaje("Ingrese el documento del cliente");
    			}else{
    				insertar();
    				$scope.cargando.valor=true;
    			}
    		}
    	}
    };
    function insertar() {
    	$scope.cargando.valor=true;  	
    	facturacion.postDirectiva(urlBoleta,{
			'anio'    			: $filter('date')(new Date(),'yyyy'),
			'mes'    			: $filter('date')(new Date(),'MM'),
			'emision'			: $filter('date')(new Date(),'yyyy-MM-dd'),
			'tipo'    			: 1,
			'establecimiento'	: $scope.nEstablecimiento,
			'serie'    			: $scope.nSerie,
			'fecha'				: $filter('date')(new Date(),'dd/MM/yyyy H:mm:ss'),
			'documento'    		: $scope.numDocCliente,
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
			'tipoDoc'    		: $scope.tipoDocumCliente,
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
										'tip_doc'   : '03',
										'num_doc'	: 'B'+resp.establecimiento+"-"+resp.serie
					            };
								return facturacion.postDirectiva($scope.urlFacturador,obj2);
		   	        	  }else{
		   	        		  if($scope.unidadElegida.sunat =='0'){
		   	        			mensaje('Facturador',"Deberá enviar a la sunat en la opción Mantenimiento de Comprobantes.",2); 	 
		   	        			$scope.cargando.valor=false;
		   	        		  }
		   	        	  }		   	        		  
	   	        	  }else{
	   	        		$scope.cargando.valor=false;
	   	        		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
						//$timeout(function() {
			    			//$route.reload();
							$scope.$apply(function () {
								$scope.cleanVariablesBoleta();
								$scope.limpiarDatosDirectivaFacturacion();
							});
			    		 //},1000);
	   	        	  }
	   	        		
   	        		
	   	          }).then(function(resp2) {
	   	        	  if(resp2!=null){
							if(resp2.tipo=='03' || resp2.tipo=='04')
								mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1);  	    
							else							
								mensaje('Facturador',"El comprobante NO se ha enviado a la SUNAT.",2);    
	   	        	  }
						$scope.cargando.valor=false;
						//$timeout(function() {
			    			//$route.reload();
							$scope.$apply(function () {
								$scope.cleanVariablesBoleta();
								$scope.limpiarDatosDirectivaFacturacion();
							}); 				
			    		 // },1000);
	   	          }).catch(function() {
	   	        	  		if($scope.unidadElegida.sunat =='1')
	   	        	  		mensaje('Facturador',"Ups, hemos tenido problemas para procesar su solicitud.",2); 	
		   	        	  	$scope.cargando.valor=false;
							//$timeout(function() {
				    			//$route.reload();
								$scope.$apply(function () {
									$scope.cleanVariablesBoleta();
									$scope.limpiarDatosDirectivaFacturacion();
								});	
				    		//},1000);
	 	          });    	
    	
    	
//    	facturacion.facturar(cad1,'03','B',$scope.cargando,{
//			'anio'    			: $filter('date')(new Date(),'yyyy'),
//			'mes'    			: $filter('date')(new Date(),'MM'),
//			'emision'			: $filter('date')(new Date(),'yyyy-MM-dd'),
//			'tipo'    			: 1,
//			'establecimiento'	: $scope.nEstablecimiento,
//			'serie'    			: $scope.nSerie,
//			'fecha'				: $filter('date')(new Date(),'dd/MM/yyyy H:mm:ss'),
//			'documento'    		: $scope.numDocCliente,
//			'nombre'   			: $scope.razonCliente,
//			'direccion'    		: $scope.direccionCliente,
//			'sub'   			: $scope.sub,
//			'igv'    			: $scope.igv,
//			'total'    			: $scope.total,
//			'detalle'			: $scope.contenido,
//			'formaPago'    		: $scope.formaPago,
//			'moneda'   			: $scope.moneda,
//			'importe'    		: $scope.importem,
//			'unidad'   			: $scope.unidades[0].descripcion,
//			'facultad'    		: $scope.unidades[0].facultad,
//			'estado'    		: '00',
//			'estUso'    		: 1,
//			'tipoDoc'    		: $scope.tipoDocumCliente,
//			'codUbigeoCliente'	: $scope.codUbigeoCliente,
//			'gravada'    		: $scope.gravada,
//			'exonerada'    		: $scope.exonerada,
//			'inafecta'			: $scope.inafecta,
//			'observacion'		: $scope.observacion
//        },$scope.unidadElegida.sunat);
	}
});