function operationNewCtrl ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal, 
                           $modalInstance) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    $scope.operation = {};
    
    //usar una funcion para que dependiendo del momento del dia se setee
    $scope.operation.shift = 'Afternoon';
    
    $scope.turnos = [ 'Afternoon', 'Beforenoon'];
    
    $scope.cuentas = [ 'Cash', 'Bank', 'Current'];
    
    $scope.operation.accountType = 'Cash' ;
    
    $scope.tarjetas = [ 'Debit', 'Credit'];
    
    $scope.operation.cardType = 'Debit';
    
    $scope.paymentsMethods =  [ 'Incoming', 'Outcoming'];
    
    $scope.operation.type = 'Incoming';
    
    $scope.clear = function () {
        $scope.date = null;
    };
    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
    
    $scope.today = function () {
        $scope. date = new Date();
    };

    $scope.today();
    
    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    
    $scope.save = function() {
        console.log("amount value on save "+$scope.operation.amount);
		if ($scope.operation.amount != NaN) {
           
            $http.get(restWebService + "categoryService/byId/" +$scope.categoryId)
                .success(function(response) {
                    $scope.operation.category = response;
                })
                .error(function() {
            });
            
            console.log("categoria que traigo del backend"+ $scope.operation.category);
            angular.extend($scope.operation, {
                    date: $scope.date
            });
            var jsonOperation = angular.toJson($scope.operation);
            console.log("despues de convertir a json: "+jsonOperation);
            console.log("http post: ...");
            $http.post(restWebService +'operationService/save', jsonOperation).success(
				function(data, status, headers, config) {
					if (status == 200) {
						console.log("contra todos los pronosticos, anda!");
                        $modalInstance.close();
					}
				}).error(function(data, status, headers, config) {
			         console.log("Error al crear operacion");
		      });
        }
       else {
           $scope.alerts = [];
           $scope.alerts.push({
                type: 'danger',
                msg: 'Ingrese un monto valido'
           });
       }
	};
    
    $scope.closeModal = function () {
        $modalInstance.dismiss();
    }
    
/*    function setCategory () {
        var d = $q.defer();
        $http.get(restWebService + "categoryService/byId/" +$scope.categoryId)
        .success(function(response) {
            console.log("set category spected operation.category undefined"+$scope.operation.category);
            $scope.operation.category = response;
            console.log("set category spected operation.category"+$scope.operation.category.name);
           // $scope.categorySelected = true;
            d.resolve();
        })
        .error(function() {
            d.reject();
        });
    }*/
    
    function loadCategories () {
        var d = $q.defer();
        $http.get(restWebService + "categoryService/categories")
        .success(function(response) {
            $scope.categories = response;
           // $scope.categorySelected = true;
            d.resolve();
        })
        .error(function() {
            d.reject();
        });

    }

    loadCategories ();
    
    $scope.loadSubcategories = function(catId) {
        $http.get(restWebService + "subcategoryService/byCategoryId/" + catId)
        .success(function(response) {   
            $scope.subcategories = response;
        })
        .error(function() {
        });
    };

    $scope.selectedType = function (accountType) {
        if (accountType == "Bank") {
            $scope.showBankOptions = true;
        }
        else {
            $scope.showBankOptions = false;
        }
        console.log("compare return value: "+$scope.showBankOptions);
    };    

    
};
