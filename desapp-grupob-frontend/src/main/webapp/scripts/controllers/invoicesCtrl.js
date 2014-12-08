function invoicesCtrl($scope, $filter, ngTableParams, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs, resolvedInvoices) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    console.log(resolvedInvoices);
    $scope.invoices = resolvedInvoices.data;
    console.log($scope.invoices);



    /*
     *  INVOICES CRUD
     */
    function refreshElements() {
        var d = $q.defer();
        $http.get(restWebService + "invoiceService/invoices")
        .success(function (response) {
            $scope.invoices = response;
            d.resolve();
        }).error(function () {
            console.log("Error al listar comprobantes");
            d.reject();
        });
    }
    
    $scope.saveInvoice = function (data) {
        $http.post(restWebService + 'invoiceService/save', data)
        .success( function (data, status, headers, config) {
            if (status == 201) {
                $scope.alerts = [];
                $scope.alerts.push({
                    type: 'success',
                    msg: 'Saved!'
                });
                console.log("Save Invoice OK");
                refreshElements(); //refresh table with new state
            }
        }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a invoice");
        });
    };

    $scope.updateInvoice = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'invoiceService/' + id, jsonCategory).success(
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
            console.log("An Error occurred while trying to update a invoice: " + id);
        });
    };

    $scope.removeInvoice = function (id) {
        $http.delete(restWebService + 'invoiceService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete Invoice OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: data
            });
            console.log("An Error occurred while trying to delete a invoice: " + id);
        });
    };



    /*
     *  EDITION UTILS
     */
    $scope.checkCreation = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input an invoice number...');
        } else {
            $http.get(restWebService + "invoiceService/byVendor" + data)
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
                    msg: 'El comprobante ya fue procesado'
                });
                d.reject();
            });
        }
        return d.promise;
    };

    $scope.launchConfirmDeleteDialog = function (id) {
        var dlg = dialogs.confirm();
        dlg.result.then(function (btn) {
            $scope.removeInvoice(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };



    /*
     *  INVOICE TABLE
     */    
    $scope.invoicesTable = new ngTableParams({
        page: 1,            // show first page
        count: 10,          // count per page
        filter: {

        },
        sorting: {
            dateToString: 'asc'        // initial sorting
        }
    }, {
        total: function () {
            return $scope.invoices.length;
        },
        getData: function($defer, params) {
            getAll();
            var filteredData = params.filter() ?
                $filter('filter')($scope.invoices, params.filter()) : $scope.invoices;
            var orderedData = params.sorting() ?
                $filter('orderBy')(filteredData, params.orderBy()) : $scope.invoices;

            params.total(orderedData.length); // set total for recalc pagination
            $defer.resolve(orderedData.slice((params.page() - 1)
                                             * params.count(), params.page() * params.count()));
        }
    });
    
    
    /*
     *  PAGINACION
     */
    $scope.filteredInvoices = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 5;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalInvoices = function(){
        return $scope.invoices.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.invoices.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredInvoices = $scope.invoices.slice(begin, end);
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