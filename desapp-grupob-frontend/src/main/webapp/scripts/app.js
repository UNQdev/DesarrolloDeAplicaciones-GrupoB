'use strict';

/**
 * @ngdoc overview
 * @name feag
 * @description # feag
 * 
 * Main module of the application.
 */

var feag = angular.module('feag', [ 'ngAnimate', 'ngCookies', 
                                    'ngResource', 'pascalprecht.translate',
                                    'ngRoute', 'ngSanitize', 'ngTouch', 'ngGrid',
                                    'xeditable', 'ui.bootstrap', 'ngTable' , 
                                    'dialogs.main','dialogs.default-translations' ]);


feag.config([ '$routeProvider', function($routeProvider) { 
                	   $routeProvider

			/** Operations **/
			.when('/operations', {
				templateUrl : 'views/operationsCRUD.html',
                controller : 'operationsCtrl',
                /*resolve: {
                    resolvedOperations: function(resolverService) {
                        return resolverService.getOperations();
                    }
                }*/
            })
            
			/** Categories **/
			.when('/categories', {
				templateUrl : 'views/categoriesCRUD.html',
				controller : 'categoriesCtrl',
				resolve: {
                    resolvedCategories: ['resolverService', function(resolverService) {
                        return resolverService.getCategories();
                    }
                ]}
			})
			
			/** Subcategories **/
			.when('/subcategories/:categoryId', {
				templateUrl : 'views/subcategoriesCRUD.html',
				controller : 'subcategoriesCtrl',
                /*resolve: {
                    resolvedSubCategories: function(resolverService) {
                        return resolverService.getSubCategories();
                    }
                }*/
			})
			
            .when('/payments', {
                templateUrl : 'views/paymentsCRUD.html',
                controller : 'paymentsCtrl',
                /*resolve: {
                    resolvedPayments: function(resolverService) {
                        return resolverService.getPayments();
                    }
                }*/
            })

            
			// INVOICES
			.when('/invoices', {
				templateUrl : 'views/invoices.html',
				controller : 'InvoiceControllerList',
                /*resolve: {
                    resolvedInvoices: function(resolverService) {
                        return resolverService.getInvoices();
                    }
                }*/
            })
			
            /** Home **/
			.otherwise({
				redirectTo : '/',
			    templateUrl : 'views/home.html',
				controller : 'homeCtrl'
			});
}]);
                  
feag.factory('resolverService', ['$http', function($http) {
  var restWebService = "http://localhost:8081/backend_api/rest/";

  var result = {
      getCategories: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'categoryService/categories' })
          .success(function(data, status, headers, config) {
              console.log(data);
              return data;
          });
          return promise;
      }        
  }
  return result;
}]);
                      
feag.config(['dialogsProvider','$translateProvider',function(dialogsProvider,$translateProvider){
	dialogsProvider.useBackdrop('static');
	dialogsProvider.useEscClose(false);
	dialogsProvider.useCopy(false);
	dialogsProvider.setSize('sm');

	$translateProvider.translations('es',{
		DIALOGS_ERROR: "Error",
		DIALOGS_ERROR_MSG: "Se ha producido un error desconocido.",
		DIALOGS_CLOSE: "Cerca",
		DIALOGS_PLEASE_WAIT: "Espere por favor",
		DIALOGS_PLEASE_WAIT_ELIPS: "Espere por favor...",
		DIALOGS_PLEASE_WAIT_MSG: "Esperando en la operacion para completar.",
		DIALOGS_PERCENT_COMPLETE: "% Completado",
		DIALOGS_NOTIFICATION: "Notificacion",
		DIALOGS_NOTIFICATION_MSG: "Notificacion de aplicacion Desconocido.",
		DIALOGS_CONFIRMATION: "Confirmacion",
		DIALOGS_CONFIRMATION_MSG: "Se requiere confirmacion.",
		DIALOGS_OK: "Bueno",
		DIALOGS_YES: "Si",
		DIALOGS_NO: "No"
	});

	$translateProvider.preferredLanguage('en-US');
}]);

feag.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
								   // 'default'
});

feag.run(function($rootScope) {
    $rootScope.categories = function() {
        var promise = $http({ method: 'GET', url: restWebService + 'categoryService/categories' })
        .success(function(data, status, headers, config) {
            console.log(data);
            return data;
        });
        return promise;
    }
});
         