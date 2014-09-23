var REGULAR_REPORT_URL = 'rest/orders/report/regular';
var BCC_MEMEBER_CHART_TITLE = "BCC Member Chart";
var BCC_SERVICE_CHART_TITLE = "BCC Service Chart";
var ETHNICITY_CHART_TITLE = "Ethnicity Chart";
var PERSON_COMPOSITION_CHART_TITLE = "Persons Chart";

$(document).ready(function() {
				
	var serviceData;
	var ethnicityData;
	var memberData = new Array();
	var familyData = new Array();
	
	$.ajax({
				type: 'GET',
				url: REGULAR_REPORT_URL,
				contentType: 'text/plain',
				//data: params,
				success: function(results, status) {
					var data = results.data[0];
					
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
				},
				error: function(xhr, status) {
					alert('failure. \n' + xhr);
				}
			});
		
    
});

function drawChart(title, dataSource, displayField, chart) {
	// prepare jqxChart settings
	var settings = {
		title: title,
		enableAnimations: true,
		showLegend: true,
		showBorderLine: false,
		padding: { left: 5, top: 5, right: 5, bottom: 5 },
		titlePadding: { left: 0, top: 0, right: 0, bottom: 10 },
		source: dataSource,
		colorScheme: 'scheme04',
		seriesGroups:
			[
				{
					type: 'pie',
					showLabels: true,
					series:
						[
							{ 
								dataField: 'total',
								displayText: displayField,
								labelRadius: 170,
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
							}
						]
				}
			]
	};
	
	// setup the chart
	$(chart).jqxChart(settings);
}