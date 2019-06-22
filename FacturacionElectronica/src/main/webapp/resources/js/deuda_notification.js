
var handleGritterNotification = function() {
	
	$('#add-regular').click( function() {
		$.gritter.add({
			title: 'This is a regular notice!',
			text: 'This will fade out after a certain amount of time. Sed tempus lacus ut lectus rutrum placerat. ',
			image: 'assets/img/user-3.jpg',
			sticky: false,
			time: ''
		});
		return false;
	});

};


var Notification = function () {
	"use strict";
    return {
        //main function
        init: function () {
            handleGritterNotification();
        }
    };
}();