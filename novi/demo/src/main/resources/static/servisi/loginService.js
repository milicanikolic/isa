'use strict';

//var LoginService=angular.module('LoginService',[])

app.factory('loginService',['$http', function($http){
	var korisnik={};
	
	korisnik.login = function (gost) {
        return $http.post('/gost/login',gost);
    };
	return korisnik;
}]);
/*
app.service('loginService',function($http) {
	return {
		
		login : function(gost,callback) {
			var url='/gost/login';
			$http.post(url,gost).then(function(data) {
				callback(data.data);
			});
		}
	}
});		
*/