<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="Aplicacion" ng-controller="session_controller">
<head>
<title>Facturacion Electronica</title>
<meta
    content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
    name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="icon" type="image/png" href="resources/assets/img/icon.png" />
<link
    href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
    rel="stylesheet" />
<link
    href="resources/assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
    rel="stylesheet" />
<link href="resources/assets/plugins/bootstrap/css/bootstrap.min.css"
    rel="stylesheet">
<link
    href="resources/assets/plugins/font-awesome/css/font-awesome.min.css"
    rel="stylesheet" />
<link href="resources/assets/css/animate.min.css" rel="stylesheet" />
<link href="resources/assets/css/style.min.css" rel="stylesheet" />
<link href="resources/assets/css/style-responsive.min.css"
    rel="stylesheet" />
<link href="resources/assets/css/theme/default.css" rel="stylesheet"
    id="theme" />
<link href="resources/assets/css/tree-unidades.css" rel="stylesheet">
<link href="resources/assets/css/ng-table.min.css" rel="stylesheet">
<link href="resources/assets/plugins/ccarrot/ccarrot-style.css"
    rel="stylesheet">
<link href="resources/assets/plugins/gritter/css/jquery.gritter.css"
    rel="stylesheet" />
<link href="resources/assets/css/select2.css" rel="stylesheet" />
<link href="resources/assets/css/ayuda.css" rel="stylesheet"/>
</head>
<body>
    <div id="page-loader" class="fade in">
        <span class="spinner"></span>
    </div>
    <div id="page-container"
        class="fade page-sidebar-fixed page-header-fixed">
        <div id="header" class="header navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a href="javascript:;" class="navbar-brand"><img
                        src="resources/assets/img/logo2.png" alt="" /> </a>  
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <span class="hidden-xs">TC compra: {{tipoCambioCompra}}</span>
	                    </a>
                    </li>  
                 	<li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <span class="hidden-xs">|</span>
	                    </a>
                    </li>                     
                    <li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <strong class="hidden-xs">TC venta: {{tipoCambioVenta}}</strong>
	                    </a>
                    </li>   
                 	<li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <span class="hidden-xs">|</span>
	                    </a>
                    </li>                                                   
                    <li class="dropdown navbar-user">
                        <div id="maxInactiveInterval" style="display: none">${pageContext.session.maxInactiveInterval}</div>
                        <a href="javascript:;" class="dropdown-toggle"
                        data-toggle="dropdown"><i class="fa fa-clock-o" aria-hidden="true"></i> <span class="hidden-xs">{{counter|formatTimer}}</span>
                    </a>
                    </li>
                  	<li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <span class="hidden-xs">|</span>
	                    </a>
                    </li>                    
                    <li class="dropdown navbar-user">
	                    <a href="javascript:;"
	                        class="dropdown-toggle" data-toggle="dropdown"> <strong
	                            class="hidden-xs"> ${userDscDependenciaPad == userDscDependencia ? '' : userDscDependenciaPad}${userDscDependenciaPad == userDscDependencia ? '' : '   -   '}${userDscDependencia}
	                        </strong>
	                    </a>
                    </li>
                  	<li class="dropdown navbar-user">
	                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
	                         <span class="hidden-xs">|</span>
	                    </a>
                    </li>                    
               		<li class="dropdown">
						<a href="javascript:;" data-toggle="dropdown" title="comprobantes no enviados a SUNAT" class="dropdown-toggle f-s-14">
							<i class="fa fa-bell-o"></i>
							<span class="label">{{comprobantesSinEnviar}}</span>
						</a> 				
						<ul class="dropdown-menu media-list pull-right animated fadeInDown">
							<li class="dropdown-header">Comprobantes ({{comprobantesSinEnviar}})</li>                 
 	                          <li class="media" ng-repeat="notificacion in notificaciones">
	                                <a href="{{hrefComprobante(notificacion.id_Cpago)}}">
	                                    <div class="media-left"><i class="fa fa-file-text media-object bg-red"></i></div>
	                                    <div class="media-body">
	                                        <h6 class="media-heading">{{notificacion.descripcion}}</h6>
	                                        <div class="text-muted f-s-11">{{notificacion.total}} comprobante(s) NO enviado(s)</div>
	                                    </div>
	                                </a>
	                            </li>                                                                                                                                        							
						</ul>  					            		
               		</li>                    
                    <li class="dropdown navbar-user">
	                    <a href="javascript:;"
	                        class="dropdown-toggle" data-toggle="dropdown"> <img
	                            src="resources/assets/img/user-13.jpg" alt="" /> <span
	                            class="hidden-xs">${userDetails.username}</span> <b class="caret"></b>
	                    </a>
	                        <ul class="dropdown-menu animated fadeInLeft">
	                            <li class="arrow"></li>
	                            <li class="divider"></li>
	                            <li><a href="j_spring_security_logout">Cerrar Sesión</a></li>
	                        </ul>                       
                     </li>
                </ul>
            </div>
        </div>
        <div id="sidebar" class="sidebar">
            <div data-scrollbar="true" data-height="100%">
                <ul class="nav">
                    <li class="nav-profile">
                        <div class="image">
                            <a href="javascript:;"><img
                                src="resources/assets/img/user-13.jpg" alt="" /></a>
                        </div>
                        <div style="top: 30">
                            <small></small>
                        </div> </br> </br> </br>
                        <div class="info">${nombre}</div>
                    </li>
                </ul>
                <ul class="nav">
                    <sec:authorize
                        access="hasAnyRole('ADMINISTRA','CAJA_ADMIN','CLIENTE_FA','CAJA_ADMIN','CAJA_RECAU', 'CAJA_RC_TE','CAJA_TICKT')">
                        <li class="has-sub"><a href="javascript:;"> <b
                                class="caret pull-right"></b> <i class="fa fa-search"></i> <span>Consultas</span>
                        </a>
                            <ul class="sub-menu">
                            	<sec:authorize
                        access="hasAnyRole('ADMINISTRA','CAJA_ADMIN','CLIENTE_FA','CAJA_ADMIN','CAJA_RECAU', 'CAJA_RC_TE')">
                                <li><a href="#consultaClientes" class="option">Clientes</a></li>
                                </sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('ADMINISTRA','CAJA_ADMIN','CLIENTE_FA','CAJA_RECAU', 'CAJA_RC_TE')">
                                    <li><a href="#consultaConceptoPago" class="option">Conceptos
                                            de Pago</a></li>
                                </sec:authorize>
                                <li><a href="#consultaListaPrecios" class="option">Lista
                                        de Bienes</a></li>
                                <li><a href="#consultaListaServicios" class="option">Lista
                                        de Servicios</a></li>
                            </ul></li>
                    </sec:authorize>
                    <sec:authorize
                        access="hasAnyRole('CLIENTE_FA','CAJA_RECAU', 'CAJA_RC_TE','CAJA_TICKT')">
                        <li class="has-sub"><a href="javascript:;"> <b
                                class="caret pull-right"></b> <i class="fa fa-database"></i> <span>Procesos</span>
                        </a>
                            <ul class="sub-menu">
                                <sec:authorize access="hasAnyRole('CAJA_RECAU', 'CAJA_RC_TE')">
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Emisión Electrónica
                                    </a>
                                        <ul class="sub-menu">
                                            <li class="has-sub"><a href="javascript:;"> <b
                                                    class="caret pull-right"></b> Boleta Electrónica
                                            </a>
                                                <ul class="sub-menu">
                                                    <li><a href="#boleta" class="option">Boleta</a></li>
                                                    <li><a href="#notaCreditoBoleta" class="option">Nota
                                                            Credito</a></li>
                                                </ul></li>
                                            <li class="has-sub"><a href="javascript:;"> <b
                                                    class="caret pull-right"></b> Factura Electrónica
                                            </a>
                                                <ul class="sub-menu">
                                                    <li><a href="#factura" class="option">Factura</a></li>
                                                    <li><a href="#notaCreditoFactura" class="option">Nota
                                                            Credito</a></li>
                                                </ul></li>
                                        </ul></li>
                                    
                                    
                                </sec:authorize>
                                <sec:authorize
                        access="hasAnyRole('CAJA_TICKT','CAJA_ADMIN')">
                                <li><a href="#emisionTickets">Tickets</a></li>
                                </sec:authorize>
                            </ul></li>
                    </sec:authorize>
                    <sec:authorize
                        access="hasAnyRole('ADMINISTRA','CAJA_ADMIN','CLIENTE_FA','CAJA_ADMIN','SOL_CONCEP','CAJA_RECAU', 'CAJA_RC_TE','CAJA_TICKT')">
                        <li class="has-sub "><a href="javascript:;"> <b
                                class="caret pull-right"></b> <i class="fa fa-cogs"></i> <span>Mantenimiento</span>
                        </a>
                            <ul class="sub-menu">
                                <sec:authorize
                                    access="hasAnyRole('CAJA_ADMIN','CAJA_RECAU','CAJA_RC_TE')">
                                    <li><a href="#mantenimientoClientes">Clientes</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('CAJA_RECAU','CAJA_RC_TE')">
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Conceptos de Pago
                                    </a>
                                        <ul class="sub-menu">
                                            <li><a href="#mantenimientoUnidadesRecaudadoras"
                                                class="option">Unidades</a></li>
                                            <li><a href="#mantenimientoConceptosPago" class="option">Conceptos</a></li>
                                        </ul></li>
                                </sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('CAJA_ADMIN','CAJA_RECAU','CAJA_RC_TE','CAJA_TICKT')">
                                    <li><a href="#mantenimientoListaItemPrecios"
                                        class="option">Lista de Bienes</a></li>
                                    <li><a href="#mantenimientoListaServicios" class="option">Lista
                                            de Servicios</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMINISTRA')">
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Conceptos de Pago
                                    </a>
                                        <ul class="sub-menu">
                                            <li><a href="#mantenimientoUnidadesRecaudadoras"
                                                class="option">Unidades</a></li>
                                            <li><a href="#mantenimientoConceptosPago" class="option">Conceptos</a></li>
                                        </ul></li>
                                    <li><a href="#mantenimientoListaItemPrecios"
                                        class="option">Lista de Bienes</a></li>
                                    <li><a href="#mantenimientoListaServicios" class="option">Lista
                                            de Servicios</a></li>
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Solicitudes
                                    </a>
                                        <ul class="sub-menu">
                                            <li><a href="#solicitudesUnidRecaudadora" class="option">Unidades</a></li>
                                            <li><a href="#solicitudesConceptosPago" class="option">Conceptos
                                                    de Pago</a></li>
                                        </ul></li>
                                </sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('CAJA_ADMIN','ADMINISTRA','CAJA_RECAU','CAJA_RC_TE')">
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Comprobantes de pago
                                    </a>
                                        <ul class="sub-menu">
                                            <li class="has-sub"><a href="javascript:;"> <b
                                                    class="caret pull-right"></b> Boleta Electrónica
                                            </a>
                                                <ul class="sub-menu">
                                                    <li><a href="#mantenimientoBoleta" class="option">Boleta</a>
                                                    </li>
                                                    <li><a href="#mantenimientoNotaCreditoBoleta"
                                                        class="option">Nota Credito</a></li>
                                                </ul></li>
                                            <li class="has-sub"><a href="javascript:;"> <b
                                                    class="caret pull-right"></b> Factura Electrónica
                                            </a>
                                                <ul class="sub-menu">
                                                    <li><a href="#mantenimientoFactura" class="option">Factura</a>
                                                    </li>
