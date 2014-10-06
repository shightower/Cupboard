var TEFAP_REPORT_URL = 'rest/orders/report/tefap';
var BCC_MEMEBER_CHART_TITLE = "BCC Attendee Chart";
var BCC_SERVICE_CHART_TITLE = "BCC Service Chart";
var ETHNICITY_CHART_TITLE = "Ethnicity Chart";
var PERSON_COMPOSITION_CHART_TITLE = "Age Group Chart";

//Report Summary Table Titles
var TOTAL_FAMILIES_TITLE = 'Total Families Served: ';
var TOTAL_POUNDS_TITLE = 'Total Weight: ';
var TOTAL_BCC_ATTENDEES_TITLE = 'Total BCC Members Served: ';
var TOTAL_NONBCC_ATTENDEES_TITLE = 'Total Non-BCC Members Served:';
var TOTAL_ADULTS_TITLE = 'Total Adults Served: ';
var TOTAL_KIDS_TITLE = 'Total Kids Served: ';
var TOTAL_TEFAP_COUNT_TITLE = 'Total TEFAP Count: ';


$(document).ready(function() {
	//hide reports section on initial load
	$('#reportSummaryDiv').hide();
	
	//start date Jqx
	$("#startDateSelection").jqxDateTimeInput({
		width: '250px',
		height: '25px',
		formatString: 'dddd MMM dd, yyyy'
	});
	
	//end date Jqx
	$("#endDateSelection").jqxDateTimeInput({
		width: '250px',
		height: '25px',
		formatString: 'dddd MMM dd, yyyy'
	});
	
	//generate button
	$("#generateTefapReportButton").jqxButton({
		width: '250',
		theme: theme
	});
	
	//action taken when generate button clicked
	$("#generateTefapReportButton").on('click', function () {
		var params = validateDates();
		
		if(params != null) {
			generateTefapReports(params);
		}
	});	
});


function generateTefapReports(params) {				
	var serviceData;
	var ethnicityData;
	var memberData = new Array();
	var familyData = new Array();
	var paramStr = 'startDate=' + params.startDate + '&';
	paramStr += 'endDate=' + params.endDate;
	
	$.ajax({
		type: 'GET',
		url: TEFAP_REPORT_URL,
		contentType: 'text/plain',
		data: paramStr,
		success: function(results, status) {
			//show div containing reports summary
			$('#reportSummaryDiv').show();
			var data = results.data[0];
			$('#totalFamilies').html(TOTAL_FAMILIES_TITLE + data.totalFamilies);
			$('#totalWeight').html(TOTAL_POUNDS_TITLE + data.totalPounds + ' lbs');
			$('#totalAdults').html(TOTAL_ADULTS_TITLE + data.totalAdults);
			$('#totalKids').html(TOTAL_FAMILIES_TITLE + data.totalKids);
			$('#totalBccAttendees').html(TOTAL_BCC_ATTENDEES_TITLE + data.totalBccAttendees);
			$('#totalNonBccAttendees').html(TOTAL_NONBCC_ATTENDEES_TITLE + data.totalNonBccAttendees);
			$('#totalTefapCount').html(TOTAL_TEFAP_COUNT_TITLE + data.totalTefapCount);
			
		},
		error: function(xhr, status) {
			alert('failure. \n' + xhr);
		}
	});
}

function drawChart(title, dataSource, displayField, chart) {
	// prepare jqxChart settings
	var settings = {
		title: title,
		description: '',
		backgroundImage: 'images/clear_background.jpg',
		enableAnimations: true,
		showLegend: true,
		showBorderLine: false,
		titlePadding: {
			left: 0,
			top: 0,
			right: 0,
			bottom: 10
		},
		source: dataSource,
		colorScheme: 'scheme04',
		seriesGroups: [{
			type: 'pie',
			showLabels: true,
			series:
				[{ 
					dataField: 'total',
					displayText: displayField,
					initialAngle: 45,
					radius: 130,
					centerOffset: 0,
					formatFunction: function (value) {
						if (isNaN(value))
							return value;
							
							if(value == 1) {
								return value + ' Person';
							} else {
								return value + ' People';
							}
					},
				}]
		}]
	};
	
	// setup the chart
	$(chart).jqxChart(settings);
}