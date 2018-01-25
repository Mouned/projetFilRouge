angular.module('project').service('productsService', ['$http', 'searchService', function ($http,searchService) {

    this.addGame = function (jeu,file) {
    	
        return $http.post('/api/products/create', jeu).then(function(response){
        	var fd = new FormData();
        	fd.append('file',file);
        	return $http.post('/api/products/upload/'+response.data.id, fd, {
        			headers: {'Content-Type': undefined}
        	}).then(function(){
        			return searchService.getAll().then(function(data){
                        return data;
        		}); 		
        	});            
        });
    };
    
    this.getPegis = function(){
    	return $http.get('/api/products/pegi').then(function(response) {
			return response.data;
		});
    }

}]);