app.controller("regventasGeneral_controller",function($scope,$http){

    var hoy= new Date(); 
    var mes=hoy.getMonth()+1;
    var anio=hoy.getFullYear();
    var cod_dep=$('#userIdCodDepPadre').text();
    var usuario=$('#userNameDep').text();
    $scope.cod_dep=cod_dep;
    $scope.usuario=usuario;
	$scope.coin=38;
    $scope.coins=[];
    $scope.month=0;
    $scope.months = [];
    $scope.years = [];
    $scope.year=anio;
    $scope.fechaComienzo="";
    $scope.fechaFinal="";
    $scope.facultad=null;
    $scope.facultades = [];
    
    var flag_fec_ini=false;
    var flag_fec_fin=false;
    var fecha1;
    var fecha2;
    
	$http.get("rest/ComprobantePagoController/listarEstablecimientoBoletaYFactura").success(function(data){
 		 var facultad={'id':10000,'name' : "UNMSM" };
  		 $scope.facultades.push(facultad);
  		 $scope.facultad= 10000;
		   	for(var i=0;i<data.length;i++){
		   		var facultad={'id': data[i].udId,'name' : data[i].unidad };
		   		$scope.facultades.push(facultad);
		   	}
 	});   
	
    $scope.sel_fec_ini=function()
    {
        if($scope.fechaComienzo!=null || $scope.fechaComienzo!=""){
        	flag_fec_ini=true;
        }
        
        if(flag_fec_ini && flag_fec_fin){
        	var fechaComienzo=$scope.fechaComienzo.split('/');
        	var fechaFinal=$scope.fechaFinal.split('/');
        	fecha1=new Date(fechaComienzo[2],fechaComienzo[1],fechaComienzo[0]);
        	fecha2=new Date(fechaFinal[2],fechaFinal[1],fechaFinal[0]);
        	if(fecha1<fecha2){
        		$scope.flag_boton=true;
        	}else{
        		$scope.flag_boton=false;
        	}
        }
        $scope.actualizarAccionxI();
    }
    
    $scope.sel_fec_fin=function(){
    	if($scope.fechaFinal!=null || $scope.fechaFinal!=""){
        	flag_fec_fin=true;
        }
    	if(flag_fec_ini && flag_fec_fin){
        	var fechaComienzo=$scope.fechaComienzo.split('/');
        	var fechaFinal=$scope.fechaFinal.split('/');
        	fecha1=new Date(fechaComienzo[2],fechaComienzo[1],fechaComienzo[0]);
        	fecha2=new Date(fechaFinal[2],fechaFinal[1],fechaFinal[0]);
        	if(fecha1<fecha2){
        		$scope.flag_boton=true;
        	}else{
        		$scope.flag_boton=false;
        	}
        }
    	$scope.actualizarAccionxI();
    }
    
    function llenarAnios(){	 
	   	for(var i=0;i<=anio-2006;i++){
	   		var year={'id' :anio-i,'name' : anio-i };
	   		$scope.years.push(year);
	   	}
   }
    
    $scope.actualizarAccion=function(){
    	if($scope.year!=hoy.getFullYear()){
    		$scope.months.length=0;
    		mes=12;
    		llenarMeses();
    	}else if($scope.year==hoy.getFullYear()){
    		mes=hoy.getMonth()+1;
    		$scope.months.length=0;
    		llenarMeses();
    		if($scope.month==null){
    			$scope.month=0;
    		}
    	}
    	$scope.accionD="rest/RegistroVenta/generarReporteRegistroVentasGeneralXMes/"+usuario+"/2/"+$scope.facultad+"/"+$scope.year+"/"+$scope.month;
    };
    $scope.actualizarAccionxI=function(){
    	$scope.fec_ini=($scope.fechaComienzo).split('/').join('-');
    	$scope.fec_fin=($scope.fechaFinal).split('/').join('-');
    	$scope.accionA="rest/RegistroVenta/generarReporteRegistroVentasXIntervalo/"+usuario+"/1/"+cod_dep+"/"+$scope.fec_ini+"/"+$scope.fec_fin+"/"+$scope.coin;
    	$scope.accionB="rest/RegistroVenta/generarReporteRegistroVentasXIntervalo/"+usuario+"/2/"+cod_dep+"/"+$scope.fec_ini+"/"+$scope.fec_fin+"/"+$scope.coin;
    };
    $scope.mostrarModalMes=function(){
    	$("#porMes").show();
    	$("#porIntervalo").hide();
    }
    
    $scope.mostrarModalIntervalo=function(){
    	$("#porIntervalo").show();
    	$("#porMes").hide();
    }
    
    function llenarMeses(){	 
    	for(var i=0;i<=mes;i++){
	    	switch(i){
	    	case 0: var month={'id' :0,'name' : 'Seleccione'};
	    			$scope.months.push(month);
	    			break;
	    	case 1: var month1={'id' :1,'name' : 'Enero'};
	    			$scope.months.push(month1);
	    			break;
	    	case 2: var month2={'id' :2,'name' : 'Febrero'};
					$scope.months.push(month2);
					break;
	    	case 3: var month3={'id' :3,'name' : 'Marzo'};
					$scope.months.push(month3);
					break;
	    	case 4: var month4={'id' :4,'name' : 'Abril'};
					$scope.months.push(month4);
					break;    
	    	case 5: var month5={'id' :5,'name' : 'Mayo'};
					$scope.months.push(month5);
					break; 
	    	case 6: var month6={'id' :6,'name' : 'Junio'};
					$scope.months.push(month6);
					break; 
	    	case 7: var month7={'id' :7,'name' : 'Julio'};
					$scope.months.push(month7);
					break; 
	    	case 8: var month8={'id' :8,'name' : 'Agosto'};
					$scope.months.push(month8);
					break; 
	    	case 9: var month9={'id' :9,'name' : 'Setiembre'};
					$scope.months.push(month9);
					break; 
	    	case 10: var month10={'id' :10,'name' : 'Octubre'};
					$scope.months.push(month10);
					break; 
	    	case 11: var month11={'id' :11,'name' : 'Noviembre'};
					$scope.months.push(month11);
					break; 
	    	case 12: var month12={'id' :12,'name' : 'Diciembre'};
					$scope.months.push(month12);
					break;
			default: break;		
	    	}	
    	}
    }
    llenarAnios();
    llenarMeses();
    $scope.estado="false"
    $scope.generarReporte=function(){
    	$('#formId').attr('method', 'POST');
    	$('#formId').attr('action', $scope.accion);
    };  
});



