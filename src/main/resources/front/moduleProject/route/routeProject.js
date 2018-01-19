angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/research', {
            templateUrl: './html/research.html',
            controller: 'controllerProject'
        });
    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);