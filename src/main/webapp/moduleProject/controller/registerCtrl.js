angular.module('project').controller('registerCtrl',['$scope','$http','registerService','$location', 'connectionService','$cookieStore', function ($scope, $http, registerService,$location, connectionService,$cookieStore) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			if(data == 'error'){
				$scope.errorRegister = true;
			}else{
				if (connectionService.isAdmin()){
					
					$location.path('/admin');
				}
				else{
					$location.path('/home');
				}
			}
		});
	}
	
	$scope.connectError = function(){
		return $scope.errorConnect;
	}
	
	$scope.registerError = function(){
		return $scope.errorRegister;
	}
	
	$scope.login = function(user){
		connectionService.connectUser(user.username,user.password).then(function(response) {
			if(response == 'error'){
				$scope.errorConnect = true;
			}else{
				if (connectionService.isAdmin()){
					$location.path('/admin');
				}
				else{
					$location.path('/home');
				}
			}
//			var basket = $cookieStore.get('Basket');
//			if(basket != undefined){
//				if(basket.id != undefined && basket.id !== response.id){
//					$cookieStore.remove('Basket');
//				}
//			}
		});		
	}
	
	$scope.logout = function(){
		connectionService.deconnectUser().then(function(reponse){
			$location.path('/home');
		});
	}
}]);