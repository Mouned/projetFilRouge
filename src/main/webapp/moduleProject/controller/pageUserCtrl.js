/**
 * Controller for user profil
 */
angular.module('project').controller('pageUserCtrl', ['$scope', '$routeParams', 'pageUserService', 'connectionService', 'searchUserService', '$location', function($scope, $routeParams, pageUserService, connectionService,searchUserService,$location) {
	if(!connectionService.isAuth()) $location.path('/sign');
	$scope.user = {};
	connectionService.getUserDetails().then(function(userDetails){
			
		searchUserService.getUserById(connectionService.getIdUser()).then(function(response){
				$scope.user = response;
				$scope.user.birthDate = new Date(response.birthDate);
		});
		
	});
	$scope.edit = function(user, pass) {
		pageUserService.editUser(user, pass).then(function(){
			console.log('profil modifié !');
			$location.path('/user');
		});
	}
}]);