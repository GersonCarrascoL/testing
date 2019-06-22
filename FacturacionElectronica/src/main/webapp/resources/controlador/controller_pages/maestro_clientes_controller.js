
app.controller('maestro_clientes_controller', ['$scope', '$filter', 'ngTableParams', 'maestroClientesService', 'dependenciaService', 'docIdentidadService', 'ubigeoService', 'sunatService', 'reniecService', function($scope, $filter, ngTableParams, maestroClientesService, dependenciaService, docIdentidadService, ubigeoService, sunatService, reniecService) {
    
    $scope.viewMode = $("#viewMode").val(); // 2:consulta o  1:mantenimiento
    $scope.viewDataUbigeo = $("#viewDataUbigeo").val(); // 1: usar ubigeo de la bd
 
    $scope.dataView = {
            title: '',
            ruc: {opCliente:1, numero:''},  // 1: persona natural,  2: persona juridica
            razonSocial:'',
            sexo: [
              {id:'1', name: 'Masculino'},
              {id:'2', name: 'Femenino'}
            ],
            selectedOption: {id:'M', name: 'Masculino'}
    };
    
    $scope.tipoSexo = {
    		masculino : {id:'1', str:'M'},
    		femenino  : {id:'2', str:'F'}
    };
    
    $scope.maskOptions_letrasYEspacios = {translation: {'B': {pattern: /[0-9A-Za-zñÑáéíóúÁÉÍÓÚ-\s]/,recursive: true}}};
    $scope.maskOptions_letrasNumerosYEspacios = {translation: {'B': {pattern: /[0-9A-Za-zñÑáéíóúÁÉÍÓÚ\s]/,recursive: true}}};

    $scope.viewSunat = {
    		numRuc : '',
            buscando: false,
            existeRuc: false,
            servicioCaido: false,
            regRucManual: false
    };
    $scope.estadoBtnValidarRUC = false;
    
    $scope.viewReniec = {
            existeDNI: false,
            mensajeVerDNI: ''
    };
    $scope.estadoBtnValidarDNI = false;
    $scope.estadoBtnRegistrarOtroDoc = false;
    
    $scope.dataSunat = {};
    $scope.dataReniec = {};
    
    $scope.modalOptions = {
            headerColorValue: 0,  // 0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
            closeButtonText: 'No',
            actionButtonText: 'Si',
            headerText: 'Confirmar',
            bodyText: 'Realizar ésta acción?',
            data: {},
            action: function(){}              
    };
    
    $(".razonSocialServCaido").mask('B', $scope.maskOptions_letrasNumerosYEspacios);    
    
    // CLIENTE CON RUC
    $scope.RUC_newClient = {
            ruc: "",
            razonSocial: "",
            nombreComercial: "",
            direccion: "-",
            nombre: "",
            apPat: "",
            apMat: "",
            email: "",
            email2: "",
            fechaReg: null,
            telf: "",
            fechaNacimiento: null,
            sexo: null,
            estado: 1,
            status: null,
            usuCrea: null,
            tipoPersona: '01',
            codUbigeo:'',
            estadoSunat: '00',
            estadoSunatDescr: '',
            condicionSunat: '00',
            condicionSunatDescr: ''
    };
    
    $scope.rucEditar= {};
    
    // CLIENTE SIN RUC
    $scope.SINRUC_client = {
            idCliente: null,
            tipoDoc: 1,
            numDoc: "",
            nombres: "",
            apePat: "",
            apeMat: "",
            direccion: "-",
            fechaNac: "",
            sexo: "",
            email: "",
            email2: "",
            abrev: "",
            nomCompleto: "",
            nombreDoc: "",
            telefono:"",
            fechaReg: null,
            estado: 1,
            status: null,
            usuCrea: null,
            codUbigeo:''
    };
    
    $scope.criterioBusqueda = [{
                                    "id": 1,
                                    "name": "R.U.C."
                                }, {
                                    "id": 2,
                                    "name": "Tipo de Documento de Identidad"
                                }, {
                                    "id": 3,
                                    "name": "Código de Matricula"
                                }, {
                                    "id": 4,
                                    "name": "Razón Social"
                                }, {
                                    "id": 5,
                                    "name": "Apellidos o Nombres"
                                }, {
                                    "id": 6,
                                    "name": "Código de Trabajador"
                                }];
    
    $scope.search = { ruc: { dato:'', option: 1}, sinruc:{dato:'', option: 2, tipodoc: 0}};    
    $scope.userCriterio = {};
    
    $scope.initVariables = function (){  
    	$scope.tab = 1;
        if($("#templ_boleta").val() == 1 || $("#templ_boleta").val() == '1') {
        	$scope.tab = 2;
        	maestroClientesService.setTipoCliente('SINRUC');
        }
        
        $scope.userCriterio.criterioIdxRUC = "1";    //criterio id busqueda clientes con RUC
        $scope.userCriterio.criterioIdxNoRUC = "2";  //criterio id busqueda clientes sin RUC
        $scope.userCriterio.criterioIdxSUM = "3";    //criterio id busqueda alumnos del SUM
        $scope.userCriterio.criterioIdxSERV = "5";    //criterio id busqueda servidores
               
        $("#datoConRuc").attr('maxlength','11');
        $("#datoConRuc").mask('00000000000');
        $("#datoSinRuc").attr('maxlength','8');        
        $("#datoSinRuc").mask('00000000');
        $("#datoAlumno").attr('maxlength','8');         
        $("#datoAlumno").mask('00000000');
        $("#datoServidor").unmask();
        $("#datoServidor").mask('B', $scope.maskOptions_letrasYEspacios);
        $("#datoServidor").attr('maxlength','100');
        
        $scope.selectDisabled1 = false; //para select de Clientes Sin RUC
        $scope.selectDisabled2 = true;  //para select de Alumnos
        $scope.selectDisabled3 = false;  //para select de Servidores
        $scope.criterioBusquedaElegida1 = "R.U.C.";
        $scope.criterioPlaceHolder1 = "Número de R.U.C.";
        $scope.criterioBusquedaElegida2 = "D.N.I.";
        $scope.criterioPlaceHolder2 = "Número de DNI";
        $scope.criterioBusquedaElegida3 = "Código de Matricula";
        $scope.criterioPlaceHolder3 = "Código de Matricula";
        $scope.criterioBusquedaElegida4 = "Apellidos o Nombres";
        
        $scope.datoBuscar1 = "";
        $scope.datoBuscar2 = "";
        $scope.datoBuscar3 = "";
        $scope.datoBuscar4 = "";
        
        $(".input_sinruc_nombres").mask('B', $scope.maskOptions_letrasYEspacios);
    };
    $scope.initVariables();
    
    
    // get TIPOS DOCUMENTO IDENTIDAD
    $scope.datosDocsIdentidad = function(){
        var promise = docIdentidadService.listardocIdentidadActivas();
        promise.then(
                           function(respLista) {
                             $scope.listaDocIDentidad = respLista.data;
                             $scope.userTipoDoc1 = respLista.data[0].idDoc; //para cliente sin RUC
                             $scope.userTipoDoc2 = respLista.data[0].idDoc; //para alumno SUM
                             $scope.userTipoDoc3 = respLista.data[0].idDoc; //para servidores san marcos
                             
                             $scope.tipoDoc_sinruc = respLista.data[0].idDoc;
                           },
                           function(errorLista){
                           }
        );
    };
    $scope.datosDocsIdentidad();
    
    
    // get datos ubigeo
    $scope.datosUbigeo = function(){
        var promise = ubigeoService.listarDataUbigeo();
        promise.then(
                           function(respLista) {
                             $scope.listaUbigeo = respLista.data;
                           },
                           function(errorLista){
                           }
        );
    };
    if($scope.viewDataUbigeo=='1') {
    	$scope.datosUbigeo();
    }
    
    $scope.filterUbigeo = {};
    
    
    $scope.clickTab = function(tab){
        if($scope.tab != tab){
            $scope.tab = tab;
            $scope.datoBuscar1 = "";
            $scope.datoBuscar2 = "";
            $scope.datoBuscar3 = "";
            $scope.datoBuscar4 = "";
            $("#noMatches").hide();
            
            if($scope.tab == 1) {
            	maestroClientesService.setTipoCliente('RUC');
            }else if($scope.tab == 2) {
            	maestroClientesService.setTipoCliente('SINRUC');
            }else if($scope.tab == 3) {
            	maestroClientesService.setTipoCliente('SUM');
            }else if($scope.tab == 4) {
            	maestroClientesService.setTipoCliente('SERVIDOR');
            }
            
            maestroClientesService.setNoData(false);
            $("#buttonAceptarCliente").prop('disabled', true);
            $("#divRegClieSinRUC").hide();
            $("#divRegClieOtroDOC").hide();
        }       
    };


    $scope.bodyPaddingRightZero=function() { 
        $("body").css("padding-right", "0px");
    };
    
    
    
    //******************************* FUNCIONES ******************************************
    //**************************** CLIENTES CON RUC **************************************    
    $scope.conRUC_optionChange=function(){
        switch ($scope.userCriterio.criterioIdxRUC) {
            case "1":
                $scope.criterioBusquedaElegida1="R.U.C.";
                $scope.criterioPlaceHolder1 = "Número de R.U.C.";
                $scope.datoBuscar1 = "";                
                $("#datoConRuc").mask('00000000000');
                $("#datoConRuc").attr('maxlength','11');
                $("#noMatches").hide();

                maestroClientesService.setNoData(false);
                $("#divRegClieRUC").hide();
                break;
            case "4":
                $scope.criterioBusquedaElegida1="Razón Social";
                $scope.criterioPlaceHolder1 = "Razón social";
                $scope.datoBuscar1 = "";
                $("#datoConRuc").unmask();
                $("#datoConRuc").mask('B', $scope.maskOptions_letrasNumerosYEspacios);
                $("#datoConRuc").attr('maxlength','100');
                $("#noMatches").hide();

                maestroClientesService.setNoData(false);
                $("#divRegClieRUC").hide();
                break;
        }
    }
    
    $scope.changePersona = function(){
        $scope.RUC_newClient = {
                ruc: "",
                razonSocial: "",
                nombreComercial: "",
                direccion: "-",
                nombre: "",
                apPat: "",
                apMat: "",
                email: "",
                email2: "",
                fechaReg: null,
                telf: "",
                fechaNacimiento: null,
                sexo: null,
                estado: 1,
                status: null,
                usuCrea: null,
                codUbigeo:'',
                estadoSunat: '00',
                estadoSunatDescr: '',
                condicionSunat: '00',
                condicionSunatDescr: ''
        };
        $scope.filterUbigeo = {};
        $scope.formNuevoRuc.$setPristine();
    };
    
    function mensajesModalNuevoRuc(){
        switch ($scope.estadoModal){
            case 1: 
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'El cliente con RUC: ' + $scope.dataView.ruc.numero + ' se ha registrado correctamente. ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'Este RUC: ' + $scope.dataView.ruc.numero + ' ya fue registrado anteriormente en el sistema. ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -2: 
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'La Razón Social: ' + $scope.dataView.razonSocial + ' ya fue registrada anteriormente en el sistema. Escriba otra Razón Social. ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -3:
            case -4: 
            case -5:
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'Hubo un error al registrar el cliente.',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    function mensajesModalEditarRuc(){
        switch ($scope.estadoModal){
            case 1: 
                $.gritter.add({
                    title: 'Actualizar cliente',
                    text: 'El cliente con RUC: ' + $scope.dataView.ruc.numero + ' se ha actualizado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case 2: 
                $.gritter.add({
                    title: 'Actualizar cliente',
                    text: 'Hubo un error al actualziar el cliente ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    $scope.buscarXRUC = function(ruc){
        var promise;
        if($scope.viewMode == 1) {
        	promise = maestroClientesService.buscarClienteRUC_ruc(ruc); //modo mantenimiento
        }
        else{
            promise = maestroClientesService.buscarClienteRUC_rucHabilitados(ruc); //modo consulta
        }
        promise.then(
                      function(respLista) {
                          $scope.tableParams1 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                          $scope.tableClienteRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                          maestroClientesService.cliente=respLista.data;
                          $("#results1").show();
                          $("#noMatches").hide();
                      },
                      function(errorLista) {                          
                          $("#results1").hide();
                      }
            );
    }; 
    $scope.buscarXRazonSocial = function(razonSocial){
        var promise;
        if($scope.viewMode == 1) {
        	promise = maestroClientesService.buscarClienteRUC_razonSocial(razonSocial); //modo mantenimiento
        }
        else{
            promise = maestroClientesService.buscarClienteRUC_razonSocialHabilitados(razonSocial); //modo consulta
        }
        promise.then(
                      function(respLista) { 
                          $scope.tableParams1 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                          $scope.tableClienteRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                          maestroClientesService.cliente=respLista.data;
                          $("#results1").show();
                          $("#noMatches").hide();
                      },
                      function(errorLista) {
                          $("#results1").hide();
                      }
            );
    };

    
    $scope.getClie_ConRUC=function(){
          if($scope.search.ruc.option == 1) {
              $scope.buscarXRUC($scope.search.ruc.dato);
          }else if($scope.search.ruc.option == 4){
              $scope.buscarXRazonSocial($scope.search.ruc.dato);
          };
    };
    
    
    $("#datoConRuc").keyup(function(){
    	var parseDato=$("#datoConRuc").val().trim();
        if(parseDato!="" && parseDato!="."){
            $scope.search.ruc.dato = parseDato;
            $scope.search.ruc.option = $scope.userCriterio.criterioIdxRUC;
            
            var promise; 
            switch ($scope.userCriterio.criterioIdxRUC) {
                case "1":
                    if($scope.viewMode == 1) {
                    	promise = maestroClientesService.buscarClienteRUC_ruc(parseDato);
                    }
                    else { 
                    	promise = maestroClientesService.buscarClienteRUC_rucHabilitados(parseDato); 
                    }
                    break;
                case "4":
                    if($scope.viewMode == 1) {
                    	promise = maestroClientesService.buscarClienteRUC_razonSocial(parseDato);
                    }
                    else { 
                    	promise = maestroClientesService.buscarClienteRUC_razonSocialHabilitados(parseDato);
                    }
                    break;
            }                      
            
            promise.then(
                           function(respLista) { 
                                 if(respLista.data==0){
                                    $("#results1").hide();
                                    $("#noMatches").show();

                                    maestroClientesService.setNoData(true);
                                    if($scope.userCriterio.criterioIdxRUC==1){
                                    	$("#divRegClieRUC").show();
                                    	if(parseDato!="" && parseDato.length==11){
                                    		$scope.estadoBtnValidarRUC = true;
                                    	}else{
                                    		$scope.estadoBtnValidarRUC = false;
                                    	}
                                    }

                                 }else{
                                    $scope.tableParams1 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                                    $scope.tableClienteRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                                    maestroClientesService.cliente=respLista.data;
                                    $("#results1").show();
                                    $("#noMatches").hide();

                                    maestroClientesService.setNoData(false);
                                    $("#divRegClieRUC").hide();
                                 }
                            },
                            function(errorLista) {
                                  $("#results1").hide();
                                  $("#noMatches").show();

                                  maestroClientesService.setNoData(true);
                                  if($scope.userCriterio.criterioIdxRUC==1){
                                	  $("#divRegClieRUC").show();
                                  }
                            }
            );       
        }else{          
            $("#results1").hide();
            $("#noMatches").hide();         
            maestroClientesService.setNoData(false);
            $("#divRegClieRUC").hide();
        }
    }); 
    
    $scope.abrirModalConfirm_crearClienteRUC = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo cliente. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.crearClienteRUC;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.crearClienteRUC = function() {
        $scope.dataView.ruc.numero = $scope.RUC_newClient.ruc;
        $scope.dataView.razonSocial = $scope.RUC_newClient.razonSocial;
        
        var promise = maestroClientesService.createClienteRUC($scope.RUC_newClient);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModal = 1;
                      $("#NuevoRUC").modal("toggle");
                      
                      $scope.search.ruc.option = 1;
                      $scope.search.ruc.dato = $scope.RUC_newClient.ruc; //guarda el ruc si es que decide editar                                            
                      
                      $scope.getClie_ConRUC();
                      mensajesModalNuevoRuc();
                  },    
                  function(dataError) {
                      if(dataError==-1 || dataError==-2 || dataError==-3 || dataError==-4){
                        $scope.estadoModal = dataError;
                      }else{
                        $scope.estadoModal = -5;
                      }
                      
                      mensajesModalNuevoRuc();
                  }
        ); 
    };
    
    $scope.abrirModalConfirm_crearClienteRUC_extra = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo cliente. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.crearClienteRUC_extra;
        
        $('#modalConfirm').modal({show: true});
    };

    $scope.crearClienteRUC_extra = function() {        
        $scope.dataView.ruc.numero = $scope.RUC_newClient.ruc;
        $scope.dataView.razonSocial = $scope.RUC_newClient.razonSocial;
        
        var rzSocial = $scope.RUC_newClient.razonSocial;
        var ruc = $scope.RUC_newClient.ruc;
        var direc = $scope.RUC_newClient.direccion.toUpperCase();
        var telf = $scope.RUC_newClient.telf;
        var email = $scope.RUC_newClient.email;
        var codUbigeoClie = $scope.RUC_newClient.codUbigeo;

        maestroClientesService.setCliente({direccion:direc, nombreCompleto:rzSocial, nombreDoc:'RUC', numDoc: ruc, codUbigeo:codUbigeoClie});

        var promise = maestroClientesService.createClienteRUC_extra($scope.RUC_newClient);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModal = 1;

                      maestroClientesService.setRegExito(true);
                      $("#NuevoRUC").modal('toggle');
                      $("#clienteBuscar").modal('toggle');
                      
                      $scope.search.ruc.option = 1;
                      $scope.search.ruc.dato = $scope.RUC_newClient.ruc; //guarda el ruc si es que decide editar                          
                      
                      mensajesModalNuevoRuc();
                  },    
                  function(dataError) {
                      maestroClientesService.setRegExito(false);
                      
                      if(dataError==-1 || dataError==-2 || dataError==-3 || dataError==-4){
                        $scope.estadoModal = dataError;
                      }else{
                        $scope.estadoModal = -5;
                      }
                      
                      mensajesModalNuevoRuc();
                  }
        );
    };
    
    $scope.openModalNewConRUC = function(){
        resetConRUC();
        $scope.dataSunat = {};
        $("#NuevoRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    
    $scope.regRucManualmente_contingencia = function(){
    	$scope.loading = false;
    	$("#mensajeVerifica").hide();
    	$("#modalServicioConsultaRUCcaido").modal('toggle');
  	  	resetConRUC();
  	  	$scope.RUC_newClient.ruc = $scope.viewSunat.numRuc;
	    $scope.RUC_newClient.estadoSunat = '00';
	    $scope.RUC_newClient.condicionSunat = '00'; 
	    $scope.RUC_newClient.sexo = '';
	    
	    $scope.viewSunat.regRucManual = true;
	    
  	  	$("#NuevoRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.linkRegRucManualmente = function(){    	
    	$scope.RUC_newClient.estadoSunat = '00';
	    $scope.RUC_newClient.condicionSunat = '00'; 
	    $scope.RUC_newClient.sexo = '';
	    $scope.RUC_newClient.razonSocial = '';
        $scope.RUC_newClient.direccion = '-';
        $scope.RUC_newClient.codUbigeo = '';
        $scope.RUC_newClient.fechaNacimiento = null;
        $scope.RUC_newClient.nombreComercial = '';
        $scope.RUC_newClient.tipoPersona = '01';
        $scope.RUC_newClient.telf = '';
        $scope.RUC_newClient.email = '';
        
    	$scope.viewSunat.servicioCaido = false;
    	$scope.viewSunat.regRucManual = true;
    	
    };
    
    $scope.linkEditRucManualmente = function(){    	    	
    	$scope.rucEditar.estadoSunat = '00';
	    $scope.rucEditar.condicionSunat = '00'; 

	    $scope.viewSunat.servicioCaido = false;
    	$scope.viewSunat.regRucManual = true;
    };

    /* Factura.html*/
    $scope.consultaRUC_FAC = function(){
    	
    	$scope.loading = true;
    	$scope.viewSunat.servicioCaido = false;
    	$scope.viewSunat.regRucManual = false;
    	$scope.dataSunat = {};
    	var ruc = $scope.datoBuscar1;
    	$scope.viewSunat.numRuc = ruc;
    	
    	var promise = sunatService.consultaRUC(ruc);
        promise.then(
                      function(respItem) {
                          $scope.dataSunat = respItem.data;
                          
                          if($scope.dataSunat.status == -1 || $scope.dataSunat.status == -3){
                        	  $scope.loading = false;
                        	  $scope.viewSunat.servicioCaido = true;
                        	  $("#mensajeVerifica").hide();
                        	  $("#modalServicioConsultaRUCcaido").modal({backdrop: 'static', keyboard: false});
                          }
                          else if($scope.dataSunat.status == 0 || $scope.dataSunat.status == -2){
                        	  $scope.loading = false;
                        	  $("#mensajeVerifica").show();
                          }else{
                        	  resetConRUC();

                              $scope.RUC_newClient.ruc = $scope.dataSunat.ruc;
                              $scope.RUC_newClient.razonSocial = $scope.dataSunat.razonSocial;
                              $scope.RUC_newClient.direccion = $scope.dataSunat.direccion.trim();
                              $scope.RUC_newClient.codUbigeo = $scope.dataSunat.codUbigeo;
                              $scope.RUC_newClient.nombDepartamento = $scope.dataSunat.nombDepartamento;
                              $scope.RUC_newClient.nombProvincia = $scope.dataSunat.nombProvincia;
                              $scope.RUC_newClient.nombreUbigeo = $scope.dataSunat.nombreUbigeo;

                              $scope.RUC_newClient.estadoSunat = $scope.dataSunat.estadoSunat;
                              $scope.RUC_newClient.estadoSunatDescr = $scope.dataSunat.estadoSunatDescr;
                              $scope.RUC_newClient.condicionSunat = $scope.dataSunat.condicionSunat;
                              $scope.RUC_newClient.condicionSunatDescr = $scope.dataSunat.condicionSunatDescr;                              

                              $scope.RUC_newClient.sexo = $scope.dataSunat.sexo;
                              $scope.RUC_newClient.fechaNacimiento = $scope.dataSunat.fechaNacimiento;
                              $scope.RUC_newClient.nombreComercial = $scope.dataSunat.nombreComercial;
                              $scope.RUC_newClient.tipoPersona = $scope.dataSunat.tipoPersona;
                              $scope.RUC_newClient.tipoPersonaDesc = $scope.dataSunat.tipoPersonaDesc;
                              $scope.RUC_newClient.telf = $scope.dataSunat.telf;
                              $scope.RUC_newClient.email = $scope.dataSunat.email;
                        	  
                        	  $scope.loading = false;
                        	  $("#mensajeVerifica").hide();
                        	  
                        	  $("#NuevoRUC").modal({backdrop: 'static', keyboard: false});
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $("#mensajeVerifica").show();
                      }
        );    	
    };

    /* maestroClientes_mant.html*/
    $scope.consultaRUC_cliente = function(){
        
        $scope.loading = true;
        $scope.viewSunat.servicioCaido = false;
        $scope.viewSunat.existeRuc = false;
        $scope.viewSunat.regRucManual = false;
        $scope.dataSunat = {};
        var ruc = $scope.RUC_newClient.ruc;
        $scope.viewSunat.numRuc = ruc;
        
        var promise = sunatService.consultaRUC(ruc);
        promise.then(
                      function(respItem) {
                          $scope.dataSunat = respItem.data;
                          
                          if($scope.dataSunat.status == -1 || $scope.dataSunat.status == -3){
                        	  $scope.loading = false;
                        	  $scope.viewSunat.servicioCaido = true;
                              $("#mensajeVerifica").hide();
                          }
                          else if($scope.dataSunat.status == 0 || $scope.dataSunat.status == -2){
                              $scope.loading = false;
                              $("#mensajeVerifica").show();
                              $scope.viewSunat.existeRuc = false;
                          }else{                            
                              
                        	  $scope.RUC_newClient.ruc = $scope.dataSunat.ruc;
                              $scope.RUC_newClient.razonSocial = $scope.dataSunat.razonSocial;
                              $scope.RUC_newClient.direccion = $scope.dataSunat.direccion.trim();
                              $scope.RUC_newClient.codUbigeo = $scope.dataSunat.codUbigeo;
                              $scope.RUC_newClient.nombDepartamento = $scope.dataSunat.nombDepartamento;
                              $scope.RUC_newClient.nombProvincia = $scope.dataSunat.nombProvincia;
                              $scope.RUC_newClient.nombreUbigeo = $scope.dataSunat.nombreUbigeo;

                              $scope.RUC_newClient.estadoSunat = $scope.dataSunat.estadoSunat;
                              $scope.RUC_newClient.estadoSunatDescr = $scope.dataSunat.estadoSunatDescr;
                              $scope.RUC_newClient.condicionSunat = $scope.dataSunat.condicionSunat;
                              $scope.RUC_newClient.condicionSunatDescr = $scope.dataSunat.condicionSunatDescr;                              

                              $scope.RUC_newClient.sexo = $scope.dataSunat.sexo;
                              $scope.RUC_newClient.sexoDescr = $scope.dataSunat.sexoDescr;
                              $scope.RUC_newClient.fechaNacimiento = $scope.dataSunat.fechaNacimiento;
                              $scope.RUC_newClient.nombreComercial = $scope.dataSunat.nombreComercial;
                              $scope.RUC_newClient.tipoPersona = $scope.dataSunat.tipoPersona;
                              $scope.RUC_newClient.tipoPersonaDesc = $scope.dataSunat.tipoPersonaDesc;
                              $scope.RUC_newClient.telf = $scope.dataSunat.telf;
                              $scope.RUC_newClient.email = $scope.dataSunat.email;                                                           
                              
                              $scope.loading = false;
                              $scope.viewSunat.existeRuc = true;
                              $("#mensajeVerifica").hide();                              
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $scope.viewSunat.existeRuc = false;
                          $("#mensajeVerifica").show();
                      }
        );      
    };


    /* form Nuevo RUC cliente*/
    $("#newCRuc_ruc").keyup(function(){
    	
	        $("#mensajeVerifica").hide();
	        
	        if($scope.viewSunat.existeRuc || $scope.viewSunat.servicioCaido || $scope.viewSunat.regRucManual){	
	        	$scope.$apply(function () { 
	        		$scope.viewSunat.existeRuc = false;
	        		$scope.viewSunat.servicioCaido = false;
	        		$scope.viewSunat.regRucManual = false;
	        		$scope.RUC_newClient.razonSocial = '';
		            $scope.RUC_newClient.direccion = '-';
		            $scope.RUC_newClient.sexo = null;
		            $scope.RUC_newClient.fechaNacimiento = null;
		            $scope.RUC_newClient.nombreComercial = '';
		            $scope.RUC_newClient.tipoPersona = '01';
		            $scope.RUC_newClient.telf = '';
		            $scope.RUC_newClient.email = '';
		            $scope.RUC_newClient.codUbigeo = '';
		            $scope.RUC_newClient.estadoSunat = '00';
		            $scope.RUC_newClient.estadoSunatDescr = '';
		            $scope.RUC_newClient.condicionSunat = '00';
		            $scope.RUC_newClient.condicionSunatDescr = '';
		
		            $scope.filterUbigeo = {};	 
	        	});
	        }     
	                  
    });
        
    $scope.updateEstadoRuc = function(ruc, estado){
        var promise = maestroClientesService.updateEstadoRuc(ruc, estado);
        promise.then(
                      function(respItem) {
                          $scope.getClie_ConRUC();
                      },

                      function(errorItem) {
                      }
        );
    };
    
    $scope.abrirModalConfirm_cambiaEstadoRuc = function(clie){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Deshabilitar';
        $scope.modalOptions.bodyText= 'Está a punto de deshabilitar a éste cliente. ¿Está seguro?';
        
        if(clie.estado==0){
        	$scope.modalOptions.headerColorValue = 0; //btn-primary
        	$scope.modalOptions.headerText = 'Habilitar';
        	$scope.modalOptions.bodyText = 'Está a punto de habilitar a éste cliente. ¿Está seguro?';          
        }

        $scope.modalOptions.data.clie = clie;

        $scope.modalOptions.action = $scope.cambiaEstadoRuc;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.cambiaEstadoRuc = function(){
        var estado = ($scope.modalOptions.data.clie.estado==1 ? 0 : 1);
        $scope.updateEstadoRuc($scope.modalOptions.data.clie.ruc, estado);
    };
    
    $scope.openModalActualizarRUC = function(itemRuc){
        $scope.rucEditar = angular.copy(itemRuc);

        if($scope.rucEditar.tipoPersona == null || $scope.rucEditar.tipoPersona == '' || $scope.rucEditar.tipoPersona == 0) {
        	$scope.rucEditar.tipoPersona = '02';
        } //persona juridica
        
        $scope.filterUbigeo.cod_dep = $scope.rucEditar.codNivel1;
        $scope.filterUbigeo.cod_prov = $scope.rucEditar.codNivel2;

        $("#mensajeVerifica2").hide();

        $("#EditarRUC").modal({backdrop: 'static', keyboard: false});
    };

    /* maestroClientes_mant.html*/
    $scope.consultaRUC_cliente2 = function(){
        
        $scope.loading = true;
        $scope.viewSunat.servicioCaido = false;
        $scope.viewSunat.regRucManual = false;
        $scope.dataSunat = {};

        var ruc = $scope.rucEditar.ruc;
        $scope.viewSunat.numRuc = ruc;
        
        var promise = sunatService.consultaRUC(ruc);
        promise.then(
                      function(respItem) {
                          $scope.dataSunat = respItem.data;
                          
                          if($scope.dataSunat.status == -1 || $scope.dataSunat.status == -3){
                        	  $scope.loading = false;
                        	  $scope.viewSunat.servicioCaido = true;
                              $("#mensajeVerifica2").hide();
                          }
                          else if($scope.dataSunat.status == 0 || $scope.dataSunat.status == -2){
                              $scope.loading = false;
                              $("#mensajeVerifica2").show();
                          }else{                            
                        	  $scope.rucEditar.ruc = $scope.dataSunat.ruc;
                              $scope.rucEditar.razonSocial = $scope.dataSunat.razonSocial;
                              $scope.rucEditar.direccion = $scope.dataSunat.direccion.trim();
                              $scope.rucEditar.codUbigeo = $scope.dataSunat.codUbigeo;
                              $scope.rucEditar.nombDepartamento = $scope.dataSunat.nombDepartamento;
                              $scope.rucEditar.nombProvincia = $scope.dataSunat.nombProvincia;
                              $scope.rucEditar.nombreUbigeo = $scope.dataSunat.nombreUbigeo;

                              $scope.rucEditar.estadoSunat = $scope.dataSunat.estadoSunat;
                              $scope.rucEditar.estadoSunatDescr = $scope.dataSunat.estadoSunatDescr;
                              $scope.rucEditar.condicionSunat = $scope.dataSunat.condicionSunat;
                              $scope.rucEditar.condicionSunatDescr = $scope.dataSunat.condicionSunatDescr;                              

                              $scope.rucEditar.sexo = $scope.dataSunat.sexo;
                              $scope.rucEditar.sexoDescr = $scope.dataSunat.sexoDescr;
                              $scope.rucEditar.fechaNacimiento = $scope.dataSunat.fechaNacimiento;
                              $scope.rucEditar.nombreComercial = $scope.dataSunat.nombreComercial;
                              $scope.rucEditar.tipoPersona = $scope.dataSunat.tipoPersona;
                              $scope.rucEditar.tipoPersonaDesc = $scope.dataSunat.tipoPersonaDesc;
                              $scope.rucEditar.telf = $scope.dataSunat.telf;
                              $scope.rucEditar.email = $scope.dataSunat.email;
  
                              $scope.loading = false;
                              $("#mensajeVerifica2").hide();
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $("#mensajeVerifica2").show();
                      }
        );      
    };
    
    
    $scope.abrirModalConfirm_updateClienteRuc = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Guardar Cambios';
        $scope.modalOptions.bodyText= 'Se procederá a registrar los cambios en el cliente. ¿Continuar?';

        $scope.modalOptions.action = $scope.updateClienteRuc;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.updateClienteRuc = function(){
        $scope.dataView.ruc.numero = $scope.rucEditar.ruc;

        var promise = maestroClientesService.updateClienteRuc($scope.rucEditar);
        promise.then(
                      function(respItem) {
                          $scope.estadoModal = 1;
                          $("#EditarRUC").modal("hide");
                          $scope.getClie_ConRUC();
                          mensajesModalEditarRuc();
                      },
                      function(errorItem) {
                          $scope.estadoModal = 2;
                          mensajesModalEditarRuc();
                      }
         );
    };
    
    function resetConRUC(){
        $scope.RUC_newClient = {
                ruc: "",
                razonSocial: "",
                nombreComercial: "",
                direccion: "-",
                nombre: "",
                apPat: "",
                apMat: "",
                email: "",
                email2: "",
                fechaReg: null,
                telf: "",
                fechaNacimiento: null,
                sexo: null,
                estado: 1,
                status: null,
                usuCrea: null,
                tipoPersona: '01',
                codUbigeo:''
        };
        $scope.dataView.ruc.opCliente = 1;
        $scope.filterUbigeo = {};
        $scope.viewSunat.existeRuc = false;
        $scope.viewSunat.servicioCaido = false;
        $scope.formNuevoRuc.$setPristine(); //reset Form
    };
    
    $scope.$on("limpiarTablaRUC", function (event, args) {
        $("#results1").hide();
        $("#results2").hide();
        $("#results3").hide();
        $("#results4").hide();
        $("#noMatches").hide();
        $("#datoConRuc").val("");
        $("#datoSinRuc").val("");
        $("#datoAlumno").val("");
        $("#datoServidor").val("");

        $scope.initVariables();
        
        $scope.tipoDoc_sinruc = $scope.listaDocIDentidad[0].idDoc;
        $scope.userTipoDoc1 = $scope.listaDocIDentidad[0].idDoc;
        $scope.userTipoDoc2 = $scope.listaDocIDentidad[0].idDoc;
                
        maestroClientesService.setNoData(false);
        
        $scope.estadoBtnValidarDNI = false;
        $scope.estadoBtnRegistrarOtroDoc = false;
        $scope.estadoBtnValidarRUC = false;

        $("#divRegClieRUC").hide();
        
        $("#divRegClieSinRUC").hide();
        $("#divRegClieOtroDOC").hide();
        
        maestroClientesService.setRegExito(false);

    });

    $('#NuevoRUC').on('hidden.bs.modal', function () {
    	$scope.bodyPaddingRightZero();
    	$scope.$apply(function () { 
    		$scope.viewSunat.regRucManual = false;
    	});
    });
    $('#EditarRUC').on('hidden.bs.modal', function () {
    	$scope.bodyPaddingRightZero();
    	$scope.$apply(function () { 
    		$scope.viewSunat.regRucManual = false;
    	});
    });
    
    //**************************** FIN CLIENTES CON RUC **************************************
   
    
    //******************************* FUNCIONES ******************************************
    //**************************** CLIENTES SIN RUC **************************************
    
 
    /* maestroClientes_mant.html  boleta.html */
    $('#sinruc_tipDoc').change(function() {
        if(this.value=='number:1'){ //DNI  
        	$scope.criterioBusquedaElegida2="D.N.I.";
        	$scope.criterioPlaceHolder2 = "Número de DNI";
        	$("#datoSinRuc").unmask();
            $("#datoSinRuc").attr('maxlength','8');         // 8 digits exactly
            $("#datoSinRuc").mask('00000000');
        }
        else if(this.value=='number:4'){ //CARNET EXTRANJERIA   
        	$scope.criterioBusquedaElegida2="CARNÉ DE EXTRANJERIA";
        	$scope.criterioPlaceHolder2 = "Número de carné";
            $("#datoSinRuc").unmask();
            $("#datoSinRuc").attr('maxlength','12');        // MAX 12 characters
            $("#datoSinRuc").mask('AAAAAAAAAAAA');
        }
        else if(this.value=='number:7'){ //PASAPORTE
        	$scope.criterioBusquedaElegida2="PASAPORTE";
        	$scope.criterioPlaceHolder2 = "Número de pasaporte";
            $("#datoSinRuc").unmask();
            $("#datoSinRuc").attr('maxlength','12');        // MAX 12 characters
            $("#datoSinRuc").mask('AAAAAAAAAAAA');
        }
        else if(this.value=='number:9'){ //OTROS
        	$scope.criterioBusquedaElegida2="Documento de Identidad";
        	$scope.criterioPlaceHolder2 = "Número de documento de identidad";
            $("#datoSinRuc").unmask();
            $("#datoSinRuc").attr('maxlength','15');         // MAX 15 characters
            $("#datoSinRuc").mask('AAAAAAAAAAAAAAA');
        }
        
        $("#divRegClieSinRUC").hide();
        $("#divRegClieOtroDOC").hide();
        $("#datoSinRuc").val("");
        $scope.datoBuscar2 = "";
        $('#datoSinRuc').focus();
    });
    
    $scope.sinRUC_optionChange=function(){
        switch ($scope.userCriterio.criterioIdxNoRUC) {
            case "2":
                $scope.datoBuscar2 = "";
                $scope.selectDisabled1 = false;
                
                if($scope.userTipoDoc1==1){ //DNI 
                	$scope.criterioBusquedaElegida2="D.N.I.";
                	$scope.criterioPlaceHolder2 = "Número de DNI";
                    $("#datoSinRuc").unmask();
                    $("#datoSinRuc").attr('maxlength','8');         // 8 digits exactly
                    $("#datoSinRuc").mask('00000000');
                }
                else if($scope.userTipoDoc1==4){ //CARNET EXTRANJERIA 
                	$scope.criterioBusquedaElegida2="CARNÉ DE EXTRANJERIA";
                	$scope.criterioPlaceHolder2 = "Número de carné";
                    $("#datoSinRuc").unmask();
                    $("#datoSinRuc").attr('maxlength','12');        // MAX 12 characters
                    $("#datoSinRuc").mask('AAAAAAAAAAAA');
                }
                else if($scope.userTipoDoc1==7){ //PASAPORTE
                	$scope.criterioBusquedaElegida2="PASAPORTE";
                	$scope.criterioPlaceHolder2 = "Número de pasaporte";
                    $("#datoSinRuc").unmask();
                    $("#datoSinRuc").attr('maxlength','12');        // MAX 12 characters
                    $("#datoSinRuc").mask('AAAAAAAAAAAA');
                }
                else if($scope.userTipoDoc1==9){ //OTROS
                	$scope.criterioBusquedaElegida2="Documento de Identidad";
                	$scope.criterioPlaceHolder2 = "Número de documento de identidad";
                    $("#datoSinRuc").unmask();
                    $("#datoSinRuc").attr('maxlength','15');         // MAX 15 characters
                    $("#datoSinRuc").mask('AAAAAAAAAAAAAAA');
                }
                
                
                $("#noMatches").hide();
                
                maestroClientesService.setNoData(false);
                $("#divRegClieSinRUC").hide();
                $("#divRegClieOtroDOC").hide();
                break;
            case "5":
                $scope.criterioBusquedaElegida2="Apellidos o Nombres";
                $scope.criterioPlaceHolder2 = "Apellidos o Nombres";
                $scope.datoBuscar2 = "";
                $scope.selectDisabled1 = true;
                $("#datoSinRuc").unmask();
                $("#datoSinRuc").attr('maxlength','100');
                $("#datoSinRuc").mask('B', $scope.maskOptions_letrasYEspacios);
                $("#noMatches").hide();
                
                maestroClientesService.setNoData(false);
                $("#divRegClieSinRUC").hide();
                $("#divRegClieOtroDOC").hide();
                break;
        };
    };
    
    function mensajesModalNuevoSinRuc(){
        switch ($scope.estadoModal){
            case 1:
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'El cliente se ha registrado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'Este número de documento ya fue registrado anteriormente en el sistema ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -2: 
                $.gritter.add({
                    title: 'Registrar cliente',
                    text: 'Hubo un error al registrar el cliente ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    
    function mensajesModalEditarSinRuc(){
        switch ($scope.estadoModal){
            case 1: 
                $.gritter.add({
                    title: 'Actualizar cliente',
                    text: 'El cliente se ha actualizado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Actualizar cliente',
                    text: 'Este número de documento ya fue registrado anteriormente en el sistema ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -2: 
                $.gritter.add({
                    title: 'Actualizar cliente',
                    text: 'Hubo un error al actualizar el cliente ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
    $scope.buscarClieXTipoDoc_numDoc = function(tipoDoc, numDoc){
        var promise = maestroClientesService.buscarClienteXTipoDoc_numDoc(tipoDoc, numDoc);
        promise.then(
                      function(respLista) { 
                    	  console.log(respLista.data);
                          $scope.tableParams2 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                          $scope.tableClienteSinRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                          maestroClientesService.cliente=respLista.data;
                          $("#results2").show();
                          $("#noMatches").hide();
                          
                      },
                      function(errorLista) {
                          $("#results2").hide();
                      }
            );
    };
    $scope.buscaClierXNombreCompleto = function(nombCompleto){
        var promise = maestroClientesService.buscarClienteXNombreCompleto(nombCompleto);
        promise.then(
                      function(respLista) { 
                          $scope.tableParams2 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                          $scope.tableClienteSinRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                          maestroClientesService.cliente=respLista.data;
                          $("#results2").show();
                          $("#noMatches").hide();
                      },
                      function(errorLista) {
                          $("#results2").hide();
                      }
            );
    };

    $scope.getClie_SinRUC=function(){
        if ($scope.search.sinruc.option == 2) {
                $scope.buscarClieXTipoDoc_numDoc($scope.search.sinruc.tipodoc, $scope.search.sinruc.dato);
        }else if ($scope.search.sinruc.option == 5){
                $scope.buscaClierXNombreCompleto($scope.search.sinruc.dato);
        };
    };
    
    $scope.handlePatternNumDoc = (function() {
      var regexDNI = /^\d{8,8}$/; //DNI
      var regexAlfaNum = /^[A-Za-z0-9]{1,15}$/; //CARNET EXTRANJERIA, PASAPORTE, OTROS
      return {
        test: function(value) {
            if( $scope.SINRUC_client.tipoDoc == 1 ) {
                  return regexDNI.test(value);
              }else{
                return regexAlfaNum.test(value);
              } 
        }
      };
    })();
    
    /* maestroClientes_mant.html  en Form Registrar Cliente*/
    $( "#tipoDocSinRUC" ).change(function() {
        var tipoDoc = this.value;
        
        $("#sinruc_numDoc").val("");
        
        if(tipoDoc=='number:1'){ //DNI
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','8');
            $("#sinruc_numDoc").mask('00000000');
        } 
        else if(tipoDoc=='number:4' || tipoDoc=='number:7'){ //CARNET DE EXTRANJERIA Y PASSAPORTE
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','12');
            $("#sinruc_numDoc").mask('AAAAAAAAAAAA');
        }
        else if(tipoDoc=='number:9'){ //OTROS
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','15');
            $("#sinruc_numDoc").mask('AAAAAAAAAAAAAAA');
        }
        else{
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','8');
            $("#sinruc_numDoc").mask('00000000');
        }

        $("#mensajeVerificaDNI").hide();
        $("#mensajeServicioDNI").hide();
        $scope.SINRUC_client.numDoc = "";
        
        $scope.viewReniec.existeDNI = false;
        $scope.SINRUC_client.nomCompleto = '';

        $scope.SINRUC_client.codUbigeo = '';
        $scope.SINRUC_client.sexo = '';
        $scope.SINRUC_client.fechaNac = '';
        $scope.SINRUC_client.direccion = '-';
        $scope.SINRUC_client.telefono = '';
        $scope.SINRUC_client.email = '';

        $scope.filterUbigeo = {};
        
        $scope.formSinRUC.$setPristine(); //reset Form
        
        $('#sinruc_numDoc').focus();
    });
    
    
    $("#datoSinRuc").keyup(function(){
        var parseDato=$("#datoSinRuc").val().trim();
        if(parseDato!="" && parseDato!="."){
            $scope.search.sinruc.dato = parseDato;
            $scope.search.sinruc.option = $scope.userCriterio.criterioIdxNoRUC;
            $scope.search.sinruc.tipodoc = $scope.userTipoDoc1;
            var promise = null; 
            switch ($scope.userCriterio.criterioIdxNoRUC) {
                case "2":
                    if($scope.viewMode == 1) {
                    	promise = maestroClientesService.buscarClienteXTipoDoc_numDoc($scope.userTipoDoc1, parseDato); 
                    }
                    else {
                    	promise = maestroClientesService.buscarClienteXTipoDoc_numDocHabilitados($scope.userTipoDoc1, parseDato);
                    }
                    break;
                case "5":
                    if($scope.viewMode == 1) {
                    	promise = maestroClientesService.buscarClienteXNombreCompleto(parseDato); 
                    }
                    else {
                    	promise = maestroClientesService.buscarClienteXNombreCompletoHabilitados(parseDato); 
                    }
                    break;
            };
            
            promise.then(
                           function(respLista) { 
                                 if(respLista.data==0){
                                    $("#results2").hide();
                                    $("#noMatches").show();
                                    
                                    maestroClientesService.setNoData(true);
                                    if($scope.userCriterio.criterioIdxNoRUC==2 && $scope.userTipoDoc1==1){
                                    	$("#divRegClieSinRUC").show();
                                    	
                                    	if(parseDato!="" && parseDato.length==8){				
                        	    			$scope.estadoBtnValidarDNI = true;
                        	        	}else{
                        	        		$scope.estadoBtnValidarDNI = false;
                        	        	}
                                    }else if($scope.userCriterio.criterioIdxNoRUC==2 && $scope.userTipoDoc1!=1){
                                    	$("#divRegClieOtroDOC").show();
                                    }
                                 }else{
                                    $scope.tableParams2 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                                    $scope.tableClienteSinRuc = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                                    $("#results2").show();
                                    $("#noMatches").hide();
                                    
                                    maestroClientesService.setNoData(false);
                                    $("#divRegClieSinRUC").hide();
                                    $("#divRegClieOtroDOC").hide();
                                 }
                            },
                            function(errorLista) {
                                  $("#results2").hide();
                                  $("#noMatches").show();
                                  
                                  maestroClientesService.setNoData(true);
                                  if($scope.userCriterio.criterioIdxNoRUC==2 && $scope.userTipoDoc1==1) {
                                	  $("#divRegClieSinRUC").show();
                                  }
                                  else if($scope.userCriterio.criterioIdxNoRUC==2 && $scope.userTipoDoc1!=1) {
                                	  $("#divRegClieOtroDOC").show();
                                  }
                            }
            );       
        }else{
            $("#results2").hide();
            $("#noMatches").hide();
            
            maestroClientesService.setNoData(false);
            $("#divRegClieSinRUC").hide();
            $("#divRegClieOtroDOC").hide();
        }
    });
    
    $scope.createClientSinRuc = function(cliente){    	
        var promise = maestroClientesService.createClienteSinRUC(cliente);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModal = 1;
                      $("#ModalSINRUC").modal("hide");
                      $scope.search.sinruc.option = 2;
                      $scope.search.sinruc.tipodoc = cliente.tipoDoc;
                      $scope.search.sinruc.dato = cliente.numDoc;
                      $scope.getClie_SinRUC();
                      mensajesModalNuevoSinRuc();  
                  },    
                  function(dataError) {
                	  console.log("holi")
                      $scope.estadoModal = dataError;
                      mensajesModalNuevoSinRuc();
                  }
        );
        
    };
    
    $scope.openModalNewSinRUC = function(){
        resetSinRUC();
        $scope.dataView.title = "REGISTRAR CLIENTE";
        $("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    /* Boleta.html Registro Otros Tipos Documentos */
    $scope.openModalNewSinRUC_BOL = function(){
    	var numDoc = $scope.datoBuscar2;
    	var tipDoc = $scope.userTipoDoc1;
        resetSinRUC();
        
        if(tipDoc==1){ //DNI
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','8');
            $("#sinruc_numDoc").mask('00000000');
        } 
        else if(tipDoc==4 || tipDoc==7){ //CARNET DE EXTRANJERIA Y PASSAPORTE
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','12');
            $("#sinruc_numDoc").mask('AAAAAAAAAAAA');
        }
        else if(tipDoc==9){ //OTROS
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','15');
            $("#sinruc_numDoc").mask('AAAAAAAAAAAAAAA');
        }
        else{
            $("#sinruc_numDoc").unmask();
            $("#sinruc_numDoc").attr('maxlength','8');
            $("#sinruc_numDoc").mask('00000000');
        }
        
        $scope.SINRUC_client.tipoDoc = tipDoc;
        $scope.SINRUC_client.numDoc = numDoc;
        
        $("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    comprobarExpRegular=function(nroDni){
    	  var re = /\d{8}/;    
          var OK = re.exec(nroDni);
          if (!OK){
        	  return false;
          }else{
        	  return true;
          }  
    }
    /* Boleta.html consulta Reniec */
    $scope.consultaDNI_BOL = function(){
    	
    	$scope.loading = true;
    	$scope.dataReniec = {};
    	var dni = $scope.datoBuscar2;
    	
    	var promise = reniecService.consultaDNI(dni);
        promise.then(
                      function(respItem) {                          
                          $scope.dataReniec = respItem.data;
                          
                          if($scope.dataReniec.status == -1 || $scope.dataReniec.status == -3){
                        	  $scope.loading = false;
                        	  $("#mensajeVerificaDNI").hide();
                        	  $("#mensajeDNI").modal({backdrop: 'static', keyboard: false});
                          }
                          else if($scope.dataReniec.status == 0){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI no encontrado';
                        	  $("#mensajeVerificaDNI").show();                        	  
                          }
                          else if($scope.dataReniec.status == -2){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'El formato del DNI es inválido';
                        	  $("#mensajeVerificaDNI").show();                        	  
                          }
                          else if($scope.dataReniec.status == -4){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra cancelado en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();                        	  
                          }
                          else if($scope.dataReniec.status == -5){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra restrigido en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();                        	  
                          }
                          else if($scope.dataReniec.status == -6){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra observado en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();                        	  
                          }
                          else {
                        	  resetSinRUC();
                        	  $("#sinruc_numDoc").unmask();
                              $("#sinruc_numDoc").attr('maxlength','8');
                              $("#sinruc_numDoc").mask('00000000');
                                
                              $scope.SINRUC_client.numDoc = $scope.dataReniec.numDni;
                              $scope.SINRUC_client.nomCompleto = $scope.dataReniec.apPrimer +' '+ $scope.dataReniec.apSegundo +' '+ $scope.dataReniec.nombres;
                              $scope.SINRUC_client.tipoDoc = 1; //DNI
                              $scope.SINRUC_client.sexo = $scope.dataReniec.genero;
                              $scope.SINRUC_client.fechaNac = formatoFecha($scope.dataReniec.fechaNacimiento);
                              
                              console.log("fechaNac: "+ $scope.SINRUC_client.fechaNac);
                              
                              $scope.loading = false;
                              $("#mensajeVerificaDNI").hide();
                              
                              $("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $scope.viewReniec.mensajeVerDNI = 'DNI no encontrado';
                          $("#mensajeVerificaDNI").show();
                      }
        );    	
    };

    /* maestroClientes_mant.html consulta Reniec */
    $scope.consultaDNI_Cliente = function(){
        
        $scope.loading = true;
        $scope.dataReniec = {};

        var dni = $scope.SINRUC_client.numDoc;
        
        var promise = reniecService.consultaDNI(dni);
        promise.then(
                      function(respItem) {
                          $scope.dataReniec = respItem.data;

                          //$scope.nroDni=comprobarExpRegular($scope.dataReniec.nroDni);
                          
                          if($scope.dataReniec.status == -1 || $scope.dataReniec.status == -3){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.existeDNI = false;
                              $scope.viewReniec.estadoServ= false;
                              $("#mensajeVerificaDNI").hide();
                              $("#mensajeServicioDNI").show();
                          }
                          else if($scope.dataReniec.status == 0){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.existeDNI = false;
                        	  $scope.viewReniec.estadoServ= false;                        	  
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI no encontrado';
                    		  $("#mensajeVerificaDNI").show();
                              $("#mensajeServicioDNI").hide();
                          }
                          else if($scope.dataReniec.status == -2){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'El formato del DNI es inválido';
                        	  $("#mensajeVerificaDNI").show();
                              $("#mensajeServicioDNI").hide();                     	  
                          }
                          else if($scope.dataReniec.status == -4){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra cancelado en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();
                              $("#mensajeServicioDNI").hide();                      	  
                          }
                          else if($scope.dataReniec.status == -5){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra restrigido en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();
                              $("#mensajeServicioDNI").hide();                       	  
                          }
                          else if($scope.dataReniec.status == -6){
                        	  $scope.loading = false;
                        	  $scope.viewReniec.mensajeVerDNI = 'DNI se encuentra observado en el RUIPN';
                        	  $("#mensajeVerificaDNI").show();
                              $("#mensajeServicioDNI").hide();                      	  
                          }
                          else {
                        	  $scope.SINRUC_client.numDoc = $scope.dataReniec.numDni;
                              $scope.SINRUC_client.nomCompleto = $scope.dataReniec.apPrimer +' '+ $scope.dataReniec.apSegundo +' '+ $scope.dataReniec.nombres;
                              $scope.SINRUC_client.tipoDoc = 1; //DNI

                              if($scope.SINRUC_client.idCliente == null || $scope.SINRUC_client.idCliente == ''){
                                 $scope.filterUbigeo = {};
                                 $scope.SINRUC_client.codUbigeo = '';
                                 $scope.SINRUC_client.sexo = $scope.dataReniec.genero;
                                 $scope.SINRUC_client.fechaNac = formatoFecha($scope.dataReniec.fechaNacimiento);
                                 $scope.SINRUC_client.direccion = '-';
                                 $scope.SINRUC_client.telefono = '';
                                 $scope.SINRUC_client.email = '';
                              }
                                  
                              $scope.loading = false;
                              $scope.viewReniec.existeDNI = true;
                              $scope.viewReniec.estadoServ= true;
                              $("#mensajeVerificaDNI").hide();
                              $("#mensajeServicioDNI").hide();  
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $scope.viewReniec.existeDNI = false;
                          $scope.viewReniec.estadoServ= false;
                          $("#mensajeVerificaDNI").hide();
                          $("#mensajeServicioDNI").show();
                      }
        );      
    };
    
    function formatoFecha(strFecha){
    	if(!strFecha) 
    		return '';
    	var fecha = strFecha.trim();
    	if(fecha.length!=8) 
    		return '';
    	return fecha.substring(6,8) +'/'+fecha.substring(4,6)+'/'+fecha.substring(0,4);
    }
    
    $scope.limpiar=function(){
    	resetSinRUC();
    	$scope.dataReniec = {};
    	$("#mensajeVerificaDNI").hide();
    	$("#mensajeServicioDNI").hide();
    	$scope.viewReniec.existeDNI = false;
        $scope.viewReniec.estadoServ= false;
    }
    
    $scope.limpiar2=function(){
    	resetSinRUC();
    	$scope.dataReniec = {};
    	$("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    }
    /* form Nuevo SINRUC cliente*/
    $("#sinruc_numDoc").keyup(function(){
        if($scope.SINRUC_client.tipoDoc == 1){
            $("#mensajeVerificaDNI").hide();
            $("#mensajeServicioDNI").hide();

            if($scope.viewReniec.existeDNI == true){
            	$scope.$apply(function () {
	            	$scope.viewReniec.existeDNI = false;
	                $scope.SINRUC_client.nomCompleto = '';
	                $scope.SINRUC_client.tipoDoc = 1; //DNI
	
	                $scope.filterUbigeo = {};
	                $scope.SINRUC_client.codUbigeo = '';
	                $scope.SINRUC_client.sexo = '';
	                $scope.SINRUC_client.fechaNac = '';
	                $scope.SINRUC_client.direccion = '-';
	                $scope.SINRUC_client.telefono = '';
	                $scope.SINRUC_client.email = '';
	
	                $scope.filterUbigeo = {};                
            	});
            }  
        }      
    });
    
    
    $scope.updateClienteSinRuc = function(cliente){
        var promise = maestroClientesService.updateClienteSinRuc(cliente);
        promise.then(
                      function(respItem) {
                          $scope.estadoModal = 1;
                          $("#ModalSINRUC").modal("hide");
                          $scope.getClie_SinRUC();
                          mensajesModalEditarSinRuc();
                      },
                      function(dataError) {
                          $scope.estadoModal = dataError;
                          mensajesModalEditarSinRuc();
                      }
            );
    };
    
    
    $scope.abrirModalConfirm_submitSinRUC = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        
        if($scope.SINRUC_client.idCliente==null){ 
        	$scope.modalOptions.headerText = 'Registrar';
        	$scope.modalOptions.bodyText = 'Está a punto de registrar el nuevo cliente. ¿Desea continuar?';
        }else{
        	$scope.modalOptions.headerText = 'Guardar Cambios';
        	$scope.modalOptions.bodyText = 'Se procederá a registrar los cambios en el cliente. ¿Continuar?';
        }


        $scope.modalOptions.action = $scope.submitSinRUC;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.submitSinRUC = function() {
        if($scope.SINRUC_client.idCliente==null){       
            $scope.createClientSinRuc($scope.SINRUC_client);    
        }else{
            $scope.updateClienteSinRuc($scope.SINRUC_client);  
        }
    };
    
    $scope.abrirModalConfirm_submitSinRUC_extra = function(){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Registrar';
        $scope.modalOptions.bodyText= 'Está a punto de registrar el nuevo cliente. ¿Desea continuar?';

        $scope.modalOptions.action = $scope.submitSinRUC_extra;

        $('#modalConfirm').modal({show: true});
    };
    
    /* BOLETA.html */
    $scope.submitSinRUC_extra = function() {        
        var nombCompleto = $scope.SINRUC_client.nomCompleto;
        var direc = $scope.SINRUC_client.direccion.toUpperCase();
        var numbDoc = $scope.SINRUC_client.numDoc;

        var tipoDoc = $scope.SINRUC_client.tipoDoc;
		
		var nameTipoDoc = 'DNI';
		
		if(tipoDoc=='1' || tipoDoc==1) 
			nameTipoDoc = 'DNI';
		if(tipoDoc=='4' || tipoDoc==4) 
			nameTipoDoc = 'CARNÉ DE EXTRANJERIA';
		if(tipoDoc=='7' || tipoDoc==7) 
			nameTipoDoc = 'PASAPORTE';
		if(tipoDoc=='9' || tipoDoc==9) 
			nameTipoDoc = 'OTROS';

        maestroClientesService.setCliente({direccion:direc, nombreCompleto:nombCompleto, nombreDoc:nameTipoDoc, numDoc: numbDoc});
        
        var promise = maestroClientesService.createClienteSinRUC_extra($scope.SINRUC_client);
        promise.then(
                  function(respItem) { 
                      $scope.estadoModal = 1;
                      
                      maestroClientesService.setRegExito(true);
                      $("#ModalSINRUC").modal('toggle');
                      $("#clienteBuscar").modal('toggle');
                      
                      $scope.search.sinruc.option = 2;
                      $scope.search.sinruc.tipodoc = $scope.SINRUC_client.tipoDoc;
                      $scope.search.sinruc.dato = $scope.SINRUC_client.numDoc;

                      resetSinRUC();
                      mensajesModalNuevoSinRuc();  
                  },    
                  function(dataError) {
                	  maestroClientesService.setRegExito(false);
                      
                      $scope.estadoModal = dataError;
                      mensajesModalNuevoSinRuc();
                  }
        );
        
    };
          
    $scope.openModalActualizarSinRUC = function(cliente){
        $scope.SINRUC_client = angular.copy(cliente);

        $scope.filterUbigeo.cod_dep = cliente.codNivel1;
        $scope.filterUbigeo.cod_prov = cliente.codNivel2;

        $scope.dataView.title = "EDITAR CLIENTE";
        $("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.updateEstadoSinRuc = function(cliente, estado){
        var promise = maestroClientesService.updateEstadoSinRuc(cliente.idCliente, estado);
        promise.then(
                      function(respItem) {
                          $scope.getClie_SinRUC();
                      },
                      function(errorItem) {
                      }
            );
    };
   
    
    $scope.abrirModalConfirm_cambiaEstadoSinRuc = function(clie){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Deshabilitar';
        $scope.modalOptions.bodyText= 'Está a punto de deshabilitar a éste cliente. ¿Está seguro?';
        
        if(clie.estado==0){
        	$scope.modalOptions.headerColorValue = 0; //btn-primary
        	$scope.modalOptions.headerText = 'Habilitar';
        	$scope.modalOptions.bodyText = 'Está a punto de habilitar a éste cliente. ¿Está seguro?';          
        }

        $scope.modalOptions.data.clie = clie;

        $scope.modalOptions.action = $scope.cambiaEstadoSinRuc;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.cambiaEstadoSinRuc = function(){
        var estado = ($scope.modalOptions.data.clie.estado==1 ? 0 : 1);
        $scope.updateEstadoSinRuc($scope.modalOptions.data.clie, estado);
    };
    
    
    
    resetSinRUC = function(){
        $scope.SINRUC_client={
                idCliente: null,
                tipoDoc: 1,
                numDoc: "",
                nombres: "",
                apePat: "",
                apeMat: "",
                direccion: "-",
                fechaNac: "",
                sexo: "",
                email: "",
                email2: "",
                abrev: "",
                nomCompleto: "",
                nombreDoc: "",
                telefono:"",
                fechaReg: null,
                estado: 1,
                status: null,
                usuCrea: null,
                codUbigeo:''
            };

        $scope.filterUbigeo = {};
        $scope.viewReniec.existeDNI = false;
        $scope.formSinRUC.$setPristine(); //reset Form
    };

    $('#ModalSINRUC').on('hidden.bs.modal', function () {
      $scope.bodyPaddingRightZero();
    });
    
    
    //**************************** FIN CLIENTES SIN RUC **************************************
    
    
    //******************************* FUNCIONES ******************************************
    //****************************** ALUMNOS SUM *****************************************
    $scope.aluSUM_optionChange=function(){
        switch ($scope.userCriterio.criterioIdxSUM) {
            case "3":
                $scope.criterioBusquedaElegida3="Código de Matricula";
                $scope.criterioPlaceHolder3 = "Código de Matricula";
                $scope.datoBuscar3 = "";
                $scope.selectDisabled2 = true;
                $("#datoAlumno").unmask();
                $("#datoAlumno").attr('maxlength','8');         
                $("#datoAlumno").mask('00000000');
                $("#noMatches").hide();
                break;
            case "2":
                $scope.datoBuscar3 = "";
                $scope.selectDisabled2 = false;
                
                if($scope.userTipoDoc2==1){ //DNI  
                	$scope.criterioBusquedaElegida3="D.N.I.";
                	$scope.criterioPlaceHolder3 = "Número de DNI";
                    $("#datoAlumno").unmask();
                    $("#datoAlumno").attr('maxlength','8');         // 8 digits exactly
                    $("#datoAlumno").mask('00000000');
                }
                else if($scope.userTipoDoc2==4){ //CARNET EXTRANJERIA
                	$scope.criterioBusquedaElegida3="CARNÉ DE EXTRANJERIA";
                	$scope.criterioPlaceHolder3 = "Número de carné";
                    $("#datoAlumno").unmask();
                    $("#datoAlumno").attr('maxlength','12');        // MAX 12 characters
                    $("#datoAlumno").mask('AAAAAAAAAAAA');
                }
                else if($scope.userTipoDoc2==7){  //PASAPORTE
                	$scope.criterioBusquedaElegida3="PASAPORTE";
                	$scope.criterioPlaceHolder3 = "Número de pasaporte";
                    $("#datoAlumno").unmask();
                    $("#datoAlumno").attr('maxlength','12');        // MAX 12 characters
                    $("#datoAlumno").mask('AAAAAAAAAAAA');
                }
                else if($scope.userTipoDoc2==0){ //OTROS
                	$scope.criterioBusquedaElegida3="Documento de Identidad";
                	$scope.criterioPlaceHolder3 = "Número de documento de identidad";
                    $("#datoAlumno").unmask();
                    $("#datoAlumno").attr('maxlength','15');         // MAX 15 characters
                    $("#datoAlumno").mask('AAAAAAAAAAAAAAA');
                }
                
                $("#noMatches").hide();
                break;
            case "5":
                $scope.criterioBusquedaElegida3="Apellidos o Nombres";
                $scope.criterioPlaceHolder3 = "Apellidos o Nombres";
                $scope.datoBuscar3 = "";
                $scope.selectDisabled2 = true;
                $("#datoAlumno").unmask();
                $("#datoAlumno").attr('maxlength','100'); 
                $("#datoAlumno").mask('B', $scope.maskOptions_letrasYEspacios);
                $("#noMatches").hide();
                break;
        };
    };
    
    /* maestroClientes_mant.html  boleta.html */
    $('#sum_tipDoc').change(function() {
        if(this.value=='number:1'){ //DNI  
        	$scope.criterioBusquedaElegida3="D.N.I.";
        	$scope.criterioPlaceHolder3 = "Número de DNI";
            $("#datoAlumno").unmask();
            $("#datoAlumno").attr('maxlength','8');         // 8 digits exactly
            $("#datoAlumno").mask('00000000');
        }
        else if(this.value=='number:4'){ //CARNET EXTRANJERIA 
        	$scope.criterioBusquedaElegida3="CARNÉ DE EXTRANJERIA";
        	$scope.criterioPlaceHolder3 = "Número de carné";
            $("#datoAlumno").unmask();
            $("#datoAlumno").attr('maxlength','12');        // MAX 12 characters
            $("#datoAlumno").mask('AAAAAAAAAAAA');
        }
        else if(this.value=='number:7'){ //PASAPORTE
        	$scope.criterioBusquedaElegida3="PASAPORTE";
        	$scope.criterioPlaceHolder3 = "Número de pasaporte";
            $("#datoAlumno").unmask();
            $("#datoAlumno").attr('maxlength','12');        // MAX 12 characters
            $("#datoAlumno").mask('AAAAAAAAAAAA');
        }
        else if(this.value=='number:0'){ //OTROS
        	$scope.criterioBusquedaElegida3="Documento de Identidad";
        	$scope.criterioPlaceHolder3 = "Número de documento de identidad";
            $("#datoAlumno").unmask();
            $("#datoAlumno").attr('maxlength','15');         // MAX 15 characters
            $("#datoAlumno").mask('AAAAAAAAAAAAAAA');
        }
        
        $("#datoAlumno").val("");
        $scope.datoBuscar3 = "";
        $('#datoAlumno').focus();
    });
    
    $("#datoAlumno").keyup(function(){
        var parseDato=$("#datoAlumno").val().trim();
        if(parseDato!="" && parseDato!="."){
            var promise = null;
            switch ($scope.userCriterio.criterioIdxSUM) {
                case "2":
                    promise = maestroClientesService.buscarAlumnoXTipoDoc_numDoc($scope.userTipoDoc2, parseDato); 
                    break;
                case "3":
                    promise = maestroClientesService.buscarAlumnoXcodMatricula(parseDato); 
                    break;
                case "5":
                    promise = maestroClientesService.buscarAlumnoXNombreCompleto(parseDato); 
                    break;
            };  
            
            promise.then(
                           function(respLista) { 
                                 if(respLista.data==0){
                                    $("#results3").hide();
                                    $("#noMatches").show();
                                    
                                 }else{
                                    $scope.tableParams3 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                                    $scope.tableAlumno = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});
                                    maestroClientesService.cliente=respLista.data;
                                    $("#results3").show();
                                    $("#noMatches").hide();
                                    
                                 }
                            },
                            function(errorLista) {
                                  $("#results3").hide();
                                  $("#noMatches").show();

                            }
            );       
        }else{
            $("#results3").hide();
            $("#noMatches").hide();

        }
    });
    
  //****************************** FIN ALUMNOS SUM *****************************************
       
  //******************************* FUNCIONES ******************************************
  //****************************** SERVIDORES SAN MARCOS *****************************************
    $scope.serv_optionChange=function(){
        switch ($scope.userCriterio.criterioIdxSERV) {
            case "6":
                $scope.criterioBusquedaElegida4="Código de Trabajodor";
                $scope.datoBuscar4 = "";
                $scope.selectDisabled3 = true;
                $("#datoServidor").unmask();
                $("#noMatches").hide();
                break;
            case "2":
                $scope.criterioBusquedaElegida4="Número de DNI o Carné de Extranjería";
                $scope.datoBuscar4 = "";
                $scope.selectDisabled3 = false;
                $("#datoServidor").unmask();
                $("#datoServidor").attr('maxlength','12'); 
                $("#datoServidor").mask('AAAAAAAAAAAA');
                $("#noMatches").hide();

                break;
            case "5":
                $scope.criterioBusquedaElegida4="Apellidos o Nombres";
                $scope.datoBuscar4 = "";
                $scope.selectDisabled3 = true;
                $("#datoServidor").unmask();
                $("#datoServidor").attr('maxlength','100');
                $("#datoServidor").mask('B', $scope.maskOptions_letrasYEspacios);
                $("#noMatches").hide();

                break;
            case "1":
                $scope.criterioBusquedaElegida4="Número de R.U.C.";
                $scope.datoBuscar4 = "";
                $scope.selectDisabled3 = true;
                $("#datoServidor").mask('0#');
                $("#noMatches").hide();
                break;
        };
    };

    
    $("#datoServidor").keyup(function(){
        var parseDato=$("#datoServidor").val().trim();
        if(parseDato!="" && parseDato!="."){
            var promise = null;
            switch ($scope.userCriterio.criterioIdxSERV) {
                case "6":
                    promise = maestroClientesService.buscarServidorXcodTrabajador(parseDato); break;
                case "2":
                    promise = maestroClientesService.buscarServidorXNumDoc(parseDato); break;
                case "5":
                    promise = maestroClientesService.buscarServidorXNombreCompleto(parseDato); break;
                case "1":
                    promise = maestroClientesService.buscarServidorXruc(parseDato); break;          
            };  
            
            promise.then(
                           function(respLista) { 
                                 if(respLista.data==0){
                                    $("#results4").hide();
                                    $("#noMatches").show();

                                 }else{
                                    $scope.tableParams4 = new ngTableParams({}, { dataset: respLista.data, counts:[]});
                                    $scope.tableServidor = new ngTableParams({count:5}, { dataset: respLista.data, counts:[]});                                 
                                    maestroClientesService.cliente=respLista.data;
                                    $("#results4").show();
                                    $("#noMatches").hide();

                                 }
                            },
                            function(errorLista) {
                                  $("#results4").hide();
                                  $("#noMatches").show();

                            }
            );       
        }else{
            $("#results4").hide();
            $("#noMatches").hide();

        }
    });

  //****************************** FIN SERVIDORES SAN MARCOS *****************************************
    $scope.cambiarMant_OTROS=function(){
    	var dni_temp=$scope.SINRUC_client.numDoc;
    	resetSinRUC();
    	$scope.SINRUC_client.numDoc=dni_temp;
    	$scope.SINRUC_client.tipoDoc=9;
        $("#mensajeVerificaDNI").hide();
        $("#mensajeServicioDNI").hide();
    };
    
    $scope.cambiarMant_DNI=function(){
    	var dni_temp=$scope.SINRUC_client.numDoc;
    	resetSinRUC();
    	$scope.SINRUC_client.numDoc=dni_temp;
    	$scope.SINRUC_client.tipoDoc=1;

        if($scope.SINRUC_client.idCliente == null || $scope.SINRUC_client.idCliente == ''){
            $scope.filterUbigeo = {};
            $scope.SINRUC_client.codUbigeo = '';
            $scope.SINRUC_client.sexo = '';
            $scope.SINRUC_client.fechaNac = '';
            $scope.SINRUC_client.direccion = '-';
            $scope.SINRUC_client.telefono = '';
            $scope.SINRUC_client.email = '';
        }
        
        $scope.loading = false;
        $scope.viewReniec.existeDNI = true;
        $scope.viewReniec.estadoServ= true;
        
        $("#mensajeVerificaDNI").hide();
        $("#mensajeServicioDNI").hide();
    };
    
    $scope.cambiarBOL_OTROS=function(){
    	$("#mensajeDNI").modal('hide');
    	var dni_temp2=$scope.datoBuscar2;
    	resetSinRUC();
    	$scope.SINRUC_client.numDoc = dni_temp2;
  	  	$scope.SINRUC_client.tipoDoc = 9;
    	$("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    };
    
    $scope.cambiarBOL_DNI=function(){
    	$("#mensajeDNI").modal('hide');
    	var dni_temp2=$scope.datoBuscar2;
    	resetSinRUC();
    	$scope.SINRUC_client.numDoc = dni_temp2;
  	  	$scope.SINRUC_client.tipoDoc = 1;
    	$("#ModalSINRUC").modal({backdrop: 'static', keyboard: false});
    };


}]);