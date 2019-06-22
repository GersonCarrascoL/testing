app.controller('mantenimientoTicket_controller',['$scope','$filter','$http','$q','mantenimientoTicketService','ngTableParams',function($scope,$filter,$http,$q,mantenimientoTicketService,ngTableParams){
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
	$scope.modalOptions = {
            headerColorValue: 0,  // 0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
            closeButtonText: 'No',
            actionButtonText: 'Si',
            headerText: 'Confirmar',
            bodyText: 'Realizar ésta acción?',
            data: {},
            action: function(){}              
    };
	$scope.user_codDependencia=parseInt($("#idDependencia").text());	
	var udId=$scope.user_codDependencia;
	$scope.fechaInicial=$filter('date')(new Date(),'dd/MM/yyyy');
	$scope.fechaFinal=$filter('date')(new Date(),'dd/MM/yyyy');
	
	console.log(udId);
	$scope.listaTickets;
	$scope.consultar=function(){
		var promise=mantenimientoTicketService.getComprobantesTicket(udId,$scope.fechaInicial,$scope.fechaFinal);
		promise.then(
			function(respLista){
				$scope.listaTickets=respLista.data;
				console.log(respLista.data);
				$scope.VisibleResultTable=(respLista.data.length==0)?false:true;
            	$scope.tableParams = new ngTableParams({filter:{enviar:""}}, { dataset: respLista.data});
			},
			function(errorLista){
				
			}
		);
	};
	$scope.consultar();
	$scope.detallar=function(c){
    	$scope.abrirModal("#detalle");
    	$scope.detalle=c;
    	$scope.detalle.fechaEmisionP=$scope.detalle.fechaEmision.substr(0,$scope.detalle.fechaEmision.length-2);
    };
    $scope.abrirModal=function(opc){
    	$scope.$broadcast("limpiarTablaRUC", {}); 
    	$("#buttonAceptarCliente").prop('disabled', true);
    	$(opc).modal("show");
    };
    $scope.verPDF=function(establecimiento,correlativo) {  
    	window.open("rest/ReporteController/generarPDF/"+establecimiento+"/"+correlativo+"/", "", "width=400,height=600");                            
    };
    $scope.anularTicket=function(codEstab,correlativo) { 
    	var promise = mantenimientoTicketService.anularTicket(codEstab,correlativo);
        promise.then(
                           function(respLista) {
                        	 mensaje('Anular',"Su Ticket ha sido anulado correctamente",1);
                        	 $scope.consultar();  
                           },
                           function(errorLista){
                        	   console.log("errorLista")
                        	   console.log(errorLista);
                        	   
                           }
        );
	}

    $scope.abrirModalConfirm_anularTicket = function(codEstab,correlativo){
    	$scope.tempCodEstab=codEstab;
    	$scope.tempCorrelativo=correlativo;
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        
        $scope.modalOptions.headerText = 'Anular';
    	$scope.modalOptions.bodyText = 'Está a punto de anular el Ticket. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.anularTicket;

        $('#modalConfirm').modal({show: true});
    };
}]);