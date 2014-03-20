$(window).load(function() {
	$('#dvLoading').fadeOut(1000);
});

$(document).ready(function() {
	// Action messages & errors fade out
	hideActionMessagesAndErrors();

	$("#loginLoginAuth_username").focus();

});

function hideActionMessagesAndErrors() {
	var timeOut = 3000;
	window.setTimeout(function() {
		$("#actionError").fadeOut(3000);
		$("#actionMessage").fadeOut(3000);
	}, timeOut);
	window.setTimeout(function() {
		$("#actionError").css("visibility", "hidden"); // $("#actionError").removeClass("in");
		$("#actionMessage").css("visibility", "hidden");// $("#actionMessage").removeClass("in");
	}, 8000);
}

function setFocusToLoginTextBox() {
	document.getElementById("login_username").focus();
}
