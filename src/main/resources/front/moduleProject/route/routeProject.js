angular.module('project').config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/res', {
            templateUrl: './html/research.html',
            controller: 'controllerProject'
        });
    $routeProvider.otherwise({
        redirectTo : '/'
    });
} ]);