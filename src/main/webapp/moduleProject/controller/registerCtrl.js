angular.module('project').controller('registerCtrl',['$scope','$http','registerService','$location', 'connectionService','$cookieStore', function ($scope, $http, registerService,$location, connectionService,$cookieStore) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			if (connectionService.isAdmin()){
				$location.path('/admin');
				$cookieStore.put('Superuser',true);
			}
			else{
				$location.path('/home');
				$cookieStore.put('Superuser',false);
			}
			$cookieStore.put('User',response.id);
			$cookieStore.put('Login',response.login);
		});
	}
	
	$scope.login = function(user){
		connectionService.connectUser(user.username,user.password).then(function(response) {
			if (connectionService.isAdmin()){
				$location.path('/admin');
				$cookieStore.put('Superuser',true);
			}
			else{
				$location.path('/home');
				$cookieStore.put('Superuser',false);
			}
			$cookieStore.put('User',response.id);
			$cookieStore.put('Login',response.login);
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