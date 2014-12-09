function vendorsCtrl($scope, $filter, ngTableParams, $http, $location, $route, $q, $log, $rootScope, $timeout, $translate, dialogs, resolvedVendors, $modal) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    console.log(resolvedVendors);
    $scope.vendors = resolvedVendors.data;
    console.log($scope.vendors);



    /*
     *  VENDORS CRUD
     */
    function refreshElements() {
        var d = $q.defer();
        $http.get(restWebService + "vendorService/vendors")
        .success(function (response) {
            $scope.vendors = response;
            d.resolve();
        }).error(function () {
            console.log("Error al listar proveedores");
            d.reject();
        });
    }

    $scope.saveVendor = function (data) {
        $http.post(restWebService + 'vendorService/save', data)
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
            console.log("An Error occurred while trying to store a vendor");
        });
    };

    $scope.updateVendor = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'vendorService/' + id, jsonCategory).success(
            function (data, status, headers, config) {
                console.log("status del success de mkUpdate: " + status);
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update Vendor OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a vendor: " + id);
        });
    };

    $scope.removeVendor = function (id) {
        $http.delete(restWebService + 'vendorService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete Vendor OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: data
            });
            console.log("An Error occurred while trying to delete a vendor: " + id);
        });
    };

    

    /*
     *  VENDOR CREATION
     */

    $scope.loadModalCreate = function () {
        var modalInstance = $modal.open({
            templateUrl: 'views/vendorCreate.html',
            controller: 'vendorNewCtrl',
            backdrop: false,
            size: 'lg',
            scope: $scope
        });
        modalInstance.result.then($scope.successfull);
    };
    
    
    
    /*
     *  EDITION UTILS
     */
    $scope.checkCreation = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input an vendor name...');
        } else {
            $http.get(restWebService + "vendorService/byVendor" + data)
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
                    msg: 'El proveedor ya fue procesado'
                });
                d.reject();
            });
        }
        return d.promise;
    };

    $scope.launchConfirmDeleteDialog = function (id) {
        var dlg = dialogs.confirm();
        dlg.result.then(function (btn) {
            $scope.removeVendor(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };

    /*
     *  PAGINACION
     */
    $scope.filteredVendors = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 5;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalVendors = function(){
        return $scope.vendors.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.vendors.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredVendors = $scope.vendors.slice(begin, end);
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