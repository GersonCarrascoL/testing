
app.controller('mantenimientoRoles_controller', ['$scope', '$filter', 'ngTableParams', 'listaRolesService', 'dependenciaService', 'reniecService','perfilService', function($scope, $filter, ngTableParams, listaRolesService, dependenciaService, reniecService,perfilService) {
    
	
	$scope.user_codDependencia=parseInt($("#idDependencia").text());	
	$scope.dataView = {
            title: '',
            noData: false,
            noDataSinRUC: false
    };
	
	$scope.estadoBtnValidarRUC = false;
    
    $scope.viewReniec = {
            existeDNI: false
    };
    $scope.estadoBtnValidarDNI = false;
    
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
	
	 //get TIPOS PERFIL
    $scope.datosPerfil = function(){
        var promise = perfilService.listarPerfilesActivos();
        promise.then(
                           function(respLista) {
                             $scope.listaPerfil = respLista.data;
                             //console.log($scope.listaPerfil[0].codigoPerfil);
                             if($scope.UsuarioRol.id_hist==null){
                            	 $scope.userTipoPer1 = respLista.data[0];
                             }
                             else{
                            	 $scope.userTipoPer1=encontrarPerfil($scope.listaPerfil,$scope.listaRoles[$scope.modalOptions.data.indice].codPerfil)
                             }
                           },
                           function(errorLista){
                           }
        );
    };
    $scope.datosPerfil();
	//UsuarioRol
	$scope.UsuarioRol = {
			id_hist: null,
			ud_id: $scope.user_codDependencia,
			nombres: "",
			apellidos: "",
			email: "",
			perfil: "",
			codPerfil: null,
			tipoDoc: 1,
			dni: "",
			estado: 1,
			status: null
    };
	
	//mensajesModalNuevoDni
	function mensajesModalNuevoDni(){
        switch ($scope.estadoModal){
            case 1:
                $.gritter.add({
                    title: 'Registrar Usuario',
                    text: 'El Usuario se ha registrado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Registrar Usuario',
                    text: 'Este número de documento ya fue registrado anteriormente en el sistema ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -2: 
                $.gritter.add({
                    title: 'Registrar Usuario',
                    text: 'Este email ya fue registrado anteriormente en el sistema',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
	
	
	//mensajesModalEditarSinRuc
	function mensajesModalEditarDni(){
        switch ($scope.estadoModal){
            case 1: 
                $.gritter.add({
                    title: 'Actualizar Usuario',
                    text: 'El Usuario se ha actualizado correctamente ',
                    image: 'resources/assets/img/notification/succes.png',
                    sticky: false,
                    time: ''
                }); break;
            case -1: 
                $.gritter.add({
                    title: 'Actualizar Usuario',
                    text: 'Este número de documento ya fue registrado anteriormente en el sistema ',
                    image: 'resources/assets/img/notification/peligro.png',
                    sticky: false,
                    time: ''
                }); break;
            case -2: 
                $.gritter.add({
                    title: 'Actualizar Usuario',
                    text: 'Hubo un error al actualizar el cliente ',
                    image: 'resources/assets/img/notification/error.png',
                    sticky: false,
                    time: ''
                }); break;
            default: break;
        }  
    }
	
	//openModalNewSinRUC
	$scope.openModalNewDni = function(){
		
		resetUsuarioRol();
        $scope.dataView.title = "REGISTRAR USUARIO";
        $("#ModalUsuarioRol").modal({backdrop: 'static', keyboard: false});
    };
 
    function resetUsuarioRol(){
        $scope.UsuarioRol = {
        		id_hist: null,
    			ud_id: $scope.user_codDependencia,
    			nombres: "",
    			apellidos: "",
    			email: "",
    			perfil: "",
    			codPerfil: $scope.listaPerfil[0].codigoPerfil,
    			tipoDoc: 1,
    			dni: "",
    			estado: 1,
    			status: null
        };
        $scope.viewReniec.existeDNI = false;
    	$scope.manual=false;
        $scope.formSinRUC.$setPristine(); //reset Form
        //$scope.userTipoPer1=$scope.listaPerfil[0].codigoPerfil;
    };
        
    /* maestroClientes_mant.html consulta Reniec */
    $scope.consultaDNI_Cliente = function(){
    	$scope.datosPerfil();
        $scope.loading = true;
        $scope.dataReniec = {};

        var dni = $scope.UsuarioRol.dni;
        
        var promise = reniecService.consultaDNI(dni);
        promise.then(
                      function(respItem) {
                          $scope.dataReniec = respItem.data;
                          console.log("dni d reniec "+$scope.dataReniec.nroDni);
                          $scope.nroDni=comprobarExpRegular($scope.dataReniec.nroDni);
                          console.log("comprobando exReg "+$scope.nroDni)
                          if($scope.dataReniec.estServ== 200 && $scope.nroDni){
                        	  //$scope.UsuarioRol.dni = $scope.dataReniec.nroDni; 
                              $scope.UsuarioRol.dni=dni;
                        	  if(!$scope.dataReniec.nroDni){
                        		  console.log("nom Separado")
                        		  $scope.viewReniec.existeDNI = false;
                        	  }
                        	  else{
                        		  console.log("nombcompleto");
                        		  $scope.viewReniec.existeDNI = true;
                        		  $scope.UsuarioRol.nombres = $scope.dataReniec.nomComp;
                                  $scope.UsuarioRol.apellidos = " ";
                                  console.log("nombre: "+$scope.UsuarioRol.nombres+"\nApellidos: "+$scope.UsuarioRol.apellidos)
                                  //$("#mensajeServicioDNI").show();
                        	  }
                        	  
                        	  
                              //$scope.UsuarioRol.estServ=$scope.dataReniec.estServ;
                        	  //$scope.loading = false;
                              //$("#mensajeVerificaDNI").show();
                              //$scope.viewReniec.existeDNI = false;
                        	  //$scope.viewReniec.estadoServ= true;

                              
                              
                              $scope.UsuarioRol.tipoDoc = 1; //DNI
                              $scope.loading = false;
                              //$scope.viewReniec.existeDNI = true;
                              $scope.viewReniec.estadoServ= true;
                              $("#mensajeVerificaDNI").hide();
                              $("#mensajeServicioDNI").hide();

                          }else{                 	  
                              $scope.loading = false;
                              $scope.viewReniec.existeDNI = false;
                              if($scope.dataReniec.estServ==404 && $scope.nroDni){
                            	  $("#mensajeVerificaDNI").show();
                                  $("#mensajeServicioDNI").hide();
                                  $scope.viewReniec.estadoServ= true;
                              }else{
                            	  if(!$scope.dataReniec.nroDni){
                            		  $("#mensajeVerificaDNI").show();
                                      $("#mensajeServicioDNI").hide();
                                      $scope.viewReniec.estadoServ= true;
                            	  }
                            	  else{
                            		  $("#mensajeVerificaDNI").hide();
                                      $("#mensajeServicioDNI").show();
                                      $scope.viewReniec.estadoServ= false;
                            	  }
                            	  
                              }
                              //$scope.UsuarioRol.estServ=$scope.dataReniec.estServ;
                          }
                      },

                      function(errorItem) {
                          $scope.loading = false;
                          $scope.viewReniec.existeDNI = false;
                          $scope.viewReniec.estadoServ= false;
                          $("#mensajeVerificaDNI").show();
                          //$("#mensajeServicio").show();
                      }
        );      
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
    $scope.limpiar=function(){
    	$("#dniRow").removeClass("col-md-12");
    	resetUsuarioRol();//revisarasdadasdadasdasd
    	$scope.manual=false;
    	$scope.dataReniec = {};
    	$("#mensajeVerificaDNI").hide();
    	$("#mensajeServicioDNI").hide();
    	$scope.viewReniec.existeDNI = false;
        $scope.viewReniec.estadoServ= false;
    };
    
    /* form Nuevo SINRUC cliente*/
    $("#sinruc_dni").keyup(function(){
    	var auxDni=$scope.UsuarioRol.dni;
        if($scope.UsuarioRol.tipoDoc == 1){
            $("#mensajeVerificaDNI").hide();
            $("#mensajeServicioDNI").hide();

            if($scope.viewReniec.existeDNI == true){
            	$scope.$apply(function () {
	            	$scope.viewReniec.existeDNI = false;
	            	resetUsuarioRol();
	            	$scope.UsuarioRol.dni=auxDni;           
            	});
            }  
        }      
    });
    //-------------------------------------------------------------------------------
    $scope.registrarManualmente = function(){
    	$scope.buscarUsuarioRolDni($scope.UsuarioRol.dni);
    	$("#dniRow").addClass("col-md-12");
    	$("#mensajeVerificaDNI").hide();
    	$("#mensajeServicioDNI").hide();
    	$scope.UsuarioRol.id_hist=1;
    	$scope.viewReniec.existeDNI=false;
    	$scope.manual=true;
    	//$("btnRegistrarClieSinRuc").attr('value', 'Registrar');
    }
        $scope.submitUsuarioRol = function() {
        	if($scope.manual) $scope.UsuarioRol.id_hist=null;
        	$scope.UsuarioRol.codPerfil=$scope.userTipoPer1.codigoPerfil;
        	$scope.UsuarioRol.perfil=encontrarPerfil($scope.listaPerfil,$scope.UsuarioRol.codPerfil).descripcionGeneral;
            if($scope.UsuarioRol.id_hist==null){
            	$scope.manual=false;
                $scope.createUsuarioRol($scope.UsuarioRol);    
            }else{
            	$("#dniRow").removeClass("col-md-12");
                $scope.updateUsuarioRol($scope.UsuarioRol);
            }
        };
        
        $scope.createUsuarioRol = function(usuario){
        	console.log(usuario)
            var promise = listaRolesService.createUsuarioRol(usuario);
            promise.then(
                      function(respItem) { 
                    	  console.log("crearUSUARIO")
                    	  console.log(usuario)
                          $scope.estadoModal = 1;
                          $("#ModalUsuarioRol").modal("hide");
                          $scope.listarRolesXDependencia($scope.user_codDependencia);
                          resetUsuarioRol();//asdadadadadadaddasdasdadadasdasdaddasdasdasd
                          mensajesModalNuevoDni();
                      },    
                      function(dataError) {
                          $scope.estadoModal = dataError;
                          mensajesModalNuevoDni();
                          //resetUsuarioRol();
                      }
            );
            
        };
        
        $scope.buscarUsuarioRolDni = function(dni){
            var promise = listaRolesService.buscarRolxDni(dni);
            promise.then(
                      function(respItem) { 
                    	  console.log(respItem.data);
                    	  if(respItem.data.length>0){
                    		  $scope.UsuarioRol.nombres=respItem.data[0].nombres;
                    		  $scope.UsuarioRol.apellidos=respItem.data[0].apellidos;
                    		  var auxEmail=respItem.data[0].email;
                    		  if(auxEmail.indexOf("@")!=-1) $scope.UsuarioRol.email=auxEmail.substr(0,auxEmail.indexOf("@"));
                    		  else $scope.UsuarioRol.email=auxEmail;
                    		  
                    	  }
                    	  else{
                    		  //$scope.UsuarioRol.nombres="";
                    		  //$scope.UsuarioRol.apellidos="";
                    	  }
                      },    
                      function(dataError) {
                          //$scope.estadoModal = dataError;
                          //mensajesModalNuevoDni();
                          //resetUsuarioRol();
                      }
            );
            
        };
        
        $scope.updateUsuarioRol = function(usuario){
            var promise = listaRolesService.updateUsuarioRol(usuario);
            promise.then(
                          function(respItem) {
                              $scope.estadoModal = 1;
                              $("#ModalUsuarioRol").modal("hide");
                              mensajesModalEditarUsuarioRol();
                              $scope.listarRolesXDependencia($scope.user_codDependencia);
                          },
                          function(dataError) {
                              $scope.estadoModal = dataError;
                              mensajesModalEditarUsuarioRol();
                          }
                );
        };
        var encontrarPerfil = function(perfil,codPerfil){
        	var i=0;
        	while(perfil[i].codigoPerfil!=codPerfil && i<perfil.length){
        		i++;
        	}
        	return perfil[i];
        }
        $scope.abrirModalConfirm_submitUsuarioRol = function(){
        	var auxId_hist=$scope.UsuarioRol.id_hist;
        	if($scope.manual){
        		auxId_hist=null;
        	}
            $scope.modalOptions.headerColorValue= 0;
            $scope.modalOptions.closeButtonText= 'No';
            $scope.modalOptions.actionButtonText= 'Si';
            
            if(auxId_hist==null){
            	console.log($scope.UsuarioRol.id_hist)
            	$scope.modalOptions.headerText = 'Registrar';
            	$scope.modalOptions.bodyText = 'Está a punto de registrar el nuevo Usuario. ¿Desea continuar?';
            }else{
            	$scope.modalOptions.headerText = 'Guardar Cambios';
            	$scope.modalOptions.bodyText = 'Se procederá a registrar los cambios en el Usuario. ¿Continuar?';
            }


            $scope.modalOptions.action = $scope.submitUsuarioRol;

            $('#modalConfirm').modal({show: true});
        };
        
        function mensajesModalEditarUsuarioRol(){
            switch ($scope.estadoModal){
                case 1: 
                    $.gritter.add({
                        title: 'Actualizar Usuario',
                        text: 'El usuario se ha actualizado correctamente ',
                        image: 'resources/assets/img/notification/succes.png',
                        sticky: false,
                        time: ''
                    }); break;
                case -1: 
                    $.gritter.add({
                        title: 'Actualizar usuario',
                        text: 'Este número de documento ya fue registrado anteriormente en el sistema ',
                        image: 'resources/assets/img/notification/peligro.png',
                        sticky: false,
                        time: ''
                    }); break;
                case -2: 
                    $.gritter.add({
                        title: 'Actualizar usuario',
                        text: 'Hubo un error al actualizar el usuario ',
                        image: 'resources/assets/img/notification/error.png',
                        sticky: false,
                        time: ''
                    }); break;
                default: break;
            }  
        }
        
    $scope.listarRolesXDependencia = function(codigoDependencia){
        var promise;
        promise = listaRolesService.listarRolesxDependencia(codigoDependencia);
        promise.then(
                      function(respLista) { 
                          $scope.modalSpinnerShow = false;
                          mostrar(respLista.data);   
                      },
                      function(errorLista) {
                          $scope.modalSpinnerShow = false;
                      }
            );

    };
    $scope.openModalActualizarUsuarioRol = function(usuario,index){
    	$("#dniRow").addClass("col-md-12");
    	$scope.datosPerfil();
        $scope.UsuarioRol = angular.copy(usuario);
        $scope.modalOptions.data.indice=index;
        $scope.dataView.title = "EDITAR USUARIO";
        $("#ModalUsuarioRol").modal({backdrop: 'static', keyboard: false});
        
    };
    
    $('#ModalUsuarioRol').on('hidden.bs.modal', function () {
        //$scope.bodyPaddingRightZero();
      });
    
    
    $scope.abrirModalConfirm_cambiaEstadoUsuRol = function(usu,index){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Deshabilitar';
        $scope.modalOptions.bodyText= 'Está a punto de deshabilitar a éste usuario. ¿Está seguro?';
        
        if(usu.estado==0){
        	$scope.modalOptions.headerColorValue = 0; //btn-primary
        	$scope.modalOptions.headerText = 'Habilitar';
        	$scope.modalOptions.bodyText = 'Está a punto de habilitar a éste usuario. ¿Está seguro?';          
        }

        $scope.modalOptions.data.usu = usu;
        $scope.modalOptions.data.indice=index;
        $scope.modalOptions.action = $scope.cambiaEstadoUsuRol;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.cambiaEstadoUsuRol = function(){
        var estado = ($scope.modalOptions.data.usu.estado==1 ? 0 : 1);
        $scope.updateEstadoUsuRol($scope.modalOptions.data.usu, estado);
        $scope.listaRoles[$scope.modalOptions.data.indice].estado=estado;    
    };
    
    $scope.updateEstadoUsuRol = function(usuario, estado){
        var promise = listaRolesService.updateEstadoUsuRol(usuario.id_hist, estado);
        promise.then(
                      function(respItem) {
                          //$scope.getClie_SinRUC();
                    	  console.log(respItem)
                      },
                      function(errorItem) {
                    	  console.log(errorItem)
                      }
            );
    };
    
    //ELIMINAR USUARIO
    
    $scope.abrirModalConfirm_eliminarUsuario = function(usuario){
        $scope.modalOptions.headerColorValue= 0;
        $scope.modalOptions.closeButtonText= 'No';
        $scope.modalOptions.actionButtonText= 'Si';
        $scope.modalOptions.headerText= 'Eliminar';
        $scope.modalOptions.bodyText= 'Está a punto de Eliminar al usuario ' + usuario.nombres + '. ¿Está seguro?';

        $scope.modalOptions.data.usuario = usuario;

        $scope.modalOptions.action = $scope.eliminarUsuarioRol;

        $('#modalConfirm').modal({show: true});
    };
    
    $scope.eliminarUsuarioRol = function(){   
    	console.log($scope.modalOptions.data.usuario.id_hist);
        $scope.deleteUsuarioRol($scope.modalOptions.data.usuario.id_hist);
    };
    //----------------------------------------------------------------------------------------------------
    $scope.deleteUsuarioRol = function(id_hist){
        var promise = listaRolesService.deleteUsuarioRol(id_hist);
        promise.then(
                      function(respItem) { 
                    	  $scope.listarRolesXDependencia($scope.user_codDependencia);

                          $.gritter.add({
                                title: 'Eliminar Usuario',
                                text: 'El usuario se ha eliminado correctamente ',
                                image: 'resources/assets/img/notification/succes.png',
                                sticky: false,
                                time: ''
                          });
                      },
                      function(errorItem) {
                          if(errorItem == -1) {
                              $.gritter.add({
                                title: 'Eliminar Usuario',
                                text: 'El Usuario no existe. Probar otra vez.',
                                image: 'resources/assets/img/notification/peligro.png',
                                sticky: false,
                                time: ''
                              });
                          }else if(errorItem == -4){
                              $.gritter.add({
                                title: 'Eliminar Servicio',
                                text: 'No se pudo realizar la operación. Intentelo más tarde.',
                                image: 'resources/assets/img/notification/error.png',
                                sticky: false,
                                time: ''
                              });
                          }
                      }
            );
    };

    $scope.openModalDetalleItem = function(usuario){
    	
        $scope.usuDetalle = angular.copy(usuario);     
        $scope.usuDetalle.nomCompleto=$scope.usuDetalle.nombres+" "+$scope.usuDetalle.apellidos;
        $("#Detalle").modal({backdrop: 'static', keyboard: false});
    };
    function mostrar(data){
        $scope.listaRoles = data;
        if($scope.listaRoles==null || $scope.listaRoles==""){
            $scope.VisibleResultTable=false;
        }else{
            $scope.tableParams = new ngTableParams({count:5, group:{ udDesc:"asc"}, filter: {descCategoriaServ:''}}, { dataset: $scope.listaRoles, counts:[]});
            $scope.VisibleResultTable=true; 
        }
    }

    
    
    $scope.listarRolesXDependencia($scope.user_codDependencia);
    
    $scope.UsuarioRol.tipoDoc=1;
    
    

}]);