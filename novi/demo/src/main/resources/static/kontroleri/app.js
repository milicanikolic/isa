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
.state('profilGosta', {
	url : '/profilGosta',
	templateUrl : 'stranice/profilGosta.html',
	controller : 'profilKorisnikaController'
})
.state('prijatelji', {
	url : '/prijatelji',
	templateUrl : 'stranice/prijatelji.html',
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
	controller : 'restoranController'
})
.state('jelovnik', {
	url : '/jelovnik',
	templateUrl : 'stranice/jelovnik.html',
	controller : 'restoranController'
})
.state('kartaPica', {
	url : '/kartaPica',
	templateUrl : 'stranice/kartaPica.html',
	controller : 'restoranController'
})
.state('menadzerRestorana', {
	url : '/menadzerRestorana',
	templateUrl : 'stranice/profilRestorana.html',
	controller : 'restoranController'
})

.state('evidencijaRadnika', {
	url : '/evidencijaRadnika',
	templateUrl : 'stranice/evidencijaZaposlenih.html',
	controller : 'restoranController'
})
.state('ponude', {
	url : '/ponude',
	templateUrl : 'stranice/ponude.html',
	controller : 'restoranController'
})
.state('ponudjac', {
	url : '/ponudjac',
	templateUrl : 'stranice/profilPonudjac.html',
	controller : 'ponudjacController'
})
.state('prikazIstorijePonuda', {
	url : '/prikazIstorijePonuda',
	templateUrl : 'stranice/istorijaPonuda.html',
	controller : 'ponudjacController'
})
.state('rezervacijaStola', {
	url : '/rezervacijaStola',
	templateUrl : 'stranice/rasporedStolova.html',
	controller : 'restoranController'
})
});