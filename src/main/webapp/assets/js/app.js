$(window).load(function() {
	$('#dvLoading').fadeOut(1000);
});

$(document).ready(function() {
	// Action messages & errors fade out
	hideActionMessagesAndErrors();

	$("#loginLoginAuth_username").focus();
});

// ===== Tooltip =====//

$('.leftDir').tipsy({
	fade : true,
	gravity : 'e',
	html : true
});
$('.rightDir').tipsy({
	fade : true,
	gravity : 'w',
	html : true
});
$('.topDir').tipsy({
	fade : true,
	gravity : 's',
	html : true
});
$('.botDir').tipsy({
	fade : true,
	gravity : 'n',
	html : true
});

/* Message and errors */
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

/* Login */
function setFocusToLoginTextBox() {
	document.getElementById("login_username").focus();
}

/************************************************************************************************************/


//Toggle all on click
$("#selectAllCheckboxes").click(function() {
	if ($("#selectAllCheckboxes").is(':checked')) {
		$(".selectedElement").each(function() {
			$(this).trigger('tickAll');
			$(this).prop("checked", true);
		});
	} else {
		$(".selectedElement").each(function() {
			$(this).trigger('untickAll');
			$(this).prop("checked", false);
		});
	}
});


function manageCheckAll() {
	if (isAllChecked()) {
		$("#selectAllCheckboxes").prop("checked", true);
	} else {
		$("#selectAllCheckboxes").prop("checked", false);
	}
}

function isAllChecked() {
	var isAllChecked = true;
	$('.selectedElement').each(function() {
		if ($(this).prop("checked") == false) {
			isAllChecked = false;
		}
	});

	return isAllChecked;
}

// Extract number from string
function extractNumber(text) {
	return text.match(/\d+/)[0];
}

/* Toggle checkbox */
function tickCheckbox(order) {
	$("#selectedElement_" + order).each(function() {
		$(this).doCheck();
	});
}

function untickCheckbox(order) {
	$("#selectedElement_" + order).each(function() {
		$(this).undoCheck();
	});
}

function toogleCheckbox(order) {
	$("#selectedElement_" + order).each(function() {
		$(this).checkboxToggle();
	});
}

/* Toggle decoration for text input */
function toogleInputVisibility(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).visibilityToggle();
	});
}

function disableInputVisibility(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).invisible();
	});
}

function enableInputVisibility(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).visible();
	});
}

/* Toggle require for text input */
function toggleInputRequired(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).inputRequiredToggle();
	});
}

function disableInputRequired(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).inputNotRequired();
	});
}

function enableInputRequired(order) {
	$("#amount-toggle_" + order + " input").each(function() {
		$(this).inputRequired();
	});
}

/* Toggle decoration for label */
function toogleTextDecoration(order) {
	$("#recordRow_" + order + " label").each(function() {
		$(this).decorationToggle();
	});
}

function disableTextDecoration(order) {
	$("#recordRow_" + order + " label").each(function() {
		$(this).disableDecoration();
	});
}

function enableTextDecoration(order) {
	$("#recordRow_" + order + " label").each(function() {
		$(this).crossOut();
	});
}
/****************/

/* Plugin */

//Visibility for input
jQuery.fn.visible = function() {
	return this.css('visibility', 'visible');
};

jQuery.fn.invisible = function() {
	return this.css('visibility', 'hidden');
};

jQuery.fn.visibilityToggle = function() {
	return this.css('visibility', function(i, visibility) {
		return (visibility == 'visible') ? 'hidden' : 'visible';
	});
};

//Input required validation
jQuery.fn.inputRequired = function() {
	return this.prop('required', true);
};

jQuery.fn.inputNotRequired = function() {
	return this.removeProp('required');
};

jQuery.fn.inputRequiredToggle = function() {
	var attr = this.attr('required');
	if (attr == undefined) 
		this.inputRequired();
	else
		this.inputNotRequired();
};

//Text decoration for label
jQuery.fn.crossOut = function() {
	return this.css('textDecoration', 'line-through');
};

jQuery.fn.disableDecoration = function() {
	return this.css('textDecoration', 'none');
};

jQuery.fn.decorationToggle = function() {
	return this.css('textDecoration', function(i, textDecoration) {
		// Use indexOf instead of '==' as in Chrome the text decoration has
		// several more values (solid rgb...)
		return (textDecoration.indexOf('line-through') > -1) ? ''
				: 'line-through';
	});
};

//Checkbox toggle
jQuery.fn.doCheck = function() {
	return this.prop('checked', true);
};

jQuery.fn.undoCheck = function() {
	return this.prop('checked', false);
};

jQuery.fn.checkboxToggle = function() {
	return this.prop('checked', function(i, checkedStatus) {
		return (checkedStatus == true) ? false : true;
	});
};



/************************************************************************************************************/
