/**
 * Service for connection
 */
angular.module('project').service('connectionService', ['$http', function($http) {
	this.getUser = function(login, password) {
		return $http.get('http://192.168.1.17:8080/authenticate?username='+login+'password='+password)
			.then(function(response){
				console.log(response);
				return response.data;
			});
	}
}])