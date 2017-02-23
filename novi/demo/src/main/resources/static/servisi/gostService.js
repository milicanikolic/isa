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
    korisnik.sviRestorani=function(){
    	return $
    }
	return korisnik;
}]);