angular.module('project').controller('registerCtrl',['$scope','$http','registerService', function ($scope, $http,registerService) {
	
	$scope.addUser = function(adherant){
		registerService.createUser(adherant).then(function(data){
			
		});
	}
}]);