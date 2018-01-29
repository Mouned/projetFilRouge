angular.module('project').controller('basketCtrl', ['$scope','$cookieStore', 'productsService','basketService', function($scope, $cookieStore, productsService,basketService){
	$scope.listGame = [];
	if($cookieStore.get('Basket')){
			productsService.getList($cookieStore.get('Basket').content).then(function(response){
				$scope.listGame = response;
		});
	}
	
	$scope.createOrder = function(){
		basketService.createOrder($cookieStore.get('Basket').id, $scope.listGame).then(function(response){
			$cookieStore.remove('Basket');
			$scope.listGame = [];
		});
	}
	
}]);