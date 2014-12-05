feag.controller('operationNewCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    $scope.clear = function () {
        $scope.date = null;
    };

    $scope.turnos = [{
        name: "Afternoon"
    }, {
        name: "Beforenoon"
    }];
    
    $scope.cuentas = [{
        name: "Cash"
    }, {
        name: "Bank"
    }, {
        name: "Current"    
    }];
    
    $scope.tarjetas = [{
        name: "Debit"  
    }, {
        name: "Credit" 
    }];
    
    $scope.tiposDeOperacion = [{
        name: "Incoming"  
    }, {
        name: "Outcoming" 
    }];

    $scope.today = function () {
        $scope.date = new Date();
    };

    $scope.today();

    $scope.reset = function() {
		$scope.operation = {};
	};

	//$scope.reset();
    
    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    
    $scope.save = function(aOperation) {
		$scope.trySubmit = true;
		
		if ($scope.operationForm.$invalid) {
			return false;
		}		
		
        console.log("antes de convertir a json: "+aOperation);
		var jsonOperation = angular.toJson(aOperation);
        
        console.log("despues de convertir a json: "+jsonOperation);
/*		$http.post('/Tpl/rest/books', jsonBook).success(
				function(data, status, headers, config) {

					if (status == 201) {
						$location.path("/");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a book");
		});*/
	};
    
    function loadCategories () {
        var d = $q.defer();
        $http.get(restWebService + "categoryService/categories")
        .success(function(response) {
            $scope.categories = response;
            $scope.categorySelected = true;
            d.resolve();
        })
        .error(function() {
            d.reject();
        });

    }

    loadCategories ();
    
    $scope.loadSubcategories = function(catId) {
        console.log(catId);
        $http.get(restWebService + "subcategoryService/byCategoryId/" + catId)
        .success(function(response) {   
            $scope.subcategories = response;
            console.log($scope.subcategories);
        })
        .error(function() {
        });
    };

    $scope.selectedType = function (accountType) {
        console.log(accountType);
        if (accountType == "Bank") {
            $scope.showBankOptions = true;
        }
        else {
            $scope.showBankOptions = false;
        }
        console.log($scope.showBankOptions);
    };    

    
});