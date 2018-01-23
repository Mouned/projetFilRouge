/**
 * Service for the menu
 */
angular.module('project').service('menuservice', ['connectionService', function(connectionService) {
	this.getUser = function() {
		return connectionService.getUser();
	};
}]);