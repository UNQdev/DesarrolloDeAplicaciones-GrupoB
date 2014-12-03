feag.controller('homeCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    function loadAmounts() {
        $http.get(restWebService + "accountService/byId/1")
        .success(function (response) {
            $scope.cash_account_amount = response.accountBalance;
            $scope.operations = response.operations; //default operations 
        }).error(function () {
            console.log("Error al cargar saldos de cuenta efectivo");
        });
        
        $http.get(restWebService + "accountService/byId/2")
        .success(function (response) {
            $scope.bank_account_available = response.available;
            $scope.bank_account_unconsolidated = response.unConsolidatedAmount;
        }).error(function () {
            console.log("Error al cargar saldos de cuenta banco");
        });
        
        $http.get(restWebService + "accountService/byId/3")
        .success(function (response) {
            $scope.current_account_amount = response.accountBalance;
        }).error(function () {
            console.log("Error al cargar saldos de cuenta corriente");
        });
    }
    
    loadAmounts();
    
    $scope.loadCashOperations = function () {
        $http.get(restWebService + "accountService/byId/1")
        .success(function (response) {
            console.log("loadCashOperations OK");
            $scope.operations = response.operations;
        }).error(function () {
            console.log("Error al cargar listado de operaciones de la cuenta efectivo");
        });
    }
    
    $scope.loadBankOperations = function () {
        $http.get(restWebService + "accountService/byId/2")
        .success(function (response) {
            console.log("loadBankOperations OK"+response.operations);
            $scope.operations = response.operations;
        }).error(function () {
            console.log("Error al cargar listado de operaciones de la cuenta banco");
        });
    }
    
    $scope.loadCurrentOperations = function () {
        $http.get(restWebService + "accountService/byId/3")
        .success(function (response) {
            console.log("loadCurrentOperations OK");
            $scope.operations = response.operations;
        }).error(function () {
            console.log("Error al cargar listado de operaciones de la cuenta corriente");
        });
    }
    
});