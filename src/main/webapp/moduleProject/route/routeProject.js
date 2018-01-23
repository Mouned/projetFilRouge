angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
    	.when('/home', {
    		templateUrl: './html/home.html',
    		controller: 'homeCtrl'
    	})
        .when('/research', {
            templateUrl: './templates/research.html',
            controller: 'controllerProject'
        })
        .when('/sign', {
            templateUrl: './html/LogSignIn.html',
            controller: 'registerCtrl'
        });
    

    $routeProvider.otherwise({
        redirectTo : '/home'
    });
} ]);