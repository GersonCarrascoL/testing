app.directive('loading', function () {
      return {
        restrict: 'E',
        replace:true,
        template: '<div style="float: right;  margin-left: 8px;" class="loading"><img src="http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif" width="20" height="20" /></div>',
        link: function (scope, element, attr) {
              scope.$watch('loading', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
      }
});

/*************  MUESTRA <DIV> CON GIF VALIDANDO...  ****************/
app.directive('loadValidandoRuc', function () {
      return {
        restrict: 'E',
        replace:true,
        template: '<div class="text-center"><img src="http://www.nasa.gov/multimedia/videogallery/ajax-loader.gif" width="32" height="32" /> <span style="font-size:15px;color:#1c436a;margin-left:8px;">  Validando...</span></div>',
        link: function (scope, element, attr) {
              scope.$watch('loadValidandoRuc', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
      }
});

/*************  MUESTRA <DIV> CON GIF VALIDANDO...  ****************/
app.directive('loadingDatos', function () {
      return {
        restrict: 'E',
        replace:true,        
        template: '<div class="row" id="cargandoData"><div class="col-md-12"><p class="hidden-sm text-center" style="font-size:16px;"><i class="fa fa-circle-o-notch fa-spin fa-2x" style="vertical-align:middle"></i> <span> Cargando datos...</span></p></div></div>',
        link: function (scope, element, attr) {
              scope.$watch('loadingDatos', function (val) {
                  if (val)
                      $(element).show();
                  else
                      $(element).hide();
              });
        }
      }
});


/*************  MUESTRA <DIV> MODAL CON GIF SPINNER  ****************/
/*************  <spinner-modal show="true/false"></spinner-modal>  ****************/
app.directive('spinnerModal', function () {
    return {
            restrict: 'EA', //E = element, A = attribute, C = class, M = comment  
            replace: true,
            scope: {
                //@ reads the attribute value, = provides two-way binding, & works with functions
                show: '@',
            },
            template: '<div id="spinnerModalDiv" class="modal fade mSpinCenter" data-backdrop="static" data-keyboard="false">' +
	            '   <style>                         ' +
				'     .mSpinCenter {				' +
	            '			text-align: center;		' +
	            '			padding: 0!important;	' +
	          	'	  }								' +
	          	'	  .mSpinCenter:before {			' +
	            '	  		content: "";			' +
	            '			display: inline-block;	' +
	            '			height: 100%;			' +
	            '			vertical-align: middle;	' +
	            '			margin-right: -4px;		' +
	          	'	  }								' +
		        '     .mSpinCenter-dialog {			' +
		        '		    display: inline-block;	' +
		        '		    width: 95px;			' +
		        '		    text-align: left;		' +
		        '		    vertical-align: middle;	' +
		        '	  }								' +
				'   </style>                        ' +
                '<div class="modal-dialog mSpinCenter-dialog">' +
                '<div class="modal-content mSpinCenter-content">' +
                '<div class="modal-body">' +
                '<i class="fa fa-circle-o-notch fa-spin fa-5x"></i>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>',
            link: function(scope, element, attrs) {
                    scope.$watch('show', function(n, o) {
                        if (n === 'true') {
                            $('#spinnerModalDiv').modal('show');
                            //console.log(o + '-->' + n);
                        } else if(n === 'false') {
                            $('#spinnerModalDiv').modal('hide');
                            //console.log(o + '-->' + n);
                        }
                    }, true);
                } //DOM manipulation
        };
});

/*************  ATRIBUTO QUE FUERZA MAYUSCULAS EN UN INPUT  ****************/
/*************  REQUIERE UN ATRIBUTO NG-MODEL EN EL INPUT  ****************/
app.directive('capitalize', function(){
    return{
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel){

            ngModel.$formatters.push(function(value){
            		if (value == undefined) value = '';
            		return value.toUpperCase();
            });

            ngModel.$parsers.push(function(value){
	                if (value == undefined) value = '';
	                var view_value = value.toUpperCase();
	                if (view_value !== value) {
	                	var selection = element[0].selectionStart;
		                ngModel.$setViewValue(view_value);
		                ngModel.$render();
		                element[0].selectionStart = selection; 
	  					element[0].selectionEnd = selection;
	                }
    				return value.toUpperCase();
            });

        }
    };
});

/*************  ATRIBUTO QUE FUERZA LETRA CAPITAL EN UN INPUT  ****************/
/*************  REQUIERE UN ATRIBUTO NG-MODEL EN EL INPUT  ****************/
app.directive('capitalizeFirst', function(){
    return{
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attr, ngModel){

            ngModel.$formatters.push(function(value){
            		if (value == undefined) value = '';
            		var view_value = value.charAt(0).toUpperCase() + value.substring(1);
            		return view_value;
            });

            ngModel.$parsers.push(function(value){
	                if (value == undefined) value = '';
	                var view_value = value.charAt(0).toUpperCase() + value.substring(1);
	                var model_value = view_value;
	                if (view_value !== value) {
	                	var selection = element[0].selectionStart;
		                ngModel.$setViewValue(view_value);
		                ngModel.$render();
		                element[0].selectionStart = selection; 
	  					element[0].selectionEnd = selection;
	                }
    				return model_value;
            });

        }
    };
});


//
app.directive('numericoDecimal', function() {
    return {
        require: "ngModel",
        link: function (scope, elem, attr, ctrl) {
            //$(elem).inputmask("hh:mm:ss", {placeholder: "HH:MM:SS", insertMode: false, showMaskOnHover: false});
            $(elem).inputmask({
                mask: "a{1,9}[.99]",
                definitions: {'a': {validator: "[0-9]"}}
            });
            elem.on('keyup', function () 
            {
                scope.$apply(function()
                {
                    ctrl.$setViewValue(elem.val());
                });
            });
        }
    };
});
