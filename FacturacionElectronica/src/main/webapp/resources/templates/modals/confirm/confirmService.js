app.service('modalService', ['$uibModal',
function ($uibModal) {

    var modalDefaults = {
        backdrop: true,
        keyboard: true,
        modalFade: true,
        appendTo: '', //angular.element(document.querySelector( '#Editar' )),
        size: 'sm',
        templateUrl: 'resources/templates/modals/confirm/modalConfirm.html'
    };

    var modalOptions = {
    	headerColorValue: 0,  //0:btn_primary, 1:btn_success, 2:btn_warning, 3: btn_danger
        closeButtonText: 'No',
        actionButtonText: 'Si',
        headerText: 'Proceder?',
        bodyText: 'Realizar ésta acción?'
    };

    this.showModal = function (customModalDefaults, customModalOptions) {
        if (!customModalDefaults) customModalDefaults = {};
        customModalDefaults.backdrop = 'static';
        return this.show(customModalDefaults, customModalOptions);
    };

    this.show = function (customModalDefaults, customModalOptions) {
        //Create temp objects to work with since we're in a singleton service
        var tempModalDefaults = {};
        var tempModalOptions = {};

        //Map angular-ui modal custom defaults to modal defaults defined in service
        angular.extend(tempModalDefaults, modalDefaults, customModalDefaults);

        //Map modal.html $scope custom properties to defaults defined in service
        angular.extend(tempModalOptions, modalOptions, customModalOptions);

        if (!tempModalDefaults.controller) {
            tempModalDefaults.controller = function ($scope, $uibModalInstance) {
                $scope.modalOptions = tempModalOptions;
                $scope.data = {
                	motivoRechazo: ''
                };
                
                $scope.modalOptions.ok = function (result) {
                	//console.log($scope.data);
                	$uibModalInstance.close($scope.data);
                };
                $scope.modalOptions.close = function (result) {
                	$uibModalInstance.dismiss('cancel');
                };
            }
        }

        return $uibModal.open(tempModalDefaults).result;
    };

}]);