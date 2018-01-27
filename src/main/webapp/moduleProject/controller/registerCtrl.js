angular.module('project').controller('registerCtrl',['$scope','$http','registerService','$location', 'connectionService','$cookieStore', function ($scope, $http, registerService,$location, connectionService,$cookieStore) {
	
	var config = {
			secure : true
	};
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			if (connectionService.isAdmin()){
				$location.path('/admin');
				$cookieStore.put('Superuser',true,config);
			}
			else{
				$location.path('/home');
				$cookieStore.put('Superuser',false,config);
			}
			$cookieStore.put('User',response.id,config);
			$cookieStore.put('Login',response.login,config);
		});
	}
	
	$scope.login = function(user){
		connectionService.connectUser(user.username,user.password).then(function(response) {
			if (connectionService.isAdmin()){
				$location.path('/admin');
				$cookieStore.put('Superuser',true,config);
			}
			else{
				$location.path('/home');
				$cookieStore.put('Superuser',false,config);
			}
			$cookieStore.put('User',response.id,config);
			$cookieStore.put('Login',response.login,config);
			
			var basket = $cookieStore.get('Basket');
			if(basket != undefined){
				if(basket.id !== response.id){
					$cookieStore.remove('Basket');
				}
			}
		});		
	}
	
	$scope.logout = function(){
		connectionService.deconnectUser().then(function(reponse){
			$cookieStore.remove('User');
			$cookieStore.remove('Superuser');
			$location.path('/home');
		});
	}
}]);