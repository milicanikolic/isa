app.controller('loginController', function ($scope,$rootScope,$window, $http,gostService) {
	//alert("ssssss");
	$scope.login = function(gost){
//		alert("ssssss");
        //console.log(gost);
	var ulogovani= gostService.login(gost)
	.success(function(data){
		var logovani=data;

		$rootScope.korisnik=data;
		if(logovani!=null){
			console.log(logovani);
			if(logovani.vrstaKorisnika=="GOST"){
				$window.location.href = '#/profilKorisnika';
			}else if(logovani.vrstaKorisnika=="KONOBAR"){
				
			}
			else if(logovani.vrstaKorisnika=="SANKER"){
				aletr("konobar");
			}
			else if(logovani.vrstaKorisnika=="KUVAR_ZA_SALATE"){
				aletr("konobar");
			}
			else if(logovani.vrstaKorisnika=="KUVAR_ZA_PECENA_JELA"){
				aletr("konobar");
			}else if(logovani.vrstaKorisnika=="KUVAR_ZA_KUVANA_JELA"){
				aletr("konobar");
			}
			else if(logovani.vrstaKorisnika=="MENADZER_RESTORANA"){
				console.log("mendzer restoraa");
				
				$window.location.href = '#/menadzerRestorana';
			}
			else if(logovani.vrstaKorisnika=="ADMIN"){
				$window.location.href = '#/admin';
			}else if(logovani.vrstaKorisnika=="PONUDJAC"){
				$window.location.href = '#/ponudjac';
			}
		}else{
			alert("pogresno logovanje");
		}
	})
	 .error(function (error) {
         $scope.status = 'Uneti podaci nisu tacni: ' + error.message;
         console.log("usao u error   "+ error.message);
     });
	
    };
});

