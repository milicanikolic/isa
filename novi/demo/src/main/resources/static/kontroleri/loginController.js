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

