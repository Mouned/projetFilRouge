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
    this.getOne = function (id) {
        var p1 = $http.get('/api/products/all'+id);
        var p2 = p1.then(function (response) {
            return response.data;
        });
    };    
    this.updateGame = function(jeu, file){
        	var fd = new FormData();
        	fd.append('file',file);
        	return $http.post('/api/products/update/image/'+jeu.id, fd, {
        			headers: {'Content-Type': undefined}
        	}).then(function(){
        			return searchService.getAll().then(function(data){
                        return data;
        		}); 		 
        	});            
    };
    
    this.deleteGame = function(id){
    	return $http.post('/api/products/delete/'+id).then(function(response){
        	         
        });
    }
}]);