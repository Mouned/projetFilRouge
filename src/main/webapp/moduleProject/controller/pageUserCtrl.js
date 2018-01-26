/**
 * Controller for user profil
 */
angular.module('project').controller('pageUserCtrl', ['$scope', '$routeParams', 'pageUserService', 'connectionService', function($scope, $routeParams, pageUserService, connectionService) {
	$scope.user = connectionService.getUser();
	$scope.edit = function(user) {
		pageUserService.editUser(user).then(function(){
			console.log('profil modifié !');
		});
	}
}]);