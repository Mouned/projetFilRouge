

angular.module('project').service('searchService', ['$http', '$q', function($http, $q){
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
					type:recherche.type
				}
				}).then(function(response) {
				return response.data;
			});
	},
	
	this.getAll = function() {
		return $http.get('/api/products/all').then(function(response) {
				return response.data;
	    });
	}

	this.quickSearch = function(recherche) {
		promise = $http.get('/api/products/search-game', {params: {gameInfo: recherche}});
		promise.resolve;
		return promise.then(function(response) {
				list = response.data;
				reinitPromise();
				return list;
			})
	}
	
	this.getUserById = function(idUser){
		return $http.get('/api/users/search',{params : {id : idUser}}).then(function(response){
			return response.data;
		});
	}
	
	this.getUserByLogin = function(loginUser){
		return $http.get('/api/users/get',{params : {login : loginUser}}).then(function(response){
			return response.data;
		});
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