angular.module('project').controller('basketCtrl', ['$scope','$cookieStore', 'productsService','basketService','connectionService', function($scope, $cookieStore, productsService,basketService,connectionService){

	$scope.price = 0;	
	$scope.listGame = [];
	
	if($cookieStore.get('Basket')){
			productsService.getList($cookieStore.get('Basket').content).then(function(response){
				console.log('RESPONSE', response.length);
				for(var i=0; i<response.length; i++){
					console.log(response[i].price);
					$scope.price += response[i].price;
				}
				
				$scope.listGame = response;
		});
	}
	
	$scope.createOrder = function(){
		basketService.createOrder(connectionService.getIdUser(), $scope.listGame).then(function(response){
			$cookieStore.remove('Basket');
			$scope.listGame = [];
		});
	}
	
	$scope.removeBasket = function(){
		$cookieStore.remove('Basket');
		$scope.listGame = [];
		$scope.price=0;
	}
	
	$scope.isAuth = function(){
		return connectionService.isAuth();
	}
	
}]);