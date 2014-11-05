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
	
//	//ACCOUNTS
//	.when('/accounts', {
//		templateUrl : 'views/accounts.html',
//		controller : 'AccountControllerList'
//	}).when('/newAccount', {
//		templateUrl : 'views/account.html',
//		controller : 'AccountControllerNew'
//	})
//	EN PRINCIPIO ESTO NO TIENE SENTIDO
//	.when('/editAccount/:accountId', {			
//		templateUrl : 'views/account.html',
//		controller : 'AccountControllerEdit'
//	}).when('/deleteAccount/:accountId', {
//		controller : 'AccountControllerDelete'
//	})	
	
	// OPERATIONS
	.when('/operations', {
		templateUrl : 'views/operations.html',
		controller : 'OperationControllerList'
	}).when('/newOperation', {
		templateUrl : 'views/operation.html',
		controller : 'OperationControllerNew'
	}).when('/editOperation/:operationId', {
		templateUrl : 'views/operation.html',
		controller : 'CategoryControllerEdit'
	}).when('/deleteOperation/:operationId', {
		controller : 'CategoryControllerDelete'
	})
	// CATEGORIES
	.when('/categories', {
		templateUrl : 'views/categories.html',
		controller : 'CategoryControllerList'
	}).when('/newCategory', {
		templateUrl : 'views/category.html',
		controller : 'CategoryControllerNew'
	}).when('/editCategory/:categoryId', {
		templateUrl : 'views/category.html',
		controller : 'CategoryControllerEdit'
	}).when('/deleteCategory/:categoryId', {
		controller : 'CategoryControlleDelete'
	})
	// INVOICES
	.when('/invoices', {
		templateUrl : 'views/invoices.html',
		controller : 'InvoiceControllerList'
	}).when('/newInvoice', {
		templateUrl : 'views/invoices.html',
		controller : 'InvoiceControllerNew'
	}).when('/editInvoice/:invoiceId', {
		templateUrl : 'views/invoices.html',
		controller : 'InvoiceControllerEdit'
	}).when('/deleteInvoice/:invoiceId', {
		controller : 'InvoiceControllerDelete'
	})
	// OTHERWISE
	.otherwise({
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
