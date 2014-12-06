feag.controller('operationNewCtrl', function ($scope, $filter, $http, $location, $route, $q, $log, $rootScope, $routeParams, $timeout, $translate, dialogs, $modal) {

    $scope.clear = function () {
        $scope.dt = null;
    };


    $scope.today = function () {
        $scope.dt = new Date();
        console.log($scope.dt);
    };

    $scope.today();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };
    
});