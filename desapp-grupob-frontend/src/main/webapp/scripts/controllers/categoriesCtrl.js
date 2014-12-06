function categoriesCtrl($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, resolvedCategories){

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    console.log(resolvedCategories);
    $scope.categories = resolvedCategories.data;
    console.log($scope.categories);
    
    
    /*
     *  CATEGORIES CRUD
     */
    function refreshElements() {
        var d = $q.defer();
        $http.get(restWebService + "categoryService/categories")
        .success(function (response) {
            $scope.categories = response;
            d.resolve();
        }).error(function () {
            console.log("Error al listar categorias");
            d.reject();
        });
        return d.promise;
    }
    
    $scope.saveCategory = function (data) {
        $http.post(restWebService + 'categoryService/save', data).success(
            function (data, status, headers, config) {
                if (status == 201) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Saved!'
                    });
                    console.log("Save Category OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a category, with name: " + data.name);
        });
    };

    $scope.updateCategory = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'categoryService/' + id, jsonCategory).success(
            function (data, status, headers, config) {
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update Category OK");
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a category: " + id);
        });
    };

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
                    refreshElements(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: 'Error: '+data
            });
            console.log("An Error occurred while trying to delete a category: " + id);
        });
    };



    /*
     *  PAGINACION
     */
    $scope.filteredCategories = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 1;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalCategories = function(){
        return $scope.categories.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.categories.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredCategories = $scope.categories.slice(begin, end);
    });



    /*
     *  EDITION UTILS
     */
    $scope.checkCreationName = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input a name...');
        } else {
            $http.get(restWebService + "categoryService/byName/" + data)
            .success(function (data, status, header, config) {
                data = data || {};
                if (status == 200) {
                    d.resolve();
                } else {
                    d.resolve();
                }
            }).error(function (data, status, header, config) {
                $scope.alerts = [];
                $scope.alerts.push({
                    type: 'danger',
                    msg: 'Error: ' + data
                });
                d.reject();
            });
        }
        return d.promise;
    };

    $scope.checkEditionName = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input a name...');
        } else {
            $http.get(restWebService + "categoryService/byName/" + data)
            .success(function (data, status, header, config) {
                data = data || {};
                if (status == 200) {
                    d.resolve();
                } else {
                    d.resolve();
                }
            }).error(function (data, status, header, config) {
                d.reject('Error: ' + data);
            });
        }
        return d.promise;
    };

    $scope.launchConfirmDeleteDialog = function (id) {
        var dlg = dialogs.confirm();
        dlg.result.then(function (btn) {
            $scope.removeCategory(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };



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
    $scope.resetNewCategoryName = function () {
        $scope.newCategory = {
            name: ''
        }
    };

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    
}