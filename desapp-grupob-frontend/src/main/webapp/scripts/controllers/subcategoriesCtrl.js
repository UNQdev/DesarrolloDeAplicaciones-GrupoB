feag.controller('subcategoriesCtrl', function ($scope, $filter, $http, $location, $route, $q, $routeParams) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
        $route.reload();
    };

    // Get all subcategories	  
    $http.get(restWebService + 'subcategoryService/byCategoryId/' + $routeParams.categoryId)
        .success(function (response) {
            $scope.subcategories = response;
        }).error(function () {
            console.log("Error al listar subcategorias");
        });

    // By perform remote validation, check if the input name already exists in db.
    // For that define validation method returning $q promise. 
    // If promise resolves to string validation failed.
    $scope.checkName = function (data) {
        var d = $q.defer();
        $http.get(restWebService + "subcategoryService/byName/" + data)
            .success(function (res) {
                res = res || {};
                if (res.status === 'ok') { // {status: "ok"}
                    d.resolve()
                } else { // {status: "error", msg: "Message from server..."}
                    d.resolve(res.msg)
                }
            }).error(function (e) {
                d.reject('Error: already exists');
            });
        return d.promise;
    };

    // add category
    $scope.saveSubCategory = function (data) {
        $http.post(restWebService + 'subcategoryService/save/' + $routeParams.categoryId, data).success(
            function (data, status, headers, config) {
                if (status == 201) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Saved!'
                    });
                    console.log("Save Category OK");
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to store a category, with name: " + data.name);
        });
    };

    // update category
    $scope.updateSubCategory = function (data, id) {
        angular.extend(data, {
            id: id
        });
        var jsonCategory = angular.toJson(data);
        $http.put(restWebService + 'subcategoryService/' + id, jsonCategory).success(
            function (data, status, headers, config) {
                console.log("status del success de mkUpdate: " + status);
                if (status == 200) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'success',
                        msg: 'Updated!'
                    });
                    console.log("Update Category OK");
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to update a category: " + id);
        });
    };

    // delete category
    $scope.removeSubCategory = function (id) {
        $http.delete(restWebService + 'subcategoryService/' + id).success(
            function (data, status, headers, config) {
                if (status == 204) {
                    $scope.alerts = [];
                    $scope.alerts.push({
                        type: 'danger',
                        msg: 'Deleted!'
                    });
                    console.log("Delete Category OK");
                }
            }).error(function (data, status, headers, config) {
            console.log("An Error occurred while trying to delete a category: " + id);
        });
    };
});