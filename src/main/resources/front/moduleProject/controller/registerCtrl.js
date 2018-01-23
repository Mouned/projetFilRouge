angular.module('project').controller('registerCtrl',['$scope','$http','registerService', 'menuService', function ($scope, $http,registerService, menuService) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			
		});
	}
	
	$scope.login = function(user){
		console.log(user);
		menuService.getUser(user.username,user.password).then(function(response) {
			$scope.user = response.data;
		});
	}
}]);