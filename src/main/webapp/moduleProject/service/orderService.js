/**
 * Service for orders
 */
angular.module('project').service('orderService', ['$http', function($http) {
	this.getOrders = function() {
		return $http.get('api/orders/search/order/all').then(function(response) {
			return response.data;
		});
	}
	
	this.getOrderUser = function(idUser) {
		return $http.get('api/orders/search/order/user', {params : {id : idUser}}).then(function(response) {
			return response.data;
		})
	}
}])