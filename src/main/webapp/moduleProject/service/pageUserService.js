/**
 * Service for user profile
 */
angular.module('project').service('pageUserService', ['$http', function($http) {
	
	this.editUser = function(user) {
		return $http.post('/api/users/update', user).then(function(response) {
			return response.data;
		})
	}
}])