angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
    	.when('/home', {
    		templateUrl: './html/home.html',
    		controller: 'homeCtrl'
    	})
        .when('/research', {
            templateUrl: './templates/research.html',
            controller: 'searchCtrl'
        })
        .when('/sign', {
            templateUrl: './html/LogSignIn.html',
            controller: 'registerCtrl'
        })
    	.when('/admin', {
    		templateUrl: './html/pageAdmin.html',
    		controller: 'pageAdminCtrl'
    	})
        .when('/test',{
            templateUrl : './templates/test.html',
            controller : 'searchCtrl'
        })
        .when('/details/:id',{
            templateUrl : './templates/gamedetails.html',
            controller : 'searchCtrl'
        })
    ;

    $routeProvider.otherwise({
        redirectTo : '/home'
    });
} ]);