/**
 * Service for products (games)
 */
angular.module('project').service('gameService', ['$http', function($http){
	
	var host = 'localhost';
	var port = '8080';
	var p = 0;
	//var mediaPromise= $http.get('http://localhost:8080/api/public/products/all');
	
//	getting the entire game list
	this.getAll = function() {
		return $http.get('http://localhost:8080/api/public/products/all').then(function(response) {
			console.log(response);
				return response;
			});
	}

	
}])