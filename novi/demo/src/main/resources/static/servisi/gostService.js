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
    
    korisnik.pretrazi=function(imePrezime,idGosta){
    	return $http.post('/prijatelji/pretrazi/'+idGosta,imePrezime);
    }
    
    korisnik.dodajPrijatelja=function(idPrijatelji,idUlogovanog){
    	return $http.post('/prijatelji/dodaj/'+idUlogovanog+'/'+idPrijatelji);
    }
    
    korisnik.zahteviZaPrijateljstvo=function(id){
    	return $http.get('/gost/proveriNotifikacije/'+id)
    }
    
    korisnik.prihvatiPrijatelja=function(idZahteva,idUlogovanog){
    	return $http.post('/prijatelji/prihvati/'+idZahteva+'/'+idUlogovanog);
    }
    
    korisnik.odbijPrijatelja=function(idZahteva,idUlogovanog){
    	return $http.post('/prijatelji/odbij/'+idZahteva+'/'+idUlogovanog);
    }
    korisnik.uzmiPrijatelje=function(id){
    	return $http.get('prijatelji/prikaziSve/'+id);
    }
    
	return korisnik;
}]);