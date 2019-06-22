
app.controller('solicitudes_unidad_recaudadora_controller', ['$scope', 'ngTableParams', 'dependenciaService', function($scope, ngTableParams, dependenciaService) {
	
	$scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
	$scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, etc
	$scope.user_codDependencia=$("#idCodDependencia").text(); //F0608, etc
	$scope.user_descDependencia=$("#descDependencia").text();

	$scope.view = {
			nombrePadre: '',
			nombreDependencia: ''
	};
	
	$scope.arbol={
			letrasRaiz : {C:5, D:5, E:5, F:5, G:5, H:5, Z:5},
			cantCarNv1: 3,
			cantCarNv2: 5
	};
	
	$scope.tipoSolicitud = [{id: "", title: "Todos"}, {id: '0', title: 'Pendientes'}, {id: '1', title: 'Aprobados'}, {id: '2', title: 'Rechazados'}];
	
	$scope.meses= [{idMes: '', dscMes: 'Todos'},{idMes: '01', dscMes: 'Enero'},{idMes: '02', dscMes: 'Febrero'},{idMes: '03', dscMes: 'Marzo'},{idMes: '04', dscMes: 'Abril'},{idMes: '05', dscMes: 'Mayo'},{idMes: '06', dscMes: 'Junio'},
				   {idMes: '07', dscMes: 'Julio'},{idMes: '08', dscMes: 'Agosto'},{idMes: '09', dscMes: 'Septiembre'},{idMes: '10', dscMes: 'Octubre'},{idMes: '11', dscMes: 'Noviembre'},{idMes: '12', dscMes: 'Diciembre'}];
	
	var hoy= new Date(); 
    var anio=hoy.getFullYear();
    $scope.years = [{'id':'','name': 'Todos' }];
    for(var i=anio;i>=1999;i--){
		var year={'id' :i,'name' : i };
		$scope.years.push(year);
	}
	
	$scope.VisibleResultTable = false;
	
	$scope.unidRecauds = [];
    
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

    $scope.modalOptions = {
                headerColorValue: 0,  //0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
                closeButtonText: 'No',
                actionButtonText: 'Si',
                headerText: 'Confirmar',
                bodyText: 'Realizar ésta acción?',
                data: {
                    motivoRechazo: ''
                },
                action: function(){}                
        };
    
    
    $scope.facultadesPadre= [{ udId: '', udDsc: 'Todos'}];
	$scope.datosFacultadesPadre = function(){
		var promise = dependenciaService.listarFacultadesYUnidadesPadre();
		promise.then(
      					   function(respLista) {
      						 if(respLista.data!=null && respLista.data!=""){
      							 $scope.facultadesPadre= $scope.facultadesPadre.concat(respLista.data);
      						 }
      					   },
            			   function(errorLista){
            			   }
      			   );
    };
    $scope.datosFacultadesPadre();
    
    
    function mensajesModalAprobarUnidad(){
		switch ($scope.estadoModalSolicitud){
	    	case 1: 
	    		$.gritter.add({
					title: 'Aprobar unidad recaudadora',
					text: 'La unidad recaudadora ha sido aprobada correctamente. ',
					image: 'resources/assets/img/notification/succes.png',
					sticky: false,
					time: ''
				});break;
	    	case 2: 
	    		$.gritter.add({
					title: 'Aprobar Unidad recaudadora',
					text: 'Hubo un error al aprobar la unidad recaudadora ',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;
	    	default: break;
		}	
	}
	
	function mensajesModalRechazarUnidad(){
		switch ($scope.estadoModalSolicitud){
	    	case 1: 
	    		$.gritter.add({
					title: 'Rechazar unidad recaudadora',
					text: 'La unidad recaudadora ha sido rechazada correctamente. ',
					image: 'resources/assets/img/notification/desaprobar.png',
					sticky: false,
					time: ''
				});break;
	    	case 2: 
	    		$.gritter.add({
					title: 'Rechazar unidad recaudadora',
					text: 'Hubo un error al rechazar la unidad recaudadora ',
					image: 'resources/assets/img/notification/error.png',
					sticky: false,
					time: ''
				}); break;
	    	default: break;
		}	
	}
	
 
    function mostrar(data){
    	$scope.unidRecauds = data;
    	if($scope.unidRecauds==null || $scope.unidRecauds==""){
    		$scope.VisibleResultTable=false;
    	}else{    	    		
    		$scope.tableParams = new ngTableParams({ count:5, group:{ udDscFac:"asc"}, filter: {estSolicitudUnidad:"0",udIdPadre:'',regYear:'',regMonth:''} }, { dataset: $scope.unidRecauds, counts:[]}); //group: "udDesc", groupOptions:{isExpanded:false} 
    		$scope.VisibleResultTable=true;
    	}
    }
    
    $scope.listarSolicitudes_unidRecaud = function(){
    	var promise = dependenciaService.listarUnidadesCodigoTodos();
        promise.then(
        	          function(respLista) { 
        	        	  mostrar(respLista.data);
        	          },
        	          function(errorLista) {
        	          }
         );
    };
    
    $scope.getSolicitudes = function(){
    	$scope.listarSolicitudes_unidRecaud();
    };
    
    
    $scope.openModalDetalleUnidad = function(item){
    	$scope.cpDetalle = angular.copy(item);
    	$("#viewUnidadRec").modal({backdrop: 'static', keyboard: false});
    };
    
    
    $scope.abrirModalConfirm_aprobarUnidad = function(udId){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Aprobar';
        $scope.modalOptions.bodyText= '¿Está seguro de Aprobar ésta undidad recaudadora?';

        $scope.modalOptions.data.udId = udId;

        $scope.modalOptions.action = $scope.aprobarUnidad;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.aprobarUnidad = function(){
        var promise = dependenciaService.aprobarUnidadCod($scope.modalOptions.data.udId);
            promise.then(
                          function(respItem) {
                              $scope.estadoModalSolicitud = 1;
                              $("#viewUnidadRec").modal("hide");
                              $scope.getSolicitudes();
                              mensajesModalAprobarUnidad();
                          },
                          function(errorItem) {
                              $scope.estadoModalSolicitud = 2;
                              mensajesModalAprobarUnidad();
                          }
            );
    };

    $scope.abrirModalMotivoRechazo = function(udId){
        $scope.modalOptions.headerColorValue= 3;
        $scope.modalOptions.closeButtonText= 'Cancelar';
        $scope.modalOptions.actionButtonText= 'Aceptar';
        $scope.modalOptions.headerText= 'Rechazar';
        $scope.modalOptions.bodyText= 'Ingrese un comentario acerca del motivo:';

        $scope.modalOptions.data.udId = udId;
        $scope.modalOptions.data.motivoRechazo = '';

        $scope.modalOptions.action = $scope.rechazarUnidad;

        $('#modalMotivoRechazo').modal({show: true});

    };
    
    $scope.rechazarUnidad = function(){

        var promise = dependenciaService.rechazarUnidadCod($scope.modalOptions.data.udId, $scope.modalOptions.data.motivoRechazo);
            promise.then(
                          function(respItem) {
                              $scope.estadoModalSolicitud = 1;                            
                              $scope.getSolicitudes();
                              $("#viewUnidadRec").modal("hide");
                              mensajesModalRechazarUnidad();
                          },
                          function(errorItem) {
                        	  $scope.estadoModalSolicitud = 2;
                              mensajesModalRechazarUnidad();
                          }
            );		
    };
    
    $scope.initView = function(){
    	$scope.getSolicitudes();
    	$("#panelResult").show();
	};
	
	$scope.initView();


}]);