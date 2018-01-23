/**
 * Controller for menu
 */
angular.module('project').controller('menuCtrl', ['connectionService', '$scope', function(connectionService, $scope){
	
//	menuService.getUser().then(function(data){
//		$scope.user = data;
//	});
	
	
	$scope.isAuthenticate = function(){
		return connectionService.isAuth();
	}
	
	$scope.isAdmin = function(){
		return connectionService.isAdmin();
	}
	
	$scope.deconnect = function(){
		connectionService.deconnectUser().then(function(){});
	}
}]);