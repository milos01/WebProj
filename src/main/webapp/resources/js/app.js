//var myApp = angular.module('App',[]);
//myApp.controller('addFriendController', ['$scope','$http', function($scope, $http) {
//
//}]);
window.onbeforeunload = function() {
	var fromNum = $("#typedFrom").val();
	var toNum = $("#typedTo").val();

	localStorage.setItem("fromValue", fromNum);
	localStorage.setItem("toValue", toNum);
};
$(function() {
	var r = localStorage.getItem("fromValue");
	var r2 = localStorage.getItem("toValue");

	$("#typedFrom").val(r);
	$("#typedTo").val(r2);
	socket = io.connect('http://localhost:3000');

	$('.resButtons').click(function() {
		buttonTextId = $(this).attr("id");
		buttonIdNumber = buttonTextId.replace(/[^\d]/g, '');
		socket.emit('reserve', buttonIdNumber);
	});

	socket.on('reserveTable', function(data) {
		$('#reserveButton' + data.id).html("Watching");
		$('#reserveButton' + data.id).css("background-color", "#ccc");
		$('#reserveButton' + data.id).css("color", "black");
		$('#reserveButton' + data.id).css("border", "1px solid gray");

	});

	$(".closeButt").click(function() {
		$(".reserveDiv").hide();
		closeTextId = $(this).attr("id");
		closeIdNumber = closeTextId.replace(/[^\d]/g, '');
		socket.emit('closeReserve', closeIdNumber);
	});

	socket.on('closeReserveTable', function(data) {
		$('#reserveButton' + data.id).html("Reserve");
		$('#reserveButton' + data.id).css("background-color", "#1ab394");
		$('#reserveButton' + data.id).css("color", "#fff");
		$('#reserveButton' + data.id).css("border", "1px solid #1ab394");
	});

	$('.reserveDiv').on('shown.bs.modal', function() {
		$(document).idleTimer(5000);
	});
	$(document).on(
			"idle.idleTimer",
			function(event, elem, obj) {
				toastr.options = {
					"positionClass" : "toast-top-right",
					"timeOut" : 8000
				}

				toastr.warning('You can call any function after idle timeout.',
						'Idle time');
				$('.custom-alert').fadeIn();
				$('.custom-alert-active').fadeOut();

			});

	$(document).on(
			"active.idleTimer",
			function(event, elem, obj, triggerevent) {
				// function you want to fire when the user becomes active again
				toastr.clear();
				$('.custom-alert').fadeOut();
				toastr.success('Great that you decided to move a mouse.',
						'You are back. ');

			});

});

$("#friendsSearch").keyup(function() {
	var friendsSearch = $(this).val();
	var ime = "Micko Mickovic"
	if (ime.match(friendsSearch)) {
		$(this).css("border-bottom", "1px solid red");
	} else {
		$(this).css("border-bottom", "none");
	}
	if (friendsSearch.length < 1) {
		$(this).css("border-bottom", "none");
	}
});

if ($("#errorAlert").is(":visible")) {
	$("#errorAlert").delay(1800).fadeOut(500);
}

$("#tokenButton").click(function() {
	$("#expiredToken").fadeOut("fast");
});

$(".resButtons").click(function() {
	$(".reserveDiv").hide();

	buttonTextId = $(this).attr("id");
	buttonIdNumber = buttonTextId.replace(/[^\d]/g, '');
	$("#reserve" + buttonIdNumber).show();

	var r = localStorage.getItem("fromValue");
	var r2 = localStorage.getItem("toValue");

	$("#fromInput" + buttonIdNumber).val(r);
	$("#toInput" + buttonIdNumber).val(r2);

});
