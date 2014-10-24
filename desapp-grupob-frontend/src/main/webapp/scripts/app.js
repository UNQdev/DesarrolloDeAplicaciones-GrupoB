'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description # app
 * 
 * Main module of the application.
 */
var app = angular.module(
		'app',
		[ 'ngAnimate', 'ngCookies', 'ngResource', 'ngRoute', 'ngSanitize',
				'ngTouch', 'ngGrid' ]).config(function($routeProvider) {
	$routeProvider

	//CATEGORIES
	.when('/categories', {
		templateUrl : 'views/categories.html',
		controller : 'CategoryControllerList'
	}).when('/editCategory/:categoryId', {
		templateUrl : 'views/editCategory.html'
		// controller : 'CategoryControllerEdit'
	}).when('/deleteCategory/:categoryId', {
		controller : 'DeleteCategoryController'
	}).otherwise({
		redirectTo : '/'
	});
});

app.factory("alert", function($window, $q) {

	// Define promise-based alert() method.
	function alert(message) {

		var defer = $q.defer();

		$window.alert(message);

		defer.resolve();

		return (defer.promise);

	}

	return (alert);

});
