app.controller('conceptos_pago_controller', ['$scope', '$filter', 'ngTableParams', 'conceptosPagoService', 'dependenciaService', 'monedaService', 'tipoConceptoPagoService', 'sunatService', function($scope, $filter, ngTableParams, conceptosPagoService, dependenciaService, monedaService, tipoConceptoPagoService, sunatService) {
	
	$scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
	$scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, CXC_ADMIN_CAJAS etc
	$scope.user_codDependencia=$("#idCodDependencia").text(); //F0608, etc
	$scope.user_descDependencia=$("#descDependencia").text();
	$scope.user_idCodDependenciaPadre=$("#userIdCodDepPadre").text();
	$scope.user_idUnidadAdministrativa=$("#idUnidadAdministrativa").text();
    $scope.user_descUnidadAdministrativa=$("#dscUnidadAdministrativa").text();
	
    $scope.administracionCentral = false;
    if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
        $scope.administracionCentral = true;
    }
	
	$scope.accion = 'rest/conceptoUnidadController/reporteConceptoPago/'+$scope.user_codDependencia+'/';
	if($scope.administracionCentral){
		$scope.accion = 'rest/conceptoUnidadController/reporteConceptoPago/adminCentral/'+$scope.user_idUnidadAdministrativa+'/';
	}	
	
	$scope.view = {
			nombrePadre: '',
			nombreDependencia: '',
			udId: null,
			numUnidad: '',
			estado_sol_codUnidad: null,
			cpBuscado: '',
			urBuscado: '',
			mantieneDatosUR: false,
			mantieneDatosConcepto: false,
			processActualizar: false,
			processVolverAEnviar: false, //cuando el concepto ha sido rechazado
			regConcepto: false,
			regNuevoCodConcepto: false
	};

	$scope.viewConcepto = {
			valorDetraccion: 10
	}
		
	
	$scope.arbol={
			letrasRaiz : {C:5, D:5, E:5, F:5, G:5, H:5, Z:5, M:5, N:5},
			cantCarNv1: 3,
			cantCarNv2: 5
	};
	
	$scope.tipoSolicitud = [{id: "", title: "Todos"}, {id: '0', title: 'Pendientes'}, {id: '1', title: 'Aprobados'}, {id: '2', title: 'Rechazados'}];
	
	
	$scope.VisibleResultTable = false;
	$scope.newcp = {nameUnidad:'', namePadre:'', numUniadad:'', opRetencion: 0, opRetencion2: 0};

	$scope.modalOptions = {
                headerColorValue: 0,  //0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
                closeButtonText: 'No',
                actionButtonText: 'Si',
                headerText: 'Confirmar',
                bodyText: 'Realizar ésta acción?',
                data: {},
                action: function(){}                
        };
    
	$scope.concepto = {
			idCPU: null,
			idCPago: null,
			codCPago: "",
			concepto: "",
			descr:"",
			idTipoCpago: null,	
			monto: null,
			estado: null,
			tIngClasif: null,
			idMoneda: null,
			facturable: null,
			igv:null,
			udId: null,
			udCod: "",
			udDesc: "",
			monedaDesc: "",
			monedaSimb: "",
			codigoUnidad: "",
			codBanco:null,
			nombreBanco:'',
			resolRectoral: '',
			udIdSol: '',
			observaciones: '',
			motivoRechazo: '',
			status: null,
			poseeDetraccion: 0,
	        codDetraccion: ''
	};
	$scope.tempConcepto= null;
	
	$scope.itemEditar= {};
    $scope.conPagos = [];
    
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
    
    $scope.conceptoMatriz = {
    		   idCpago: null,
    		   codCpago: "",
               concepto: "",
               idTipoCpago: null,
               nombreTipoCpago: "",
               cpEst: 1,
               facturable: 0,
               igv: 0,
               fechaReg: null,
               codBanco: "",
               nombreBanco: "",
               estSolicitud: 1,
               usuario: null
    };
     
    
    $scope.datosMonedas = function(){
		var promise = monedaService.listarMonedasActivas();
		promise.then(
      					   function(respLista) {
      						 $scope.listaMonedas = respLista.data;
      						 $scope.concepto.idMoneda = respLista.data[0].idMoneda;
      					   },
            			   function(errorLista){
            			   }
      			   );
    };
	$scope.datosMonedas();
	
	$scope.datosBancos = function(){
			var promise = conceptosPagoService.listarBancos();
			promise.then(
	      					   function(respLista) {
	      						 $scope.listaBancos = respLista.data;
	      					   },
	            			   function(errorLista){
	            			   }
	      	);
	};
	$scope.datosBancos();
	
	$scope.datoTiposConcepto = function(){
		var promise = tipoConceptoPagoService.listarTiposConceptosActivos();
		promise.then(
      					   function(respLista) {
      						 $scope.listaTipoConcepto = respLista.data;
      					   },
            			   function(errorLista){
            			   }
      	);
		
	};
	$scope.datoTiposConcepto();
	

	// get CATALOGO CODIGO DETRACCIONES
	$scope.datosCatCodDetracciones = function(){
			var promise = sunatService.dataCataDetracciones();
			promise.then(
	      					   function(respLista) {
	      						 $scope.listaCatCodDetracciones = respLista.data;
	      					   },
	            			   function(errorLista){
	            			   }
	      			   );
	};	
	$scope.datosCatCodDetracciones();
	

	$scope.mostrarScrollBody=function() {   
	   $("body").css("padding-right", "0px");
    };
    $scope.ocultarScrollBody=function() {
	   //$( "body" ).addClass( "scrollHidden" );
    };

    function mostrarModalMensaje(titulo, mensaje){
    	$scope.modalMensajeTitle = titulo;
    	$scope.modalMensajeText = mensaje;
		$("#modalMensaje").modal({backdrop: 'static', keyboard: false});
    }
    
    function mensajesModalNuevo(){
		switch ($scope.estadoModalNuevo){
	    	case 1: 
	    		$.gritter.add({
					title: 'Registrar concepto',
					text: 'El concepto de pago se ha registrado correctamente ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				}); break;
	    	case -1: 
	    		$.gritter.add({
					title: 'Registrar concepto',
					text: 'La unidad: '+$scope.concepto.udDesc + ' actualmente no se encuentra habilitada para recibir conceptos de pago',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -2: 
	    		$.gritter.add({
					title: 'Registrar concepto',
					text: 'El codigo de concepto: '+$scope.concepto.codCPago + ' ya está siendo usado por esta misma unidad',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -3: 
	    		$.gritter.add({
					title: 'Registrar concepto',
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
					text: 'El concepto de pago se ha actualizado correctamente ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				}); break;
	    	case -1: 
	    		$.gritter.add({
					title: 'Guardar cambios',
					text: 'La unidad: '+$scope.concepto.udDesc + ' actualmente no se encuentra habilitada para recibir conceptos de pago',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -2: 
	    		$.gritter.add({
					title: 'Guardar cambios',
					text: 'El codigo de concepto: '+$scope.concepto.codCPago + ' ya está siendo usado por esta misma unidad',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -3: 
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
	
	function mensajesModalVolverEnviarConcepto(){
		switch ($scope.estadoModalEditar){
			case 1: 
	    		$.gritter.add({
					title: 'Volver a enviar',
					text: 'El concepto de pago se ha enviado correctamente ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				}); break;
	    	case -1: 
	    		$.gritter.add({
					title: 'Volver a enviar',
					text: 'La unidad: '+$scope.concepto.udDesc + ' actualmente no se encuentra habilitada para recibir conceptos de pago',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -2: 
	    		$.gritter.add({
					title: 'Volver a enviar',
					text: 'El codigo de concepto: '+$scope.concepto.codCPago + ' ya está siendo usado por esta misma unidad',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;	    		
	    	case -3: 
	    		$.gritter.add({
					title: 'Volver a enviar',
					text: 'No se pudo realizar la operación',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	
	    	default: break;
		}	
	}
	
	function mensajesModalBuscarConcepto(){
		switch ($scope.estadoModalBuscarConcepto){
	    	case -1: 
	    		$.gritter.add({
					title: 'Aceptar Concepto',
					text: 'El concepto de pago: '+$scope.unidadRecaudadora.numUnidad +'-'+ $scope.selectedRowConcepto.codCpago + ' ya está siendo usado. Por favor elija otro.',
					image: 'resources/assets/img/notification/peligro.png',
					sticky: false,
					time: ''
				}); break;	    			    		
	    	case -2: 
	    		$.gritter.add({
					title: 'Aceptar Concepto',
					text: 'No se pudo realizar la operación',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;	
	    	default: break;
		}	
	}
	
    function mostrar(data){
    	$scope.conPagos = data;    	
    	if($scope.conPagos==null || $scope.conPagos==""){
    		$scope.VisibleResultTable=false;
    	}else{
    		if ($scope.viewMode == 1){ //modo mantenimiento
    			$scope.tableParams = new ngTableParams({count:20, sorting: {idCPU: 'desc'}, filter: {estadoSolicitud:""} }, { dataset: $scope.conPagos, counts:[]});
            } 
            else { //modo consulta
            	$scope.tableParams = new ngTableParams({count:3, group:{ udDesc:"asc"}, sorting: {idCPU: 'desc'}, filter: {estadoSolicitud:""} }, { dataset: $scope.conPagos, counts:[]});
            }     		
    		
 
    		$scope.VisibleResultTable=true;
    	}
    }
    
    $scope.listarConceptosPagoXDependencia = function(codigoDependencia){
        var promise = null;
        if ($scope.viewMode == 1){ 
        	promise = conceptosPagoService.listarConceptosPagoXDependencia(codigoDependencia); 
        } //modo mantenimiento
        else { 
        	promise = conceptosPagoService.listarConceptosPagoXDependenciaHabilitados(codigoDependencia); 
        } //modo consulta
        
        promise.then(
        	          function(respLista) { 
        	        	  mostrar(respLista.data);
        	          },
        	          function(errorLista) {
        	          }
        	);
    };
    
    $scope.listarConceptosPagoXUnaDependencia = function(codigoDependencia){
        var promise = null;
        if ($scope.viewMode == 1){ //modo mantenimiento
        	promise = conceptosPagoService.listarConceptosPagoXUnaDependencia(codigoDependencia); 
        } 
        else { //modo consulta
        	promise = conceptosPagoService.listarConceptosPagoXDependenciaHabilitados(codigoDependencia);
        } 
        
        promise.then(
        	          function(respLista) { 
        	        	  mostrar(respLista.data);
        	          },
        	          function(errorLista) {
        	          }
        	);
    };
    
    $scope.getConceptosPago = function(){
    	$scope.listarConceptosPagoXDependencia($scope.dependenciaElegida);
    };

    

    $scope.openModalNewConceptoEspec = function(idUnidad, desc_unidad, numUnidad){
    	if($scope.view.estado_sol_codUnidad == null || $scope.view.estado_sol_codUnidad == 0 || $scope.view.estado_sol_codUnidad == 2){
    		
    		$scope.modalOptions.headerColorValue= 0;
	        $scope.modalOptions.closeButtonText= 'Aceptar';
	        $scope.modalOptions.headerText= 'Registrar Concepto';
	        $scope.modalOptions.bodyText= 'No se puede registrar conceptos en ésta unidad. La unidad no está registrada en el sistema.';

	        if($scope.view.estado_sol_codUnidad == null){ //No esta registrado
    			$scope.modalOptions.bodyText = 'No se puede registrar conceptos en ésta unidad. La unidad no está registrada en el sistema.';    			
    		}
    		if($scope.view.estado_sol_codUnidad == 0){ //No esta validado (PENDIENTE)
    			$scope.modalOptions.bodyText = 'No se puede registrar conceptos en ésta unidad. La unidad está pendiente de validación por el área de Tesorería.';    			
    		}
    		if($scope.view.estado_sol_codUnidad == 2){ //Esta RECHAZADO
    			$scope.modalOptions.bodyText = 'No se puede registrar conceptos en ésta unidad. La unidad ha sido rechazada por la unidad de Tesorería.';    			
    		}

	        $('#modalShowMessage').modal({show: true});
    	}
    	else{
	    	resetConcepto();
	    	resetUnidadRecaudadora();
	    	$scope.formSolCP.$setPristine(); //reset Form
	    	$scope.unidadRecaudadora.udDscUnidad = desc_unidad;
	    	$scope.unidadRecaudadora.numUnidad = numUnidad;
	    	$scope.unidadRecaudadora.udIdUnidad = idUnidad;
	    	$scope.concepto.udId = idUnidad;
	    	$scope.view.processActualizar = false;
	    	$scope.view.regConcepto = true;
	    	$scope.view.regNuevoCodConcepto = false; //para un codigo q no existe
	    	
	    	$scope.openModalBuscarConcepto();
    	}
    };
    
    
    
    $scope.updateEstado = function(idCPU, estado){
    	var promise = conceptosPagoService.updateEstado(idCPU, estado);
        promise.then(
        	          function(respItem) { 
        	        	  $scope.getConceptosPago();
        	          },
        	          function(errorItem) {
        	          }
        	);
    };

    $scope.abrirModalConfirm_cambiaEstado = function(item){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Deshabilitar';
        $scope.modalOptions.bodyText= 'Está a punto de deshabilitar el concepto de pago. ¿Está seguro?';

        if(item.estado==0){
    		$scope.modalOptions.headerText = 'Habilitar';
    		$scope.modalOptions.bodyText = 'Está a punto de habilitar el concepto de pago. ¿Está seguro?';    		
    	}

        $scope.modalOptions.data.item = item;

        $scope.modalOptions.action = $scope.cambiaEstado;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.cambiaEstado = function(){    	
        var estado = ($scope.modalOptions.data.item.estado==1 ? 0 : 1);
	    $scope.updateEstado($scope.modalOptions.data.item.idCPU, estado);
    };
    
    
    $scope.updateConcepto = function(item){
    	var promise = conceptosPagoService.updateConceptoPago(item);
        promise.then(
        	          function(respItem) {
        	        	  $scope.estadoModalEditar = 1;
    	  	        	  $scope.getConceptosPago();
    	  	        	  $scope.cancelarActualizacion();
    	  	        	  mensajesModalEditar();
        	          },
        	          function(dataError) {
        	        	  $scope.estadoModalEditar = dataError;
    	  	        	  mensajesModalEditar();
        	          }
        );
    };
    
    $scope.volverEnviarConcepto = function(item){
    	var promise = conceptosPagoService.volverEnviarConcepto(item);
        promise.then(
        	          function(respItem) {
        	        	  $scope.estadoModalEditar = 1;
    	  	        	  $scope.getConceptosPago();
    	  	        	  $scope.cancelarActualizacion();
    	  	        	  mensajesModalVolverEnviarConcepto();
        	          },
        	          function(dataError) {
        	        	  $scope.estadoModalEditar = dataError;
        	        	  mensajesModalVolverEnviarConcepto();
        	          }
        );
    };
    
    $scope.openModalActualizar = function(item){
    	$scope.tempConcepto = angular.copy(item); //temporal para comparar si se ha editado
    	$scope.concepto = angular.copy(item);

    	if($scope.concepto.poseeDetraccion==1) {   
    		var valor = $scope.concepto.porcentDetraccion;
            var lenPartEntera = String(Math.trunc(valor)).length; // numero de digitos parte entera
            var lenPartDecimal= String(valor).match(/\d/g).length - lenPartEntera; //numero de digitos - numero digitos parte entera
            var len = (lenPartDecimal-2 <= 0)?0:(lenPartDecimal-2);
            var porcentaje = (parseFloat(valor*100)).toFixed(len); //convertimos a valor porcentual %
            $scope.viewConcepto.valorDetraccion = porcentaje;    		
    	}else{
    		$scope.viewConcepto.valorDetraccion = 10;
    	}

    	$scope.unidadRecaudadora.udDscUnidad = $scope.concepto.udDesc;
    	$scope.unidadRecaudadora.numUnidad= $scope.concepto.codigoUnidad;

    	if($scope.concepto.facturable == null || $scope.concepto.facturable == ""){
    		$scope.concepto.facturable= 0;
    	}

    	if($scope.concepto.igv == null || $scope.concepto.igv == ""){
    		$scope.concepto.igv= 0;
    	}
  	 
    	
    	$scope.view.processActualizar = true;

    	if(item.estadoSolicitud == 2){
    		$scope.view.processVolverAEnviar=true;
    	}else{
    		$scope.view.processVolverAEnviar=false;
    	}
    	$scope.view.mantieneDatosUR = false;
    	$scope.view.mantieneDatosConcepto = false;
    	$scope.view.regConcepto = false;
    	$scope.view.regNuevoCodConcepto = false;
    	
    	$('#modalConceptoPago').modal({show: true});  
    };
    
    $scope.cancelarActualizacion = function(){
    	
    	$scope.view.processActualizar = false;
    	$scope.view.processVolverAEnviar=false;
    	$scope.view.mantieneDatosUR = false;
    	$scope.view.mantieneDatosConcepto = false; 
    	
    	limpiarDatos();
    	$("#modalConceptoPago").modal('toggle');
    	$scope.formSolCP.$setPristine();
    }
    
    $scope.cancelarRegistro = function(){
    	
    	$scope.view.processActualizar = false;
    	$scope.view.processVolverAEnviar=false;
    	$scope.view.mantieneDatosUR = true;
    	$scope.view.mantieneDatosConcepto = true; 
    	limpiarDatos();
    	$("#modalConceptoPago").modal('toggle');
    	$scope.formSolCP.$setPristine();
    }


    $scope.abrirModalConfirm_actualizarItem = function(){
    	if(angular.equals($scope.concepto, $scope.tempConcepto) == true){
	        $scope.modalOptions.headerColorValue= 2;
	        $scope.modalOptions.closeButtonText= 'Aceptar';
	        $scope.modalOptions.bodyText= 'No se han detectado cambios en éste concepto. Si desea salir sin guardar los cambios haga click en el botón [Cancelar]';

	        if($scope.view.processVolverAEnviar==false){
    			$scope.modalOptions.headerText = "Guardar cambios";  			
    		}else{
    			$scope.modalOptions.headerText = "Volver a enviar";    		
    		}

	        $('#modalShowMessage').modal({show: true});
    	}else{

    		$scope.modalOptions.headerColorValue= 0;
	        $scope.modalOptions.closeButtonText= 'No';
	        $scope.modalOptions.actionButtonText= 'Si';
	        $scope.modalOptions.headerText= 'Guardar cambios';
	        $scope.modalOptions.bodyText= 'Se procederá a registrar los cambios del concepto de pago. ¿Continuar?';

	        if($scope.view.processVolverAEnviar==true){
        		$scope.modalOptions.headerText = 'Volver a enviar';
        		$scope.modalOptions.bodyText =  'Se procederá a enviar los cambios del concepto de pago. ¿Continuar?';
        	}

	        $scope.modalOptions.action = $scope.actualizarItem;

	        $('#modalConfirm').modal({show: true});

    	}
    };
    
    $scope.actualizarItem = function(){    	
    	if($scope.concepto.descr == null || $scope.concepto.descr.trim() == ''){
    		$scope.concepto.descr = $scope.concepto.concepto;
    	}
    	if($scope.concepto.monto == null || $scope.concepto.monto.trim() == ''){
    		$scope.concepto.monto = 0.00;
    	}
    	$scope.concepto.udId = $scope.unidadRecaudadora.udIdUnidad;
    	
    	if($scope.view.processVolverAEnviar==false){
    		$scope.updateConcepto($scope.concepto);
    	}else{
    		$scope.volverEnviarConcepto($scope.concepto);
    	}

    };
    
    $scope.openModalDetalleConcepto = function(item){
    	$scope.cpDetalle = angular.copy(item);

    	if($scope.cpDetalle.facturable == null || $scope.cpDetalle.facturable == ""){
    		$scope.cpDetalle.facturable= 0;
    	}
    	if($scope.cpDetalle.igv == null || $scope.cpDetalle.igv == ""){
    		$scope.cpDetalle.igv= 0;
    	}
    	
    	$("#detalleConceptoPago").modal({backdrop: 'static', keyboard: false});
    };
    
    function resetConcepto(){
    	$scope.concepto = {
    			idCPU: null,
    			idCPago: null,
    			codCPago: "",
    			concepto: "",
    			descr:"",
    			idTipoCpago: null,	
    			monto: null,
    			estado: null,
    			tIngClasif: null,
    			idMoneda: null,
    			facturable: null,
    			igv:null,
    			udId: null,
    			udCod: "",
    			udDesc: "",
    			monedaDesc: "",
    			monedaSimb: "",
    			codigoUnidad: "",
    			codBanco:null,
    			resolRectoral: '',
    			udIdSol: '',
    			observaciones: '',
    			motivoRechazo: '',
    			status: null,
    			poseeDetraccion: 0,
	        	codDetraccion: ''
    	};
    	
    	$scope.newcp.nameUnidad = "";
    	$scope.viewConcepto.valorDetraccion = 10;
    	$scope.concepto.idMoneda = $scope.listaMonedas[0].idMoneda;
        $scope.formSolCP.$setPristine(); //reset Form
    }
    
    function resetUnidadRecaudadora(){
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
    }
    
    function limpiarDatos(){
    	if(!$scope.view.mantieneDatosUR){
    		resetUnidadRecaudadora();
    	}
    	if(!$scope.view.mantieneDatosConcepto){
    		resetConcepto();
    	}
    }
    
    /********************************************** ELIMINAR UNIDAD-CONCEPTO ************************************************/
    $scope.deleteConcepto_unidad = function(idCPU, idCPAGO){
    	var promise = conceptosPagoService.deleteConceptoUnidad(idCPU, idCPAGO);
        promise.then(
        	          function(respItem) { 
        	        	  $scope.getConceptosPago();
        	        	  $.gritter.add({
        						title: 'Eliminar concepto',
        						text: 'El concepto de pago se ha eliminado correctamente ',
        						image: 'resources/assets/img/notification/succes.png',
        						sticky: false,
        						time: ''
        				  });
        	          },
        	          function(errorItem) {
        	        	  if(errorItem == -1) {
        	        		  $.gritter.add({
        	  					title: 'Eliminar concepto',
        	  					text: 'Este concepto de pago no está registrado en esta unidad',
        	  					image: 'resources/assets/img/notification/error.png',
        	  					sticky: false,
        	  					time: ''
        	  				  });
        	        	  }else if(errorItem == -2){
        	        		  $.gritter.add({
        	  					title: 'Eliminar concepto',
        	  					text: 'No se pudo realizar la operación',
        	  					image: 'resources/assets/img/notification/error.png',
        	  					sticky: false,
        	  					time: ''
        	  				  });
        	        	  }
        	          }
        	);
    };

    $scope.abrirModalConfirm_eliminarConcepto = function(item){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Eliminar';
        $scope.modalOptions.bodyText= 'Está a punto de Eliminar el concepto de pago. ¿Está seguro?';

        $scope.modalOptions.data.item = item;

        $scope.modalOptions.action = $scope.eliminarConcepto_unidad;

        $('#modalConfirm').modal({show: true});
    };

    $scope.eliminarConcepto_unidad = function(){    	
        $scope.deleteConcepto_unidad($scope.modalOptions.data.item.idCPU, $scope.modalOptions.data.item.idCPago);	
    };
    /********************************************** FIN ELIMINAR UNIDAD-CONCEPTO ************************************************/
    
    
    /********************************************** REGISTRAR RELACION UNIDAD-CONCEPTO ************************************************/
    $scope.regConceptoUnidad = function(concepto){
		var promise = null;
		
		if($scope.view.regNuevoCodConcepto){
			promise = conceptosPagoService.createConceptoUnidadMaestra(concepto);
		}
		else{ promise = conceptosPagoService.createConceptoUnidad(concepto); }
		
		promise.then(
	  	          function(respItem) { 
	  	        	  $scope.estadoModalNuevo = 1;
	  	        	  $("#modalConceptoPago").modal("toggle");
	  	        	   
	  	        	  $scope.getConceptosPago();
	  	        	  $scope.view.regNuevoCodConcepto = false;
	  	        	  mensajesModalNuevo();
	  	          },	
	  	          function(dataError) {
	  	        	  $scope.estadoModalNuevo = dataError;
	  	        	  mensajesModalNuevo();
	  	          }
	  	);
    };

    $scope.abrirModalConfirm_agregarConcepto = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo concepto de pago. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.agregarConceptoUnidad;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.agregarConceptoUnidad = function() {
    	if($scope.concepto.descr == null || $scope.concepto.descr.trim() == ''){
    		$scope.concepto.descr = $scope.concepto.concepto;
    	}
    	if($scope.concepto.monto == null || $scope.concepto.monto.trim() == ''){
    		$scope.concepto.monto = 0.00;
    	}
		$scope.concepto.udId = $scope.unidadRecaudadora.udIdUnidad;
		$scope.concepto.udIdSol = $scope.user_idCodDependenciaPadre;
		
		if($scope.administracionCentral){
            $scope.concepto.udIdAdministrativa = $scope.user_idUnidadAdministrativa;
        }else{
            $scope.concepto.udIdAdministrativa = $scope.user_idCodDependenciaPadre;
        }
		

		$scope.regConceptoUnidad($scope.concepto);
    };
    /********************************************* FIN REGISTRAR RELACION UNIDAD-CONCEPTO *******************************************/
    
    
    /********************************************** SELECCION DEL CONCEPTO ************************************************/
    $scope.selectedRowConcepto = null;
    $scope.selectedRow = null;
    $scope.selectRowConcepto= function(rowIndex,concepto){
    	  $scope.selectedRow = rowIndex;
    	  $scope.selectedRowConcepto = concepto;
	};
	
	$scope.seleccionarConcepto=function(concepto){
		resetConcepto();
		$scope.concepto.idTipoCpago = concepto.idTipoCpago;
		$scope.concepto.tcpDescr = concepto.nombreTipoCpago;
		$scope.concepto.idCPago = concepto.idCpago;
		$scope.concepto.codCPago = concepto.codCpago;
		$scope.concepto.concepto = concepto.concepto;
		$scope.concepto.descr = concepto.descr;
		$scope.concepto.codBanco = concepto.codBanco;
		$scope.concepto.nombreBanco = concepto.nombreBanco;
		$scope.concepto.igv = concepto.igv;
		$scope.concepto.facturable = concepto.facturable;
		
    	if($scope.concepto.facturable == null || $scope.concepto.facturable == ""){
    		$scope.concepto.facturable= 0;
    	}
    	if($scope.concepto.igv == null || $scope.concepto.giv == ""){
    		$scope.concepto.giv= 0;
    	}
    	
    	$scope.view.regConcepto = true;
    	$scope.view.regNuevoCodConcepto = false;
	};
	
	$scope.dobleClickConcepto=function(rowIndex,concepto){  
		   $scope.selectRowConcepto(rowIndex,concepto);
		   $scope.seleccionarConcepto(concepto);
		   $('#buscarConcepto').modal('toggle');
		   $('#modalConceptoPago').modal({show: true});
	};
	
	
	$scope.aceptarConcepto2=function(){
		$scope.seleccionarConcepto($scope.selectedRowConcepto);
		 $('#buscarConcepto').modal('toggle');
		 $('#modalConceptoPago').modal({show: true});
	};
	
	$scope.aceptarConcepto=function(){		
		//var promise = conceptosPagoService.existeConceptoPago($scope.selectedRowConcepto.idCpago, $scope.unidadRecaudadora.udIdUnidad);
		var promise = conceptosPagoService.existeConceptoPago($scope.selectedRowConcepto.idCpago, $scope.unidadRecaudadora.udIdUnidad);
        
        promise.then(
            	       function(respLista) { 
            	             if(respLista.data==0){
            	            	 $scope.estadoModalBuscarConcepto = -2;
        	            		 mensajesModalBuscarConcepto();
            	             }else{
            	            	 if(respLista.data[0].contador==0){
            	            		 $scope.seleccionarConcepto($scope.selectedRowConcepto);
                	         		 $('#buscarConcepto').modal('toggle');
                	         		 $('#modalConceptoPago').modal({show: true});                	         		 
                	         		 $scope.ocultarScrollBody();
            	            	 }else{
            	            		 $scope.estadoModalBuscarConcepto = -1;
            	            		 mensajesModalBuscarConcepto();
            	            	 }            	            	 
            	             }
            	        },
            	        function(errorLista) {
            	        	$scope.estadoModalBuscarConcepto = -2;
   	            		 	mensajesModalBuscarConcepto();
            	        }
        );
	};
    
    $scope.openModalBuscarConcepto = function(){
    	$scope.view.cpBuscado = '';
    	$scope.selectedRowConcepto = null;
        $scope.selectedRow = null;
        $("#noMatches_CP").hide();
        $("#results_CP").hide();
    	$('#buscarConcepto').modal({show: true});
	};
	$('#buscarConcepto').on('shown.bs.modal', function () {
		$('#cpBuscado').focus();
	});
	
	$("#cpBuscado").keyup(function(){
        var parseDato=$("#cpBuscado").val().trim();
        
        if(parseDato!="" && parseDato.length>2){
        	var dato = parseDato;	
        	var promise = conceptosPagoService.buscarConceptosMaestros(dato,dato,$scope.user_idCodDependenciaPadre);	
            
            promise.then(
                	       function(respLista) { 
                	             if(respLista.data==0){
                	            	$("#results_CP").hide();
                	            	$("#noMatches_CP").show();
                	             }else{
                	                $scope.tableParams1 = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                	                $scope.selectedRowConcepto = respLista.data[0];
                	                $scope.selectedRow = 0;
                	                $("#results_CP").show();
                	                $("#noMatches_CP").hide();
                	             }
                	        },
                	        function(errorLista) {
                	        	  $("#results_CP").hide();
                	        	  $("#noMatches_CP").show();
                	        }
            );       
        }else{
        	$scope.selectedRow = null;
        	$("#results_CP").hide();
        	$("#noMatches_CP").hide();
        	$('#btnAcceptarCP').prop('disabled', true);
        }
    });
	
	$scope.crearConceptoManualmente = function(){
		resetConcepto();
		$scope.view.regNuevoCodConcepto = true;
		$scope.view.regConcepto = false;
		$scope.view.mantieneDatosConcepto = true;
		$('#buscarConcepto').modal('toggle');
		$scope.concepto.codCPago = $scope.view.cpBuscado;
		$scope.concepto.idTipoCpago = $scope.listaTipoConcepto[0].idTipoCpago;
		$scope.concepto.codBanco = $scope.listaBancos[0].codigo;
		$scope.concepto.facturable = 0;
		$scope.concepto.igv = 0;
		$('#modalConceptoPago').modal({show: true});		
		$scope.ocultarScrollBody();
	};


	$('#modalConceptoPago').on('hidden.bs.modal', function () {
		$scope.mostrarScrollBody();
	});
	
	/****************************************** FIN SELECCION DEL CONCEPTO ********************************************/
	
	
	/********************************************** SELECCION DE UNIDAD RECAUDADORA ************************************************/
    $scope.selectedRowURecaudadora = null;
    $scope.selectedRowUR = null;
    $scope.selectRowURecaudadora= function(rowIndex,uRecaudadora){
    	  $scope.selectedRowUR = rowIndex;
    	  $scope.selectedRowURecaudadora = uRecaudadora;
	};
	
	$scope.seleccionarUR=function(uRecaudadora){
		$scope.unidadRecaudadora.udIdUnidad = uRecaudadora.udIdUnidad;
		$scope.unidadRecaudadora.udDscUnidad = uRecaudadora.udDscUnidad;
		$scope.unidadRecaudadora.codFacUnidad = uRecaudadora.codFacUnidad;
		$scope.unidadRecaudadora.numUnidad = uRecaudadora.numUnidad;
		$scope.unidadRecaudadora.estUnidad = uRecaudadora.estUnidad;
		$scope.unidadRecaudadora.estSolicitudUnidad = uRecaudadora.estSolicitudUnidad;
		$scope.unidadRecaudadora.udIdFac = uRecaudadora.udIdFac;
		$scope.unidadRecaudadora.udDscFac = uRecaudadora.udDscFac;
	};
	
	$scope.dobleClickURecaudadora=function(rowIndex,uRecaudadora){  
		   $scope.selectRowURecaudadora(rowIndex,uRecaudadora);
		   $scope.seleccionarUR(uRecaudadora);
		   $('#buscarCodigoUniRec').modal('hide');
	};
	
	$scope.aceptarURecaudadora=function(){
		$scope.seleccionarUR($scope.selectedRowURecaudadora);
		$('#buscarCodigoUniRec').modal('hide');
	}
    
    $scope.openModalBuscarURecaudadora = function(){
    	$scope.view.urBuscado = '';
    	$scope.selectedRowURecaudadora = null;
        $scope.selectedRowUR = null;
        
        var promise = dependenciaService.listarUnidadesCodigoXDependencia($scope.user_idCodDependenciaPadre);	
        
        promise.then(
            	       function(respLista) {
            	             if(respLista.data==0){
            	            	$("#results_UR").hide();
            	            	$("#noMatches_UR").show();
            	             }else{
            	                $scope.tableParamsUR = new ngTableParams({count:10}, { dataset: respLista.data, counts:[]});
            	                $("#results_UR").show();
            	                $("#noMatches_UR").hide();
            	             }
            	        },
            	        function(errorLista) {
            	        	  $("#results_UR").hide();
            	        	  $("#noMatches_UR").show();
            	        }
        );       
        
        $("#results_UR").hide();
    	$("#buscarCodigoUniRec").modal({backdrop: 'static', keyboard: false});
	};
	$('#buscarCodigoUniRec').on('shown.bs.modal', function () {
		$('#urBuscado').focus();
	});
	
	$("#urBuscado").keyup(function(){
        var parseDato=$("#urBuscado").val().trim();
        if(parseDato!=""){
        	var dato = parseDato;	
        	var promise = dependenciaService.buscarUnidadesCodigoXDep($scope.user_idCodDependenciaPadre, dato);	
            
            promise.then(
                	       function(respLista) {
                	             if(respLista.data==0){
                	            	$("#results_UR").hide();
                	            	$("#noMatches_UR").show();
                	             }else{
                	                $scope.tableParamsUR = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                	                $("#results_UR").show();
                	                $("#noMatches_UR").hide();
                	             }
                	        },
                	        function(errorLista) {
                	        	  $("#results_UR").hide();
                	        	  $("#noMatches_UR").show();
                	        }
            );       
        }else{
        	$("#results_UR").hide();
        	$("#noMatches_UR").hide();
        }
    });
	/****************************************** FIN SELECCION DE UNIDAD RECAUDADORA ********************************************/
	
	/* conceptosPago.html*/
    $scope.$watchGroup(['concepto.concepto','concepto.descr'], function(newValues, oldValues, scope) {
        
    	$scope.concepto.concepto = $filter('uppercase')(newValues[0]); //MAYUSCULAS
    	$scope.concepto.descr = $filter('uppercase')(newValues[1]);
    }, true);
    
    
    $scope.initView = function(){
    	if($scope.user_codPerfil=="CXC_ADMIN_CAJAS" ||$scope.user_codPerfil=="CXC_FACULTAD" || $scope.user_codPerfil=="CXC_CAJA_RECAUD" || $scope.user_codPerfil=="CXC_SOLIC_CONCEP"){
    		var promise = dependenciaService.dependenciasNombrePadre($scope.user_codDependencia);
	        promise.then(
	        	          function(respLista) { 
	        	        	  var primeraLetra=$scope.user_codDependencia.substring(0,1);
	      					  var cantLetras= $scope.user_codDependencia.length;
	        	        	  var nodoPadre= respLista.data;        	        	  
						      
	        	        	  if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //C,D,E,F,G,H,Z
					    		  $scope.view.nombrePadre = $scope.user_descDependencia;
					    		  $("#boxEncabezado").hide();
					    	  }
	        	        	  else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //C,D,E,F,G,H,Z
					    		  $scope.view.nombrePadre = $scope.user_descDependencia;
					    		  $("#boxEncabezado").hide();
					    	  }else{								 
					    		  $("#txtNombreDependencia").text($scope.user_descDependencia);
					    		  //$scope.view.nombreDependencia = $scope.user_descDependencia;
					    		  $scope.view.nombrePadre = nodoPadre.namePadre;
					    		  $("#boxEncabezado").show();
					    	  } 	 
	        	          },
	        	          function(errorLista) {
	        	        	  //console.error('Error while fetching Currencies : ' + errorLista);
	        	          }
	        );
        	$scope.dependenciaElegida = $scope.user_codDependencia;
        	
        	if ($scope.viewMode == 1){ //modo mantenimiento
        		$scope.view.nombreDependencia = '';
        		$scope.view.udId = null;
        		$scope.view.numUnidad = '';
        		$scope.view.estado_sol_codUnidad = null;
            } 
            else { //modo consulta
            	$scope.view.nombreDependencia = $scope.user_descDependencia;
            	$scope.getConceptosPago();            	
            	$("#panelResult").show();
            } 
        	
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
    var estadoSolcodUnidad;
    
    $scope.openModalUnidad = function(){
		var perfil=$("#idPerfil").text();
		var dependencia=$("#idCodDependencia").text();
		
		$scope.estadoModalDependencia = 0; // estado inicial( No ha elegido nada aun )
		
		var promise;
		
		if ($scope.viewMode == 1){ // mantenimiento
			if($scope.administracionCentral){
	        	promise = dependenciaService.dependenciasPorUsuarioYPerfilXTeso();
	        }else{
	        	promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
	        }
		}else{ // consulta
			promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
		}
			
        promise.then(
        	          function(respLista) { 
        	        	  $scope.dependencia = respLista.data;
        	        	  $("#modalDependencia").modal({backdrop: 'static', keyboard: false});
        	          },
        	          function(errorLista) {
        	          }
        );
	};
	
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
	
		var promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
        promise.then(
        	          function(respLista) { 
        	        	  $scope.dependencia2 = respLista.data;
        	        	  $("#modalDependencia2").modal({backdrop: 'static', keyboard: false});
        	          },
        	          function(errorLista) {
        	          }
        );
	};
    
    $scope.expandirContraer=function(event, desc_unidad){
        var textElementoClickeado=event.currentTarget.text;
        nombreCompletoNodoSeleccionado=desc_unidad;
       
        if(elementoClickeado){
       	 angular.element(elementoClickeado).css({"background-color":""});
        }
        
        elementoClickeado=event.currentTarget; 
        angular.element(elementoClickeado).css({"background-color":"#81F7F3"});
        
        
        if(textElementoClickeado=="UNMSM"){
       	 	$scope.primerNivel=!$scope.primerNivel;
        }else{ 
	       	 var indice=textElementoClickeado.indexOf("-");  	 
	       	 $scope.cod_dep=textElementoClickeado.substring(0,indice);
	       	 codigoNodoSeleccionado=textElementoClickeado.substring(0,indice);
	       	 $scope[$scope.cod_dep]=!$scope[$scope.cod_dep];	  
        }
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
	 
	 $scope.expandirContraer3=function(event, idUnidad, desc_unidad, numUnidad, estadoSol){
	        var textElementoClickeado=event.currentTarget.text;
	        nombreCompletoNodoSeleccionado=desc_unidad;
	       
	        if(elementoClickeado2){
	       	 angular.element(elementoClickeado2).css({"background-color":""});
	        }
	        
	        elementoClickeado2=event.currentTarget; 
	        angular.element(elementoClickeado2).css({"background-color":"#81F7F3"});
	        
	        udIdNodoSeleccionado = null;
	        numero_escuelaUnidad = '';
	        estadoSolcodUnidad=null;
	         
	        if(textElementoClickeado=="UNMSM"){
	       	 	$scope.primerNivel=!$scope.primerNivel;
	        }else{ 
		       	 var indice=textElementoClickeado.indexOf("-");  	 
		       	 $scope.cod_dep=textElementoClickeado.substring(0,indice); //F06...
		       	 codigoNodoSeleccionado=textElementoClickeado.substring(0,indice);
		       	 $scope[$scope.cod_dep]=!$scope[$scope.cod_dep];
		       	 udIdNodoSeleccionado=idUnidad;
		       	 numero_escuelaUnidad = numUnidad;
		         estadoSolcodUnidad = estadoSol;
		         
	        }
	 };
	 	 
	 $scope.aceptarDependencia=function(){ 
		var nodoPadre;
		
		if(nombreCompletoNodoSeleccionado == null || nombreCompletoNodoSeleccionado == ""){
			 $scope.estadoModalDependencia = -3; // estado errado: No ha seleccionado nada aun
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
			        	        	  nodoPadre= respLista.data;

			        	        	  if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //C,D,E,F,G,H,Z y nivel 1
							    		  $scope.view.nombrePadre = nombreCompletoNodoSeleccionado;
							    		  $("#boxEncabezado").hide();
							    	  }
			        	        	  else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //Facultad nivel 2
							    		  $scope.view.nombrePadre = nombreCompletoNodoSeleccionado;
							    		  $scope.view.nombreDependencia = nombreCompletoNodoSeleccionado;
							    		  $("#boxEncabezado").hide();
							    	  }else{								 
							    		  $("#txtNombreDependencia").text(nombreCompletoNodoSeleccionado);
							    		  $scope.view.nombreDependencia = nombreCompletoNodoSeleccionado;
							    		  $scope.view.nombrePadre = nodoPadre.namePadre;
							    		  $("#boxEncabezado").show();
							    	  }
										 
								      $scope.estadoModalDependencia = 1; // estado correcto: recibido
								      
								      $scope.view.udId = udIdNodoSeleccionado;
								      $scope.view.estado_sol_codUnidad = estadoSolcodUnidad;
								      
								      if(estadoSolcodUnidad==null){
								    	  $scope.view.numUnidad = '';
								      }else{
								    	  $scope.view.numUnidad = numero_escuelaUnidad;
								      }
								      $("#modalDependencia").modal("hide");
								      $("#panelResult").show();
			        	          },
			        	          function(errorLista) {
			        	        	  $scope.estadoModalDependencia = 2; // estado errado: No hay conexion
			        	          }
			        );

					 $scope.dependenciaElegida = codigoNodoSeleccionado;		 
					 $scope.listarConceptosPagoXUnaDependencia(codigoNodoSeleccionado);
			 }
		 }
		
		 mensajesModalDependencia();
		 $scope.estadoModalDependencia = 0;
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
									    		  $scope.newcp.nameUnidad = nombreCompletoNodoSeleccionado;
									    		  $scope.newcp.numUnidad = numero_escuelaUnidad;
									    		  $scope.newcp.namePadre = '';
									    	}
											else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //Facultad y nivel 2
									    		  $scope.newcp.nameUnidad = nombreCompletoNodoSeleccionado;
									    		  $scope.newcp.numUnidad = numero_escuelaUnidad;
									    		  $scope.newcp.namePadre = '';
									    	}else{								 
									    		$scope.newcp.nameUnidad = nombreCompletoNodoSeleccionado;
									    		$scope.newcp.numUnidad = numero_escuelaUnidad;
									    		$scope.newcp.namePadre = nodoPadre.namePadre;
									    	}
											
											$scope.concepto.udId = udIdNodoSeleccionado;
											$scope.concepto.udCod = codigoNodoSeleccionado;
											$scope.itemEditar.udId = udIdNodoSeleccionado;
											$scope.itemEditar.udCod = codigoNodoSeleccionado;
											
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
	 
	 $("#btnReporte2").click(function () {		 
		if($scope.conPagos==null || $scope.conPagos==""){
		 	$("#noMatches2").show();
		 	$("#tableReporteCP").hide();
	    }else{
	    	$("#noMatches2").hide();
    		$("#tableReporteCP").show();	    	
	    }
	    
	    $("#reporteConceptosPago").modal({backdrop: 'static', keyboard: false});
		
	 });
	 
	 $("#printRepCP").click(function () {
		  window.print();
	 });
	 
}]);