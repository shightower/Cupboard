var REGULAR_REPORT_URL = 'rest/orders/report/regular';
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
	$("#generateReportButton").jqxButton({
		width: '200',
		theme: theme
	});
	
	//action taken when generate button clicked
	$("#generateReportButton").on('click', function () {
		var params = validateDates();
		
		if(params != null) {
			generateReports(params);
		}
	}); 
});

function generateReports(params) {				
	var serviceData;
	var ethnicityData;
	var memberData = new Array();
	var familyData = new Array();
	var paramStr = 'startDate=' + params.startDate + '&';
	paramStr += 'endDate=' + params.endDate;
	
	$.ajax({
		type: 'GET',
		url: REGULAR_REPORT_URL,
		contentType: 'text/plain',
		data: paramStr,
		success: function(results, status) {
			//show div containing reports summary
			$('#reportSummaryDiv').show();
			var data = results.data[0];
			$('#totalFamilies').html(TOTAL_FAMILIES_TITLE + data.totalFamilies);
			$('#totalWeight').html(TOTAL_POUNDS_TITLE + data.totalPounds + 'LBs');
			$('#totalAdults').html(TOTAL_ADULTS_TITLE + data.totalAdults);
			$('#totalKids').html(TOTAL_FAMILIES_TITLE + data.totalKids);
			$('#totalBccAttendees').html(TOTAL_BCC_ATTENDEES_TITLE + data.totalBccAttendees);
			$('#totalNonBccAttendees').html(TOTAL_NONBCC_ATTENDEES_TITLE + data.totalNonBccAttendees);
			
			/**
			//data for Adults/Kids Chart
			var adultsObj = new Object();
			adultsObj.person="Adults";
			adultsObj.total=data.totalAdults;
			var kidsObj = new Object();
			kidsObj.person="Kids";
			kidsObj.total=data.totalKids;
			familyData[0] = adultsObj;
			familyData[1] = kidsObj;
			drawChart(PERSON_COMPOSITION_CHART_TITLE, familyData, "person", '#personsChart');
			
			//data for Is BCC Attendee Chart
			var attendeeObj = new Object();
			attendeeObj.value = "Yes";
			attendeeObj.total = data.totalBccAttendees;
			var nonAttendeeObj = new Object();
			nonAttendeeObj.value = "No";
			nonAttendeeObj.total = data.totalNonBccAttendees;
			memberData[0] = attendeeObj;
			memberData[1] = nonAttendeeObj;
			drawChart(BCC_MEMEBER_CHART_TITLE, memberData, "value", '#attendeeChart');
			
			//data for Ethnicity Chart
			ethnicityData = data.raceReports;
			drawChart(ETHNICITY_CHART_TITLE, ethnicityData, "ethnicity", '#bccMemberChart');
			
			//data for BCC Service Chart
			serviceData = data.bccServiceReports;
			drawChart(BCC_SERVICE_CHART_TITLE, serviceData, "service", '#bccServiceChart');
			*/
			
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