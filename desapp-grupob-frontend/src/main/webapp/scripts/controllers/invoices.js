function InvoiceControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend_api/rest/invoiceService/invoices")
			.success(function(response) {
				$scope.invoices = response;
			}).error(function() {
				console.log("error");
			});
}


