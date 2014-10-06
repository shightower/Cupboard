function validateDates() {
	var startDate = $('#startDateSelection').jqxDateTimeInput('getDate');
	var endDate = $('#endDateSelection').jqxDateTimeInput('getDate');
	
	//set hours, minutes, seconds to beginning of selected day
	startDate.setHours(0);
	startDate.setMinutes(0);
	startDate.setSeconds(0);
	startDate.setMilliseconds(0);
	
	//set hours, minutes, seconds to very end of selected day
	endDate.setHours(23);
	endDate.setMinutes(59);
	endDate.setSeconds(59);
	endDate.setMilliseconds(999);
	
	//object to hold REST parameters
	var params = new Object();
	
	if(startDate == null) {
		errorNotification('Please enter a valid starting date');
		params = null;
	} else if(endDate == null) {
		errorNotification('Please enter a valid ending date');
		params = null;
	} else if(startDate > endDate) {
		errorNotification('Ending date must come after Start date');
		params = null;
	} else {
		params.startDate = startDate;
		params.endDate = endDate;
	}
	
	return params;
}

function errorNotification(msg) {
	var n = noty({
			layout: 'center',
			type: 'error', 
			text: '<h3>' + msg + '</h3>',
			timeout: 2000
		});
}