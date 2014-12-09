function vendorNewCtrl($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal, 
                            $modalInstance) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    $scope.alerts = [];

    $scope.vendor = {
        name: null,
        taxCode: null
    };

    $scope.clear = function () {
        $scope.date = null;
    };

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };

    $scope.save = function() {
        if ($scope.vendor.name == null) {
            

            /**
                VALIDAR QUE EL TAXCODE NO EXISTE
                EN ADICION A QUE NO SE REPITA EL NOMBRE
            **/

            
            angular.extend($scope.vendor, {
                name: $scope.name,
                taxCode: $scope.taxCode
            });
            var jsonVendor = angular.toJson($scope.vendor);
            $http.post(restWebService +'vendorService/save', jsonVendor).success(
                function(data, status, headers, config) {
                    if (status == 200) {
                        $modalInstance.close();
                    }
                }).error(function(data, status, headers, config) {
                console.log("Error al crear el proveedor");
                $scope.alerts.push({
                    type: 'danger',
                    msg: data
                });
            });
        } else {
            $scope.alerts.push({
                type: 'danger',
                msg: 'Ingrese un nombre!'
            });
        }
    };

    $scope.closeModal = function () {
        $modalInstance.dismiss();
    }

};