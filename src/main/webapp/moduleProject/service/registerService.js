angular.module('project').service('registerService', ['$http','connectionService', function($http,connectionService){
	
	
	this.createUser = function(user){
		return $http.post('/api/users/create', user).then(function() {
					return connectionService.connectUser(user.login,user.password).then(function(response){
						return response.data;
					});
			}).catch(function onError(response) {
				return "error";
			});
	}
}]);
	

/*{"id":1,"birthDate":"2017-10-12","email":"mousssa@moussa.moussa","firstname":"moussa","lastname":"moussa","login":"mouned",
"password":"moussatest","phoneNumber":"515613153","isAdmin":"false"}*/