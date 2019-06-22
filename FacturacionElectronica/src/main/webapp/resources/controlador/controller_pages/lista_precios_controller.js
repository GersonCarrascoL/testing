app.controller('lista_precios_controller', ['$scope', '$filter', 'ngTableParams', 'listaPreciosService', 'dependenciaService', 'monedaService', 'listapHistorialService', 'unidadMedidaService', 'catCodProductoService', 'categoriasBienesService', 'conceptosPagoService', 'tipoResolucion', function($scope, $filter, ngTableParams, listaPreciosService, dependenciaService, monedaService, listapHistorialService,unidadMedidaService,catCodProductoService,categoriasBienesService,conceptosPagoService,tipoResolucion) {

    $scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
    $scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, etc
    $scope.user_codDependencia=$("#idCodDependencia").text(); //F0608, etc
    $scope.user_descDependencia=$("#descDependencia").text();
    $scope.user_username = $("#userNameDep").text(); //nombre de usuario
    $scope.user_idCodDependenciaPadre=$("#userIdCodDepPadre").text();
    $scope.user_idUnidadAdministrativa=$("#idUnidadAdministrativa").text();
    $scope.user_descUnidadAdministrativa=$("#dscUnidadAdministrativa").text();

    $scope.administracionCentral = false;
    $scope.actionReporteBienes="rest/ReporteController/generarReporteBienesFacu/"+$scope.user_username+"/"+$scope.user_codDependencia+"/";
    if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
        $scope.administracionCentral = true;
        $scope.actionReporteBienes="rest/ReporteController/generarReporteBienesTeso/"+$scope.user_username+"/"+$scope.user_idUnidadAdministrativa+"/";
    }
    
    $scope.view = {
            nombrePadre: '',
            nombreDependencia: ''
    };
    
    $scope.viewEstado = {
            codigo: '',
            descr: ''           
    };

    $scope.viewItem = {
            codigo: '',
            descr: '',
            precioTemp: 0,
            codTipoperacionIgvTemp: 30
    };
    
    $scope.viewCategoria = {
            nombre: '',
            actionReleased: false
    };
    
    $scope.viewResol = {
    		//Si hay nuevas resoluciones sólo agregar a esta lista, no es necesario modificar otra cosa
    		tiposResolucion: [ {strNameResol: 'RECTORAL', lblResol: 'Resolución Rectoral', patternResol: /^N° ([0-9]){5}-R-([0-9]){2}$/, inputMaskResol: "N° 99999-R-99" },
    		                   {strNameResol: 'DECANAL', lblResol: 'Resolución Decanal', patternResol: /^N° ([0-9]){3,5}-D-[A-Z]{2,}(-[A-Z]{1,})*-([0-9]){4}$/, inputMaskResol: {mask:"N° 9{3,5}-D-A{2,15}-[A{1,15}-][A{1,15}-][A{1,15}-][A{1,15}-]9999",greedy: false} },
    		                   {strNameResol: 'DIRECTORAL', lblResol: 'Resolución Directoral', patternResol: /^N° ([0-9]){3,5}-[A-Z]{2,}(-[A-Z]{1,})*-([0-9]){4}$/, inputMaskResol: {mask:"N° 9{3,5}-A{2,15}-[A{1,15}-][A{1,15}-][A{1,15}-][A{1,15}-]9999",greedy: false} }
    		                   
    		                   
    		],
            numResolucionTemp:'',
            lblResolSelected: '',
            plcHolderResolSelected: '',
            resolSelected: {}
    };
    
    $scope.getResolSelected = function(strTipoResol){
    	var strTResol = strTipoResol || '';
    	strTResol = strTResol.trim().toUpperCase();
    	var list = $scope.listaTiposResolucion;
    	var i;
    	for (i in list){
    		if(list[i].nameTipoResol == strTResol) return list[i];
    	}
    	return null;
    };
    
    $scope.getLabelTipoResolucion = function (strTipoResol){
    	var strTResol = strTipoResol || '';
    	strTResol = strTResol.trim().toUpperCase();
    	var list = $scope.viewResol.tiposResolucion;
    	var i;
    	for (i in list){
    		if(list[i].strNameResol == strTResol) return list[i].lblResol;
    	}
    	return list[0].lblResol;
    };
    
    $scope.getPlcHolderResolucion = function (strTipoResol){
    	var strTResol = strTipoResol || '';
    	strTResol = strTResol.trim().toUpperCase();
    	var list = $scope.viewResol.tiposResolucion;
    	var i;
    	for (i in list){
    		if(list[i].strNameResol == strTResol) return list[i].strNameResol.toLowerCase();
    	}
    	return list[0].strNameResol.toLowerCase();
    };
    
    $scope.getInputMaskResol = function(strTipoResol){
    	var strTResol = strTipoResol || '';
    	strTResol = strTResol.trim().toUpperCase();
    	var list = $scope.viewResol.tiposResolucion;
    	var i;
    	for (i in list){
    		if(list[i].strNameResol == strTResol) return list[i].inputMaskResol;
    	}
    	return list[0].inputMaskResol;
    };
    
    $scope.getPatternResol = function (strTipoResol){
    	var strTResol = strTipoResol || '';
    	strTResol = strTResol.trim().toUpperCase();
    	var list = $scope.viewResol.tiposResolucion;
    	var i;
    	for (i in list){
    		if(list[i].strNameResol == strTResol) return list[i].patternResol;
    	}
    	return list[0].patternResol;
    };
    
    $scope.handleResolPatternEntry = (function() {
        var regex = '';
        var patt = '';        
        return {
          test: function(value) {
        	patt = $scope.viewResol.resolSelected.nameTipoResol || '';
            regex = new RegExp($scope.getPatternResol(patt));
            return regex.test(value);
          }
        };
    })();
    
    $scope.loadingDatos = false;
    
    $scope.historial = {
            unidadName: '',
            itemName: '',
            codItem: ''
    };
    
    $scope.arbol={
            letrasRaiz : {C:5, D:5, E:5, F:5, G:5, H:5, Z:5, M:5, N:5},
            cantCarNv1: 3,
            cantCarNv2: 5
    };
    
    $scope.VisibleResultTable = false;
    $scope.noDataTable = false;
    $scope.newlp = {nameUnidad:'', namePadre:''};

    $scope.item = {
            udId: null,
            anio: 2016,
            codigo: '',
            descripcion: '',
            precio: null,
            estado: 1,
            idMoneda: null,
            udCod: '',
            udDesc: '',
            monedaDesc: '',
            monedaSimb: '',
            usuarioReg: '',
            usuarioModif: '',
            nUnidadMedida: '',
            codigoCatalogo: '',
            codTipoperacionIgv: 10
    };
    $scope.itemEditar= {};
        
    $scope.listaPrecios=[];
    
    $scope.itemCategoria = {
    		idBienCata: null,
    		udIdCategoria: null,
    		descCategoriaBien: ''
    };

    $scope.modalOptions = {
                headerColorValue: 0,  //0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
                closeButtonText: 'No',
                actionButtonText: 'Si',
                headerText: 'Confirmar',
                bodyText: 'Realizar ésta acción?',
                data: {},
                action: function(){}                
    };
    
    // get MONEDAS
    $scope.datosMonedas = function(){
            var promise = monedaService.listarMonedasActivas();
            promise.then(
                               function(respLista) {                               
                                 $scope.listaMonedas = respLista.data;                                 
                                 $scope.item.idMoneda = respLista.data[0].idMoneda;
                               },
                               function(errorLista){
                               }
                       );
    };  
    $scope.datosMonedas();
    
    // get CATALOGO CODIGO PRODUCTOS
    $scope.datosCatCodProductos = function(){
            var promise = catCodProductoService.listarCatCodProductos();
            promise.then(
                               function(respLista) {
                                 $scope.listaCatCodProd = respLista.data;
                                 $scope.item.codigoCatalogo = respLista.data[0].codigo;
                               },
                               function(errorLista){
                               }
                       );
    };  
    $scope.datosCatCodProductos();
    
    // get UNIDADES DE MEDIDA
    $scope.datosUnidadesMedida = function(){
            var promise = unidadMedidaService.listarUnidadesMedida();
            promise.then(
                               function(respLista) {
                                 $scope.listaUnidMedida = respLista.data;
                                 $scope.item.nUnidadMedida = respLista.data[0].unimedcod;
                               },
                               function(errorLista){
                               }
                       );
    };  
    $scope.datosUnidadesMedida();
    
    // get CATEGORIAS BIENES POR UNIDAD MAESTRA
    $scope.listCatBienSelect = [{ idBienCata: '', udIdCategoria: '', descCategoriaBien: 'Todos', filterCategoriaBien: ''},
                                { idBienCata: '!', udIdCategoria: '', descCategoriaBien: 'Sin Categoria', filterCategoriaBien: '!'}
                               ];
    $scope.listaCategoriasBien = [];
    $scope.datosCategoriasBienes = function(){
        var promise;
        if($scope.administracionCentral){
            promise = categoriasBienesService.listarCategoriasXUnidadBase($scope.user_idUnidadAdministrativa);
        }else{
            promise = categoriasBienesService.listarCategoriasXUnidadBase($scope.user_idCodDependenciaPadre);
        }
        
        promise.then(
                           function(respLista) {
                             $scope.listaCategoriasBien = respLista.data;
                             $scope.listCatBienSelect= $scope.listCatBienSelect.concat(respLista.data);
                           },
                           function(errorLista){
                           }
                   );
    };  
    $scope.datosCategoriasBienes();
    
    // get TIPOS RESOLUCIONES
    $scope.datosTiposResolucion = function(){
            var promise = tipoResolucion.listarTiposResolucion();
            promise.then(
                               function(respLista) {
                                 $scope.listaTiposResolucion = respLista.data;
                                 $scope.viewResol.resolSelected = respLista.data[0];
                               },
                               function(errorLista){
                               }
                       );
    };  
    $scope.datosTiposResolucion();

  
  $scope.bodyPaddingRightZero=function() { 
     $("body").css("padding-right", "0px");
  };
  
    $scope.cambioResolucion_reg = function(){
	  $(".maskResolucion").inputmask('remove');
	  $scope.item.numResolucion = '';
	  
	  var strTipoResol = $scope.viewResol.resolSelected.nameTipoResol || '';
	  strTipoResol = strTipoResol.trim();

	  $scope.viewResol.lblResolSelected = $scope.getLabelTipoResolucion(strTipoResol);
	  $scope.viewResol.plcHolderResolSelected = $scope.getPlcHolderResolucion(strTipoResol);
	  $(".maskResolucion").inputmask($scope.getInputMaskResol(strTipoResol));   
	};
	  
	$scope.cambioResolucion_edit = function(){
		$(".maskResolucion").inputmask('remove');	
		$scope.itemEditar.numResolucion = '';
		
		var strTipoResol = $scope.viewResol.resolSelected.nameTipoResol || '';
		strTipoResol = strTipoResol.trim();
		  
		$scope.viewResol.lblResolSelected = $scope.getLabelTipoResolucion(strTipoResol);
		$scope.viewResol.plcHolderResolSelected = $scope.getPlcHolderResolucion(strTipoResol);
		
		if($scope.itemEditar.nameTipoResol != null && $scope.itemEditar.nameTipoResol.trim() == $scope.viewResol.resolSelected.nameTipoResol){
			$scope.itemEditar.numResolucion = $scope.viewResol.numResolucionTemp;
		}
		$(".maskResolucion").inputmask($scope.getInputMaskResol(strTipoResol));
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
                    title: 'Registrar artículo',
                    text: 'El artículo se ha registrado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case 2: 
                $.gritter.add({
                    title: 'Registrar artículo',
                    text: 'Hubo un error al registrar el artículo ',
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
                    title: 'Actualizar artículo',
                    text: 'El artículo se ha actualizado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case 2: 
                $.gritter.add({
                    title: 'Actualizar artículo',
                    text: 'Hubo un error al actualizar el artículo ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    function mensajesModalNuevaCategoria(){
        switch ($scope.estadoModalNuevaCategoria){
            case 1: 
                $.gritter.add({
                    title: 'Registrar Categoría',
                    text: 'La categoría se ha registrado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -3: 
                $.gritter.add({
                    title: 'Registrar Categoría',
                    text: 'Hubo un error al registrar la categoría ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    function mensajesModalEditarCategoria(){
        switch ($scope.estadoModalEditarCategoria){
            case 1: 
                $.gritter.add({
                    title: 'Actualizar Categoría',
                    text: 'La categoría se ha actualizado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Actualizar Categoría',
                    text: 'Ya existe otra categoría con el mismo nombre: '+ $scope.viewCategoria.nombre,
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -3: 
                $.gritter.add({
                    title: 'Actualizar Categoría',
                    text: 'Hubo un error al actualizar la categoría ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }

    function mensajesModalEliminarCategoria(){
        switch ($scope.estadoModalEliminarCategoria){
            case 1: 
                $.gritter.add({
                    title: 'Eliminar Categoría',
                    text: 'La categoría se ha eliminado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Eliminar Categoría',
                    text: 'No existe ninguna categoría con el nombre: '+ $scope.viewCategoria.nombre,
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -3: 
                $.gritter.add({
                    title: 'Eliminar Categoría',
                    text: 'Hubo un error al eliminar la categoría ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    $scope.orderNullToDown = function(bien) {
        if(bien.descCategoriaBien==null || bien.descCategoriaBien=='null' || bien.descCategoriaBien==''){
        	return 'zzzzzzzzzzzz';
        }         	
        return bien.descCategoriaBien;
    };
    
    function mostrar(data){
        $scope.listaPrecios = data;
        if($scope.listaPrecios==null || $scope.listaPrecios==""){
            $scope.VisibleResultTable=false;
            $scope.noDataTable = true;
        }else{
            $scope.tableParams = new ngTableParams({count:5, group:{ udDesc:"asc"}, filter: {descCategoriaBien:''}}, { dataset: $scope.listaPrecios, counts:[]});
            $scope.VisibleResultTable=true; 
            $scope.noDataTable = false;
        }
    }
     
    $scope.listarPreciosXDependencia = function(codigoDependencia){
        var promise;  
        $scope.VisibleResultTable = false;
        $scope.noDataTable = false;
        $scope.loadingDatos = true;
        
        if ($scope.viewMode == 1){ 
        	promise = listaPreciosService.listarPreciosXDependencia(codigoDependencia); 
        } //modo mantenimiento
        else{ 
        	promise = listaPreciosService.listarItemsXDependenciaHabilitados(codigoDependencia); 
        } //modo consulta
        
        promise.then(
                      function(respLista) { 
                    	  $scope.loadingDatos = false;  
                          mostrar(respLista.data);
                      },
                      function(errorLista) {
                    	  $scope.loadingDatos = false;  
                      }
            );

    };
    
    $scope.listarPreciosXAdmiCentral = function(udIdAdministrativa){
        var promise;
        $scope.VisibleResultTable = false;
        $scope.noDataTable = false;
        $scope.loadingDatos = true;
        
        if ($scope.viewMode == 1){ 
        	promise = listaPreciosService.listarPreciosXAdmiCentral(udIdAdministrativa); 
        } //modo mantenimiento
        else{ 
        	promise = listaPreciosService.listarPreciosXAdmiCentralHabilitados(udIdAdministrativa); 
        } //modo consulta
        
        promise.then(
                      function(respLista) { 
                    	  $scope.loadingDatos = false;  
                    	  
                          mostrar(respLista.data);
                      },
                      function(errorLista) {
                    	  $scope.loadingDatos = false;  
                      }
            );      
    };
               

    $scope.getListaPrecios = function(){
    	if ($scope.administracionCentral){
            $scope.listarPreciosXAdmiCentral($scope.user_idUnidadAdministrativa);        
        }else{
        	$scope.listarPreciosXDependencia($scope.dependenciaElegida);
        }
    };
    
    
    $scope.openModalHistorialPrecios = function(item){
        $scope.historial.unidadName = item.udDesc;
        $scope.historial.itemName = item.descripcion;
        $scope.historial.codItem = item.codigo;     
        
        var promise = listapHistorialService.listarHistorialXitem(item.udId, item.codigo);
        promise.then(
                      function(respLista) { 
                          if(respLista.data==0){
                            $("#resultHistorial").hide();
                            $("#noHistory").show();
                         }else{
                            $scope.tableParamsHistorial = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                            $("#resultHistorial").show();
                            $("#noHistory").hide();
                         }
                      },
                      function(errorLista) {
                          $("#resultHistorial").hide();
                          $("#noHistory").show();
                      }
        );
        $("#modalHistorialPrecios").modal({backdrop: 'static', keyboard: false});
    };
        
    $scope.createItem = function(item){
        var promise = listaPreciosService.createItem(item);
        promise.then(
                  function(respItem) { 
                      $scope.dependenciaElegida = $scope.item.udCod;
                      $scope.estadoModalNuevo = 1;
                      $("#Nuevo").modal("hide");
                      
                      if($scope.newlp.namePadre == '') {
                        $scope.view.nombrePadre = $scope.newlp.nameUnidad; 
                        $("#boxEncabezado").hide();
                      }else {
                        $("#txtNombreDependencia").text($scope.newlp.nameUnidad);
                        $scope.view.nombrePadre = $scope.newlp.namePadre;
                        $("#boxEncabezado").show();
                      }

                      $scope.getListaPrecios();
                      $("#panelResult").show();
                      mensajesModalNuevo();
                  },
    
                  function(errorItem) {
                      $scope.estadoModalNuevo = 2;
                      mensajesModalNuevo();
                  }
        );

    };
    
    $scope.createItemEspec = function(item){
        var promise = listaPreciosService.createItem(item);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModalNuevo = 1;
                      $("#NuevoEspecifico").modal("hide");
                      
                      $scope.getListaPrecios();
                      mensajesModalNuevo();
                  },
    
                  function(errorItem) {
                      $scope.estadoModalNuevo = 2;
                      mensajesModalNuevo();
                  }
        );

    };
    
    $scope.createItemAdmiCentral = function(item){
        var promise = listaPreciosService.createItem(item);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModalNuevo = 1;
                      $("#Nuevo").modal("hide");
                      
                      $scope.getListaPrecios();
                      mensajesModalNuevo();
                  },
    
                  function(errorItem) {
                      $scope.estadoModalNuevo = 2;
                      mensajesModalNuevo();
                  }
        );

    };
    
    $scope.openModalNewItem = function(){
        resetItem();

        $scope.item.usuarioReg = $scope.user_username;
        $("#Nuevo").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.openModalNewItemEspec = function(udId, desc_unidad){
        resetItem();

        $scope.newlp.nameUnidad = desc_unidad;
        $scope.item.udId = udId;
        $scope.item.usuarioReg = $scope.user_username;
        $("#NuevoEspecifico").modal({backdrop: 'static', keyboard: false});
    };

    $scope.abrirModalConfirm_registrarItem = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo artículo. ¿Desea continuar?';
        
        $scope.modalOptions.action = $scope.submit;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.submit = function() {            	
    	$scope.item.tipoResolucion = $scope.viewResol.resolSelected.codTipoResol;
    	
    	if($scope.administracionCentral){
            $scope.item.udIdAdministrativa = $scope.user_idUnidadAdministrativa;
            $scope.createItemAdmiCentral($scope.item);
        }else{
            $scope.item.udIdAdministrativa = $scope.user_idCodDependenciaPadre;
            $scope.createItem($scope.item);
        }       
    };


    $scope.abrirModalConfirm_registrarItemEspec = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo artículo. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.submitEspec;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.submitEspec = function() {         	    	    	
    	$scope.item.resolRectoral = $scope.viewResol.numResolRectoral;
        $scope.item.resolDecanal = $scope.viewResol.numResolDecanal;
        $scope.item.resolDirectoral = $scope.viewResol.numResolDirectoral;
        
        $scope.item.tipoResolucion = $scope.viewResol.resolSelected.codTipoResol;
        
        if($scope.administracionCentral){
            $scope.item.udIdAdministrativa = $scope.user_idUnidadAdministrativa;
        }else{
            $scope.item.udIdAdministrativa = $scope.user_idCodDependenciaPadre;
        }        

        $scope.createItemEspec($scope.item);
    };
    
    
    $scope.updateEstado = function(udId, codItem, estado){
        var promise = listaPreciosService.updateEstado(udId, codItem, estado);
        promise.then(
                      function(respItem) { 
                          $scope.getListaPrecios();
                      },
                      function(errorItem) {
                          $scope.estadoActiva = 2;
                      }
            );
    };

    $scope.abrirModalConfirm_cambiaEstado = function(item){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Deshabilitar';
        $scope.modalOptions.bodyText= 'Está a punto de deshabilitar éste artículo. ¿Está seguro?';

        if(item.estado==0){
            $scope.modalOptions.headerText = 'Habilitar';
            $scope.modalOptions.bodyText = 'Está a punto de habilitar éste artículo. ¿Está seguro?';           
        }

        $scope.modalOptions.data.item = item;

        $scope.modalOptions.action = $scope.cambiaEstado;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.cambiaEstado = function(){
        var estado = ($scope.modalOptions.data.item.estado==1 ? 0 : 1);
        $scope.estadoActiva = estado;
        $scope.viewEstado.codigo = $scope.modalOptions.data.item.codigo;
        $scope.viewEstado.descr = $scope.modalOptions.data.item.descripcion;
        $scope.updateEstado($scope.modalOptions.data.item.udId, $scope.modalOptions.data.item.codigo, estado); //accion

    };
    
    
    $scope.updateItem = function(item){
        var promise = listaPreciosService.updateItem(item);
        promise.then(
                      function(respItem) { 
                          $scope.estadoModalEditar = 1;
                          $scope.getListaPrecios();
                          mensajesModalEditar();
                          
                      },
                      function(errorItem) {
                          mensajesModalEditar();
                          $scope.estadoModalEditar = 2;
                      }
            );
    };
    
    $scope.openModalActualizar = function(item){
        $scope.itemEditar = angular.copy(item);
        $scope.itemEditar.usuarioModif = $scope.user_username;
        
        $(".maskResolucion").inputmask('remove');	        

        if($scope.itemEditar.tipoResolucion!=null){
        	$scope.viewResol.numResolucionTemp = $scope.itemEditar.numResolucion;
        	$scope.viewResol.resolSelected = $scope.getResolSelected($scope.itemEditar.nameTipoResol);
        }else{
        	$scope.viewResol.numResolucionTemp = '';
        	$scope.viewResol.resolSelected = $scope.listaTiposResolucion[0];        	
        }
        $(".maskResolucion").inputmask($scope.getInputMaskResol($scope.itemEditar.nameTipoResol));
        
        $scope.viewResol.plcHolderResolSelected = $scope.getPlcHolderResolucion($scope.itemEditar.nameTipoResol);
    	$scope.viewResol.lblResolSelected = $scope.getLabelTipoResolucion($scope.itemEditar.nameTipoResol);
    	
    	if($scope.itemEditar.precio != null) $scope.viewItem.precioTemp = $scope.itemEditar.precio;
    	else $scope.viewItem.precioTemp = 0;
    	
    	if($scope.itemEditar.codTipoperacionIgv != null) $scope.viewItem.codTipoperacionIgvTemp = $scope.itemEditar.codTipoperacionIgv;
    	else $scope.viewItem.codTipoperacionIgvTemp = 30;

        $scope.formItemEdit.$setPristine();
        
        $("#Editar").modal({backdrop: 'static', keyboard: false});
    };

    $scope.abrirModalConfirm_actualizarItem = function(){
    	if( ( (parseFloat($scope.itemEditar.precio) !== parseFloat($scope.viewItem.precioTemp)) || (parseInt($scope.itemEditar.codTipoperacionIgv) !== parseInt($scope.viewItem.codTipoperacionIgvTemp)) )
        		&& ($scope.itemEditar.numResolucion === $scope.viewResol.numResolucionTemp) ){
        		$scope.modalOptions.headerColorValue= 2;
                $scope.modalOptions.closeButtonText= 'Aceptar';
                $scope.modalOptions.headerText= 'Guardar cambios';
                $scope.modalOptions.bodyText= 'La resolución: ' + $scope.itemEditar.numResolucion  + ' debe ser actualizada. Favor de actualizarlo antes de continuar.';

                $('#modalMessages').modal({show: true});
        }else{
		    	$scope.modalOptions.headerColorValue= 0;
		        $scope.modalOptions.closeButtonText= 'No';
		        $scope.modalOptions.actionButtonText= 'Si';
		        $scope.modalOptions.headerText= 'Guardar cambios';
		        $scope.modalOptions.bodyText= 'Se procederá a registrar los cambios en éste artículo. ¿Continuar?';
		
		        $scope.modalOptions.action = $scope.actualizarItem;
		
		        $('#modalConfirm').modal({show: true});
        }
    };
    
    $scope.actualizarItem = function(){
    	$scope.itemEditar.tipoResolucion = $scope.viewResol.resolSelected.codTipoResol;      
        $scope.updateItem($scope.itemEditar);
        $("#Editar").modal("hide");
    };
    
    
    $scope.deleteItemPrecio = function(udId, codItem){
        var promise = listaPreciosService.deleteItemPrecio(udId, codItem);
        promise.then(
                      function(respItem) { 
                          $scope.getListaPrecios();

                          $.gritter.add({
                                title: 'Eliminar Item',
                                text: 'El item N°: ' + $scope.viewItem.codigo +' se ha eliminado correctamente ',
                                image: 'resources/assets/img/notification/succes.png',
                                sticky: false,
                                time: ''
                          });
                      },
                      function(errorItem) {
                          if(errorItem == -1) {
                              $.gritter.add({
                                title: 'Eliminar Item',
                                text: 'El item ' +  $scope.viewItem.codigo + ' no existe. Probar otra vez.',
                                image: 'resources/assets/img/notification/peligro.png',
                                sticky: false,
                                time: ''
                              });
                          }else if(errorItem == -2){
                              $.gritter.add({
                                title: 'Eliminar Item',
                                text: 'No se podrá eliminar el item ' +  $scope.viewItem.codigo + '. Está siendo usado en los comprobantes de pago.',
                                image: 'resources/assets/img/notification/peligro.png',
                                sticky: false,
                                time: ''
                              });
                          }else if(errorItem == -4){
                              $.gritter.add({
                                title: 'Eliminar Item',
                                text: 'No se pudo realizar la operación. Intentelo más tarde.',
                                image: 'resources/assets/img/notification/error.png',
                                sticky: false,
                                time: ''
                              });
                          }
                      }
            );
    };


    $scope.abrirModalConfirm_eliminarItem = function(item){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Eliminar';
        $scope.modalOptions.bodyText= 'Está a punto de Eliminar el item N°: ' + item.codigo + '. ¿Está seguro?';

        $scope.modalOptions.data.item = item;

        $scope.modalOptions.action = $scope.eliminarItemPrecio;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.eliminarItemPrecio = function(){     
        $scope.viewItem.codigo = $scope.modalOptions.data.item.codigo;
        $scope.deleteItemPrecio($scope.modalOptions.data.item.udId, $scope.modalOptions.data.item.codigo);
    };


    /************************************************ CONCEPTOS DE PAGO ***********************************************/
    $scope.openModalConceptos = function(option){
        $scope.actionOption = option;
        $scope.estadoBotonAceptarConcepto = false;
        $scope.selectedRow = null;

        var promise;
        if( $scope.actionOption==1 ){
          promise = conceptosPagoService.listarConceptosPagoHabilidatosSoloXUnaDep($scope.item.udId);
        }else{
          promise = conceptosPagoService.listarConceptosPagoHabilidatosSoloXUnaDep($scope.itemEditar.udId);
        }
        
        promise.then(
                      function(respLista) { 
                          if(respLista.data==0){
                            $("#resultConceptos").hide();
                            $("#noResultConceptos").show();
                         }else{
                            $scope.tableParamsConceptosPago = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                            $("#resultConceptos").show();
                            $("#noResultConceptos").hide();
                         }
                      },
                      function(errorLista) {
                          $("#resultConceptos").hide();
                          $("#noResultConceptos").show();
                      }
        );
        $("#modalListaConceptosPago").modal({backdrop: 'static', keyboard: false});
    };

    $scope.selectRowConcepto = function(rowIndex,concepto){
      $scope.selectedRow = rowIndex;
      $scope.conceptoElegido = concepto;
      $scope.estadoBotonAceptarConcepto = true;
    };

    $scope.dobleClickRowConcepto=function(rowIndex,concepto){    
       $scope.selectRowConcepto(rowIndex,concepto);
       $scope.aceptarConcepto()       
    };

    $scope.aceptarConcepto=function(){   
        if( $scope.actionOption==1 ){
          $scope.item.idUnidadConcepto = $scope.conceptoElegido.idCPU;
          $scope.item.codigoConcepto6digitos = $scope.conceptoElegido.codigoConcepto6digitos;
          $scope.item.descrUnidadConcepto = $scope.conceptoElegido.descr;
        }else{
          $scope.itemEditar.idUnidadConcepto = $scope.conceptoElegido.idCPU;
          $scope.itemEditar.codigoConcepto6digitos = $scope.conceptoElegido.codigoConcepto6digitos;
          $scope.itemEditar.descrUnidadConcepto = $scope.conceptoElegido.descr;
        }
        $('#modalListaConceptosPago').modal('toggle');
    };

    $scope.limpiarConceptoElegido=function(){    
        $scope.item.idUnidadConcepto = null;
        $scope.item.codigoConcepto6digitos = '';
        $scope.item.descrUnidadConcepto = '';
    };

    $scope.limpiarConceptoElegidoEditar=function(){    
        $scope.itemEditar.idUnidadConcepto = null;
        $scope.itemEditar.codigoConcepto6digitos = '';
        $scope.itemEditar.descrUnidadConcepto = '';
    };

    /*********************************************************************************************************************/
    
    function resetItem(){
        $scope.item = {
                udId: null,
                codigo: "",
                descripcion: "",
                precio: null,
                estado: 1,
                idMoneda: null,
                udCod: "",
                udDesc: "",
                monedaDesc: "",
                monedaSimb: "",
                usuarioReg: "",
                usuarioModif: "",
                nUnidadMedida: "",
                idBienCata:null,
                codTipoperacionIgv: 10
            };
        $scope.newlp.nameUnidad = "";
        $scope.item.idMoneda = $scope.listaMonedas[0].idMoneda;
        $scope.item.codigoCatalogo = $scope.listaCatCodProd[0].codigo;
        $scope.item.nUnidadMedida = $scope.listaUnidMedida[0].unimedcod;
        
        $scope.viewResol.resolSelected = $scope.listaTiposResolucion[0];
        $scope.viewResol.lblResolSelected = $scope.getLabelTipoResolucion($scope.viewResol.resolSelected.nameTipoResol);
        $scope.viewResol.plcHolderResolSelected = $scope.getPlcHolderResolucion($scope.viewResol.resolSelected.nameTipoResol);
        $(".maskResolucion").inputmask('remove');
        $(".maskResolucion").inputmask($scope.getInputMaskResol($scope.viewResol.resolSelected.nameTipoResol));
        
        $scope.formItemNuevo.$setPristine(); //reset Form
        $scope.formItemNuevoEspec.$setPristine();
        
    }
    
    $scope.openModalDetalleItem = function(item){
        $scope.itDetalle = angular.copy(item);
        
        $("#Detalle").modal({backdrop: 'static', keyboard: false});
    };
    
    
    /******************************************* CATEGORIAS  *************************************************/
    $scope.getListaCategoriasBien = function(){
      var promise;      
      if($scope.administracionCentral){
          promise = categoriasBienesService.listarCategoriasXUnidadBase($scope.user_idUnidadAdministrativa);
        }else{
          promise = categoriasBienesService.listarCategoriasXUnidadBase($scope.user_idCodDependenciaPadre);
        }
        promise.then(
                      function(respLista) { 
                          if(respLista.data==0){
                            $("#resultGestionCategorias").hide();
                            $("#noResultCategorias").show();
                         }else{
                            $scope.listaCategoriasBien = respLista.data;
                            $scope.tableParamsCategorias = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                            $("#resultGestionCategorias").show();
                            $("#noResultCategorias").hide();
                         }
                      },
                      function(errorLista) {
                          $("#resultGestionCategorias").hide();
                          $("#noResultCategorias").show();
                      }
        );
    };

    $scope.createCategoriaBien = function(categoria){
        var promise = categoriasBienesService.createCategory(categoria);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModalNuevaCategoria = 1;
                      $("#modalNombreCategoria").modal("toggle");
                      $scope.getListaCategoriasBien();
                      resetItemCategoria();
                      mensajesModalNuevaCategoria();  
                  },    
                  function(dataError) {
                      $scope.estadoModalNuevaCategoria = dataError;
                      mensajesModalNuevaCategoria();
                  }
        );
        
    };

    $scope.updateCategoriaBien = function(categoria){
        var promise = categoriasBienesService.updateCategory(categoria);
        promise.then(
                      function(respItem) {
                          $scope.estadoModalEditarCategoria = 1;
                          $("#modalNombreCategoria").modal("toggle");
                          $scope.getListaCategoriasBien();
                          mensajesModalEditarCategoria();
                      },
                      function(dataError) {
                          $scope.estadoModalEditarCategoria = dataError;
                          if(dataError==-1){
                            $("#modalNombreCategoria").modal("toggle");
                            $scope.getListaCategoriasBien();
                          }
                          mensajesModalEditarCategoria();
                      }
            );
    };

    $scope.deleteCategoriaBien = function(idBienCata){
        var promise = categoriasBienesService.deleteCategory(idBienCata);
        promise.then(
                      function(respItem) {
                          $scope.estadoModalEliminarCategoria = 1;
                          $scope.getListaCategoriasBien();
                          mensajesModalEliminarCategoria();
                      },
                      function(dataError) {
                          $scope.estadoModalEliminarCategoria = dataError;
                          mensajesModalEliminarCategoria();
                      }
            );
    };
        
    $scope.openModalGestionCategoriasBien = function(){
        $scope.viewCategoria.actionReleased = false;

        if($scope.listaCategoriasBien==0){
              $("#resultGestionCategorias").hide();
              $("#noResultCategorias").show();
        }else{
              $scope.tableParamsCategorias = new ngTableParams({}, { dataset: $scope.listaCategoriasBien, counts:[]});
              $("#resultGestionCategorias").show();
              $("#noResultCategorias").hide();
        }

        $("#gestionCategoriasBien").modal({backdrop: 'static', keyboard: false});
    };

    $scope.openModalRegistrarCategoria = function(){
        resetItemCategoria();
        $("#modalNombreCategoria").modal({backdrop: 'static', keyboard: false});
    };

    $scope.openModalActualizarCategoria = function(categoria){
         $scope.itemCategoria = angular.copy(categoria);
        $("#modalNombreCategoria").modal({backdrop: 'static', keyboard: false});
    };

    $scope.submitCategoria= function() {        
        $scope.viewCategoria.nombre = $scope.itemCategoria.descCategoriaBien;

        if($scope.itemCategoria.idBienCata==null){
            if($scope.administracionCentral){
              $scope.itemCategoria.udIdCategoria = $scope.user_idUnidadAdministrativa;
            }else{
              $scope.itemCategoria.udIdCategoria = $scope.user_idCodDependenciaPadre;
            }     

            $scope.createCategoriaBien($scope.itemCategoria);    
        }else{
            $scope.updateCategoriaBien($scope.itemCategoria); 
        }

        $scope.viewCategoria.actionReleased = true;
    };

    $scope.abrirModalConfirm_eliminarCategoria = function(categoria){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Eliminar';
        $scope.modalOptions.bodyText= 'Está a punto de Eliminar la categoría: \'' + categoria.descCategoriaBien + '\'. Los bienes que estén relacionados pasarán a \'Sin Categoria\'. ¿Está seguro?';

        $scope.modalOptions.data.categoria = categoria;

        $scope.modalOptions.action = $scope.eliminarItemCategoria;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.eliminarItemCategoria = function(){     

        $scope.viewCategoria.nombre = $scope.itemCategoria.descCategoriaBien;
        $scope.deleteCategoriaBien($scope.modalOptions.data.categoria.idBienCata);
        $scope.viewCategoria.actionReleased = true;
    };

    function resetItemCategoria(){
        $scope.itemCategoria = {
          idBienCata: null,
          udIdCategoria: null,
          descCategoriaBien: ''
        };

        $scope.formCategoryName.$setPristine(); //reset Form        
    }
    
    $scope.getMyBienes = function(){
    	$scope.view.nombrePadre = $scope.user_descUnidadAdministrativa;
    	$scope.getCategoriesAndBienes($scope.user_idUnidadAdministrativa);
    	
    };
    
    $scope.getCategoriesAndBienes = function(idUnidadPadreAdministrativa, udCodDependencia){
        var promise = categoriasBienesService.listarCategoriasXUnidadBase(idUnidadPadreAdministrativa);

        $scope.listCatBienSelect = [{ idBienCata: '', udIdCategoria: '', descCategoriaBien: 'Todos', filterCategoriaBien: ''},
                                { idBienCata: '!', udIdCategoria: '', descCategoriaBien: 'Sin Categoria', filterCategoriaBien: '!'}
                               ];
        
        promise.then(
                           function(respLista) {
                             $scope.listaCategoriasBien = respLista.data;
                             $scope.listCatBienSelect = $scope.listCatBienSelect.concat(respLista.data);
                             
                             if (typeof udCodDependencia === "undefined" || udCodDependencia === null) { 
                                $scope.listarPreciosXAdmiCentral(idUnidadPadreAdministrativa);
                             }else{
                                $scope.listarPreciosXDependencia(udCodDependencia);
                             }
                           },
                           function(errorLista){
                           }
                   );

    };
    
    /******************************************* FIN CATEGORIAS **********************************************/
    
    $('#Nuevo').on('hidden.bs.modal', function () {
      $scope.bodyPaddingRightZero();
    });
    $('#NuevoEspecifico').on('hidden.bs.modal', function () {
      $scope.bodyPaddingRightZero();
    });
    $('#Editar').on('hidden.bs.modal', function () {
      $scope.bodyPaddingRightZero();
    });
    $('#gestionCategoriasBien').on('hidden.bs.modal', function () {
        $scope.bodyPaddingRightZero();

        if($scope.viewCategoria.actionReleased){
          $scope.$apply(function () {
              $scope.listCatBienSelect = [{ idBienCata: '', udIdCategoria: '', descCategoriaBien: 'Todos', filterCategoriaBien: ''},
                                          { idBienCata: '!', udIdCategoria: '', descCategoriaBien: 'Sin Categoria', filterCategoriaBien: '!'}
                                         ];
              $scope.listCatBienSelect = $scope.listCatBienSelect.concat($scope.listaCategoriasBien);
              $scope.getListaPrecios();
          });
        }
      });
      $('#spinnerModalDiv').on('hidden.bs.modal', function () {
        $scope.bodyPaddingRightZero();
      });
    

    $scope.initView = function(){
        if($scope.user_codPerfil=="EMISOR_DE_TICKETS" || $scope.user_codPerfil=="CXC_FACULTAD" || $scope.user_codPerfil=="CXC_CAJA_RECAUD" || $scope.user_codPerfil=="CXC_ADMIN_CAJAS" || $scope.user_codPerfil=="CXC_CLIENTE"){
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
                                  $scope.view.nombrePadre = nodoPadre.namePadre;
                                  $("#boxEncabezado").show();
                              } 
                          },
                          function(errorLista) {
                          }
            );
            $scope.dependenciaElegida = $scope.user_codDependencia;
            $scope.getListaPrecios();
            $("#panelResult").show();
        }
        else if($scope.user_codPerfil=="CXC_TESO" || $scope.user_codPerfil=="CXC_CAJA_RECAUD_TESO"){
            $scope.administracionCentral = true;
            $scope.view.nombrePadre = $scope.user_descUnidadAdministrativa;
            $scope.getListaPrecios();
            $("#panelResult").show();
        }
    };
    
    
    $scope.initView();
    

  //============================================= MODAL ARBOL ======================================== 
    var elementoClickeado;
    var elementoClickeado2;
    var codigoNodoSeleccionado;
    var nombreCompletoNodoSeleccionado;
    var udIdNodoSeleccionado;
    
    $scope.openModalUnidad = function(){
        var perfil=$("#idPerfil").text();
        var dependencia=$("#idCodDependencia").text();
        
        $scope.estadoModalDependencia = 0; // estado inicial( No ha elegido nada aun )
    
        var promise = dependenciaService.dependenciasPorUsuarioYPerfil(dependencia, perfil);
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
            case -3: mostrarModalMensaje("Aviso","Usted no ha seleccionado nada aun."); break;
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
    
        var promise = null;
        
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
     
     $scope.expandirContraer2=function(event, idUnidad, desc_unidad){
            var textElementoClickeado=event.currentTarget.text;
            nombreCompletoNodoSeleccionado=desc_unidad;
           
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
         
     $scope.aceptarDependencia=function(){ 
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
                                      nodoPadre= respLista.data;

                                      if( (primeraLetra in $scope.arbol.letrasRaiz) && cantLetras==$scope.arbol.cantCarNv1){ //C,D,E,F,G,H,Z y nivel 1
                                          $scope.view.nombrePadre = nombreCompletoNodoSeleccionado;
                                          $("#boxEncabezado").hide();
                                      }
                                      else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //Facultad nivel 2
                                          $scope.view.nombrePadre = nombreCompletoNodoSeleccionado;
                                          $("#boxEncabezado").hide();
                                      }else{                                 
                                          $("#txtNombreDependencia").text(nombreCompletoNodoSeleccionado);
                                          $scope.view.nombrePadre = nodoPadre.namePadre;
                                          $("#boxEncabezado").show();
                                      }      
                                         
                                      $scope.estadoModalDependencia = 1; // estado correcto: recibido
                                      $("#modalDependencia").modal("hide");
                                      
                                      $scope.dependenciaElegida = codigoNodoSeleccionado;
                                      
                                      $scope.getCategoriesAndBienes(nodoPadre.idUnidad, codigoNodoSeleccionado);
                                      $("#panelResult").show();
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
                                                  $scope.newlp.nameUnidad = nombreCompletoNodoSeleccionado;
                                                  $scope.newlp.namePadre = '';
                                            }
                                            else if( primeraLetra=="F" && cantLetras==$scope.arbol.cantCarNv2){ //Facultad y nivel 2
                                                  $scope.newlp.nameUnidad = nombreCompletoNodoSeleccionado;
                                                  $scope.newlp.namePadre = '';
                                            }else{                               
                                                $scope.newlp.nameUnidad = nombreCompletoNodoSeleccionado;
                                                $scope.newlp.namePadre = nodoPadre.namePadre;
                                            }
                                            
                                            $scope.item.udId = udIdNodoSeleccionado;
                                            $scope.item.udCod = codigoNodoSeleccionado;
                                            
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