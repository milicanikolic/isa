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
    
    restoran.uzmiNamirnice=function(){
    	return $http.get('/namirnica/prikaziNamirnice');
    }
    
    restoran.dodajNamirnicu=function(namirnica){
    	return $http.post('namirnica/dodajNamirnicu',namirnica);
    }
    
    restoran.dodajPonudu=function(id,nam1,nam2,nam3,datumOd,datumDo){
    	return $http.post('ponuda/dodajPonuduMenadzera/'+id+'/'+nam1+'/'+nam2+'/'+nam3+'/'+datumOd+'/'+datumDo);
    }
    
    restoran.dodajPonudjaca=function(id, ponudjac){
    	return $http.post('ponudjac/dodajPonudjaca/'+id,ponudjac);
    }
    
    restoran.izmiStolove=function(id){
    	return $http.get('restoran/uzmiStolove/'+id);
    }
    restoran.rezervisi1=function(id,datum,vreme,trajanje){
    	return $http.get('gost/prikazStolova/'+id+'/'+datum+'/'+vreme+'/'+trajanje);
    }
    restoran.rezervisi2=function(idG,idR,datum,vreme,trajanje,idS){
    	return $http.post('gost/rezervisi/'+idG+'/'+idR+'/'+datum+'/'+vreme+'/'+trajanje+'/'+idS);
    }
    restoran.dodajRadnika=function(id,gost){
    	return $http.post('radnik/dodajRadnika/'+id,gost);
    }
    
	return restoran;
}]);