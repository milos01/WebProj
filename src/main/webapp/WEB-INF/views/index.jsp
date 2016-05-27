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
	background: #f5f5f5;
}
</style>
</head>
<body ng-app="qfcApp">
	<c:if test="${errors.errorCount gt 0}">
		<div class="alert alert-danger" id="errorAlert"
			style="text-align: center; position: absolute; width: 100%">
			<c:forEach items="${errors.allErrors}" var="error">
				<strong>${error.defaultMessage}</strong>
				<br />
			</c:forEach>
		</div>
	</c:if>

	<!-- Modal for registration -->
	<div id="registerModal" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 400px">

			<!-- Register modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Register</h4>
				</div>
				<form action="register" method="POST">
					<input class="form-control" name="email" type="text" id="regEmail"
						placeholder="Email"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;">

					<input class="form-control" type="text" id="firstName"
						placeholder="First name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						name="firstName" /> 
					<input class="form-control" name="lastName"
						type="text" id="lastName" placeholder="Last name"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						ng-model="username_model" /> <input class="form-control"
						type="password" id="regPassword" name="password"
						placeholder="Password"
						style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
						ng-model="reg_password_model" />

					<div class="modal-footer" style="margin-top: 15px;">
						<button type="submit" class="btn btn-success"
							style="background: #1ab394">Register</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>

		</div>
	</div>
	<c:if test="${!empty token}">
		<div class="container" id="expiredToken"
			style="text-align: center; position: absolute; width: 100%; height: 50px; border-bottom: 1px solid #ccc; padding: 15px; background: #fff">
			<strong>Your request has expired, click <a
				href="resendRegistrationToken/${token}">here</a> to resend
				activation link.
			</strong>
			<button id="tokenButton" type="button" class="close"
				style="position: absolute; right: 10px">&times;</button>
		</div>
	</c:if>
	<!-- End modal for registration -->
	<c:if test="${!empty errorMessage}">
		<div class="alert alert-danger" id="errorAlert"
			style="text-align: center; position: absolute; width: 100%">
			<strong>${errorMessage}</strong>
		</div>
	</c:if>
	<c:if test="${!empty successMessage}">
		<div class="alert alert-success" id="errorAlert"
			style="text-align: center; position: absolute; width: 100%">
			<strong>${successMessage}</strong>
		</div>
	</c:if>
<!--    Login Form -->
	<div class="container" style="width: 500px; color: black" ng-controller="loginController" novalidate>
		<div
			style="width: 500px; font-size: 35px; text-align: center; margin-top: 21vh;">
			<div style="width: 500px; height: 180px; margin-left: -115px">
				<img alt="" src="../springmvc/resources/logo.png">
			</div>
			<p style="margin-top: -30px; font-family: 'Lobster Two', cursive;">Quest
				For Chef</p>
			<form action="login" method="POST" name="lognForm" novalidate>

                <div class="form-group" ng-class="{ 'has-error' : lognForm.loginEmail.$invalid && lognForm.loginEmail.$error && !lognForm.loginEmail.$pristine }">
				<input class="form-control" name="loginEmail" type="email"
					placeholder="Email"
					style="width: 300px; height: 45px; margin: auto auto; margin-top: 35px;" ng-model-options="{ updateOn: 'blur' }"
					ng-model="user.loginEmail" email-available required>
                </div>
                <p ng-show="lognForm.loginEmail.$error.required && !lognForm.loginEmail.$pristine" style="font-size:17px;position:absolute;margin-left:-58px;color:#a94442;margin-top:-48px">Email is required.</p>
                <p ng-show="lognForm.loginEmail.$error.email && !lognForm.loginEmail.$pristine" style="font-size:17px;position:absolute;margin-left:-58px;color:#a94442;margin-top:-48px">Email is not valid.</p>
                <p ng-show="lognForm.loginEmail.$error.emailAvailable && !lognForm.loginEmail.$error.email && !lognForm.loginEmail.$error.required && !lognForm.loginEmail.$pristine" style="font-size:17px;position:absolute;margin-left:-60px;color:#a94442;margin-top:-48px">Email not exists!</p>

                <div class="form-group" ng-class="{ 'has-error' : lognForm.loginPassword.$invalid && !lognForm.loginPassword.$pristine }">
				<input class="form-control" name="loginPassword" type="password"
					placeholder="Password"
					style="width: 300px; height: 45px; margin: auto auto; margin-top: 15px;"
					 ng-model-options="{ updateOn: 'blur' }" ng-model="loginPassword" required>
                </div>
                <p ng-show="lognForm.loginPassword.$invalid && !lognForm.loginPassword.$pristine" style="font-size:17px;position:absolute;margin-left:-90px;color:#a94442;margin-top:-48px">Password is required.</p>
               
                <input class="btn btn-default"
					name="loginSubmit" type="submit" value="Sign in"
					style="float: left; margin-left: 100px; margin-top: 15px;color:#1ab394" ng-disabled="lognForm.$invalid">
				<p
					style="float: right; margin-top: 15px; font-size: 17px; margin-right: 100px; padding: 7px;cursor: pointer"
					data-toggle="modal" data-target="#registerModal">Sign up</p>
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
	<script type="text/javascript" src="../springmvc/resources/js/validationByPage/indexPage.js"></script>
</body>
</html>
