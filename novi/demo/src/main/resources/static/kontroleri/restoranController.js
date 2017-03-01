app.controller('restoranController', function ($scope,$window,$rootScope,restoranService, $http) {
	
	$scope.prikazMeni=1;
	$scope.prikazMeniPonude=1;
	
	$scope.uzmiRestoran=function(){	
		
		restoranService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovan=data;
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
	
	$scope.evidencijaRadnika=function(){
		$window.location.href = '#/evidencijaRadnika';
	}
	
	$scope.prikaziDodajR=function(){
		$scope.prikazMeni=0;
		$scope.dodavanjeRadnika=1;
	};
	$scope.dodajRadnika=function(gost){
		
	}
	$scope.prikaziponudeMeni=function(){
		$window.location.href = '#/ponude';
	}
	
	$scope.prikaziDodajNamirnicu=function(){
		
		$scope.dodavanjeNamirnice=1;
		$scope.prikazMeniPonude=0;
		restoranService.uzmiNamirnice()
		.success(function(data){
			$scope.sveNamirnice=data;
		})
	}
	
	$scope.dodajNamirnicu=function(namirnica){
		console.log("usao u dodaj namirnicu cont");
		console.log(namirnica);
		restoranService.dodajNamirnicu(namirnica)
		.success(function(data){
			$scope.sveNamirnice=data;
			$scope.pogresnaNamirnica=0;
		})
		.error(function(data){
			$scope.sveNamirnice=data;
			$scope.pogresnaNamirnica=1;
			$scope.namirnica={};
		})
	}
	$scope.dodavanjeNamirnicaKraj=function(){
		$scope.dodavanjeNamirnice=0;
		$scope.prikazMeniPonude=1;
	}
	
	$scope.prikaziDodajPonudu=function(){
		$scope.dodavanjePonude=1;
		$scope.prikazMeniPonude=0;
		restoranService.uzmiNamirnice()
		.success(function(data){
			$scope.sveNamirnice=data;
		})
		
		restoranService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovani=data;
			
			console.log($scope.ulogovani);
		})
	}
	
	

    
    $scope.dodajPonudu=function(ponuda){
    	
    	
    	restoranService.dodajPonudu($scope.ulogovani.id,$scope.nam1,$scope.nam2,$scope.nam3,$scope.datumOd,$scope.datumDo)
    	.success(function(data){
    		$scope.dodavanjePonude=0;
    		$scope.prikazMeniPonude=1;
		})
    }
	$scope.prikaziDodajPonudjaca=function(){
		$scope.dodavanjePonudjaca=1;
		$scope.prikazMeniPonude=0;
		restoranService.uzmiUlogovanog()
		.success(function(data){
			$scope.ulogovan=data;
		})
	}
	$scope.dodajPonudjaca=function(ponudjac){
		restoranService.dodajPonudjaca($scope.ulogovan.id,ponudjac)
		.success(function(data){
			$scope.dodavanjePonudjaca=0;
			$scope.prikazMeniPonude=1;
			$scope.ponudjac={};
		})
	}
});