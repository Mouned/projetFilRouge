angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
    	.when('/home', {
    		templateUrl: "./html/home.html"
    	})
        .when('/research', {
            templateUrl: './templates/research.html',
            controller: 'controllerProject'
        });
    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);