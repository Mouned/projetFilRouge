/**
 * Service for products (games)
 */
angular.module('project').service('gameService', ['$http', function($http){
	
	var host = 'localhost';
	var port = '8080';
	var p = 0;
	//var mediaPromise= $http.get('http://localhost:8080/api/public/products/all');
//	var host = 'http://192.168.1.13';
//	var port = '8080';
//	var p = 0;
	var mediaPromise= $http.get('localhost:8080/api/products/all');
	
//	getting the entire game list
	this.getAll = function() {
<<<<<<< HEAD
		return $http.get('http://192.168.1.17:8080/api/products/all').then(function(response) {
			console.log(response);
=======
		return $http.get('http://192.168.1.25:8080/api/products/all').then(function(response) {
>>>>>>> 1e3fdb5b11dc64bb3c3c96c8155032c53180bdb4
				return response;
			});
	}

	
}])