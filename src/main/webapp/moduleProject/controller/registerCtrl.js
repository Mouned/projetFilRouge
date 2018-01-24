angular.module('project').controller('registerCtrl',['$scope','$http','registerService', 'connectionService', function ($scope, $http,registerService, connectionService) {
	
	$scope.addUser = function(adherent){
		registerService.createUser(adherent).then(function(data){
			
		});
	}
	
	$scope.login = function(user){
		connectionService.connectUser(user.username,user.password).then(function(response) {
		});		
	}
	
	$scope.logout = function(){
		connectionService.deconnectUser().then(function(reponse){
			
		});
	}
}]);