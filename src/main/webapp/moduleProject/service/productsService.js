angular.module('project').service('productsService', ['$http', 'searchService', function ($http,$searchService) {

    var addGame = function (jeu) {
        return $http.post('/api/products/create', jeu).then(function(response){
            return searchService.getAll().then(function(data){
                return data;
            })
        });
    };

}]);