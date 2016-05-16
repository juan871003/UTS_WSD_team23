function addResponse() {
	var form = $("#addResponseForm");
	var name = $("#input_name");
	var checkboxes = $("input[name='checkbox_response']");
	
	if(name.val().trim().length > 0 ){
		form.append(name);
		form.append(checkboxes);
		
		form.submit();
	} else {
		$("#required-name-message").css("display","inherit");
	}
}

function validateAddResponse() {
	var name = $("#input_name");
	return (name.val().trim().length > 0 );
}