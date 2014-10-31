function OperationControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend_api/rest/operationService/operations")
			.success(function(response) {
				$scope.operations = response;
			}).error(function() {
				console.log("error");
			});
}
function OperationControllerNew($scope, $http) {
	$http.get("http://localhost:8081/backend_api/rest/operationService/newOperation")
			.success(function(response) {
				$scope.operations = response;
			}).error(function() {
				console.log("error");
			});
}