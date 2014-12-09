function paymentsCtrl($scope, $filter, ngTableParams, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs, resolvedPayments, $modal) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    console.log(resolvedPayments);
    $scope.payments = resolvedPayments.data;
    console.log($scope.payments);
    

    // Get all categories	  
    function refreshElements() {
        var d = $q.defer();
        $http.get(restWebService + "paymentService/payments")
        .success(function (response) {
            $scope.payments = response;
            d.resolve();
        }).error(function () {
            console.log("Error al listar pagos");
            d.reject();
        });
    }
    //revisar
    $scope.checkPaymentByNumber = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input a invoice number...');
        } else {
            $http.get(restWebService + "paymentService/byNumber/" + data)
            .success(function (data, status, header, config) {
                data = data || {};
                if ( status == 200 ) {
                    d.resolve();
                } else {
                    d.resolve();
                }
            }).error(function (e) {
                $scope.alerts = [];
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'El pago ya fue procesado'
                });
                d.reject();
            });
        }
        return d.promise;
    };
    //revisar
    $scope.checkPaymentByVendor = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input a vendor name...');
        } else {
            $http.get(restWebService + "paymentService/byVendor/" + data)
            .success(function (res) {
                res = res || {};
                if (res.status === 'ok') {
                    d.resolve()
                } else {
                    d.resolve(res.msg)
                }
            }).error(function (e) {
                $scope.alerts = [];
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'El pago ya fue procesado'
                });
                d.reject();
            });
        }
        return d.promise;
    };


    $scope.successfull = function () {
        console.log("OK saved!");
        $scope.alerts = [];
        $scope.alerts.push({
            type: 'success',
            msg: 'Saved!'
        });
        refreshElements();
    };

    $scope.loadModalCreate = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/paymentCreate.html',
            controller: 'paymentNewCtrl',
            backdrop: false,
            size: 'lg',
            scope: $scope
        });
        modalInstance.result.then($scope.successfull);
    };  
    
    $scope.updatePayment = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'paymentService/' + id, jsonCategory).success(
            function (data, status, headers, config) {
                console.log("status del success de mkUpdate: " + status);
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update Payment OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a payment: " + id);
        });
    };

    /*
     *  PAYMENTS TABLE
     */    
    $scope.paymentsTable = new ngTableParams({
        page: 1,            // show first page
        count: 10,          // count per page
        filter: {
            
        },
        sorting: {
            dateToString: 'asc'        // initial sorting
        }
    }, {
        total: function () {
            return $scope.payments.length;
        },
        getData: function($defer, params) {
            var filteredData = params.filter() ?
                $filter('filter')($scope.payments, params.filter()) : $scope.payments;
            var orderedData = params.sorting() ?
                $filter('orderBy')(filteredData, params.orderBy()) : $scope.payments;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve(orderedData.slice((params.page() - 1)
                    * params.count(), params.page() * params.count()));
        }
    });



    /*
     *  PAGINACION
     */
    $scope.filteredPayments = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 5;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalPayments = function(){
        return $scope.payments.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.payments.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredPayments = $scope.payments.slice(begin, end);
    });


    /*
     *  EXTRAS
     */    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
}