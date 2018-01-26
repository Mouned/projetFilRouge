/**
 * Controller for user profil
 */
angular.module('project').controller('pageUserCtrl', ['$scope', '$routeParams', 'pageUserService', 'connectionService', function($scope, $routeParams, pageUserService, connectionService) {
	$scope.user = connectionService.getUser();
	$scope.edit = function(user, pass) {
		pageUserService.editUser(user, pass).then(function(){
			console.log('profil modifié !');
		});
	}
}]);