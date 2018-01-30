/**
 * Service for user profile
 */
angular.module('project').service('pageUserService', ['$http', function($http) {
	
	this.editUser = function(user, pass) {
		user.password = pass;
		console.log(user);
		return $http.post('/api/users/update', user).then(function(response) {
			return response.data;
		})
	}
	
	this.getAll = function(){
		return $http.get('/api/users/all').then(function(response){
			return response.data;
		});
	}
	
	this.deleteUser = function(id){
		return $http.post('/api/users/delete/'+id).then(function(){
			return pageUserService.getAll().then(function(response){
				return response.data;
			});
		})
	}
}])