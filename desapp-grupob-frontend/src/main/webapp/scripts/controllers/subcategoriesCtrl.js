function subcategoriesCtrl($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, resolvedSubCategories) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";
    
    console.log(resolvedSubCategories);
    $scope.subcategories = resolvedSubCategories.data;
    console.log($scope.subcategories);
    
    // SubCategories Father's Name
    function loadSourceCategory() {
        $http.get(restWebService + 'categoryService/byId/' + $routeParams.categoryId)
        .success(function (response) {
            $scope.categoryName = response.name;
        }).error(function () {
            console.log("Error buscar nombre de categoria de origen");
        });
    };
    loadSourceCategory();



    /*
     *  SUBCATEGORIES CRUD
     */   
    function refreshElements() {
        var d = $q.defer();
        $http.get(restWebService + "subcategoryService/subcategories")
        .success(function (response) {
            $scope.subcategories = response;
            d.resolve();
        }).error(function () {
            console.log("Error al listar subcategorias");
            d.reject();
        });
        return d.promise;
    }
    
    $scope.saveSubCategory = function (data) {
        $http.post(restWebService + 'subcategoryService/save/' + $routeParams.categoryId, data).success(
            function (data, status, headers, config) {
                if (status == 201) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Saved!'
                    });
                    console.log("Save SubCategory OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a SubCategory, with name: " + data.name);
        });
    };

    $scope.updateSubCategory = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'subcategoryService/' + id, jsonCategory).success(
            function (data, status, headers, config) {
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update SubCategory OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a SubCategory: " + id);
        });
    };

    $scope.removeSubCategory = function (id) {
        $http.delete(restWebService + 'subcategoryService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete SubCategory OK");
                    getAll(); //refresh table with new state
                }
            }).error(function (data, status, headers, config) {
            $scope.alerts = [];
            $scope.alerts.push({
                type: 'danger',
                msg: 'Error: '+ data
            });
            console.log("An Error occurred while trying to delete a SubCategory: " + id);
        });
    };



    /*
     *  EDITION UTILS
     */
    $scope.checkCreationName = function (data) {
        var d = $q.defer();
        if (data == undefined) {
            d.reject('Input a name...');
        } else {
            $http.get(restWebService + "subcategoryService/byName/" + data)
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
            d.reject('Input a valid name...');
        } else {
            $http.get(restWebService + "subcategoryService/byName/" + data)
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
            $scope.removeSubCategory(id);
        }, function (btn) {
            console.log("Delete cancelled");
        });
    };



    /*
     *  PAGINACION
     */
    $scope.filteredSubCategories = [];
    $scope.currentPage = 1;
    $scope.itemsOnPage = 5;
    $scope.setItemsOnPage = function(value){
        $scope.itemsOnPage = value;
    }
    $scope.totalSubCategories = function(){
        return $scope.subcategories.length;
    }
    $scope.pageQtty = function () {
        return Math.ceil($scope.subcategories.length / $scope.itemsOnPage);
    };
    $scope.$watch('currentPage + itemsOnPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsOnPage);
        var end = begin + $scope.itemsOnPage; 
        $scope.filteredSubCategories = $scope.subcategories.slice(begin, end);
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
    $scope.resetNewSubCategoryName = function () {
        $scope.newSubCategory = {
            name: ''
        }
    };
    
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    
}