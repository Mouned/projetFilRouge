/**
 * Controleur pour la page home
 */
angular.module('project').controller('homeCtrl', ['$scope', 'searchService', function($scope, searchService) {
	console.log('controller for home');
	/**
	 * Showing the games on home page
	 */
	searchService.getAll().then(function(data){
		$scope.list = data;
	});
	
}]);