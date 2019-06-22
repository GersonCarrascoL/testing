app.controller("notaCreditoFactura_controller",['$scope','$q','$http','ngTableParams','notaService','$filter',function($scope,$q,$http,ngTableParams,notaService,$filter){
   $scope.cod_dep=$("#idCodDependencia").text();
	$("#numeroFactura").attr('maxlength','8');
	$("#numeroFactura").mask('00000000');
	$scope.numeroComprobante='';
	   $scope.limpiarFormulario=function(){
		   $scope.formulario={establecimiento:{},fecha:$filter('date')(new Date(),'dd/MM/yyyy'),tipoNota:[],siguiente:false,preVisualizacion:false,modalSpinnerShow:false};
		   $scope.nota={tipo:'00',motivo:'',numeroDocumentoAsociado:'',listaDetalle:[]};
		   $scope.facturaPreSeleccionada=null;
		   $scope.facturaSeleccionada=null;
		   $scope.tipoNotaSeleccionada=null;
		   $scope.facturaSeleccionadaDetalle=null;
		   $scope.bandImporte=[];
		   $scope.bandDescripcion=[];
		   $scope.oper= {gravada:0,exonerada:0,inafecta:0,igv:0};
		   $scope.itemModificar={};
		   $scope.itemsIniciales=[];		   
	   };
	   $scope.calcularOperaciones=function(){
		   	$scope.oper= {gravada:0,exonerada:0,inafecta:0,igv:0};
			 if($scope.tableDetalle!=null){
				 if($scope.nota.tipo=='03'||$scope.nota.tipo=='07'){
					 for(var i=0;i<$scope.tableDetalle.data.length;i++){
						 if($scope.tableDetalle.data[i].bandera)
						 switch($scope.tableDetalle.data[i].codTipoIgv){
						 case 10: $scope.oper.gravada=$scope.oper.gravada+$scope.tableDetalle.data[i].precioT;
					 			  $scope.oper.igv=$scope.oper.igv+$scope.tableDetalle.data[i].igvT;	
						 		  break;
						 case 20: $scope.oper.exonerada=$scope.oper.exonerada+$scope.tableDetalle.data[i].precioT;
						 		  break;
						 case 30: $scope.oper.inafecta=$scope.oper.inafecta+$scope.tableDetalle.data[i].precioT;
						 		  break;
						 default: break;
						 }
					 }					 
				 }else{
					 for(var j=0;j<$scope.tableDetalle.data.length;j++){
						 switch($scope.tableDetalle.data[j].codTipoIgv){
						 case 10: $scope.oper.gravada=$scope.oper.gravada+$scope.tableDetalle.data[j].precioT;
						 		  $scope.oper.igv=$scope.oper.igv+$scope.tableDetalle.data[j].igvT;
						 		  break;
						 case 20: $scope.oper.exonerada=$scope.oper.exonerada+$scope.tableDetalle.data[j].precioT;
						 		  break;
						 case 30: $scope.oper.inafecta=$scope.oper.inafecta+$scope.tableDetalle.data[j].precioT;
						 		  break;
						 default: break;
						 }
					 }					 
				 }
				
			 }  
	   };
	   $scope.limpiartableDetalle=function(i){
	   		$scope.tableDetalle.data[i].cantidad=$scope.itemModificar.cantidad;
			$scope.tableDetalle.data[i].precioU=$scope.itemModificar.precioU;
			$scope.tableDetalle.data[i].descripcion=$scope.itemModificar.descripcion;		   
	   }
	   $scope.modificarImporte=function(i){
	        if($scope.bandImporte[i]){
	        	$scope.itemModificar.cantidad=$scope.tableDetalle.data[i].cantidad;
	        	$scope.itemModificar.precioU=$scope.tableDetalle.data[i].precioU;
	        	$scope.itemModificar.descripcion=$scope.tableDetalle.data[i].descripcion;
	            $scope.bandImporte[i]=false;
	        }else{
        		if($scope.tableDetalle.data[i].cantidad >0 && $scope.tableDetalle.data[i].cantidad<=$scope.itemsIniciales[i].cantidad){
        			if($scope.tableDetalle.data[i].precioU >0 && $scope.tableDetalle.data[i].precioU<=$scope.itemsIniciales[i].precioU){
        				if($scope.tableDetalle.data[i].descripcion==$scope.itemsIniciales[i].descripcion && $scope.nota.tipo=='03'){
        					$scope.limpiartableDetalle(i);
                            mensaje('Observacion','La descripcion debe ser diferente a la descripcion de la factura original',3);
        				}else{
            				if($scope.tableDetalle.data[i].descripcion!=''){
            					if($scope.nota.tipo=='07'){
        	        				$scope.tableDetalle.data[i].precioT=$scope.tableDetalle.data[i].cantidad*$scope.tableDetalle.data[i].precioU;
        	        				$scope.tableDetalle.data[i].igvU=($scope.igv*$scope.tableDetalle.data[i].precioU).toFixed(2);
        	        				$scope.tableDetalle.data[i].igvT=$scope.tableDetalle.data[i].cantidad*$scope.tableDetalle.data[i].igvU;
            					}
            					$scope.formulario.siguiente=true;
    	        				$scope.tableDetalle.data[i].bandera=true;	        					
            				}else{
        	        			$scope.limpiartableDetalle(i);
                                mensaje('Observacion','La descripción no debe ser vacía',3);
            				}       					
        				}
        			}else{
        				$scope.limpiartableDetalle(i);
                        mensaje('Observacion','El valor unitario debe ser y menor al valor unitario de la factura original y positivo.',3);
    				}	        				
        		}else{
        			$scope.limpiartableDetalle(i);
                    mensaje('La cantidad a devolver debe ser menor o igual a la cantidad del item respectivo de la Factura Original y positivo..',3);
        		}	        				        			
	            $scope.bandImporte[i]=true;
	            $scope.itemModificar={};	            
	        }
	   };	    	   
	   $scope.updateDetalle=function(){
		 if($scope.tipoNotaSeleccionada!=null){
			 if($scope.nota.tipo!=$scope.tipoNotaSeleccionada.idTipo && $scope.facturaSeleccionada!=null)
				 $scope.initDetalleFacturaSeleccionada();	
			 			 
			 $scope.nota.tipo=$scope.tipoNotaSeleccionada.idTipo;
			 if($scope.nota.tipo=='' || $scope.nota.tipo=='03' || $scope.nota.tipo=='07')
				 $scope.formulario.siguiente=false;
			 else
				 $scope.formulario.siguiente=true;
		 }
	   }; 
	   $scope.initFacturaParaNotas=function(){
           if($scope.numeroComprobante!='' && $scope.numeroComprobante!=undefined){
               var promise = notaService.getComprobantesParaNotas($scope.cod_dep,2,$scope.numeroComprobante);
               promise.then(
                   function(resp){
                       $scope.tableFacturas = new ngTableParams({count:5}, { dataset: resp, counts:[]});
                   },
                   function(error){
                   }
               );
           }
	   };
	   $scope.initDetalleFacturaSeleccionada=function(){
		   var promise = notaService.getDetalleComprobante($scope.facturaSeleccionada.numeroDocumento,$scope.facturaSeleccionada.tipo);
           promise.then(
     	          function(resp){ 
     	        	  $scope.tableDetalle = new ngTableParams({count:5}, { dataset: resp, counts:[]});
     	        	  for(var i=0;i<resp.length;i++){
     	        	        $scope.bandImporte[i]=true;
     	        	        $scope.itemsIniciales=angular.copy(resp);
     	        	  }
     	          },
     	          function(error){    	        	 
     	          }
           );		   
	   };	   
	   $scope.selectRowFactura = function(rowIndex,row){
		   $scope.facturaPreSeleccionada = row;
	   };
	   $scope.aceptarModalFactura=function(){
		   $scope.nota.numeroDocumentoAsociado = $scope.facturaPreSeleccionada.numeroDocumento;
		   $scope.facturaSeleccionada=$scope.facturaPreSeleccionada;
		   $scope.facturaPreSeleccionada=null;
		   $scope.numeroComprobante='';
		   $('#facturaBuscar').modal('toggle');
		   $scope.initDetalleFacturaSeleccionada();		   
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
	    		}else{
                    if(tipo==3){
                        $.gritter.add({
                            title: titulo,
                            text: texto,
                            image: 'resources/assets/img/notification/info.png',
                            sticky: false,
                            time: 10000
                        });
                    }
                }
	    	}
	    };	
	    
	   $scope.abrirModal=function(idModal){
/*		   if(idModal=='#facturaBuscar'){
			   $scope.initFacturaParaNotas();
		   }*/
		   $(idModal).modal("show");
	   };	   
	   $scope.verPDF=function(establecimiento,serie,tipo) {   	
	        window.open("rest/ReporteController/generarPDF/"+establecimiento+"/"+serie+"/"+tipo+"/"+false+"/", "", "width=800,height=600");
	   };
	   $scope.registrarNotaCredito=function(){
			 if($scope.nota.tipo=='03'||$scope.nota.tipo=='07'){
				 for(var i=0;i<$scope.tableDetalle.data.length;i++){
					 if($scope.tableDetalle.data[i].bandera)
						 $scope.nota.listaDetalle.push({idDetalleAsociado:$scope.tableDetalle.data[i].idDetComp,cantidad:$scope.tableDetalle.data[i].cantidad,precioUnitario:$scope.tableDetalle.data[i].precioU,descripcion:$scope.tableDetalle.data[i].descripcion});
				 }					 
			 }	
			 $scope.modalSpinnerShow = true;
	 		notaService.registrarNotaCredito($scope.nota).then(function(resp) { 
	 			if(resp.comprobante!=0){
	 				$scope.verPDF(resp.establecimiento,resp.serie,resp.tipo);
	 	        	 $scope.init();
		             $scope.documento={'num_ruc': '20148092282','tip_doc' : '07','num_doc'	: 'F'+resp.establecimiento+"-"+resp.serie};
		             return notaService.enviarFacturador($scope.urlFacturador,$scope.documento,$scope.formulario.modalSpinnerShow);	 				
	 			}else{
	 				mensaje('Nota de Credito',resp.observacion,2);
	 				$scope.modalSpinnerShow = false;
	 			}

 	          }).then(function(resp2) {
					if(resp2.tipo=='03' || resp2.tipo=='04')
						mensaje('Facturador','El comprobante se ha enviado a la SUNAT',1);
					else
						mensaje('Facturador','El comprobante NO se ha enviado a la SUNAT',2);
					$scope.modalSpinnerShow = false;
			}).catch(function() {
 	        	 	$scope.modalSpinnerShow = false;
					mensaje('Facturador','Ups, hemos tenido problemas para procesar su solicitud.',2);
 	          });			   
	   };
	   $scope.init=function(){
		   $scope.limpiarFormulario();
		   var promise = notaService.geInitFactura();
           promise.then(
     	          function(resp) { 
     	        	  $scope.formulario.establecimiento=resp.unidad[0];
     	        	  
      	        	  $scope.formulario.tipoNota=resp.tipoNotaCredito;
      	        	  $scope.formulario.tipoNota.unshift({idTipo:'00',descripcion:'Seleccione un tipo',boleta:'',factura:'',est:'',detalle:''});
      	        	  $scope.tipoNotaSeleccionada=$scope.formulario.tipoNota[0];
      	        	  
      	        	  $scope.igv=resp.igv[0].monto;
      	        	  $scope.urlFacturador=resp.urlFacturador;
     	          },
     	          function(error) {
     	          }
           );
//		   var promise = notaService.getEstablecimiento();
//           promise.then(
//     	          function(resp) { 
//     	        	  $scope.formulario.establecimiento=resp;
//     	          },
//     	          function(error) {
//     	          }
//           );	
//           notaService.getTipoNotaCreditoFactura().then(
//      	          function(resp) { 
//      	        	  $scope.formulario.tipoNota=resp;
//      	        	  $scope.formulario.tipoNota.unshift({idTipo:'00',descripcion:'Seleccione un tipo',boleta:'',factura:'',est:'',detalle:''});
//      	        	  $scope.tipoNotaSeleccionada=$scope.formulario.tipoNota[0];
//      	          },
//      	          function(error) {
//      	          }
//            );   
//           notaService.getIgv().then(
//      	          function(resp) { 
//      	        	  $scope.igv=resp;
//      	          },
//      	          function(error) {
//      	          }
//            );  
//           notaService.getUrlFacturador().then(
//      	          function(resp) { 
//      	        	  $scope.urlFacturador=resp;
//      	          },
//      	          function(error) {
//      	          }
//            ); 
	   };
}]);