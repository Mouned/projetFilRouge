angular.module('project').service('productsService','searchService', function ($scope, $http,$searchService) {

    var addGame = function (jeu) {
        return $http.post('/api/products/create', jeu).then(function(response){
            return searchService.getAll().then(function(data){
                return data;
            })
        });
    };

    $scope.ajout = function (jeu) {
        console.log(jeu);
        addGame(jeu).then();
    };
});