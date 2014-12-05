feag.controller('operationsCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal) {
    

	// Variables
   var restWebService = "http://localhost:8081/backend_api/rest/";
	
   function loadOperations () {
        $http.get(restWebService + "operationService/operations")
        .success(function (response) {
            console.log("loadOperations OK");
            console.log(response);
            $scope.operations = response;
        }).error(function () {
            console.log("Error al cargar listado de operaciones");
        });
    }
	
    loadOperations ();
    
    $scope.loadModalCreate = function () {
        $modal.open({
            templateUrl: 'views/operationCreate.html',
            backdrop: false,
            size: 'lg',
            scope: $scope
        });
    }
    
});