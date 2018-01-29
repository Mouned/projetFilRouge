

angular.module('project').service('searchService', ['$http', '$q', 'connectionService', function($http, $q, connectionService){
	var list = {};
	var promise = $q.defer();
	
	this.search = function(recherche) {
		return $http.get('/api/products/advanced-search', 
				{params : {
					title:recherche.titre,
					gamePublisher:recherche.editeur,
					pegi:recherche.pegi,
					priceMin:recherche.priceMin,
					priceMax:recherche.priceMax,
					type:recherche.type,
					isAdmin: connectionService.isAdmin()
				}
				}).then(function(response) {
				return response.data;
			});
	},
	
	this.getAll = function() {
		return $http.get('/api/products/all', {params: {isAdmin: connectionService.isAdmin()}}).then(function(response) {
				return response.data;
	    });
	}

	this.quickSearch = function(recherche) {
		promise = $http.get('/api/products/search-game', {params: {gameInfo: recherche, isAdmin: connectionService.isAdmin()}});
		promise.resolve;
		return promise.then(function(response) {
				list = response.data;
				reinitPromise();
				return list;
			})
	}
	
	this.getList = function() {
		return list;
	}
	
	this.setList = function(liste){
		list = liste
	}
	
	var reinitPromise = function() {
		promise = $q.defer();
	}
	
}]);