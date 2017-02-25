app.factory('adminService',['$http', function($http){

	var admin={};
	
	admin.uzmiUlogovanog = function () {
        return $http.get('/gost/uzmiUlogovanog');
    };
    admin.uzmiAdmina = function (korisnik) {
        return $http.get('/admin/uzmi/'+korisnik.korisnickoIme);
    };
    admin.regMenadzerRestorana=function(menadzer,imeRestorana){
    	console.info(menadzer);
    	console.info(imeRestorana);
    	return $http.post('/restoran/dodajMenadzera/'+imeRestorana,menadzer);
    }
    admin.uzmiRestorane=function(){
    	return $http.get('/restoran/uzmiSve/');
    }
    
    admin.dodajRestoran=function(restoran){
    	return $http.post('/restoran/dodaj',restoran);
    }
    
    admin.dodajAdmina=function(noviAdmin){
    	return $http.post('/admin/dodaj',noviAdmin);
    }
    
    
    return admin;
}]);