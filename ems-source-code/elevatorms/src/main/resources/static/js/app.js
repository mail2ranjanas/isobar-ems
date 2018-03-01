	var app =angular.module('app', ['ngRoute']);

	app.config(function($routeProvider) {
		$routeProvider.when('/', {
				templateUrl : 'pages/ems.html',
				controller  : 'ElevatorController'
			});

	});
