<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Quest For Chef</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link href='https://fonts.googleapis.com/css?family=Lobster+Two'
	rel='stylesheet' type='text/css'>
<style>
body {
	background: #e6e6e6;
}
</style>
</head>
<body ng-app="app">
	<c:if test="${!empty errorMessage}">
		<div class="alert alert-danger" id="errorAlert" style="text-align:center;position:absolute;width:100%">
			<strong>${errorMessage}</strong>
		</div>
	</c:if>
	<div class="container" style="width: 500px; color: black"
		ng-controller="LoginController">
		<div
			style="width: 500px; font-size: 35px; text-align: center; margin-top: 21vh;">
			<div style="width: 500px; height: 180px; margin-left: -115px">
				<img alt="" src="../springmvc/resources/logo.png">
			</div>
			<p style="margin-top: -30px; font-family: 'Lobster Two', cursive;">Quest
				For Chef</p>
			<form action="login" method="POST">
				<input class="form-control" name="username" type="text"
					id="username" placeholder="Username"
					style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;"
					ng-model="username_model"> <input class="form-control"
					name="password" type="password" id="password"
					placeholder="Password"
					style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
					ng-model="password_model"> <input class="btn btn-default"
					name="loginSubmit" type="submit" value="Sign in"
					style="float: left; margin-left: 100px; margin-top: 15px"
					ng-click="loginSubmit_model()" />
				<p
					style="float: right; margin-top: 15px; font-size: 17px; margin-right: 100px; padding: 7px">Sign
					up</p>
			</form>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	<script type="text/javascript" src="../springmvc/resources/js/app.js"></script>
</body>
</html>
