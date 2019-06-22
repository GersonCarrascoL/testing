
app.controller('solicitudes_conceptoPago_controller', ['$scope', 'ngTableParams', 'conceptosPagoService', 'dependenciaService', 'monedaService', 'tipoConceptoPagoService', function($scope, ngTableParams, conceptosPagoService, dependenciaService, monedaService, tipoConceptoPagoService) {
    
    $scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
    $scope.user_codPerfil = $("#idPerfil").text(); //CX_TESO, CXC_FACULTAD, CXC_CAJA_RECAUD, etc
    $scope.user_codDependencia=$("#idCodDependencia").text(); //F0608, etc
    $scope.user_descDependencia=$("#descDependencia").text();
    
    $scope.view = {
            nombrePadre: '',
            nombreDependencia: ''
    };

    $scope.viewConcepto = {
            valorDetraccion: 10
    }
    
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
    $scope.newcp = {nameUnidad:'', namePadre:'', opRetencion: 0, opRetencion2: 0};

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
    
    $scope.concepto = {
            idCPago: null,
            codCPago: "",
            concepto: "",
            descr:"",
            idTipoCpago: null,    
            monto: null,
            estado: 1,
            tIngClasif: null,
            idMoneda: null,
            facturable: 1,
            igv:0,
            udId: null,
            udCod: "",
            udDesc: "",
            monedaDesc: "",
            monedaSimb: "",
            codigoUnidad: "",
            resolRectoral: '',
            observaciones: "",
            status: null,
            estadoSolicitudMaestro: null
    };
    
    $scope.itemEditar= {};
    
    $scope.conPagos = [];
    
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
    
    function mensajesModalAprobarConcepto(){
        switch ($scope.estadoModalSolicitud){
            case 1: 
                $.gritter.add({
                    title: 'Aprobar concepto',
                    text: 'El concepto de pago ha sido aprobado correctamente. ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                });break;
            case 2: 
                $.gritter.add({
                    title: 'Aprobar concepto',
                    text: 'Hubo un error al aprobar el concepto de pago ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }

    function mensajesModalRechazarConcepto(){
        switch ($scope.estadoModalSolicitud){
            case 1: 
                $.gritter.add({
                    title: 'Rechazar concepto',
                    text: 'El concepto de pago ha sido rechazado correctamente. ',
                    image: 'resources/assets/img/notification/desaprobar.png',
                    sticky: false,
                    time: ''
                });break;
            case 2: 
                $.gritter.add({
                    title: 'Rechazar concepto',
                    text: 'Hubo un error al rechazar el concepto de pago ',
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
            $scope.tableParams = new ngTableParams({ count:5, group:{ udDesc:"asc"}, filter: {estadoSolicitud:"0",udIdPadre:'',regYear:'',regMonth:''} }, { dataset: $scope.conPagos, counts:[]});
            $scope.VisibleResultTable=true;
        }
    }
    
    $scope.listarSolicitudes_conceptoPago = function(){
        var promise = conceptosPagoService.listarConceptosPagoTodos();
        promise.then(
                      function(respLista) { 
                          mostrar(respLista.data);
                      },
                      function(errorLista) {
                      }
         );
    };
    
    $scope.getSolicitudes = function(){
        $scope.listarSolicitudes_conceptoPago();
    };
    
    $scope.createConceptoUnidad = function(concepto){
        var promise = conceptosPagoService.createConceptoUnidad(concepto);
        promise.then(
                  function(respItem) { 
                      $scope.dependenciaElegida = $scope.concepto.udCod;
                      $scope.estadoModalNuevo = 1;
                      $("#Nuevo").modal("hide");
                      
                      if($scope.newcp.namePadre == '') {
                        $scope.view.nombrePadre = $scope.newcp.nameUnidad; 
                        $("#boxEncabezado").hide();
                      }else {
                        $("#txtNombreDependencia").text($scope.newcp.nameUnidad);
                        $scope.view.nombrePadre = $scope.newcp.namePadre;
                        $("#boxEncabezado").show();
                      }
                      
                      $scope.getConceptosPago();
                      $("#panelResult").show();
                      mensajesModalNuevo();
                  },    
                  function(dataError) {
                      $scope.estadoModalNuevo = dataError;
                      mensajesModalNuevo();
                  }
        );
        
    };
    
    $scope.createConceptoUnidadEspec = function(concepto){
        var promise = conceptosPagoService.createConceptoUnidad(concepto);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModalNuevo = 1;
                      $("#NuevoEspecifico").modal("hide");
                       
                      $scope.getConceptosPago();
                      mensajesModalNuevo();
                  },    
                  function(dataError) {
                      $scope.estadoModalNuevo = dataError;
                      mensajesModalNuevo();
                  }
        );
        
    };
    
    $scope.openModalNewConcepto = function(){
        resetConcepto();
        $("#Nuevo").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.openModalNewConceptoEspec = function(idUnidad, desc_unidad){
        resetConcepto(); 
        $scope.formNuevoCP2.$setPristine(); //reset Form
        $scope.newcp.nameUnidad = desc_unidad;
        $scope.concepto.udId = idUnidad;
        $("#NuevoEspecifico").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.submitNewConceptoUnidad = function() {
        $scope.createConceptoUnidad($scope.concepto);
    };
    
    $scope.submitNewConceptoUnidadEspec = function() {
        $scope.createConceptoUnidadEspec($scope.concepto);
    };
    
    
    $scope.updateEstado = function(idCPago, estado){
        var promise = conceptosPagoService.updateEstado(idCPago, estado);
        promise.then(
                      function(respItem) { 
                          $scope.getConceptosPago();
                      },
                      function(errorItem) {
                      }
            );
    };
    
    $scope.cambiaEstado = function(item){
        var estado = (item.estado==1 ? 0 : 1);
        $scope.updateEstado(item.idCPago, estado);
    };
    
    
    $scope.updateConcepto = function(item){
        var promise = conceptosPagoService.updateConceptoPago(item);
        promise.then(
                      function(respItem) {
                          $scope.estadoModalEditar = 1;
                          $("#Editar").modal("hide");
                          $scope.getConceptosPago();
                          mensajesModalEditar();
                      },
                      function(dataError) {
                          $scope.estadoModalEditar = dataError;
                          mensajesModalEditar();
                      }
            );
    };
    
    $scope.openModalDetalleConcepto = function(item){
        $scope.cpDetalle = angular.copy(item);
        $scope.newcp.nameUnidad = $scope.cpDetalle.udDesc;
        $scope.newcp.namePadre = $scope.cpDetalle.udDescPadre;

        if($scope.cpDetalle.facturable == null || $scope.cpDetalle.facturable == ""){
            $scope.cpDetalle.facturable= 0;
        }
        if($scope.cpDetalle.igv == null || $scope.cpDetalle.igv == ""){
            $scope.cpDetalle.igv= 0;
        }

        if($scope.cpDetalle.poseeDetraccion==1) {
            var valor = $scope.cpDetalle.porcentDetraccion;
            var lenPartEntera = String(Math.trunc(valor)).length; // numero de digitos parte entera
            var lenPartDecimal= String(valor).match(/\d/g).length - lenPartEntera; //numero de digitos - numero digitos parte entera
            var len = (lenPartDecimal-2 <= 0)?0:(lenPartDecimal-2);
            var porcentaje = (parseFloat(valor*100)).toFixed(len); //convertimos a valor porcentual %
            $scope.viewConcepto.valorDetraccion = porcentaje;           
        }else{
            $scope.viewConcepto.valorDetraccion = 10;
        }

        $("#viewConcepto").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.actualizarItem = function(){ 

        $scope.updateConcepto($scope.itemEditar);
    };
    
    function resetConcepto(){
        $scope.concepto = {
                idCPago: null,
                codCPago: "",
                concepto: "",
                descr:"",
                idTipoCpago: null,    
                monto: null,
                estado: 1,
                tIngClasif: null,
                idMoneda: null,
                facturable: 1,
                igv:0,
                udId: null,
                udCod: "",
                udDesc: "",
                monedaDesc: "",
                monedaSimb: "",
                codigoUnidad: "",
                resolRectoral: '',
                observaciones: "",
                status: null,
                estadoSolicitudMaestro: null
        };
        
        $scope.newcp.nameUnidad = "";
        $scope.concepto.idMoneda = $scope.listaMonedas[0].idMoneda;
        $scope.formNuevoCP.$setPristine(); //reset Form
    }

    $scope.abrirModalConfirm_aprobarConcepto = function(idCPU){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Aprobar';
        $scope.modalOptions.bodyText= '¿Está seguro de Aprobar éste concepto de pago?';

        $scope.modalOptions.data.idCPU = idCPU;

        $scope.modalOptions.action = $scope.aprobarConcepto;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.aprobarConcepto = function(){
        var promise = conceptosPagoService.aprobarConcepto($scope.modalOptions.data.idCPU);
            promise.then(
                          function(respItem) {
                              $scope.estadoModalSolicitud = 1;
                              $("#viewConcepto").modal("hide");
                              $scope.getSolicitudes();
                              mensajesModalAprobarConcepto();
                          },
                          function(errorItem) {
                              $scope.estadoModalSolicitud = 2;
                              mensajesModalAprobarConcepto();
                          }
            );

    };


    $scope.abrirModalMotivoRechazo = function(idCPU){
        $scope.modalOptions.headerColorValue= 3;
        $scope.modalOptions.closeButtonText= 'Cancelar';
        $scope.modalOptions.actionButtonText= 'Aceptar';
        $scope.modalOptions.headerText= 'Rechazar';
        $scope.modalOptions.bodyText= 'Ingrese un comentario acerca del motivo:';

        $scope.modalOptions.data.idCPU = idCPU;
        $scope.modalOptions.data.motivoRechazo = '';

        $scope.modalOptions.action = $scope.rechazarConcepto;

        $('#modalMotivoRechazo').modal({show: true});

    };
    
    $scope.rechazarConcepto = function(){
        var promise = conceptosPagoService.rechazarConcepto($scope.modalOptions.data.idCPU, $scope.modalOptions.data.motivoRechazo);
            promise.then(
                          function(respItem) {
                              $scope.estadoModalSolicitud = 1;                            
                              $scope.getSolicitudes();
                              $("#viewConcepto").modal("hide");
                              mensajesModalRechazarConcepto();
                          },
                          function(errorItem) {
                        	  $scope.estadoModalSolicitud = 2;
                              mensajesModalRechazarConcepto();
                          }
            );

    };
    
    $scope.initView = function(){
            $scope.getSolicitudes();
            $("#panelResult").show();
    };
    
    $scope.initView();
    
        
}]);
