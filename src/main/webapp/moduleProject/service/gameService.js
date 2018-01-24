/**
 * Service for products (games)
 */
angular.module('project').service('gameService', ['$http', function($http){
	
	/**
	 * Getting the entire game list
	 */
	this.getAll = function() {
		return $http.get('/api/products/all').then(function(response) {
			console.log(response);
				return response;
			});
	}

	
}])