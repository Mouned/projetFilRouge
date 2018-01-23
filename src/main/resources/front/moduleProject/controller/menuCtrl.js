/**
 * Controller for menu
 */
angular.module('project').controller('menuCtrl', ['menuService', function(menuService){
	$scope user = {};
	menuService.getUser().then(function(response) {
		$scope.user = response.data;
	});
}]);