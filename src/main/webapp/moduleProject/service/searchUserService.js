/**
 * Search Service for users
 */
angular.module('project').service('searchUserService', ['$http', function($http) {
	this.getUserById = function(idUser){
		return $http.get('/api/users/search',{params : {id : idUser}}).then(function(response){
			return response.data;
		});
	}
	
	this.getUserByLogin = function(loginUser){
		return $http.get('/api/users/get',{params : {login : loginUser}}).then(function(response){
			return response.data;
		});
	}
}]);