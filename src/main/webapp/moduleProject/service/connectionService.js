/**
 * Service for connection
 */
angular.module('project').service('connectionService', ['$http', function($http) {
	this.getUser = function(username,password) {
		return $http.post('/authenticate', {username : username, password:password})
			.then(function(response){
				console.log(response);
				return response.data;
			});
	};
}])