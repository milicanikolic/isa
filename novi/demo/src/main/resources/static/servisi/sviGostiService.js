'use strict';

/*app.service('sviGostiService',function($http) {
	return {
		
		getAll : function(callback) {
			var url='/gost';
			console.log("fvjkvdvnjv");
			$http.get(url).then(function(data) {
				console.log("ewf");
				callback(data.data);
			});
		}
	}
});		*/

app.factory('sviGostiService',['$http', function($http){

	var nesto={}
	
	nesto.getAll=function(){
		return $http.get('/gost');
	};
	
	return nesto;
}]);