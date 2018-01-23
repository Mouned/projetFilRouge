/**
 * Controleur pour la page home
 */
angular.module('project').controller('homeCtrl', ['$scope', 'gameService', function($scope, gameService) {
	console.log('controller for home');
	/**
	 * Showing the games on home page
	 */
	gameService.getAll().then(function(data){
		$scope.list = data;
	});
	
	/**
	 * Getting the right menu for visitor, user or admin
	 */
	
}]);