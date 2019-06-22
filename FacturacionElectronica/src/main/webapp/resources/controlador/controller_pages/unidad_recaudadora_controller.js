app.controller('unidad_recaudadora_controller', ['$scope', 'ngTableParams', 'dependenciaService', function($scope, ngTableParams, dependenciaService) {
	
	$scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
	$scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, etc
	$scope.user_codDependencia=$("#idCodDependencia").text(); //F0608, etc
	$scope.user_descDependencia=$("#descDependencia").text();
	$scope.user_idCodDependenciaPadre=$("#userIdCodDepPadre").text();
	$scope.user_idUnidadAdministrativa=$("#idUnidadAdministrativa").text();
    $scope.user_descUnidadAdministrativa=$("#dscUnidadAdministrativa").text();
	
    $scope.administracionCentral = false;
    if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
        $scope.administracionCentral = true;
    }
	
	
	$scope.view = {
			codigoUnidad: '', //codigo 3 digitos
			processActualizar: false,
			nombrePadre: '',
			nombreDependencia: '',
			cpBuscado: '',
			urBuscado: '',
			mantieneDatosUR: false,
			mantieneDatosConcepto: false,
			processVolverAEnviar: false, //cuando el concepto ha sido rechazado
			regNuevoCodConcepto: false
	};
	
	$scope.arbol={
			letrasRaiz : {C:5, D:5, E:5, F:5, G:5, H:5, Z:5, M:5, N:5},
			cantCarNv1: 3,
			cantCarNv2: 5
	};
	
	$scope.tipoSolicitud = [{id: "", title: "Todos"}, {id: '0', title: 'Pendientes'}, {id: '1', title: 'Aprobados'}, {id: '2', title: 'Rechazados'}];
	
	
	$scope.VisibleResultTable = false;
	$scope.newcp = {nameUnidad:'', namePadre:'', numUnidad:'', opRetencion: 0, opRetencion2: 0};
	
	$scope.modalOptions = {
            headerColorValue: 0,  //0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
            closeButtonText: 'No',
            actionButtonText: 'Si',
            headerText: 'Confirmar',
            bodyText: 'Realizar ésta acción?',
            data: {},
            action: function(){}                
    };
    
    
    $scope.unidadRecaudadora = {
    		udIdUnidad: null,
	        udDscUnidad: "",
	        codFacUnidad: null,
	        numUnidad: "",
	        estUnidad: null,
	        estSolicitudUnidad: null,
	        udIdSolicitante: null,
	        udIdFac: null,
	        udDscFac: ""
	};
    
    $scope.tempUnidad= null;
    
    function mostrarModalMensaje(titulo, mensaje){
    	$scope.modalMensajeTitle = titulo;
    	$scope.modalMensajeText = mensaje;
		$("#modalMensaje").modal({backdrop: 'static', keyboard: false});
    }
    
    function mensajesModalNuevo(){
		switch ($scope.estadoModalNuevo){
	    	case 1: 
	    		$.gritter.add({
					title: 'Registrar Unidad',
					text: 'La unidad para los conceptos se ha registrado correctamente ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				}); break;
	    	case -1: 
	    		$.gritter.add({
					title: 'Registrar Unidad',
					text: 'La unidad actualmente ya tiene un código asignado. Pruebe eliminando o editando la unidad.',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;
	    	case -2: 
	    		$.gritter.add({
					title: 'Registrar Unidad',
					text: 'El código ' + $scope.view.codigoUnidad + ' ya está siendo usado por otra unidad o dependencia.',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;
	    	case -4: 
	    		$.gritter.add({
					title: 'Registrar Unidad',
					text: 'No se pudo realizar la operación',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	
	    	default: break;
		}	
	}
	
	function mensajesModalEditar(){
		switch ($scope.estadoModalEditar){
			case 1: 
	    		$.gritter.add({
					title: 'Guardar cambios',
					text: 'La unidad se ha actualizado correctamente ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				}); break;   		
	    	case -1: 
	    		$.gritter.add({
					title: 'Guardar cambios',
					text: 'El codigo: '+$scope.view.codigoUnidad + ' ya está siendo usado por otra unidad. Use otro código disponible.',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -4: 
	    		$.gritter.add({
					title: 'Guardar cambios',
					text: 'No se pudo realizar la operación',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	
	    	default: break;
		}	
	}
    
    $scope.getUnidadesRecaudadoras = function(){
    	var promise;

    	if($scope.administracionCentral){
    		promise = dependenciaService.listarUnidadesCodigoXDependencia($scope.user_idUnidadAdministrativa);	
    	}else{
    		promise = dependenciaService.listarUnidadesCodigoXDependencia($scope.user_idCodDependenciaPadre);
    	}

        promise.then(
            	       function(respLista) {
            	             if(respLista.data==0){
            	            	$("#results_UR").hide();
            	            	$("#noMatches_UR").show();
            	             }else{
            	            	 $scope.tableParamsUR = new ngTableParams({count:10, filter: {estSolicitudUnidad:""}}, { dataset: respLista.data, counts:[]});
            	                $("#results_UR").show();
            	                $("#noMatches_UR").hide();
            	             }
            	        },
            	        function(errorLista) {
            	        	  $("#results_UR").hide();
            	        	  $("#noMatches_UR").show();
            	        }
        ); 
    };
    
    $scope.createUnidadCodigo = function(unidad){
		var promise = dependenciaService.createUnidadCodigo(unidad);
		promise.then(
	  	          function(respItem) { 
	  	        	  $scope.estadoModalNuevo = 1;
	  	        	  $("#modalUnidadRecaudadora").modal("hide");	  	        	 
	  	        	  
	  	        	  $scope.getUnidadesRecaudadoras();
	  	        	  mensajesModalNuevo();
	  	          },	
	  	          function(dataError) {
	  	        	  $scope.estadoModalNuevo = dataError;
	  	        	  mensajesModalNuevo();
	  	          }
	  	);
		
    };
    
    $scope.updateUnidad = function(unidad){
    	var promise = dependenciaService.updateUnidadCodigo(unidad);
        promise.then(
        	          function(respItem) {
        	        	  $scope.estadoModalEditar = 1;
    	  	        	  $("#modalUnidadRecaudadora").modal("hide");
        	        	  $scope.getUnidadesRecaudadoras();
    	  	        	  mensajesModalEditar();
        	          },
        	          function(dataError) {
        	        	  $scope.estadoModalEditar = dataError;
    	  	        	  mensajesModalEditar();
        	          }
        );
    };
    
    
    $scope.openModalNewUnidad = function(){
    	resetFormUnidadRecauda();
    	$scope.view.processActualizar = false;
    	$("#modalUnidadRecaudadora").modal({backdrop: 'static', keyboard: false});
	};
	
	$scope.abrirModalConfirm_regUnidadRecauda = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar la nueva unidad para los conceptos. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.submitNewUnidadRecauda;

        $('#modalConfirm').modal({show: true});
    };
	
	$scope.submitNewUnidadRecauda = function() {    	
		$scope.view.codigoUnidad = $scope.unidadRecaudadora.numUnidad;
        $scope.createUnidadCodigo($scope.unidadRecaudadora);
    };
    
    $scope.openModalActualizarUnidad = function(unidad){
    	$scope.tempUnidad= angular.copy(unidad); //temporal para comparar si se ha editado
    	$scope.unidadRecaudadora = angular.copy(unidad);
    	
    	$scope.view.processActualizar = true;
    	
    	$("#modalUnidadRecaudadora").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.abrirModalConfirm_actualizarUnidad = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Guardar cambios';
        $scope.modalOptions.bodyText= 'Se procederá a registrar los cambios de la undidad. ¿Continuar?';

        $scope.modalOptions.action = $scope.actualizarUnidad;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.actualizarUnidad = function(){    	
    	$scope.view.codigoUnidad = $scope.unidadRecaudadora.numUnidad;
    	$scope.updateUnidad($scope.unidadRecaudadora);
    };
    
    
    $scope.deleteUnidadCodigo = function(udId, codigo_unidad){
    	var promise = dependenciaService.deleteUnidadCodigo(udId, codigo_unidad);
        promise.then(
        	          function(respItem) { 
        	        	  $scope.getUnidadesRecaudadoras();
        	        	  $.gritter.add({
        						title: 'Eliminar Unidad',
        						text: 'La unidad se ha eliminado correctamente ',
        						image: 'resources/assets/img/notification/succes.png',
        						sticky: false,
        						time: ''
        				  });
        	          },
        	          function(errorItem) {
        	        	  if(errorItem == -1) {
        	        		  $.gritter.add({
        	  					title: 'Eliminar Unidad',
        	  					text: 'El código de la unidad no existe. Probar otra vez.',
        	  					image: 'resources/assets/img/notification/peligro.png',
        	  					sticky: false,
        	  					time: ''
        	  				  });
        	        	  }else if(errorItem == -4){
        	        		  $.gritter.add({
        	  					title: 'Eliminar Unidad',
        	  					text: 'No se pudo realizar la operación',
        	  					image: 'resources/assets/img/notification/error.png',
        	  					sticky: false,
        	  					time: ''
        	  				  });
        	        	  }
        	          }
        	);
    };
    
    $scope.abrirModalConfirm_eliminarUnidadCodigo = function(unidad){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Eliminar';
        $scope.modalOptions.bodyText= 'Está a punto de Eliminar la unidad. ¿Está seguro?';
        
        $scope.modalOptions.data.unidad = unidad;

        $scope.modalOptions.action = $scope.eliminarUnidadCodigo;

        $('#modalConfirm').modal({show: true});
    };

    $scope.eliminarUnidadCodigo = function(unidad){    	
        $scope.deleteUnidadCodigo($scope.modalOptions.data.unidad.udIdUnidad, $scope.modalOptions.data.unidad.numUnidad);
    };
     
   
    $scope.openModalDetalleUnidad = function(item){
    	$scope.cpDetalle = angular.copy(item);

    	$("#viewUnidadRec").modal({backdrop: 'static', keyboard: false});
    };
	
    function resetFormUnidadRecauda(){
		$scope.unidadRecaudadora = {
				udIdUnidad: null,
		        udDscUnidad: "",
		        codFacUnidad: null,
		        numUnidad: "",
		        estUnidad: null,
		        estSolicitudUnidad: null,
		        udIdSolicitante: null,
		        udIdFac: null,
		        udDscFac: ""
		};

		if($scope.administracionCentral){
			$scope.unidadRecaudadora.typeOfUser = 1;
		}else{
			$scope.unidadRecaudadora.typeOfUser = 0;
		}		
    	
        $scope.formUnidadRecauda.$setPristine(); //reset Form
    }
    
    
    $scope.initView = function(){
    	if($scope.user_codPerfil=="CXC_ADMIN_CAJAS" || $scope.user_codPerfil=="CXC_FACULTAD" || $scope.user_codPerfil=="CXC_CAJA_RECAUD" || $scope.user_codPerfil=="CXC_SOLIC_CONCEP"){
    		var promise = dependenciaService.dependenciasNombrePadre($scope.user_codDependencia);
	        promise.then(
	        	          function(respLista) { 
	        	        	  var primeraLetra=$scope.user_codDependencia.substring(0,1);
	      					  var cantLetras= $scope.user_codDependencia.length;
	        	        	  var nodoPadre= respLista.data;

	        	        	  if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //C,D,E,F,G,H,Z
					    		  $scope.view.nombrePadre = $scope.user_descDependencia;
					    	  }
	        	        	  else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //C,D,E,F,G,H,Z
					    		  $scope.view.nombrePadre = $scope.user_descDependencia;
					    	  }else{								 
					    		  $("#txtNombreDependencia").text($scope.user_descDependencia);
					    		  $scope.view.nombrePadre = nodoPadre.namePadre;
					    	  } 	 
	        	          },
	        	          function(errorLista) {
	        	          }
	        );
        	$scope.dependenciaElegida = $scope.user_codDependencia;

        	$scope.getUnidadesRecaudadoras();        	
    	} 
    	else if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
            $scope.administracionCentral = true;
            $scope.view.nombrePadre = $scope.user_descUnidadAdministrativa;
            $scope.getUnidadesRecaudadoras();
        }
    };
    
    $scope.initView();

    
    
  //============================================= MODAL ARBOL ======================================== 
	var elementoClickeado;
    var elementoClickeado2;
    var codigoNodoSeleccionado;
    var nombreCompletoNodoSeleccionado;
    var udIdNodoSeleccionado;
    var numero_escuelaUnidad;
	
	function mensajesModalDependencia(){
		switch ($scope.estadoModalDependencia){
	    	case 0, 1: break;
	    	case -4: mostrarModalMensaje("Aviso","Usted debe seleccionar alguna unidad de la dependencia."); break;
	    	case -3: mostrarModalMensaje("Aviso","Usted no ha seleccionado nada aún."); break;
	    	case -2: mostrarModalMensaje("Aviso","Usted debe seleccionar alguna facultad." ); break;
	    	case -1: mostrarModalMensaje("Aviso","Usted debe seleccionar alguna facultad o dependencia."); break;
	    	case 2: mostrarModalMensaje("Error","No hay conexion en estos momentos"); break;
	    	default: break;
		};	
	};
    
    $scope.openModalUnidad2 = function(){
		var perfil=$("#idPerfil").text();
		var dependencia=$("#idCodDependencia").text(); 

		$scope.estadoModalDependencia = 0; // estado inicial( No ha elegido nada aun )
	
		var promise;

		if($scope.administracionCentral){
        	promise = dependenciaService.dependenciasPorUsuarioYPerfilXTeso();
        }else{
        	promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
        }

        promise.then(
        	          function(respLista) { 
        	        	  $scope.dependencia2 = respLista.data;
        	        	  $("#modalDependencia2").modal({backdrop: 'static', keyboard: false});
        	          },
        	          function(errorLista) {
        	          }
        );
	};
    
	 $scope.expandirContraer2=function(event, idUnidad, desc_unidad, numUnidad){
	        var textElementoClickeado=event.currentTarget.text;
	        nombreCompletoNodoSeleccionado=desc_unidad;
	        numero_escuelaUnidad = numUnidad;
	       
	        if(elementoClickeado2){
	       	 angular.element(elementoClickeado2).css({"background-color":""});
	        }
	        
	        elementoClickeado2=event.currentTarget; 
	        angular.element(elementoClickeado2).css({"background-color":"#81F7F3"});
	         
	        if(textElementoClickeado=="UNMSM"){
	       	 	$scope.primerNivel=!$scope.primerNivel;
	        }else{ 
		       	 var indice=textElementoClickeado.indexOf("-");  	 
		       	 $scope.cod_dep=textElementoClickeado.substring(0,indice); //F06...
		       	 codigoNodoSeleccionado=textElementoClickeado.substring(0,indice);
		       	 $scope[$scope.cod_dep]=!$scope[$scope.cod_dep];
		       	 udIdNodoSeleccionado=idUnidad;		  
	        }
	 };
	 	
	 $scope.aceptarDependencia2=function(){ 
			var nodoPadre;
			
			if(nombreCompletoNodoSeleccionado == null || nombreCompletoNodoSeleccionado == ""){
				 $scope.estadoModalDependencia = -3; // estado errado: debe seleccionar alguna facultad o dependencia
			}
			else if (nombreCompletoNodoSeleccionado == "UNMSM") {
				$scope.estadoModalDependencia = -1; // estado errado: debe seleccionar alguna facultad o dependencia
			}else{
				
				var primeraLetra=codigoNodoSeleccionado.substring(0,1);
				var cantLetras= codigoNodoSeleccionado.length;
				
				if (primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv1) {	
					$scope.estadoModalDependencia = -2; // estado errado: debe seleccionar alguna facultad
				}
				else if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //nivel 1
					$scope.estadoModalDependencia = -4;//Usted debe seleccionar alguna unidad de la dependencia
				} 
				else {
						var promise = dependenciaService.dependenciasNombrePadre(codigoNodoSeleccionado);
				        promise.then(
				        	          function(respLista) { 
				        	        	    nodoPadre = respLista.data;
				        	        	    
											if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //C,D,E,F,G,H,Z y nivel 1
									    		  $scope.unidadRecaudadora.udDscUnidad = nombreCompletoNodoSeleccionado;
									    		  $scope.unidadRecaudadora.udDscFac = '';
									    	}
											else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //Facultad y nivel 2
									    		  $scope.unidadRecaudadora.udDscUnidad = nombreCompletoNodoSeleccionado;
									    		  $scope.unidadRecaudadora.udDscFac = '';
									    	}else{								 
									    		$scope.unidadRecaudadora.udDscUnidad = nombreCompletoNodoSeleccionado;
									    		$scope.unidadRecaudadora.udDscFac = nodoPadre.namePadre;
									    	}
											
											$scope.unidadRecaudadora.udIdUnidad = udIdNodoSeleccionado;
											
											$scope.estadoModalDependencia = 1; // estado correcto: recibido
											$("#modalDependencia2").modal("hide");
				        	          },
				        	          function(errorLista) {
				        	        	  $scope.estadoModalDependencia = 2; // estado errado: No hay conexion
				        	          }
				        );

				}
			 }
			
			 mensajesModalDependencia();
			 $scope.estadoModalDependencia = 0;
		 
	 };
	 //============================================= FIN MODAL ARBOL ========================================

}]);