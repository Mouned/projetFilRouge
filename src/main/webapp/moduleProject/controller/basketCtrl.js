angular.module('project').controller('basketCtrl', ['connectionService', '$scope', 'searchService', '$location', '$cookies', 'productsService', function(connectionService, $scope, searchService, $location, $cookies, productsService){
	$scope.listGame = [];
	if($cookies.get('panier')){
		var monPanier = $cookies.get('panier').substring(1).split(',');

			productsService.getList(monPanier).then(function(response){
				$scope.listGame = response;
		});
	}
	
}]);