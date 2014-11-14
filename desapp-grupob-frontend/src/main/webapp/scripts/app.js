'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description # app
 * 
 * Main module of the application.
 */
var feag = angular.module('feag', [ 'ngAnimate', 'ngCookies', 'ngResource',
		'ngRoute', 'ngSanitize', 'ngTouch', 'ngGrid', 'xeditable' ]);

feag.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider

			// //ACCOUNTS
			// .when('/accounts', {
			// templateUrl : 'views/accounts.html',
			// controller : 'AccountControllerList'
			// }).when('/newAccount', {
			// templateUrl : 'views/account.html',
			// controller : 'AccountControllerNew'
			// })
			// EN PRINCIPIO ESTO NO TIENE SENTIDO
			// .when('/editAccount/:accountId', {
			// templateUrl : 'views/account.html',
			// controller : 'AccountControllerEdit'
			// }).when('/deleteAccount/:accountId', {
			// controller : 'AccountControllerDelete'
			// })

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

			// Categories ABM

			.when('/categoriesCRUD', {
				templateUrl : 'views/categoriesCRUD.html',
				controller : 'categoriesCtrl'
			})
			// CATEGORIES 
			.when('/categories', {
				templateUrl : 'views/categories.html',
				controller : 'categoryControllerList'
			})

			.when('/newCategory', {
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

		} ]);

feag.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
});

feag.factory("alert", function($window, $q) {

	// Define promise-based alert() method.
	function alert(message) {

		var defer = $q.defer();

		$window.alert(message);

		defer.resolve();

		return (defer.promise);

	}

	return (alert);

});
