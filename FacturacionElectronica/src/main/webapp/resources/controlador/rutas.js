var app=angular.module("Aplicacion", ['ngSanitize','ngRoute','ngTable','ui.bootstrap','rt.select2', 'angular.filter']);
app.config(function($routeProvider) {
        $routeProvider
          
        .when('/', {
           templateUrl : 'resources/templates/inicio.html'

        })
          
        .when('/factura', {
            templateUrl : 'resources/templates/comprobantePago/factura.html',
            controller  : 'factura_controller'
        })
        
        .when('/boleta', {
            templateUrl : 'resources/templates/comprobantePago/boleta.html',
            controller  : 'boleta_controller'
        })
        
        .when('/mantenimientoFactura', {
            templateUrl : 'resources/templates/comprobantePago/mantenimientoFactura.html',
            controller  : 'mantenimiento_factura_controller'
        })
        
        .when('/mantenimientoBoleta', {
            templateUrl : 'resources/templates/comprobantePago/mantenimientoBoleta.html',
            controller  : 'mantenimiento_boleta_controller'
        })
        
        .when('/mantenimientoNotaCreditoBoleta', {
            templateUrl : 'resources/templates/comprobantePago/mantenimientoNotaCreditoBoleta.html',
            controller  : 'mantenimiento_notaCreditoBoleta_controller'
        })
        
        .when('/mantenimientoNotaCreditoFactura', {
            templateUrl : 'resources/templates/comprobantePago/mantenimientoNotaCreditoFactura.html',
            controller  : 'mantenimiento_notaCreditoFactura_controller'
        })

        .when('/mantenimientoEstablecimientoBoletaYFactura', {
            templateUrl : 'resources/templates/comprobantePago/mantenimientoEstablecimientoBoletaYFactura.html',
            controller  : 'mantenimiento_establecimiento_boleta_y_factura_controller'
        })

        //-----------------------DRAMOS--------------------
        .when('/mantenimientoListaItemPrecios', {
            templateUrl : 'resources/templates/mantenimiento/listaPrecios.html',
            controller  : 'lista_precios_controller'
        })
        .when('/mantenimientoListaServicios', {
            templateUrl : 'resources/templates/mantenimiento/listaServicios_mant.html',
            controller  : 'lista_servicios_controller'
        })
        .when('/mantenimientoConceptosPago', {
            templateUrl : 'resources/templates/mantenimiento/conceptosPago.html',
            controller  : 'conceptos_pago_controller'
        })
        .when('/mantenimientoUnidadesRecaudadoras', {
            templateUrl : 'resources/templates/mantenimiento/unidades_recaudadoras_mant.html',
            controller  : 'unidad_recaudadora_controller'
        })
        .when('/mantenimientoClientes', {
            templateUrl : 'resources/templates/mantenimiento/maestroClientes_mant.html',
            controller  : 'maestro_clientes_controller'
        })
        .when('/solicitudesConceptosPago', {
            templateUrl : 'resources/templates/solicitudes/solicitudesConceptosPago.html',
            controller  : 'solicitudes_conceptoPago_controller'
        })
        .when('/solicitudesUnidRecaudadora', {
            templateUrl : 'resources/templates/solicitudes/solicitudesUnidadRecaudadora.html',
            controller  : 'solicitudes_unidad_recaudadora_controller'
        })
        .when('/consultaListaPrecios', {
            templateUrl : 'resources/templates/consultas/listaprecios_consulta.html',
            controller  : 'lista_precios_controller'
        })
        .when('/consultaListaServicios', {
            templateUrl : 'resources/templates/consultas/listaServicios_consulta.html',
            controller  : 'lista_servicios_controller'
        })
        .when('/consultaConceptoPago', {
            templateUrl : 'resources/templates/consultas/conceptopago_consulta.html',
            controller  : 'conceptos_pago_controller'
        })
        .when('/consultaClientes', {
            templateUrl : 'resources/templates/consultas/maestroClientes.html',
            controller  : 'maestro_clientes_controller'
        })
        
        .when('/emisionTickets', {
            templateUrl : 'resources/templates/comprobantePago/emisionTicket.html',
            controller  : 'ticket_controller'
        })
        
        .when('/mantenimientoTickets',{
        	templateUrl : 'resources/templates/comprobantePago/mantenimientoTicket.html',
        	controller  : 'mantenimientoTicket_controller'
        })
        .when('/regventas',{
        	templateUrl : 'resources/templates/reportes/regventas.html',
        	controller  : 'regventas_controller'
        })
        .when('/regventasGeneral',{
        	templateUrl : 'resources/templates/reportes/regventasGeneral.html',
        	controller  : 'regventasGeneral_controller'
        })
        .when('/ayuda',{
        	templateUrl : 'resources/templates/consultas/ayuda.html',
        	controller  : 'ayuda_controller'
        })
        .when('/notaCreditoFactura',{
        	templateUrl : 'resources/templates/comprobantePago/nota_credito_factura.html',
        	controller  : 'notaCreditoFactura_controller'
        }) 
        .when('/notaCreditoBoleta',{
        	templateUrl : 'resources/templates/comprobantePago/nota_credito_boleta.html',
        	controller  : 'notaCreditoBoleta_controller'
        })  
        .when('/roles',{
        	templateUrl : 'resources/templates/mantenimiento/roles.html',
        	controller	: 'mantenimientoRoles_controller'
        })
        .otherwise({
            redirectTo: '/'
        });
}).run(function($rootScope, $route, $location){
   //Bind the `$locationChangeSuccess` event on the rootScope, so that we dont need to 
   //bind in induvidual controllers.

   $rootScope.$on('$locationChangeSuccess', function() {
        $rootScope.actualLocation = $location.path();
    });        

   $rootScope.$watch(function () {
	   	return $location.path()
	   }, function (newLocation, oldLocation) {
        if($rootScope.actualLocation === newLocation) {
            $(".modal-backdrop.fade.in.modal-stack").removeClass("modal-backdrop");
            $("body").removeClass("modal-open")
        }
    });
});;