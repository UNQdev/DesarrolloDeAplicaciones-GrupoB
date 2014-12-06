function operationsCtrl ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal) {
    

	// Variables
   var restWebService = "http://localhost:8081/backend_api/rest/";
    
   $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
   };    
	
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
    
    $scope.successfull = function () {
        console.log("OK saved!");
        $scope.alerts = [];
           $scope.alerts.push({
                type: 'success',
                msg: 'Saved!'
           });
        loadOperations ();
    }
    
    $scope.loadModalCreate = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/operationCreate.html',
            controller: 'operationNewCtrl',
            backdrop: false,
            size: 'lg',
            scope: $scope
        });
        modalInstance.result.then($scope.successfull);
    }
    
};