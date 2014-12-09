function operationsCtrl($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal, resolvedOperations) {


    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    $scope.operations = resolvedOperations.data;
    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
   };
    
    $scope.turnos = [
        {value: 'Afternoon', text: 'Afternoon'},
        {value: 'Beforenoon', text: 'Beforenoon'}
    ]; 

    $scope.showShift = function(operation) {
        var selected =  {value: 1, text: 'Afternoon'};
        if(operation.shift) {
            selected = $filter('filter')($scope.turnos, {value: operation.shift});
        }
        return selected.length ? selected[0].text : 'Not set';
    };
    
    function loadOperations() {
        $http.get(restWebService + "operationService/operations")
            .success(function (response) {
                $scope.operations = response;
            }).error(function () {
                console.log("Error al cargar listado de operaciones");
            });
    };

    $scope.successfull = function () {
        console.log("OK saved!");
        $scope.alerts = [];
        $scope.alerts.push({
            type: 'success',
            msg: 'Saved!'
        });
        loadOperations();
    };

    $scope.loadModalCreate = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/operationCreate.html',
            controller: 'operationNewCtrl',
            backdrop: false,
            size: 'lg',
            scope: $scope
        });
        modalInstance.result.then($scope.successfull);
    };

    $scope.updateOperation = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonOperation = angular.toJson(data);
        $http.put(restWebService + 'operationService/', jsonOperation).success(
            function (data, status, headers, config) {
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update Operation OK");
                    loadOperations(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a category: " + id);
        });
    };
    
    
    $scope.exportOperations = function(){
        listDict = 			[{
            a: 'Date',
            b: 'Category',
            c: 'Subcategory',
            d: 'Concept',
            e: 'Shift',
            f: 'Amount',
            g: 'Account',
            h: 'OperationType',
            i: 'CardType',
        }];
        for (var i = 0; i < $scope.operations.length; i++) {
            operation = $scope.operations[i];
            dict = 
                {
                a: operation.dateToString,
                b: operation.category.name,
                c: operation.subcategory.name,
                d: operation.concept,
                e: operation.shift,
                f: operation.realAmount,
                g: operation.accountType,
                h: operation.type,
                i: operation.cardType,
            };
            listDict.push(dict);
        }
        return listDict;
    }

    /*
     *  PAGINACION
     */
    $scope.filteredOperations = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 5;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalOperations = function(){
        return $scope.operations.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.operations.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredOperations = $scope.operations.slice(begin, end);
    });


};