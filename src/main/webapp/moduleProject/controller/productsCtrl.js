angular.module('project').controller('productsCtrl', function($scope,$http,$routeParams) {

    var id = $routeParams.id;

    $http.get('/api/products/all/' + id + '/')
        .then(function (response) {
            $scope.jeux = response.data;
            console.log($scope.jeux);
        });
});