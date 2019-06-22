app.filter('color', function() {
    return function(i){
	    	switch (i) {
			case '0': return "btn btn-success";
			case '1': return "btn btn-warning";
			case '2': return "btn btn-danger";
			default:
				return "btn btn-warning";
		}
	};
});
app.filter('nombre', function() {
    return function(i){
    	switch (i) {
			case '0':
				return "Enviado y Aceptado SUNAT";
			case '1':
				return "Sin enviar SUNAT";
			case '2':
				return "Enviado y Anulado SUNAT";
			default:
				return "Sin enviar SUNAT";
		}
	}
});
app.filter('simbolo', function() {
    return function(i){
    	return (i=="DOLARES")?"$":"S/";
	}
});
app.filter('tipo', function() {
    return function(i){
    	return (i=="1")?"BOLETA":"FACTURA";
	}
});
app.filter('tipoComprobante', function() {
    return function(i){
    	switch (i) {
			case 10:
				return "Gravada"
			case 20:
				return "Exonerada"
			case 30:
				return "Inafecta"
			default:
		}
	}
});
app.filter('texto', function() {
    return function(i){
    	return (i==null)?"-":i;
	}
});
app.filter('estado', function() {
    return function(i){
    	return (i==1)?'Habilitado':'Deshabilitado';
	}
});
app.filter('colorEstado', function() {
    return function(i){
    	return (i==1)?'btn btn-success':'btn btn-danger';
	}
});