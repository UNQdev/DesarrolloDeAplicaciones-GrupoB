feag.controller('homeCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    // Get all operations	  
    function getAll() {
        $http.get(restWebService + "operationService/operations")
        .success(function (response) {
            console.log(response);
            $scope.operations = response;
        }).error(function () {
            console.log("Error al listar categorias");
        });
    }

    //Initial call to render list
    getAll();
});