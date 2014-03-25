$(window).load(function() {
	$('#dvLoading').fadeOut(1000);
});

$(document).ready(function() {
	// Action messages & errors fade out
	hideActionMessagesAndErrors();

	$("#loginLoginAuth_username").focus();

});

//===== Tooltip =====//

$('.leftDir').tipsy({fade: true, gravity: 'e', html:true});
$('.rightDir').tipsy({fade: true, gravity: 'w', html:true});
$('.topDir').tipsy({fade: true, gravity: 's', html:true});
$('.botDir').tipsy({fade: true, gravity: 'n', html:true});

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

