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
$stateProvider.state('profilKorisnika', {
	url : '/profilKorisnika',
	templateUrl : 'stranice/profilKorisnika.html',
	controller : 'profilKorisnikaController'
})
$stateProvider.state('restorani', {
	url : '/restorani',
	templateUrl : 'stranice/gostRestorani.html',
	controller : 'profilKorisnikaController'
})
$stateProvider.state('izmenaKorinika', {
	url : '/izmenaKorinika',
	templateUrl : 'stranice/izmenaKorisnika.html',
	controller : 'izmenaKorinikaController'
})

$stateProvider.state('admin', {
	url : '/admin',
	templateUrl : 'stranice/profilAdmin.html',
	controller : 'adminController'
})
.state('profilRestorana', {
	url : '/profilRestorana',
	templateUrl : 'stranice/profilRestorana.html',
	controller : 'profilKorisnikaController'
})

});