app.directive('formularioDatosTicket', function ($http,$filter,$q, utilCharsService,dependenciaService) {
    return {
        restrict:'E',
        template: 
	        "<form name='formularioCabecera'>"+
	        "<div class='row justify-content-md-center'>"+
				"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		    		"<label>Unidad: </label>"+	
		    		"<input type='text' class='form-control' placeholder='Unidad' ng-model='dependencia.unidad' title='{{dependencia.unidad}}' readonly>"+
		    		"<br/>"+
		    	"</div>"+		        
		    	"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		        	"<label>Local:</label>"+
		        	"<input type='text' class='form-control' placeholder='Serie' ng-model='dependencia.local' readonly>"+
		        	"<br/>"+
		    	"</div>"+		    	
		    	"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		        	"<label>Fecha:</label>"+
		        	"<input type='text' class='form-control' placeholder='Fecha' ng-model='maqReg.fechaSistema' readonly>"+
		        	"<br/>"+
		    	"</div>"+
		    "</div>"+
		    "<div class='row justify-content-md-center'>"+
				"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		    		"<label>Marca: </label>"+	
		    		"<input type='text' class='form-control' placeholder='Unidad' ng-model='maqReg.marca' title='{{maqReg.marca}}' readonly>"+
		    		"<br/>"+
		    	"</div>"+		        
		    	"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		        	"<label>Modelo:</label>"+
		        	"<input type='text' class='form-control' placeholder='Serie' ng-model='maqReg.modelo' readonly>"+
		        	"<br/>"+
		    	"</div>"+		    	
		    	"<div class='col-xs-12 col-sm-6 col-md-4'>"+
		        	"<label>Serie de Fabricación:</label>"+
		        	"<input type='text' class='form-control' placeholder='Fecha' ng-model='maqReg.serieFabricacion' readonly>"+
		        	"<br/>"+
		    	"</div>"+
		    "</div>"+
	    	"</form>",
        link: function ($scope, element, attr) {
        	console.log("unidad Escogida")
        	console.log($scope.unidadElegida)
        	$scope.dependencia={
        			unidad:$("#dscUnidadAdministrativa").text(),
        			local:$("#descDependencia").text()
        	}
        	var udId=$("#idDependencia").text();
        	console.log(udId)
        	$http.get("rest/ComprobanteTicketController/getMaqRegistradora/"+udId).success(function(data){
         		$scope.maqReg=data[0];
         		console.log(data[0])
         		var maqReg=data[0];
         		$scope.nroAutorizacion=$scope.maqReg.numeroAutorizacion;
            	$scope.direccion=$scope.maqReg.direccion;
            	$scope.nombCortoLocal=$scope.maqReg.nombCortoLocal;
            	$scope.serieFabMaq=$scope.maqReg.serieFabricacion;
            	console.log($scope.nroAutorizacion+$scope.direccion+$scope.nombCortoLocal+$scope.serieFabMaq+$scope.serieFabMaq);

         	});
//        	var elementoClickeado;
//            $scope.arbol={
//                    letrasRaiz : {C:5, D:5, E:5, F:5, G:5, H:5, Z:5, M:5, N:5},
//                    cantCarNv1: 3,
//                    cantCarNv2: 5
//            };  
//            function mensajesModalDependencia(){
//                switch ($scope.estadoModalDependencia){
//                    case 0, 1: break;
//                    case -5: mensajePositivo('Aviso','Usted No debe manejar facultades.','resources/assets/img/notification/peligro.png');break;
//                    case -4: mensajePositivo('Aviso','Usted debe seleccionar alguna unidad de la dependencia.','resources/assets/img/notification/peligro.png');break;
//                    case -3: mensajePositivo('Aviso','Usted debe seleccionar alguna unidad de la dependencia.','resources/assets/img/notification/peligro.png');break;
//                    case -2: mensajePositivo('Aviso','Usted debe seleccionar alguna facultad.','resources/assets/img/notification/peligro.png');break;
//                    case -1: mensajePositivo('Aviso','Usted debe seleccionar alguna facultad o dependencia.','resources/assets/img/notification/peligro.png');break;
//                    case 2: mensajePositivo('Error','No hay conexion en estos momentos.','resources/assets/img/notification/peligro.png'); break;
//                    default: break;
//                };  
//            };
//                        
//            $scope.openModalUnidad = function(){
//            	$scope.loading = true;
//            	var perfil=$("#idPerfil").text();
//                var dependencia=$("#idCodDependencia").text();
//                
//                $scope.estadoModalDependencia = 0; // estado inicial( No ha elegido nada aun )
//                
//                var promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
//
//                promise.then(
//                              function(respLista) { 
//                                  $scope.dependencia = respLista.data;  
//                                  $scope.loading = false;
//                                  $("#modalDependencia").modal({backdrop: 'static', keyboard: false});
//                              },
//                              function(errorLista) {
//                            	  $scope.loading = false;
//                              }
//                );
//            }; 
//            $scope.expandirContraer=function(event, desc_unidad){
//                var textElementoClickeado=event.currentTarget.text;
//                nombreCompletoNodoSeleccionado=desc_unidad;
//               
//                if(elementoClickeado){
//                 angular.element(elementoClickeado).css({"background-color":""});
//                }
//                
//                elementoClickeado=event.currentTarget; 
//                angular.element(elementoClickeado).css({"background-color":"#81F7F3"});
//                
//                
//                if(textElementoClickeado=="UNMSM"){
//                    $scope.primerNivel=!$scope.primerNivel;
//                }else{ 
//                     var indice=textElementoClickeado.indexOf("-");      
//                     $scope.cod_dep=textElementoClickeado.substring(0,indice);
//                     codigoNodoSeleccionado=textElementoClickeado.substring(0,indice);
//                     $scope[$scope.cod_dep]=!$scope[$scope.cod_dep];
//                }
//             };    
//            $scope.aceptarDependencia=function(){ 
//                var nodoPadre;                
//                if(nombreCompletoNodoSeleccionado == null || nombreCompletoNodoSeleccionado == ""){
//                     $scope.estadoModalDependencia = -3; // estado errado: debe seleccionar alguna facultad o dependencia
//                }
//                else if (nombreCompletoNodoSeleccionado == "UNMSM") {
//                     $scope.estadoModalDependencia = -1; // estado errado: debe seleccionar alguna facultad o dependencia
//                }else{	                    
//	                    var primeraLetra=codigoNodoSeleccionado.substring(0,1);
//	                    var cantLetras= codigoNodoSeleccionado.length;
//	                    
//	                    if (primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv1) { 
//	                        $scope.estadoModalDependencia = -2; // estado errado: debe seleccionar alguna facultad
//	                    }
//	                    else if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //nivel 1
//	                        $scope.estadoModalDependencia = -4;//Usted debe seleccionar alguna unidad de la dependencia
//	                    } 
//	                    else{
//	                    	$scope.unidadElegida.descripcion = nombreCompletoNodoSeleccionado;
//	                    	$("#modalDependencia").modal("hide");
//	                    }                     	                                             
//                   }                
//                   mensajesModalDependencia();
//                   $scope.estadoModalDependencia = 0;
//            };     
            
        	$scope.cargando={valor:false};
        	$http.get("rest/FacturaController/listarTipoImpuesto/IGV").success(function(data){
         		$scope.igvGlobal=data[0].monto;
         	});
        	
        	$scope.importeNumTemp=0.00; //importeNumtemp para el modal buscar bien y/o servicio
            $scope.razonCliente=null;
            $scope.direccionCliente=null;
            $scope.band=[];
            for(var i=0;i<100;i++){
                $scope.band[i]=true;
            }
            
            var actualizacion=function(){
            	$scope.sub=0;
            	$scope.igv=0;
            	$scope.gravada=0;
            	$scope.exonerada=0;
            	$scope.inafecta=0;
            	for(var i in $scope.contenido){
            		switch ($scope.contenido[i].codTipoIgv) {
        				case 10:
        					$scope.gravada+=$scope.contenido[i].precioT;
        					break;
        				case 20:
        					$scope.exonerada+=$scope.contenido[i].precioT;
        					break;
        				case 30:
        					$scope.inafecta+=$scope.contenido[i].precioT;
        					break;
        				default:
        			}
            		$scope.sub+=$scope.contenido[i].precioT;
            		$scope.igv+=$scope.contenido[i].igvT;
            	}
                $scope.total=Math.round(($scope.igv+$scope.sub)*100)/100;
                if($scope.contenido.length!=0){
                	$scope.gravadam=$scope.contenido[0].moneda+" "+$scope.gravada.toFixed(2);
                	$scope.exoneradam=$scope.contenido[0].moneda+" "+$scope.exonerada.toFixed(2);
                	$scope.inafectam=$scope.contenido[0].moneda+" "+$scope.inafecta.toFixed(2);
                	$scope.subm=$scope.contenido[0].moneda+" "+$scope.sub.toFixed(2);
                	$scope.igvm=$scope.contenido[0].moneda+" "+$scope.igv.toFixed(2);
                	$scope.totalm=$scope.contenido[0].moneda+" "+$scope.total.toFixed(2);
                    $scope.importem=$scope.totalm;
                }else{
                	$scope.gravadam=$scope.gravada;
                	$scope.exoneradam=$scope.exonerada;
                	$scope.inafectam=$scope.inafecta;
                	$scope.subm=$scope.sub;
                	$scope.igvm=$scope.igv;
                	$scope.totalm=$scope.total;
                    $scope.importem=$scope.totalm;
                }
            };
            
            function validar(elemento){
            	if (elemento == undefined || elemento == '') return '';
            	else return elemento;
            }

            $scope.actualizarItem=function(i){
            	$scope.contenido[i].cantidad=validar($scope.contenido[i].cantidad);            	

            	$scope.contenido[i].igvT=$scope.contenido[i].igvU.toFixed(2)*$scope.contenido[i].cantidad;            	
            	$scope.contenido[i].precioT=$scope.contenido[i].precioU*$scope.contenido[i].cantidad;           	
            	actualizacion();
            };
            
            var hashMap = new Map();
            $scope.funcion=function(i,p){
            	p.cantidad = + p.cantidad;
                if($scope.band[i]){
                	hashMap.set(i,p.cantidad);
                    $scope.band[i]=false;
                }else{
                	if(p.cantidad==""){
                		p.cantidad=hashMap.get(i);
                		$scope.actualizarItem(i);
                	}
                    $scope.band[i]=true;
                }
            };
            
        	$http.get("rest/MonedaController/listarMonedas").success(function(data){
        		$scope.monedas=data;
        		$scope.moneda=data[0].monedaDesc;
        	});
        	
        	$scope.fecha = $filter('date')(new Date(),'dd/MM/yyyy');
        	
        	$scope.busquedaCriterio = {
        		porNombre : '01',
        		porCodigo : '02'
        	};
        	$scope.cambiarBusquedaXBienOServicio = function(value) {
        	    $scope.optionBusquedaItem = value;
        	    $scope.buscado = '';
        	    $scope.dialog.$setPristine();
        	};
        	function autocompletado(data) {
            	$('#demo3').typeahead({
                    source: data,
                    displayField: 'descripItemYCategoria',
                    valueField: 'codigo',
                    onSelect: function(item) {
                        for(var i in data){
                        	if(data[i].codigo==item.value){
                        		$scope.tabla=data[i];
                        		$scope.importeTotalTemporal=$scope.tabla.conIgv.toFixed(2);
                        		
                        		$scope.importeTemporal=$scope.tabla.precio.toFixed(2);
                        		$scope.importeNumTemp=parseFloat($scope.importeTemporal);
                        		$scope.visibilidad=true;
                        		break;
                        	}
                        }
                    },
                    grepper:function (data){
                    	var items=[];
                    	this.query = this.query.trim();
                    	this.query = this.query.replace(/\s+/g, " ");
                    	if(this.query=="") return items;
                    	var re = new RegExp('\\b'+utilCharsService.accent_fold(this.query), 'i');
                    	for(var i in data){
                    		if( data[i].tipoItem==$scope.optionBusquedaItem && 
                    			 (
                    			 ($scope.optionBuscarPor==$scope.busquedaCriterio.porNombre && re.test(data[i]['search']))
                    			||
                    			($scope.optionBuscarPor==$scope.busquedaCriterio.porCodigo && this.query.toLowerCase()==data[i].codigo.toLowerCase().substring(0, this.query.length))
                    			)
                    		  ){
                    			items.push(data[i]);
                    		}
                    			
                    	}
                    	return items;
                    }
                });
        	}
        	function actualizarAutocompletado(o){
        		$('#demo3').typeahead('destroy');
        		var d = $q.defer();
        	    var p = d.promise;
        	    $http.get("rest/ListaItemPrecioController/listaYConceptoXDependencia/"+o.codUnidad)
                .success(function(data) {
                    d.resolve(data);
                })
                .error(function(err) {
                    d.reject(err)
                });
        	    p.then(function(value) {
                	$scope.productos=value;
                	for (var i=0; i<value.length; i++) {
                		value[i]['search'] = utilCharsService.accent_fold(value[i]['descripcion']);
                	}
                	autocompletado(value);
        	    });
        	}
            $scope.visibilidad=false;
            $scope.seleccionar=function(){
            	if($scope.tabla==null){
            		mensaje("No se encontro el producto");
            	}else{
            		$('#buscar').modal("hide");
            		$scope.dialog.$setPristine();
                	$scope.seleccionado=$scope.tabla.codigo;
                	$scope.descripcion=$scope.tabla.descripcion;
                	$scope.tabla=null;
                	$scope.buscado=null;
                	$scope.visibilidad=false;
                	$('#cantidadBiSe').focus();
            	}
            };
            $scope.limpiarSeleccion=function(){
            	
            }
            $scope.contenido=[];
            $scope.sub=0;
            $scope.igv=0;
            $scope.total=0;
        	$http.get("rest/FacturaController/precioCambio").success(function(data){
        		$scope.precioCambio=data.precioCambio;
        	});
            $scope.cambio=function(){
            	if($scope.contenido[0].moneda=="$"){
            		$scope.importem="S/ "+(Math.round($scope.total*$scope.precioCambio*100)/100);
            	}else{
            		$scope.importem="$ "+(Math.round($scope.total*(1/$scope.precioCambio)*100)/100);
            	}
            };
            var index;
            $scope.actualizarContenido=function(){
            	if($scope.edit.codigo>0 && $scope.edit.codigo<=$scope.productos.length && $scope.edit.codigo%1==0){
                    if($scope.edit.cantidad>0 && $scope.edit.cantidad%1==0){
                    	temp2=buscar($scope.edit.codigo);
                    	$scope.contenido[index]={
                    			cantidad:$scope.edit.cantidad,
                    			codigo:$scope.edit.codigo,
                    			descripcion:temp2.descripcion,
                    			precioU:temp2.precioU,
                    			precioT:temp2.precioU*$scope.edit.cantidad
                    	};
                    	actualizacion();
                    }else{
                    	mensaje("Cantidad invalida");
                    }
            	}else{
            		mensaje("Codigo invalido");
            	}
            };
            $scope.editar=function(i,cantidad,codigo){
            	$("#editar").modal("show");
            	$scope.edit={cantidad:cantidad,codigo:codigo};
            	index=i;
            };
            
            $scope.Moneda=function(){
            	if($scope.formaPago=="VOUCHER BANCO" || $scope.formaPago=="VOUCHER POS"){
            		$scope.importem=null;
            	}else{
                	if(($scope.moneda=="DOLARES" && $scope.contenido[0].moneda=="$") || ($scope.moneda=="SOLES" && $scope.contenido[0].moneda=="S/")){
                		$scope.importem=$scope.totalm;
                		$scope.monedaflag=false;
                	}else{
                		if($scope.formaPago=="EFECTIVO"){
                			$scope.cambio();
                			$scope.monedaflag=true;
                		}
                	}
            	}
            };
            var buscar=function(cod){
            	for(var i in $scope.productos){
            		if(cod==$scope.productos[i].codigo){
            			var temp={
            					cantidad:parseFloat($scope.cantidad),
            					codigo:$scope.productos[i].codigo,
            					descripcion:$scope.productos[i].descripcion,
            					moneda:$scope.productos[i].monedaSimb,
            					precioU:parseFloat($scope.productos[i].precio.toFixed(2)),
            					igvU:$scope.productos[i].igv,
            					igvT:$scope.productos[i].igv*$scope.cantidad,
            					precioT:Math.round($scope.cantidad*$scope.productos[i].precio*100) / 100,
             					tipo:$scope.productos[i].tipo,
             					codProductoSUNAT:$scope.productos[i].codProductoSUNAT,
             					poseeDetraccion:$scope.productos[i].poseeDetraccion,
             					codDetraccion:$scope.productos[i].codDetraccion,
             					porcentDetraccion:$scope.productos[i].porcentDetraccion,
             					codTipoIgv:$scope.productos[i].codTipoIgv,
             					nUnidadMedida:$scope.productos[i].nUnidadMedida
            			};
            			return temp;
            		}
            	}
            };
            $scope.flag=false;
            $scope.aniadir=function(){
            	//$scope.cantidad=validar($scope.cantidad);
            	if ($scope.descripcion==null || $scope=="") {
        			mensaje("Elija algún producto");
        		}
            	else{
                    if($scope.cantidad>0){
                    	
                    	if($scope.contenido.length==0){
                    		$scope.formularioAniadir.$setPristine();
                        	$scope.flag=true;
                        	var temp2=buscar($scope.seleccionado);
                         	if(temp2.tipo==1){
                         		temp2.igvT=temp2.igvU.toFixed(2)*temp2.cantidad;
                         		temp2.precioU=parseFloat($scope.importeTotalTemporal-temp2.igvU.toFixed(2));
                         		temp2.precioT=temp2.precioU.toFixed(2)*temp2.cantidad;
                         	}
                        	$scope.contenido.push(temp2);
                        	actualizacion();
                            $scope.cantidad=null;
                            $scope.seleccionado=null;
                            $scope.descripcion=null;
                            $scope.pagoflag=true;
                            if($scope.contenido[0].moneda=="$"){
                            	$scope.moneda="DOLARES";
                            }else{
                            	$scope.moneda="SOLES";
                            }
                            $scope.estadoImporte=true;
                        }else{
                        	var bandera=true;
                        	for(var i in $scope.contenido){
                        		if($scope.contenido[i].codigo==$scope.seleccionado){
                        			mensaje("el producto ya se encuentra registrado");
                        			bandera=false;
                        			break;
                        		}
                        	}
                        	if(bandera){
                            	var temp2=buscar($scope.seleccionado);
                             	if(temp2.tipo==1){
                             		temp2.igvT=temp2.igvU.toFixed(2)*temp2.cantidad;
                             		temp2.precioU=parseFloat($scope.importeTotalTemporal-temp2.igvU.toFixed(2));
                             		temp2.precioT=temp2.precioU.toFixed(2)*temp2.cantidad;
                             	}
                        		if(temp2.moneda==$scope.contenido[0].moneda){
                        			$scope.formularioAniadir.$setPristine();
                                	$scope.contenido.push(temp2);
                                	actualizacion();
                                    $scope.cantidad='';
                                    $scope.seleccionado=null;
                                    $scope.descripcion=null;
                            		
                        		}else{
                        			$.gritter.add({
                        				title: 'Advertencia',
                        				text: "La moneda debe ser la misma",
                        				image: 'resources/assets/img/notification/peligro.png',
                        				sticky: false,
                        				time: ''
                        			});
                        		}
                        	}
                        }
                    }else{
                    	mensaje("La cantidad debe ser un entero positivo");
                    }
            	}
            };
             $scope.cambiarImporte=function(){
            	 	
            		if(parseFloat($scope.importeTemporal)>0.00){
                		$scope.estBtnSel=(parseFloat($scope.importeTemporal)>$scope.tabla.precio)?true:false;
                     	if ($scope.tabla.estadoIGV==1) {
                     		$scope.tabla.igv=parseFloat($scope.importeTemporal)*$scope.igvGlobal;
                 		}else{
                 			$scope.tabla.igv=0;
                 		}
                     	$scope.tabla.conIgv=parseFloat($scope.importeTemporal)+$scope.tabla.igv;
                	}else{
                		$scope.estBtnSel=true;
                		$scope.tabla.igv=0;
                		$scope.tabla.conIgv=0;
                	}
             };
             $scope.limpiarform=function(){
            	 $scope.estBtnSel=false;
             }
             $scope.cambiarImporteTotal=function(){
         	 	
         		if(parseFloat($scope.importeTotalTemporal)>0.00){
             		$scope.estBtnSel=(parseFloat($scope.importeTotalTemporal)>$scope.tabla.conIgv)?true:false;
                  	if ($scope.tabla.estadoIGV==1) {
                  		$scope.tabla.igv=((parseFloat($scope.importeTotalTemporal)*$scope.igvGlobal)/(1+$scope.igvGlobal));
              		}else{
              			$scope.tabla.igv=0;
              		}
                  	$scope.tabla.precio=parseFloat($scope.importeTotalTemporal)-$scope.tabla.igv;
             	}else{
             		$scope.estBtnSel=true;
             		$scope.tabla.igv=0;
             		$scope.tabla.precio=0;
             	}
          };
            $scope.borrar=function(pos){
            	$scope.band[pos]=true;
            	$scope.contenido.splice(pos, 1);
            	actualizacion();
            	if($scope.contenido.length==0){
            		$scope.pagoflag=false;
            	}else{
            		$scope.pagoflag=true;
            	}
            };
            $scope.tipo="Número R.U.C.";
            $scope.r1=function(){
            	$scope.tipo="Número R.U.C.";
            };
            $scope.r2=function(){
            	$scope.tipo="Razón social";
            };
            
            $scope.formaPago="efectivo";
            $scope.moneda="soles";
            
            var ftemp="E";
            $scope.FormaPago=function(){
            	if($scope.formaPago=="VOUCHER BANCO" || $scope.formaPago=="VOUCHER POS"){
            		if($scope.formaPago=="VOUCHER BANCO"){
            			$scope.bancoElegido=null;
            			$scope.visibilidadFormaPago=true;
            			$scope.pos=false;
            		}else{
            			$scope.bancoElegido="VISA";
            			$scope.visibilidadFormaPago=false;
            			$scope.pos=true;
            		}
            		$scope.monedaflag=false;
            		$scope.estadoImporte=false;
            		$scope.importem=null;
            	    ftemp="V";
            	}else{
            		$scope.visibilidadFormaPago=false;
            		$scope.pos=false;
            		$scope.estadoImporte=true;
            		if(ftemp="V"){
            			$scope.importem=$scope.totalm;
            		}
            	}
            };
            $scope.cerrar=function(){
            	$('#descargarPDF').modal("hide");
            	$scope.recarga();
            };
            $scope.recarga=function(){
            	$timeout(function() {
            			$route.reload();
            		  },1000);
            };
            function mostrar(data){
        		$scope.tablaCliente = data;
            	if($scope.tablaCliente==null || $scope.tablaCliente==""){
            		mensaje("No existe el cliente");
            		$scope.visibilidadCliente=false;
            	}else{
            		$scope.visibilidadCliente=true;
            	}
            }
            $scope.buscarCliente=function(){
            	if($scope.tipo=="Número R.U.C."){
            		$http.get("rest/ClienteRucController/ruc/"+$scope.nombreTemp).success(function(data){
            			mostrar([data])});
            	}else if($scope.tipo=="Razón social"){
            		var flag2=false;
            		for(var i  in $scope.nombreTemp){
            			if($scope.nombreTemp[i]=="."){
            				flag2=true;
            				break;
            			}
            		}
            		if(flag2){
            			$http.get("rest/ClienteRucController/razon/"+$scope.nombreTemp+".").success(function(data){
            				mostrar(data)});
            		}else{
            			$http.get("rest/ClienteRucController/razon/"+$scope.nombreTemp).success(function(data){
            				mostrar(data)});
            		}
            	}else{
            		mensaje("Seleccione algun tipo de cliente");
            	}
            };
            $scope.selectRowCliente = function(rowIndex,cliente){
          	  $scope.selectedRow=rowIndex;
          	  $scope.selectedRowCliente = cliente;
          	  $scope.estadoBotonAceptar=true;
          	  $("#buttonAceptarCliente").prop('disabled', false);
          	};
            $scope.visibilidadCliente=false;
            $scope.dobleClickCliente=function(rowIndex,cliente){	  
          	   $scope.selectRowCliente(rowIndex,cliente);
          	   $scope.selCliente();
            };
            $scope.seleccionUnidad=function(){
            	if($scope.unidadElegida == null){
            		$scope.nEstablecimiento=null;
            		$scope.nSerie=null;
            	}else{
            		console.log("otuti")
                	$scope.nEstablecimiento=$scope.unidadElegida.nEstablecimiento;
                	$scope.nSerie=$scope.unidadElegida.nSerie;
                	$scope.nroAutorizacion=$scope.unidadElegida.numeroAutorizacion;
                	$scope.direccion=$scope.unidadElegida.direccion;
                	$scope.nombCortoLocal=$scope.unidadElegida.nombCortoLocal;
                	$scope.serieFabMaq=$scope.unidadElegida.serieFabricacion;
                	console.log($scope.nEstablecimiento+$scope.nSerie+$scope.nroAutorizacion+$scope.direccion+$scope.nombCortoLocal+$scope.serieFabMaq+$scope.serieFabMaq);
            	}
            };
            $scope.abrirModal=function(opc){
            	$scope.$broadcast("limpiarTablaRUC", {}); 
            	$("#buttonAceptarCliente").prop('disabled', true);
            	$(opc).modal("show");
            };
            function mensaje(o){
            	$.gritter.add({
        			title: 'Error',
        			text: o,
        			image: 'resources/assets/img/notification/error.png',
        			sticky: false,
        			time: ''
        		});
            }
            function mensajePositivo(t,m,i){
            	$.gritter.add({
        			title: t,
        			text: m,
        			image: i,
        			sticky: false,
        			time: ''
        		});
            }
            
            $scope.optionBusquedaItem = 'BN';
            $scope.optionBuscarPor = '01';
            
            $scope.abrirModalPrecios=function(){
            	$scope.cantidad=null;
        		if($scope.unidadElegida == null || $scope.unidadElegida==""){
        			mensaje("Registre una unidad de establecimiento");
            	}else{            		
            		$scope.buscado = '';
            		$scope.dialog.$setPristine();
            		$scope.tabla=null;
                	$scope.visibilidad=false;
            		actualizarAutocompletado($scope.unidadElegida);
            		$scope.abrirModal('#buscar');
            	}
            };
            $scope.registrar=function(){
            	$("#modalRegistrar").modal("show");
            };
            $scope.previsualizacion=false;
            $scope.retroceder=function(){
            	$scope.previsualizacion=false;
            };
            $scope.previsualizar=function(){
            	console.log({
        			'establecimiento'	: $scope.nEstablecimiento,
        			'nomCortoLocal'		: $scope.nombCortoLocal,
        			'total'    			: $scope.total,
        			'formaPago'    		: $scope.formaPago,
        			'moneda'   			: $scope.moneda,
        			'importe'    		: $scope.importem,
        			'sub'   			: $scope.sub,
        			'igv'    			: $scope.igv,
        			'unidad'   			: $scope.unidades[0].descripcion,
        			'facultad'    		: $scope.unidades[0].facultad,
        			'serieFabMaq'		: $scope.serieFabMaq,
        			'direccion'			: $scope.direccion,
        			'nroAutorizacion'	: $scope.nroAutorizacion,
        			'detalle'			: $scope.contenido
                })
            	procede=true;
            	for(var i in $scope.band){
            		if($scope.band[i]==false){
            			mensaje("Hay un campo que todavia no ha sido modificado");
            			procede=false;
            		}
            	}
            	if(procede){
            		if($scope.tipoDocumCliente=='OTROS'){
            			if($scope.moneda=='DOLARES' && ($scope.total.toFixed(2)*$scope.tipoCambioVenta)>700)
                			mensajePositivo('Tipo de Documento','Cuando el importe total supera los S/ 700 soles debe seleccionar UN TIPO DE DOCUMENTO DIFERENTE A OTROS','resources/assets/img/notification/peligro.png');      
            			
            			else
            				if($scope.total.toFixed(2)>700)
            					mensajePositivo('Tipo de Documento','Cuando el importe total supera los S/ 700 soles debe seleccionar UN TIPO DE DOCUMENTO DIFERENTE A OTROS','resources/assets/img/notification/peligro.png');      
            		}
        			else{
                    	hashMap.forEach(function (value, key, map) {
                    		if($scope.band[key]==false){
                    			$scope.contenido[key].cantidad=value;
                        	    $scope.actualizarItem(key);
                        	    $scope.band[key]=true;
                    		}
                    	});
                    	$scope.previsualizacion=true;
                    	mensajePositivo('Recomendación','Verifique sus datos antes de registrar','resources/assets/img/notification/peligro.png');        				
        			}

            	}
            };
            
            
            $(".cantidad").inputmask({
                mask: "a{1,9}[.99]",
                definitions: {'a': {validator: "[0-9]"}}
            });

            $("#importe").inputmask({
                mask: "a{1,9}[.99]",
                definitions: {'a': {validator: "[0-9]"}}
            });
            
            $scope.patronNoZeros = (function() {
            	var numericosDecimal = /^[0-9]+(\.[0-9]{1,2})?$/
                var regex = '';
                return {
                  test: function(value) {
                	if(value == 0)
                		return false;
                	else{
                		regex = new RegExp(numericosDecimal);
                		return regex.test(value);
                	}
                  }
                };
            })();

        }
      }
});