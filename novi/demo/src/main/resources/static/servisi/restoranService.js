app.factory('restoranService',['$http', function($http){
	var restoran={};
	
	restoran.uzmiUlogovanog = function () {
        return $http.get('/gost/uzmiUlogovanog');
    };
	
	restoran.uzmiRestoran = function () {
        return $http.get('/restoran/uzmiRestoran');
    };

    restoran.prikaziJelovnik = function (id) {
        return $http.get('/restoran/jelovnik/'+id);
    };
    
    restoran.jelovnik1 = function () {
        return $http.get('/restoran/uzmiJelovnik');
    };
    restoran.kartaPica = function () {
        return $http.get('/restoran/kartaPica');
    };
    restoran.postaviRestoranMenadzera=function(MRKorisnicko){
    	return $http.post('/restoran/postaviRestoranMR/'+MRKorisnicko);
    }
    restoran.uzmiPice=function(id){
    	return $http.get('/restoran/uzmiPice/'+id);
    }
    restoran.izmeniPice=function(novoPice, idStarog){
    	return $http.put('/restoran/izmeniPice/'+idStarog,novoPice);
    }
    
    restoran.obrisiPice=function(idPica){
    	return $http['delete']('/restoran/izbrisiPice/'+idPica);
    }
    
    restoran.uzmiJelo=function(id){
    	return $http.get('/restoran/uzmiJelo/'+id);
    }
    restoran.izmeniJelo=function(novoJelo, idStarog){
    	return $http.put('/restoran/izmeniJelo/'+idStarog,novoJelo);
    }
    restoran.obrisiJelo=function(idJela){
    	return $http['delete']('/restoran/izbrisiJelo/'+idJela);
    }
    restoran.dodajJelo=function(novoJelo,idRestorana){
    	return $http.post('/restoran/dodajJelo/'+idRestorana,novoJelo);
    }
    restoran.dodajPice=function(novoPice,idRestorana){
    	return $http.post('/restoran/dodajPice/'+idRestorana,novoPice);
    }
	return restoran;
}]);