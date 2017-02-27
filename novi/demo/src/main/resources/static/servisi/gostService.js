app.factory('gostService',['$http', function($http){
	var korisnik={};
	
	korisnik.uzmiUlogovanog = function () {
        return $http.get('/gost/uzmiUlogovanog');
    };
	
	korisnik.login = function (gost) {
        return $http.post('/gost/login',gost);
    };
    
    korisnik.registruj=function(gost){
    	return $http.post('/gost/registruj',gost);
    }
    korisnik.uzmiGosta=function(id){
    	return $http.get('/gost/uzmiGosta/'+id);
    }
    korisnik.izmeniGosta=function(izmenjenGost,id){
    	return $http.put('/gost/izmeniGosta/'+id,izmenjenGost);
    }
    
    korisnik.sviRestorani=function(){
    	return $http.get('/restoran/uzmiSve');
    }
    
    korisnik.postaviRestoran=function(idRestorana){
    	return $http.post('/restoran/postaviRestoran/'+idRestorana);
    }
    
	return korisnik;
}]);