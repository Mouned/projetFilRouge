/**
 * Service for connection
 */
angular.module('project').service('sessionService', ['$http', function($http) {

	this.getSessionDetail = function(){
		return $http.get('/api/session/get').then(function(response){
			return response.data;
		});
	}
}]);