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
                                    'xeditable', 'ui.bootstrap', 'ngTable',
                                    'dialogs.main','dialogs.default-translations',
                                    'ng-currency', 'ngCsv', 'tc.chartjs' ]);


feag.config([ '$routeProvider', function($routeProvider) { $routeProvider

			.when('/operations', {
				templateUrl : 'views/operationsCRUD.html',
                controller : 'operationsCtrl',
                resolve: {
                    resolvedOperations: ['resolverService', function(resolverService) {
                        return resolverService.getOperations();
                    }
                ]}
            })
            
            .when('/payments', {
                templateUrl : 'views/paymentsCRUD.html',
                controller : 'paymentsCtrl',
                resolve : {
                    resolvedPayments : ['resolverService', function(resolverService) {
                        return resolverService.getPayments();
                    }
               ]}
            })
            
            .when('/analysis', {
                templateUrl : 'views/analysis.html',
                controller : 'analysisCtrl',
                resolve : {
                    resolvedCategoriesTotals : ['resolverService', function(resolverService) {
                        return resolverService.getCategoriesTotals();
                    }]
                    /*,
                    resolvedSubCategoriesTotals : ['resolverService', function(resolverService) {
                    return resolverService.getSubCategoriesTotals();
                    }],
                    resolvedMonthAccrual : ['resolverService', function(resolverService) {
                        return resolverService.getMonthAccrual();
                    }],
                    resolvedVendorsTotals : ['resolverService', function(resolverService) {
                        return resolverService.getVendorsTotals();
                    }]*/
                }
            })

			.when('/categories', {
				templateUrl : 'views/categoriesCRUD.html',
				controller : 'categoriesCtrl',
				resolve : {
                    resolvedCategories : ['resolverService', function(resolverService) {
                        return resolverService.getCategories();
                    }
                ]}
			})
			
            .when('/subcategories/:categoryId', {
				templateUrl : 'views/subcategoriesCRUD.html',
				controller : 'subcategoriesCtrl',
                resolve : {
                    resolvedSubCategories : ['resolverService', '$route', function(resolverService, $route) {
                        console.log($route);
                        var id = $route.current.params.categoryId;
                        return resolverService.getSubCategories(id);
                    }
                ]}
			})
			

			.when('/invoices', {
				templateUrl : 'views/invoicesCRUD.html',
				controller : 'invoicesCtrl',
                resolve : {
                    resolvedInvoices : ['resolverService', function(resolverService) {
                        return resolverService.getInvoices();
                    }
                ]}
            })

            .when('/vendors', {
                templateUrl : 'views/vendorsCRUD.html',
                controller : 'vendorsCtrl',
                resolve : {
                    resolvedVendors : ['resolverService', function(resolverService) {
                        return resolverService.getVendors();
                    }
                ]}
            })
			
            /** Home **/
			.otherwise({
				redirectTo : '/',
			    templateUrl : 'views/home.html',
				controller : 'homeCtrl'
			});
}]);
                  
feag.service('resolverService', ['$http', function($http) {
  var restWebService = "http://localhost:8081/backend_api/rest/";

  var result = {
      getOperations: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'operationService/operations' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getCategories: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'categoryService/categories' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getSubCategories: function(id) {
          var promise = $http({ method: 'GET', url: restWebService + 'subcategoryService/byCategoryId/' + id })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getPayments: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'paymentService/payments' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getVendors: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'vendorService/vendors' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getInvoices: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'invoiceService/invoices' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getCategoriesTotals: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'analysisService/categoriesTotals' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getSubCategoriesTotals: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'analysisService/subCategoriesTotals' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getMonthAccrual: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'analysisService/monthAccrual' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
      getVendorsTotals: function() {
          var promise = $http({ method: 'GET', url: restWebService + 'analysisService/vendorsTotals' })
          .success(function(data, status, headers, config) {
              return data;
          });
          return promise;
      },
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
