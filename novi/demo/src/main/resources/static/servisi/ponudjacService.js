app.factory('ponudjacService',['$http', function($http){
	var ponuda={};
	
	ponuda.initPonudjac=function(){
		return $http.get('/gost/uzmiUlogovanog');
	}
	
	ponuda.izmenaPonudjaca=function(id,ponudjac){
		return $http.put('/ponudjac/izmeniPodatke/'+id,ponudjac);
	}
	ponuda.prikaziPonude=function(idMR){
		return $http.get('/ponuda/prikaziAktivne/'+idMR);
	}
	ponuda.dodajPonuduP=function(idPonudjaca,idPonudeMR,ponuda){
		return $http.post('/ponudjac/dodajPonudu/'+idPonudjaca+'/'+idPonudeMR,ponuda);
	}
	ponuda.istorijaPonuda=function(id){
		return $http.get('/ponudjac/usmiIstoriju/'+id);
	}
	return ponuda;
}]);