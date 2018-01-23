/**
 * Service for the menu
 */
angular.module('project').service('menuService', ['connectionService', function(connectionService) {
	this.getUser = function() {
		return connectionService.getUser();
	};
}]);