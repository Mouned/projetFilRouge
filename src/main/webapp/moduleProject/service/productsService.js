angular.module('project').service('productsService', ['$http', 'searchService', function ($http,searchService) {

	var path = '/api/products/';
	var create = 'create';
	var pegi = 'pegi';
	var get = 'get';
	var all = 'all';
	var update = 'update';
	var delet = 'delete';
	var disable = 'disable';
	var image = 'image';
	var getList = 'getList';
	var lengthList = 0;
	
    this.addGame = function (jeu,file) {
    	
        return $http.post(path+create, jeu).then(function(response){
        	var fd = new FormData();
        	fd.append('file',file);
        	return $http.post(path+upload+response.data.id, fd, {
        			headers: {'Content-Type': undefined}
        	}).then(function(){
        			return searchService.getAll().then(function(data){
                        return data;
        		}); 		
        	});            
        });
    };
    
    this.getPegis = function(){
    	return $http.get(path+pegi).then(function(response) {
			return response.data;
		});
    }
    this.getOne = function (id) {
        var p1 = $http.get(path+get+'/'+id, {params: {isAdmin: connectionService.isAdmin()}});
        var p2 = p1.then(function (response) {
            return response.data;
        });
    };    
    
    this.getList = function(idList){
    	console.log(idList);
    	var pathListId = '';
    	for(var key in idList){
    		if(key == (idList.length-1))
    			pathListId+=idList[key];
    		else
    			pathListId+=idList[key]+',';
    	}
    	return $http.get(path+getList+'/'+pathListId).then(function(response){
            lengthList = response.data.length;
			return response.data;
    	});
    }
    
    this.updateGame = function(jeu, file){
    	return $http.post(path+upload,jeu).then(function(response){
    		if(file != undefined){
    			var fd = new FormData();
    			fd.append('file',file);
    			return $http.post(path+image+jeu.id, fd, {
    				headers: {'Content-Type': undefined}
    			}).then(function(){
    				return searchService.getAll().then(function(data){
    					return data;
    				});
    			});
    		}else{
    			return searchService.getAll().then(function(data){
					return data;
				});
    		}
    	});
    };
    
    this.deleteGame = function(id){
    	return $http.post(path+delet+'/'+id).then(function(response){
    		return searchService.getAll().then(function(data){
				return data;
			}); 
        });
    }
    this.disableOrEnableGame = function(id) {
    	return $http.post(path+disable+'/'+id).then(function(response) {
    		return response.data;
    	});
    }
    	this.getLengthList = function () {
    	return lengthList;
    }
    
}]);