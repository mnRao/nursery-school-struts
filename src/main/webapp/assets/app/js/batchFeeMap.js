$(document).ready(function() {
	initializeCheckboxes();
});

function initializeCheckboxes() {
	// Initialize: tick whose has non-empty amount value
	$(':input[type="text"]').each(function() {
		var textFieldId = $(this).prop("id");
		var order = extractNumber(textFieldId);

		if ($(this).val() == '') {
			untickCheckbox(order);
			enableTextDecoration(order);
			disableInputVisibility(order);
			disableInputRequired(order);
		} else {
			tickCheckbox(order);
			disableTextDecoration(order);
			enableInputVisibility(order);
			enableInputRequired(order);
		}
	});
	// Set state for check-all checkbox according to other checkboxes
	manageCheckAll();
	
	//On click each checkbox
	$('.selectedElement').bind('click', function() {
		var cbId = $(this).prop("id");
		var order = extractNumber(cbId);

		toogleTextDecoration(order);
		toogleInputVisibility(order);
		toggleInputRequired(order);

		// Set checked or not checked for toggle-all button
		manageCheckAll();
	});

	$('.selectedElement').bind('tickAll', function() {
		var cbId = $(this).prop("id");
		var order = extractNumber(cbId);

		disableTextDecoration(order);
		enableInputVisibility(order);
		enableInputRequired(order);
	});

	$('.selectedElement').bind('untickAll', function() {
		var cbId = $(this).prop("id");
		var order = extractNumber(cbId);

		enableTextDecoration(order);
		disableInputVisibility(order);
		disableInputRequired(order);
	});
}







