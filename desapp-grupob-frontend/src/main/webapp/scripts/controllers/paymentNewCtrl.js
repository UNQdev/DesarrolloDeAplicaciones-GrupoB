function paymentNewCtrl ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal, 
                            $modalInstance) {

    // Variables
    var restWebService = "http://localhost:8081/backend_api/rest/";

    $scope.alerts = [];

    $scope.payment = {};

    function setShift () {
        var date = new Date();
        if (date.getHours > 12) {
            $scope.payment.shift = 'Beforenoon';
        }
        else {
            $scope.payment.shift = 'Afternoon';
        }
    };

    setShift ();
    
    $scope.taxes = [
        {name:'10.5', value: 0.105},
        {name:'21', value: 0.21},
        {name:'27', value: 0.27}
    ];
    
    $scope.selectedTax = [ {name:'10.5', value: 0.105} ];
    
    $scope.facturas = [ 'A', 'B' , 'C' , 'X'];
    
    $scope.payment.invoiceType = 'C';
    
    $scope.turnos = [ 'Afternoon', 'Beforenoon'];

    $scope.cuentas = [ 'Cash', 'Bank', 'Current'];

    $scope.payment.accountType = 'Cash' ;

    $scope.tarjetas = [ 'Debit', 'Credit'];

    $scope.payment.type = 'Outcoming';
    
    $scope.clear = function () {
        $scope.date = null;
    };

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    $scope.today = function () {
        $scope.date = new Date();
    };

    $scope.today();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };

    $scope.save = function() {
        if ($scope.payment.amount != NaN) {
            angular.extend($scope.payment, {
                date: $scope.date, category: 1, tax : $scope.selectedTax.value
            });
            var jsonPayment = angular.toJson($scope.payment);
        console.log(jsonPayment);
           $http.post(restWebService +'paymentService/save', jsonPayment).success(
                function(data, status, headers, config) {
                    if (status == 200) {
                        $modalInstance.close();
                        console.log ("Payment Saved!");
                    }
                }).error(function(data, status, headers, config) {
                console.log("Error al crear payment");
                $scope.alerts.push({
                    type: 'danger',
                    msg: data
                });
            });
        }
        else {
            $scope.alerts.push({
                type: 'danger',
                msg: 'Ingrese un monto valido'
            });
        }
    };

    $scope.closeModal = function () {
        $modalInstance.dismiss();
    };

    function loadSubcategories () {
        $http.get(restWebService + "subcategoryService/byCategoryId/" + 1)
        .success(function(response) {   
            $scope.subcategories = response;
        })
        .error(function() {
        });
    };
    
    loadSubcategories();
    
    
    function loadVendor () {
        $http.get(restWebService + "vendorService/vendors")
        .success(function(response) {   
            $scope.vendors = response;
        })
        .error(function() {
        });
    };
    
    loadVendor () ;
    
    $scope.loadCuit = function (vendorId){
        $http.get(restWebService + "vendorService/byId/" + vendorId)
        .success(function(response) {   
            $scope.payment.vendorTaxCode = response.taxCode;
        })
        .error(function() {
        });
    }

    $scope.selectedType = function (accountType) {
        if (accountType == "Bank") {
            $scope.showBankOptions = true;
        }
        else {
            $scope.showBankOptions = false;
        }
    };
    
    $scope.selectedInvoice = function (invoiceType) {
        if (invoiceType == "A") {
            $scope.showTaxOptions = true;
            $scope.payment.hasIIBB = true;
        }
        else {
            $scope.showTaxOptions = false;
            $scope.payment.hasIIBB = false;
        }
    };  


};
