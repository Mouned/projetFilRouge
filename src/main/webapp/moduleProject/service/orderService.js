/**
 * Service for orders
 */
angular.module('project').service('orderService', ['$http', function($http) {
	this.getOrders = function() {
		return $http.get('api/orders/search/order/all').then(function(response) {
			return response.data;
		})
	}
}])