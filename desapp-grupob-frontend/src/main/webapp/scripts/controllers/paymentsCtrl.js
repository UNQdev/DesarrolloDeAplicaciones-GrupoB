function paymentsCtrl($scope, $filter, ngTableParams, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs, resolvedPayments) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    console.log(resolvedPayments);
    $scope.payments = resolvedPayments.data;
    console.log($scope.payments);
    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
    
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

    $scope.launchConfirmDeleteDialog = function (id) {
        var dlg = dialogs.confirm();
        dlg.result.then(function (btn) {
            $scope.removePayment(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };

    $scope.savePayment = function (data) {
        $http.post(restWebService + 'paymentService/save', data)
        .success( function (data, status, headers, config) {
                if (status == 201) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Saved!'
                    });
                    console.log("Save Payment OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a payment");
        });
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

    $scope.removePayment = function (id) {
        $http.delete(restWebService + 'paymentService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete Payment OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: data
            });
            console.log("An Error occurred while trying to delete a payment: " + id);
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
     *  INTERNACIONALIZACION
     */
    $scope.lang = 'en-US';
    $scope.language = 'English';

    $scope.$watch('lang', function (val, old) {
        switch (val) {
            case 'en-US':
                $scope.language = 'English';
                break;
            case 'es':
                $scope.language = 'Spanish';
                break;
        }
    });

    $scope.setLanguage = function (lang) {
        $scope.lang = lang;
        $translate.use(lang);
        console.log(lang);
    };



    /*
     *  EXTRAS
     */    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
}