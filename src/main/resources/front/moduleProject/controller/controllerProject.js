angular.module('project').controller('controllerProject', function ($scope, $http) {
    $scope.liste = [];
    $http.get('http://192.168.1.17:8080/api/products/all').then(function (response) {
        console.log('RechercheMedia', response);
        for(var key in response.data) {
            var jeux = response.data[key];
            $scope.liste.push(jeux);
        }
    });/*
    var addJeux = function(Jeux){
        return $http.post('url : ajout dun jeux', Jeux);
    };

    $scope.ajout = function(Jeux){
        console.log(Jeux);
        addJeux(Jeux).then();
    }*/
});