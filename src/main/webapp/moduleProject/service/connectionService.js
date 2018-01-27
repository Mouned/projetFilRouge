/**
 * Service for connection
 */
angular.module('project').service('connectionService', ['$http','$q', function($http,$q) {
	
	//var promiseUser = $q.defer();
	
	var isAuth = false;
	var isAdmin = false;
	var user = {};
	
	
	this.connectUser = function(username,password) {
		return $http.post('/authenticate',undefined, {params: {username : username, password : password}})
			.then(function(response){
				return $http.get('/api/users/get',{ params : {login:username}}).then(function(response){
					user = response.data;
					isAuth = true;
					isAdmin = user.isAdmin;
					return response.data;
				});
			});
	};
	
	this.deconnectUser = function() {
		return $http.post('/logout')
			.then(function(response){
				isAuth = false;
				user = {};
				isAdmin = false;
			});
	};
	
	this.setIsAuth = function(value){
		isAuth = value;
	}
	
	this.setIsAdmin = function(value){
		isAdmin = value;
	}
	
	this.isAuth = function(){
		return isAuth;
	}
	
	this.isAdmin = function(){
		return isAdmin;
	}
	
	this.getUser = function() {
		return user;
	}
}])