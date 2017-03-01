app.controller('loginController', function ($scope,$rootScope,$window, $http,gostService) {
	//alert("ssssss");
	$scope.login = function(gost){
//		alert("ssssss");
        //console.log(gost);
	var ulogovani= gostService.login(gost)
	.success(function(data){
		$scope.pogresnoLogovanje=0;
		var logovani=data;

		$rootScope.korisnik=data;
		if(logovani!=null){
			console.log(logovani);
			if(logovani.vrstaKorisnika=="GOST"){
				$window.location.href = '#/profilKorisnika';
			}else if(logovani.vrstaKorisnika=="KONOBAR"){
				console.log("usao u konobar");
				$window.location.href = '#/radnik';
			}
			else if(logovani.vrstaKorisnika=="SANKER"){
				$window.location.href = '#/radnik';
			}
			else if(logovani.vrstaKorisnika=="KUVAR_ZA_SALATE"){
				$window.location.href = '#/radnik';
			}
			else if(logovani.vrstaKorisnika=="KUVAR_ZA_PECENA_JELA"){
				$window.location.href = '#/radnik';
			}else if(logovani.vrstaKorisnika=="KUVAR_ZA_KUVANA_JELA"){
				$window.location.href = '#/radnik';
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
         $rootScope.pogresnoLogovanje=1;
         console.log("errooooor");
     });
	
    };
});

