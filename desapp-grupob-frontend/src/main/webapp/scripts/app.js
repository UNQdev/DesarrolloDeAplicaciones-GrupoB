'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description # app
 * 
 * Main module of the application.
 */
var feag = angular.module('feag', [ 'ngAnimate', 'ngCookies', 'ngResource', 'pascalprecht.translate', 
		'ngRoute', 'ngSanitize', 'ngTouch', 'ngGrid', 'xeditable', 'ui.bootstrap', 'ngTable' , 'dialogs.main','dialogs.default-translations' ]);

feag.config([ '$routeProvider', '$locationProvider',
		function($routeProvider, $locationProvider) {
			$routeProvider

        
			/** Operations **/
			.when('/operations', {
				templateUrl : 'views/operationsCRUD.html',
                controller : 'operationsCtrl'
            })
			/** Categories **/
			.when('/categories', {
				templateUrl : 'views/categoriesCRUD.html',
				controller : 'categoriesCtrl'
			})
			
			/** Subcategories **/
			.when('/subcategories/:categoryId', {
				templateUrl : 'views/subcategoriesCRUD.html',
				controller : 'subcategoriesCtrl'
			})
			
            .when('/paymentsCRUD', {
                templateUrl : 'views/paymentsCRUD.html',
                controller : 'paymentsCtrl'
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
			
            /** Home **/
			.otherwise({
				redirectTo : '/',
			    templateUrl : 'views/home.html',
				controller : 'homeCtrl'
			});

		} ]);

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
}])

feag.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
									// 'default'
});
