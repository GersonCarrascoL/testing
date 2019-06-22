app.controller('session_controller', function($scope,$http,$timeout,comprobantesPagoService) {
    $('.option').click(function(){
        $('.option').removeAttr('style');
        $(this).css('color', '#fff');
    });
    $scope.comprobantesSinEnviar=0;
    $scope.notificaciones=null;
    $scope.tipoCambioCompra=0;
    $scope.tipoCambioVenta=0;
     var ud_id=$("#idDependencia").text();
	 $http.post('rest/SistemaController/getCabecera',ud_id)
	 .success(function(data) { 
		 if(data!=null){
			 $scope.notificaciones=data.notificacion;
			 for(var i=0;i<data.length;i++){
				 $scope.comprobantesSinEnviar=$scope.comprobantesSinEnviar+data[i].total;
			 }
		       $scope.tipoCambioCompra=data.tipoCambio[0].factorTccompra;
		       $scope.tipoCambioVenta=data.tipoCambio[0].factorTcventa;
		 }
	  }); 
    $scope.counter = $("#maxInactiveInterval").text();
    $scope.stopped = false;
    $scope.buttonText='Stop';
    $scope.onTimeout = function(){
        if($scope.counter==10){
            $('#logout_popup').modal('show');
        }
        if($scope.counter==1){
            window.location.assign("j_spring_security_logout");
        }
        $scope.counter--;
        mytimeout = $timeout($scope.onTimeout,1000);
    }
    var mytimeout = $timeout($scope.onTimeout,1000);
    $scope.takeAction = function(){
        if(!$scope.stopped){
            $timeout.cancel(mytimeout);
            $scope.buttonText='Resume';
        }
        else
        {
            mytimeout = $timeout($scope.onTimeout,1000);
            $scope.buttonText='Stop';
        }
        $scope.stopped=!$scope.stopped;
    }
    $scope.resetTimer=function() {
        $('#logout_popup').modal('toggle');
        $scope.counter = $("#maxInactiveInterval").text();
    }
    $scope.hrefComprobante=function(id_Cpago) {
    	switch (id_Cpago) {
		case 1: return "#mantenimientoBoleta";
		case 2: return "#mantenimientoFactura";
		case 4: return "#mantenimientoNotaCreditoFactura";
		case 5: return "#mantenimientoNotaCreditoBoleta";
		default:
			return "#mantenimientoBoleta";
	}
    }
	$scope.abrirPanel=function(){
		$("#panelAyuda").modal({show: 'true'});
		console.log("aqui");
	}
});