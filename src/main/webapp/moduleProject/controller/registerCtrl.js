angular.module('project').controller('registerCtrl',['$scope','$http','registerService','$location', 'connectionService', function ($scope, $http, registerService,$location, connectionService) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			
		});
	}
	
	$scope.login = function(user){
		connectionService.connectUser(user.username,user.password).then(function(response) {
			if (connectionService.isAdmin())
				$location.path('/admin');
			else
				$location.path('/home');
		});		
	}
	
	$scope.logout = function(){
		connectionService.deconnectUser().then(function(reponse){
			$location.path('/home');
		});
	}
}]);