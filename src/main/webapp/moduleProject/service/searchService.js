

angular.module('project').service('searchService', ['$http', function($http){
	
	this.search = function(recherche) {
		return $http.get('http://192.168.1.17:8080/api/products/advanced-search', 
				{params : {
					title:recherche.titre,
					gamePublisher:recherche.editeur,
					pegi:recherche.pegi,
					priceMin:recherche.priceMin,
					priceMax:recherche.priceMax,
					type:recherche.type
				}
				}).then(function(response) {
					console.log(response);
				return response.data;
			});
	},
	
	this.getAll = function() {
		return $http.get('http://192.168.1.17:8080/api/products/all').then(function(response) {
				return response.data;
	    });
	}

}]);