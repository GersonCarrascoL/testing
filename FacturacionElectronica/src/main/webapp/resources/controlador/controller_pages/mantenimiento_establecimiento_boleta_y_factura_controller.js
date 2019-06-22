app.controller("mantenimiento_establecimiento_boleta_y_factura_controller",function($scope, $http, ngTableParams, dependenciaService){
	
	$scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, etc
    
    $scope.administracionCentral = false;
    if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
        $scope.administracionCentral = true;
    };	
	$scope.seleccionar=function(){
	  $scope.codEst=$scope.estemp.codigo;
	  $scope.direccion=$scope.estemp.direccion;
	  $scope.verDireccion=true;
	};
	$scope.selectRowCliente = function(rowIndex,establecimiento){
    	  $scope.selectedRow=rowIndex;
    	  $scope.estemp=establecimiento;
	};
	$scope.dobleClickCliente=function(rowIndex,establecimiento){
		   $scope.selectRowCliente(rowIndex,establecimiento);
		   $scope.seleccionar();
		   $('#buscar').modal('hide');
	};
	function cargar() {
		$http.get("rest/ComprobantePagoController/listarEstablecimientoBoletaYFacturaxUsuario").success(function (data) {
	        $scope.tableParams = new ngTableParams({}, { dataset: data, counts:[]});
	        $scope.tabla=data;
	    });
	}
	cargar();
    $scope.abrirModal=function(opc){
    	$(opc).modal("show");
    	if(opc=="#Nuevo"){
    		$scope.formularioNuevo.$setPristine();
    	}
    };
    $("#botonBuscar").click(function() {
    	$("#buscar").modal("show");
    });
    $scope.editar=function(c){
    	$scope.abrirModal("#Editar");
    	$scope.temp={
	    		'udId'   			: c.udId,
	    		'unidad'   			: c.unidad,
	    		'codEst'   			: c.codEst,
				'ultFact'    		: c.ultFact,
				'ultBole'    		: c.ultBole,
				'direccion'    		: c.direccion,
				'telefono'    		: c.telefono,
				'correo'    		: c.correo,
				'sunat'    			: c.sunat    
    	};
    	$scope.unidad=$scope.temp.unidad;
    	$scope.udId=$scope.temp.udId;
    	$scope.sunat = (c.sunat == 1 ? true : false);
    };
    
    $scope.update=function(){
    	if($scope.unidad==null || $scope.unidad==""){
    		mensaje("Ingrese una unidad");
    	}else{
    		if (!isNaN(Number($scope.temp.codEst)) && (Number($scope.temp.codEst)%1==0) && (Number($scope.temp.codEst)>0)){
            	if (!isNaN(Number($scope.temp.ultFact)) && (Number($scope.temp.ultFact)%1==0) && (Number($scope.temp.ultFact)>0)){
            		if (!isNaN(Number($scope.temp.ultBole)) && (Number($scope.temp.ultBole)%1==0) && (Number($scope.temp.ultBole)>0)){
                		if($scope.temp.direccion==null || $scope.temp.direccion==""){
                			mensaje("Ingrese una dirección");
                		}else{
                			if($scope.temp.telefono==null || $scope.temp.telefono==""){
                				mensaje("Ingrese un telefono");
                			}else{
                				if($scope.temp.correo==null || $scope.temp.correo==""){
                					mensaje("Ingrese un correo");
                				}else{
                					$('#Editar').modal('hide');
                					
            			    		$http.post('rest/ComprobantePagoController/modificarEstablecimientoBoletaYFactura',
            			                    {
            			    		    		'udId'   			: $scope.udId,
            			    		    		'unidad'   			: ""+$scope.unidad,
            			    		    		'codEst'   			: ""+$scope.temp.codEst,
            			    					'ultFact'    		: ""+$scope.temp.ultFact,
            			    					'ultBole'    		: ""+$scope.temp.ultBole,
            			    					'direccion'    		: ""+$scope.temp.direccion,
            			    					'telefono'    		: ""+$scope.temp.telefono,
            			    					'correo'    		: ""+$scope.temp.correo,
            			    					'sunat'    			: ($scope.sunat) ? '1' : '0'
            			                    }
            			                )
            			                    .success(function (data, status, headers, config) {
            			                    	mensajeConfirmacion("Cambios almacenados correctamente");
            			                    	cargar();
            			                    })
            			                    .error(function(data, status, headers, config){
            			                    	mensajeConfirmacion("Cambios almacenados correctamente");
            			                    	cargar();
            			                    });
                				}
                			}
                		}
                	}else{
                		mensaje("El número de boleta debe ser un numero entero positivo");
                	}
            	}else{
            		mensaje("El número de factura debe ser un numero entero positivo");
            	}
        	}else{
        		mensaje("El número de código de establecimiento debe ser un numero entero positivo");
        	}
    	}
    };
    $scope.confirmacion=function(c){
    	$scope.abrirModal('#confirmacion');
    	$scope.conf=c;
    };
    $scope.borrar=function(c){
    	$http.post('rest/ComprobantePagoController/borrarEstablecimientoBoletaYFactura',
                {
		    		'codEst'   			: c.codEst
                }
            )
                .success(function (data, status, headers, config) {
                	mensajeConfirmacion("Se elimino correctamente");
                	cargar();
                    console.log(data);
                    console.log(status);
                    console.log(headers);
                    console.log(config);
                })
                .error(function(data, status, headers, config){
                	mensajeConfirmacion("Se elimino correctamente");
                	cargar();
                    console.log(data);
                    console.log(status);
                    console.log(headers);
                    console.log(config);
                });
    };
    function mensajeConfirmacionCambio(titulo,texto){
    	$.gritter.add({
			title: titulo,
			text: texto,
			image: 'resources/assets/img/notification/succes.png',
			sticky: false,
			time: ''
		});
    }
    function mensajeCambio(id,tituloMensaje,textoMensaje){
    	$scope.tituloMensaje=tituloMensaje;
    	$scope.textoMensaje=textoMensaje;
        $scope.abrirModal(id);
    }
    $scope.cambio=function(c,i){
    	$scope.indice=i;
    	if(c.estado==1){
    		mensajeCambio("#confirmacion2","Deshabilitar","¿Seguro que desea deshabilitar el establecimiento "+c.codEst+"?");
    		$scope.cTemp=c;
    	}else{
    		mensajeCambio("#confirmacion2","Habilitar","¿Seguro que desea habilitar el establecimiento "+c.codEst+"?");
    		$scope.cTemp=c;
    	}
    };
    $scope.cambiarColor=function(c,i){
    	if(c.estado==1){
    		$http.post('rest/ComprobantePagoController/modificarEstadoEstablecimientoBoletaYFactura',
	                {
    					'codEst'   			: c.codEst,
    					'estado'    		: 0
	                }
	            )
	                .success(function (data, status, headers, config) {
	                	mensajeConfirmacionCambio('Establecimiento','El establecimiento '+c.codEst+' ha sido deshabilitado');
	                	$scope.tableParams.data[i].estado=0;
	                })
	                .error(function(data, status, headers, config){

	                	mensajeConfirmacionCambio('Establecimiento','El establecimiento '+c.codEst+' ha sido deshabilitado');
	                	$scope.tableParams.data[i].estado=0;
	                });
    	}else{
    		$http.post('rest/ComprobantePagoController/modificarEstadoEstablecimientoBoletaYFactura',
	                {
    					'codEst'   			: c.codEst,
    					'estado'    		: 1
	                }
	            )
	                .success(function (data, status, headers, config) {
	                	mensajeConfirmacionCambio('Establecimiento','El establecimiento '+c.codEst+' ha sido habilitado');
	                	$scope.tableParams.data[i].estado=1;
	                })
	                .error(function(data, status, headers, config){
	                	mensajeConfirmacionCambio('Establecimiento','El establecimiento '+c.codEst+' ha sido habilitado');
	                	$scope.tableParams.data[i].estado=1;
	                });
    	}
    };
    $scope.detallar=function(c){
    	$scope.abrirModal("#detalle");
    	$scope.detalle=c;
    	$scope.detalle.fechaEmision=$scope.detalle.fechaEmision.substr(0,$scope.detalle.fechaEmision.length-2);
    };
    $scope.texto=function(t){
    	if(t==null){
    		return "-";
    	}else{
    		return t;
    	}
    };
    $scope.operacionBancaria=function(fp){
    	if(fp=="VOUCHER"){
    		return true;
    	}else{
    		return false;
    	}
    };
    $scope.moneda=function(m){
    	if(m=="DOLARES"){
    		return "$"
    	}else{
    		return "S/"
    	}
    };
    $scope.tipo=function(t){
    	switch (t) {
			case 1: return "BOLETA";
			case 2: return "FACTURA";
			case 3: return "TICKET";
		}
    };
    function mensaje(o){
    	$.gritter.add({
			title: 'Mensaje',
			text: o,
			image: 'resources/assets/img/notification/error.png',
			sticky: false,
			time: ''
		});
    }
    function mensajeConfirmacion(o){
    	$.gritter.add({
			title: 'Mensaje',
			text: o,
			image: 'resources/assets/img/notification/succes.png',
			sticky: false,
			time: ''
		});
    }
    $scope.guardar=function(t){
    		if($scope.unidad==null || $scope.unidad==""){
        		mensaje("Ingrese una unidad");
        	}else{
        		if (!isNaN(Number($scope.codEst)) && (Number($scope.codEst)%1==0) && (Number($scope.codEst)>0)){
                	if (!isNaN(Number($scope.ultFact)) && (Number($scope.ultFact)%1==0) && (Number($scope.ultFact)>0)){
                		if (!isNaN(Number($scope.ultBole)) && (Number($scope.ultBole)%1==0) && (Number($scope.ultBole)>0)){
                    		if($scope.direccion==null || $scope.direccion==""){
                    			mensaje("Ingrese una dirección");
                    		}else{
                    			if($scope.telefono==null || $scope.telefono==""){
                    				mensaje("Ingrese un telefono");
                    			}else{
                    				if($scope.correo==null || $scope.correo==""){
                    					mensaje("Ingrese un correo");
                    				}else{
                    					var flag=false;
                    			    	for(var i in $scope.tabla){
                    			    		if($scope.tabla[i].unidad==$scope.unidad){
                    			    			flag=true;
                    			    		}else{
                    			    			flag=false;
                    			    		}
                    			    	}
                    			    	if(flag){
                    			    		mensaje("La unidad ya se encuentra registrada");
                    			    	}else{
                    			    		$('#Nuevo').modal('hide');
                    			    		$http.post('rest/ComprobantePagoController/insertarEstablecimientoBoletaYFactura',
                    			                    {
                    			    		    		'udId'   			: $scope.udId,
                    			    		    		'unidad'   			: $scope.unidad,
                    			    		    		'codEst'   			: $scope.codEst,
                    			    					'ultFact'    		: $scope.ultFact,
                    			    					'ultBole'    		: $scope.ultBole,
                    			    					'direccion'    		: $scope.direccion,
                    			    					'telefono'    		: $scope.telefono,
                    			    					'correo'    			: $scope.correo,
                    			    					'estado'    		: 1
                    			                    }
                    			                )
                    			                    .success(function (data, status, headers, config) {
                    			                    	mensajeConfirmacion("Se almaceno correctamente");
                    			                    	cargar();
                    			                    	$scope.udId=null;
                    			                    	$scope.unidad=null;
                    			                    	$scope.codEst=null;
                    			                    	$scope.ultFact=null;
                    			                    	$scope.ultBole=null;
                    			                    	$scope.direccion=null;
                    			                    	$scope.telefono=null;
                    			                    	$scope.correo=null;
                    			                        console.log(data);
                    			                        console.log(status);
                    			                        console.log(headers);
                    			                        console.log(config);
                    			                    })
                    			                    .error(function(data, status, headers, config){
                    			                    	mensajeConfirmacion("Se almaceno correctamente");
                    			                    	cargar();
                    			                    	$scope.udId=null;
                    			                    	$scope.unidad=null;
                    			                    	$scope.codEst=null;
                    			                    	$scope.ultFact=null;
                    			                    	$scope.ultBole=null;
                    			                    	$scope.direccion=null;
                    			                    	$scope.telefono=null;
                    			                    	$scope.correo=null;
                    			                        console.log(data);
                    			                        console.log(status);
                    			                        console.log(headers);
                    			                        console.log(config);
                    			                    });
                    			    	}
                    				}
                    			}
                    		}
                    	}else{
                    		mensaje("El número de boleta debe ser un numero entero positivo");
                    	}
                	}else{
                		mensaje("El número de factura debe ser un numero entero positivo");
                	}
            	}else{
            		mensaje("El número de código de establecimiento debe ser un numero entero positivo");
            	}
        	}
    };
});