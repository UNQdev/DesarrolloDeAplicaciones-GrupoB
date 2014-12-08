function operationNewCtrl ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal, 
                           $modalInstance) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    $scope.alerts = [];
    
    $scope.operation = {};
    
    function setShift () {
        var date = new Date();
        if (date.getHours > 12) {
            $scope.operation.shift = 'Afternoon';
        }
        else {
            $scope.operation.shift = 'Beforenoon';
        }
    }
    
    setShift ();
    
    $scope.turnos = [ 'Afternoon', 'Beforenoon'];
    
    $scope.cuentas = [ 'Cash', 'Bank', 'Current'];
    
    $scope.operation.accountType = 'Cash' ;
    
    $scope.tarjetas = [ 'Debit', 'Credit'];

    $scope.paymentsMethods =  [ 'Incoming', 'Outcoming'];
    
    $scope.operation.type = 'Incoming';
    
    $scope.clear = function () {
        $scope.date = null;
    };
    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
    
    $scope.today = function () {
        $scope.date = new Date();
    };

    $scope.today();
    
    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    
    $scope.save = function() {
		if ($scope.operation.amount != NaN) {
            angular.extend($scope.operation, {
                    date: $scope.date
            });
            var jsonOperation = angular.toJson($scope.operation);
            $http.post(restWebService +'operationService/save', jsonOperation).success(
				function(data, status, headers, config) {
					if (status == 200) {
                        $modalInstance.close();
					}
				}).error(function(data, status, headers, config) {
			         console.log("Error al crear operacion");
                     $scope.alerts.push({
                        type: 'danger',
                        msg: data
                    });
		      });
        }
       else {
           $scope.alerts.push({
                type: 'danger',
                msg: 'Ingrese un monto valido'
           });
       }
	};
    
    $scope.closeModal = function () {
        $modalInstance.dismiss();
    }
    
    function loadCategories () {
        var d = $q.defer();
        $http.get(restWebService + "categoryService/categoriesForNewOperation")
        .success(function(response) {
            $scope.categories = response;
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
    };    

    
};
