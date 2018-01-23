angular.module('project').service('registerService', ['$http', function($http){
	
	
	this.createUser = function(user){
		return $http.post('/api/users/create', user).then(function(response) {
					console.log(response);
			});
	}
}]);
	

/*{"id":1,"birthDate":"2017-10-12","email":"mousssa@moussa.moussa","firstname":"moussa","lastname":"moussa","login":"mouned",
"password":"moussatest","phoneNumber":"515613153","isAdmin":"false"}*/