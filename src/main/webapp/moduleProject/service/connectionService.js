/**
 * Service for connection
 */
angular.module('project').service('connectionService', ['$http','$q','sessionService','searchUserService', function($http,$q,sessionService,searchUserService) {
	
	//var promiseUser = $q.defer();
	
	var isAuth = false;
	var isAdmin = false;
	var user = {};
	var login = undefined;
	var idUser = undefined;
	
	
	this.connectUser = function(username,password) {
		return $http.post('/authenticate',undefined, {params: {username : username, password : password}})
			.then(function(response){
				return $http.get('/api/users/get',{ params : {login:username}}).then(function(response){
					user = response.data;
					isAuth = true;
					isAdmin = user.isAdmin;
					login = user.login;
					idUser = user.id;
					return response.data;
				});
			});
	};
	
	this.deconnectUser = function() {
		return $http.post('/logout')
			.then(function(response){
				isAuth = false;
				isAdmin = false;
				user = {};
				login = undefined;
				idUser = undefined;
			});
	};
	
	this.getUserDetails = function(){
		return sessionService.getSessionDetail().then(function(response){
			if(response != 0){
				isAuth = true;
				var authorities = response.authorities;
				isAdmin = false;
				login = response.username;
				for(var key in authorities){
					if(authorities[key].authority == 'ADMIN'){
						isAdmin = true;
					}
				}
				searchUserService.getUserByLogin(login).then(function(response){
					idUser = response.id;
				});
				
				user = {
						auth : isAuth,
						admin : isAdmin,
						login : login,
						id : idUser
				}
			}
			return user;
		});
	}
	
	this.clearUser = function(){
		isAuth = false;
		isAdmin = false;
		user = {};
		login = undefined;
		idUser = undefined;
	}
	
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
	
	this.getLogin = function(){
		return login;
	}
	
	this.getIdUser = function(){
		return idUser;
	}
}])