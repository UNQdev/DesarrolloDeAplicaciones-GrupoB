feag.controller('categoriesCtrl', function ($scope, $filter, $http, $location) {
    
	// Variables
	var restWebService = "http://localhost:8081/backend_api/rest/";
	
	$scope.closeAlert = function(index) {
		    $scope.alerts.splice(index, 1);
		  };
		  
	$http.get(restWebService + "categoryService/categories")
        .success(function (response) {
            $scope.categories = response;
        }).error(function () {
            console.log("Error al listar categorias");
        }); 
	
    $scope.checkName = function (name) {
    	$http.get(restWebService + "categoryService/byName/"+name)
        .success(function(data, status, headers, config) {
        	console.log("check name, data: "+data);
        	console.log("check name, status: "+status);
            if (status == 201) {
            	return [500,'error'];
            }
            else {
            	return [200,'ok'];
            }
        }); 
    };
    
    // add category
    $scope.saveCategory = function (data) {
    	$http.post(restWebService + 'categoryService/save', data).success(
				function(data, status, headers, config) {
					if (status == 201) {
						$scope.alerts = [];
		            	$scope.alerts.push({type: 'success' , msg: 'Saved!'});
		            	console.log("Save Category OK");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a category");
		});
    };

    // update category
    $scope.updateCategory = function (data, id) {
    	angular.extend(data, {id:id});
    	var jsonCategory = angular.toJson(data);
    	$http.put(restWebService + 'categoryService/'+id, jsonCategory).success(
    			function(data, status, headers, config){
    				console.log("status del success de mkUpdate: "+status);
    				if(status == 200){
    					$scope.alerts = [];
		            	$scope.alerts.push({type: 'success' , msg: 'Updated!'});
    					console.log("Update Category OK");
    				}
       	}).error(function(data, status, headers, config){
       		console.log("An Error occurred while trying to update a category: " + id);
    	});
    }

    // delete category
    $scope.removeCategory = function (id) {
    	$http.delete(restWebService+'categoryService/'+ id).success(
				function(data, status, headers, config) {
					if (status == 204) {
						$scope.alerts = [];
		            	$scope.alerts.push({type: 'danger' , msg: 'Deleted!'});
						console.log("Delete Category OK");
					}
				}).error(function(data, status, headers, config) {
					console.log("An Error occurred while trying to store a book");
		});
    };
    	
});