<!--                                                     <li><a href="#mantenimientoFactura" class="option">Factura</a> -->
<!--                                                     </li> -->
                                                    <li><a href="#mantenimientoNotaCreditoFactura"
                                                        class="option">Nota Credito</a></li>
                                                </ul></li>
                                        </ul></li>
                                </sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('CAJA_TICKT','CAJA_ADMIN')">
                                    <li><a href="#mantenimientoTickets"
                                        class="option">Ticket</a></li>
                               	</sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('CAJA_ADMIN','ADMINISTRA','CAJA_RC_TE')">
                                    <li class="has-sub"><a href="javascript:;"> <b
                                            class="caret pull-right"></b> Mantenimiento de
                                            establecimiento
                                    </a>
                                        <ul class="sub-menu">
                                                <li><a
                                                    href="#mantenimientoEstablecimientoBoletaYFactura"
                                                    class="option">Boleta y factura</a></li>
                                        </ul></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('SOL_CONCEP')">
                                    <li><a href="#mantenimientoUnidadesRecaudadoras2"
                                        class="option">Unidades Recaudadoras</a></li>
                                    <li><a href="#formSolicitudConceptosPago" class="option">Formulario
                                            de Solicitud de Conceptos de Pago</a></li>
                                </sec:authorize>
                                <sec:authorize
                                    access="hasAnyRole('CAJA_ADMIN','ADMINISTRA','CAJA_RC_TE')">
                                    <li><a href="#roles"
                                        class="option">Roles</a></li>
                                </sec:authorize>
                            </ul></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('CAJA_ADMIN','ADMINISTRA','CAJA_RC_TE')">
                        <li class="has-sub"><a href="javascript:;"> <b
                                class="caret pull-right"></b> <i class="fa fa-file-pdf-o"></i> <span>Reportes</span>
                        </a>
                            <ul class="sub-menu">
                                <sec:authorize access="hasAnyRole('CAJA_ADMIN','CAJA_RC_TE')">
                                    <li><a href="#regventas" class="option">Registro de
                                            Ventas</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMINISTRA')">
                                    <li><a href="#regventasGeneral" class="option">Registro
                                            de Ventas</a></li>
                                </sec:authorize>
                            </ul></li>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ADMINISTRA','CAJA_ADMIN','CLIENTE_FA','CAJA_ADMIN','SOL_CONCEP','CAJA_RECAU', 'CAJA_RC_TE','CAJA_TICKT')">
                        <li class="has-sub"><a href="javascript:;"> <b
                                class="caret pull-right"></b> <i class="fa fa-question"></i> <span>Ayuda</span>
                        </a>
                            <ul class="sub-menu">
                                    <li><a href="#ayuda" class="option">Preguntas Frecuentes</a></li>
                            </ul></li>
                    </sec:authorize>
                    <li><a href="javascript:;" class="sidebar-minify-btn"
                        data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
                </ul>
            </div>
        </div>
        <div class="sidebar-bg"></div>
        <div id="idPerfil" style="display: none">${userPerfil}</div>
        <div id="idCodDependencia" style="display: none">${userCodDependencia}</div>
        <div id="idDependencia" style="display: none">${userIdDependencia}</div>
        <div id="descDependencia" style="display: none">${userDscDependencia}</div>
        <div id="userNameDep" style="display: none">${userDetails.username}</div>
        <div id="userIdCodDepPadre" style="display: none">${idDepPadre}</div>
        <div id="userDscDependenciaPad" style="display: none">${userDscDependenciaPad}</div>
        <div id="idUnidadAdministrativa" style="display: none">${idUnidadAdministrativa}</div>
        <div id="dscUnidadAdministrativa" style="display: none">${dscUnidadAdministrativa}</div>
        <div id="content" class="content">
            <div ng-view></div>
        </div>
        <div class="modal fade" id="logout_popup" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true"
            data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div style="width: 100%; height: 100%; margin: 0px; padding: 0px">
                            <div style="width: 25%; margin: 0px; padding: 0px; float: left;">
                                <img src="resources/assets/img/tiempo.gif" />
                            </div>
                            <div
                                style="width: 70%; margin: 0px; padding: 0px; float: right; padding-top: 10px; padding-left: 3%;">
                                <h4>¡Su sesión está a punto de expirar!</h4>
                                <p style="font-size: 15px;">
                                    Usted será desconectado en <span id="timer"
                                        style="display: inline; font-size: 30px; font-style: bold">{{counter}}</span>
                                    segundos.
                                </p>
                                <p style="font-size: 15px;">¿Quiere permanecer conectado?</p>
                            </div>
                        </div>
                        <div
                            style="margin-left: 30%; margin-bottom: 20px; margin-top: 20px;">
                            <a href="javascript:;" ng-click="resetTimer()"
                                class="btn btn-primary" aria-hidden="true">Sí, mantenerme
                                conectado</a> <a href="j_spring_security_logout"
                                class="btn btn-danger" aria-hidden="true">No, salir</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="javascript:;"
            class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
            data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
    </div>
    <script src="resources/assets/plugins/jquery/jquery-1.9.1.min.js"></script>
    <script
        src="resources/assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
    <script
        src="resources/assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
    <script src="resources/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script
        src="resources/assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="resources/assets/plugins/jquery-cookie/jquery.cookie.js"></script>
    <script src="resources/assets/js/jquery.mask.min.js"></script>
    <script src="resources/assets/js/jquery.inputmask.bundle.js"></script>
    <script src="resources/assets/plugins/gritter/js/jquery.gritter.js"></script>
    <script src="resources/assets/js/apps.min.js"></script>
    <script src="resources/js/angular.js"></script>
    <script src="resources/js/angular-route.min.js"></script>
    <script src="resources/js/ui-bootstrap-tpls-1.3.3.min.js"></script>
    <script src="resources/assets/js/ng-table.js"></script>
    <script src="resources/js/bootstrap-typeahead.js"></script>
    <script src="resources/js/angular-filter.min.js"></script>
    <script src="resources/controlador/rutas.js"></script>
    <script src="resources/directiva/directivas.js"></script>
    <script
        src="resources/controlador/controller_pages/factura_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/boleta_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/ayuda_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimiento_factura_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimiento_boleta_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimiento_establecimiento_boleta_y_factura_controller.js"></script>
    <script src="resources/filtros/mantenimiento_filter.js"></script>
    <script src="resources/directiva/facturacionDirective.js"></script>
    <script src="resources/directiva/ticketDirective.js"></script>
    <script
        src="resources/controlador/controller_pages/conceptos_pago_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/lista_precios_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/lista_servicios_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/maestro_clientes_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/solicitudesConceptoPago_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/solicitudes_unidad_recaudadora_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/unidad_recaudadora_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimiento_notaCreditoFactura_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimiento_notaCreditoBoleta_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/mantenimientoRoles_controller.js"></script>
    <script src="resources/service/mantenimientoTicket_service.js"></script>    
    <script src="resources/service/lista_precios_service.js"></script>
    <script src="resources/service/conceptos_pago_service.js"></script>
    <script src="resources/service/maestro_clientes_service.js"></script>
    <script src="resources/service/dependencia_service.js"></script>
    <script src="resources/service/docIdentidad_service.js"></script>
    <script src="resources/service/moneda_service.js"></script>
    <script src="resources/service/tipoConceptoPago_service.js"></script>
    <script src="resources/service/listap_historial_service.js"></script>
    <script src="resources/service/UnidadMedida_service.js"></script>
    <script src="resources/templates/modals/confirm/confirmService.js"></script>
    <script src="resources/service/sunat_service.js"></script>
    <script src="resources/service/reniec_service.js"></script>
    <script src="resources/service/catCodProducto_service.js"></script>
    <script src="resources/service/ubigeo_service.js"></script>
    <script src="resources/service/listaServ_historial_service.js"></script>
    <script src="resources/service/lista_servicios_service.js"></script>
    <script src="resources/service/categoriasServicios_service.js"></script>
    <script src="resources/service/categoriasBienes_service.js"></script>
    <script src="resources/service/comprobantesPago_service.js"></script>
    <script src="resources/service/facturacion_service.js"></script>
    <script src="resources/service/tipoResolucion_service.js"></script>
    <script src="resources/service/mantenimientoRoles_service.js"></script>
    <script src="resources/service/perfil_service.js"></script>
    <script
        src="resources/controlador/controller_pages/regventas_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/regventasGeneral_controller.js"></script>
    <script src="resources/js/angular-select2.min.js"></script>
    <script src="resources/js/select2.js"></script>
    <script src="resources/js/angular-sanitize.min.js"></script>
    <script
        src="resources/controlador/controller_pages/nota_credito_factura_controller.js"></script>
    <script
        src="resources/controlador/controller_pages/nota_credito_boleta_controller.js"></script>
    <script src="resources/service/nota_service.js"></script>
    
    <script src="resources/controlador/controller_pages/ticket_controller.js"></script>
    <script src="resources/controlador/controller_pages/mantenimientoTicket_controller.js"></script>
     <script src="resources/service/maqLocal_service.js"></script>
    
    <script
        src="resources/controlador/controller_pages/session_controller.js"></script>
    <script src="resources/filtros/session_filter.js"></script>
    <script src="resources/service/util_chars_service.js"></script>
    <script>
        $(document).ready(function() {
            App.init();
        });
    </script>
</body>
</html>