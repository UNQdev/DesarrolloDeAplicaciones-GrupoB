feag.controller('paymentsCtrl', function ($scope, $filter, ngTableParams, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
    
    // Get all categories	  
    function getAll() {
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

    //Initial call to render list
    getAll();
    

    // By perform remote validation, | if the input name already exists in db.
    // For that define validation method returning $q promise. 
    // If promise resolves to string validation failed.
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

    /*$scope.launchConfirmDeleteDialog = function (id) {
        var dlg = dialogs.confirm();
        dlg.result.then(function (btn) {
            $scope.removeCategory(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };*/

    // add category
    $scope.savePayment = function (data) {
        $http.post(restWebService + 'paymentService/save', data)
        .success( function (data, status, headers, config) {
                if (status == 201) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Saved!'
                    });
                    console.log("Save Category OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a category, with name: " + data.name);
        });
    };

    // update category
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
                    console.log("Update Category OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a category: " + id);
        });
    };

    // delete category
    $scope.removeCategory = function (id) {
        $http.delete(restWebService + 'categoryService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete Category OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: data
            });
            console.log("An Error occurred while trying to delete a category: " + id);
        });
    };
    
    
    /* PAYMENTS TABLE */
    $scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10,          // count per page
        filter: {
            name: ''        // initial filter
        },
        sorting: {
            name: ''        // initial sorting
        }
    }, {
        counts: [],
        total: function() {
            return getData().length;
            // return $scope.payments.length; // length of data
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
    
    /*$scope.tableParams = new ngTableParams({
        page : 1, // show first page
        count : 30, // count per page
        sorting : {
            name : 'asc' // initial sorting
        }
    }, {
        counts: [],
        total: function() {
            return getData().length;
        }, // length of data
        getData : function($defer, params) {
            var filteredData = billElements;
            var orderedData = params.sorting() ? $filter('orderBy')(
                filteredData, params.orderBy()) : filteredData;

            $defer.resolve(orderedData.slice((params.page() - 1)
                                             * params.count(), params.page() * params.count()));
        },
        $scope : {
            $data : {}
        }
    });*/
});