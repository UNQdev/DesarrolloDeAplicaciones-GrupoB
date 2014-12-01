feag.controller('homeCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    function loadAmounts() {
        $http.get(restWebService + "accountService/byName/"+"CASH")
        .success(function (response) {
            $scope.cash_account_amount = response.accountBalance;
            $scope.operations = response.operations;
        }).error(function () {
            console.log("Error al listar categorias");
        });
        
        $http.get(restWebService + "accountService/byName/"+"BANK")
        .success(function (response) {
            $scope.bank_account_amount = response.accountBalance;
        }).error(function () {
            console.log("Error al listar categorias");
        });
        
        $http.get(restWebService + "accountService/byName/"+"CURRENT")
        .success(function (response) {
            $scope.current_account_amount = response.accountBalance;
        }).error(function () {
            console.log("Error al listar categorias");
        });
    }
    loadAmounts();
});