$(window).load(function() {
	$('#dvLoading').fadeOut(1000);
});

$(document).ready(function() {
	$("#loginLoginAuth_username").focus();
});

function setFocusToLoginTextBox() {
	document.getElementById("login_username").focus();
}
