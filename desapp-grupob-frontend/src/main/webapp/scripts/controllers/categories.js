function CategoryControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend_api/rest/categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			}).error(function() {
				console.log("error");
			});
}
function CategoryControllerNew($scope, $http, $location, alert){
	$http.get("http://localhost:8081/backend_api/rest/operationService/operations")
	.success(function(response) {
		$scope.operations = response;
	})
	.error(function() {
		console.log("error");
	});
	$scope.title = 'New Category';
	$scope.submit = function(form){
		$http.get("http://localhost:8081/backend_api/rest/categoryService/save/"+$scope.categoryName+"/"+$scope.operationId)
			.success(function(response){
				/*
				 * Analizar si queremos este mensaje
				 */
				alert("Category <" + response.name + "> has been succesfully created")
				.then(function(){
						/* 
						 * Aca redirecciona a la pagina de categorias?
						 * Queremos eso o queremos quedarnos en /operations?
						 */
						$location.path('/categories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}