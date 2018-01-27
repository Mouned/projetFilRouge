/**
 * Service for orders
 */
angular.module('project').service('orderService', ['$http', function($http) {
	this.getOrders = function() {
		return $http.get('api/orders/search/order/all').then(function(response) {
			return response.data;
		});
	}
	
	this.getOrderUser = function(id) {
		return $http.get('api/orders/search/order/user', {params : {id : id}}).then(function(response) {
			return response.data;
		})
	}
}])