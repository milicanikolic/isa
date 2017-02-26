app.controller('restoranController', function ($scope,$window,$rootScope,restoranService, $http) {
	

	
	$scope.uzmiRestoran=function(){	
		
		restoranService.uzmiUlogovanog()
		.success(function(data){
			var logovani=data;
			
			if(logovani.vrstaKorisnika=="GOST"){
				console.log("ulogovan gooost");
				$scope.gost=1;
				
				restoranService.uzmiRestoran()
				.success(function(data){
					console.log("usao u sukses od gosta");
					
					$scope.jedanRestoran=data;

				})
				
			}else if(logovani.vrstaKorisnika=="MENADZER_RESTORANA"){
				$scope.MR=1;
				
				restoranService.postaviRestoranMenadzera(logovani.korisnickoIme)
				.success(function(data){
					console.log("usao u sukses od menazdera restorana");
					console.log($scope.MR)
					
					$scope.jedanRestoran=data;

				})
			}
				
		})
		
		
	}
	$scope.prikaziJelovnik=function(id){
		console.info("jelovnik");
		$window.location.href = '#/jelovnik';
		/*restoranService.prikaziJelovnik(id)
		.success(function(data){
			$scope.jelovnik=data;
			$rootScope.jelovnik=data;
			console.log($scope.jelovnik);
			

		})*/
	}
	$scope.jelovnik=function(){
		console.info("jelovnik sa stranice");

		restoranService.jelovnik1()
		.success(function(data){
			$scope.jelovnik=data;
			
		})
	}
	
	$scope.prikazPica=function(){
		$window.location.href = '#/kartaPica';
	}
	
	$scope.kartaPica=function(){
		restoranService.kartaPica()
		.success(function(data){
			$scope.listaPica=data;
			
			

		})
	}
	$scope.izmeniPice=function(id){
		
		restoranService.uzmiPice(id)
		.success(function(data){
			
			$scope.trenutnoPice=data;
			$scope.izmenaPica=1;
			$scope.dodavanjePica=0;
			console.log($scope.trenutnoPice);
		})
	}
	
	$scope.izmeniPice2=function(novoPice){
		console.log("izmena pica 2: "+$scope.trenutnoPice.id);
		restoranService.izmeniPice(novoPice,$scope.trenutnoPice.id)
		.success(function(data){
			$scope.listaPica=data;
			$scope.izmenaPica=0;
			
			
		})
		
		
	}
	
	$scope.obrisiPice=function(idPica){
		restoranService.obrisiPice(idPica)
		.success(function(data){
			$scope.listaPica=data;
			
		})
	}
	
	
	$scope.izmeniJelo=function(id){
		
		restoranService.uzmiJelo(id)
		.success(function(data){
			
			$scope.trenutnoJelo=data;
			$scope.izmenaJela=1;
			
			
		})
	}
	
	$scope.izmeniJelo2=function(novoJelo){
		console.log("izmena jela 2: "+$scope.trenutnoJelo.id);
		restoranService.izmeniJelo(novoJelo,$scope.trenutnoJelo.id)
		.success(function(data){
			$scope.jelovnik=data;
			$scope.izmenaJela=0;
			
		})
		
	}
	$scope.obrisiJelo=function(idJelo){
		restoranService.obrisiJelo(idJelo)
		.success(function(data){
			$scope.jelovnik=data;
			
		})
	}
	$scope.dodajJelo=function(){
		$scope.izmenaJela=0;
		$scope.dodavanjeJelo=1;
	}
	
	$scope.dodajJelo2=function(novoJelo){
		restoranService.dodajJelo(novoJelo,$scope.jedanRestoran.id)
		.success(function(data){
			$scope.jelovnik=data;
			$scope.dodavanjeJelo=0;
			
		})
		
	}
	$scope.dodajPice=function(){
		$scope.izmenaPica=0;
		$scope.dodavanjePica=1;
	}
	$scope.dodajPice2=function(novoPice){
		restoranService.dodajPice(novoPice,$scope.jedanRestoran.id)
		.success(function(data){
			$scope.listaPica=data;
			$scope.dodavanjePica=0;
		})
	}
	
});