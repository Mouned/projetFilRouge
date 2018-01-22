/**
 * Controleur pour la page home
 */
angular.module('project').controller('homeCtrl', ['$scope', 'gameService', function($scope, gameService) {
	console.log('controller for home');
	gameService.getAll().then(function(data){
		$scope.list = data;
	});
}]);