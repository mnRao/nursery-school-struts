$(window).load(function() {
	$('#dvLoading').fadeOut(1000);
});

$(document).ready(function() {
	// Action messages & errors fade out
	hideActionMessagesAndErrors();

	$("#loginLoginAuth_username").focus();

});

function hideActionMessagesAndErrors() {
	var timeOut = 2000;
	window.setTimeout(function() {
		$("#actionError").slideUp(500);
		$("#actionError").css("visibility", "hidden"); // $("#actionError").removeClass("in");
		$("#actionMessage").slideUp(500);
		$("#actionMessage").css("visibility", "hidden");// $("#actionMessage").removeClass("in");
	}, timeOut);
}

function setFocusToLoginTextBox() {
	document.getElementById("login_username").focus();
}
