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
		return $http.get('http://192.168.1.17:8080/api/products/all').then(function(response) {
			console.log(response);
				return response;
			});
	}

	
}])