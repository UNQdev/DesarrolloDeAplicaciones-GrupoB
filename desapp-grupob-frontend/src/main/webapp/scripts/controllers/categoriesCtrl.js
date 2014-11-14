feag.controller('categoriesCtrl', function ($scope, $filter, $http, $location) {
    
	// Variables
	var restWebService = "http://localhost:8081/backend_api/rest/";
	
	$http.get(restWebService + "categoryService/categories")
        .success(function (response) {
            $scope.categories = response;
        }).error(function () {
            console.log("Error al listar categorias");
        }); 
    
    $scope.checkName = function (data, id) {
        if (id === 2 && data !== 'awesome') {
            return "Username 2 should be `awesome`";
        }
    };
    
    $scope.saveCategory = function (data, id) {
        	console.log(data.id + data.name);
        	console.log("add?"+$scope.addNewCategory);
        	if ($scope.addNewCategory) {
        		console.log("mkSave rules");
        		mkSave (data);
        		$scope.addNewCategory = false;
        	}
        	else {
        		console.log("mkUpdate rules");
        		mkUpdate (data, id);
        	}	
        };


    // remove user
    $scope.removeCategory = function (id) {
    	$http.delete(restWebService+'categoryService/'+ id).success(
				function(data, status, headers, config) {
					if (status == 204) {
						console.log("Deletion Completed.");
						console.log(data);
					}
				}).error(function(data, status, headers, config) {
					console.log("An Error occurred while trying to store a book");
		});
    };

    // add user
    $scope.addCategory = function () {
        $scope.inserted = {
            id: $scope.categories.length+1,
            name: '',
        };
        $scope.categories.push($scope.inserted);
        $scope.addNewCategory = true;
    };
    
    // functions
    function mkSave (data){
    	$http.post(restWebService + 'categoryService/save', data).success(
				function(data, status, headers, config) {
					if (status == 201) {
						console.log("Save Category OK");
					}
				}).error(function(data, status, headers, config) {
			console.log("An Error occurred while trying to store a category");
		});
    }
    
    function mkUpdate (data, id) {
    	angular.extend(data, {id:id});
    	var jsonCategory = angular.toJson(data);
    	$http.put(restWebService + 'categoryService/'+id, jsonCategory).success(
    			function(data, status, headers, config){
    				console.log("status del success de mkUpdate: "+status);
    				if(status == 200){
    					console.log("Update Category OK");
    				}
       	}).error(function(data, status, headers, config){
       		console.log("An Error occurred while trying to update a category: " + id);
    	});
    }
});

