angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/research', {
            templateUrl: './templates/research.html',
            controller: 'controllerProject'
        });
    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);