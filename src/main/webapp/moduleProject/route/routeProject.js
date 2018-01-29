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
        .when('/user/:id', {
            templateUrl: './html/pageUser.html',
            controller: 'pageUserCtrl'
        })
        .when('/details/:id', {
            templateUrl: './templates/gamedetails.html',
            controller: 'searchCtrl'
        })
        .when('/contact', {
            templateUrl: './templates/contact.html',
            contoller: 'searchCtrl'
        })
        .when('/basket',{
            templateUrl : './html/basket.html',
            controller: 'basketCtrl'
        })
        .when('/orders', {
            templateUrl: './html/orderList.html',
            controller: 'orderCtrl'
        })
        .when('/order', {
            templateUrl: './html/orderUser.html',
            controller: 'orderCtrl'
        })
        .when('/user', {
            templateUrl: './html/user.html',
            controller: 'userCtrl'
        })
        .when('/about', {
            templateUrl: './html/about.html'
        });

    $routeProvider.otherwise({
        redirectTo: '/home'
    });
}]);