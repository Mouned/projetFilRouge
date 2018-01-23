angular.module('project').controller('registerCtrl',['$scope','$http','registerService', 'connectionService', function ($scope, $http,registerService, connectionService) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			
		});
	}
	
	$scope.login = function(user){
		console.log(user.username + ' ; '+ user.password);
		connectionService.getUser(user.username,user.password).then(function(response) {
			$scope.user = response.data;
		});
	}
}]);