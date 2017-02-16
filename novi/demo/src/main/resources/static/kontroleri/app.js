'use strict';
var app=angular.module('app',['ui.router','ngMaterial']);

app.config(function($stateProvider, $urlRouterProvider) {


	$urlRouterProvider.otherwise('/login');

$stateProvider.state('login', {
	url : '/login',
	templateUrl : 'stranice/login.html',
	controller : 'loginController'
})
$stateProvider.state('sviGosti', {
	url : '/sviGosti',
	templateUrl : 'stranice/sviGosti.html',
	controller : 'sviGostiController'
})
$stateProvider.state('registruj', {
	url : '/registruj',
	templateUrl : 'stranice/Registracija.html',
	controller : 'registracijaController'
})
});