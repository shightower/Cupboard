var REGULAR_REPORT_URL = 'rest/orders/report/regular';

$(document).ready(function() {
				
	var serviceData;
	var ethnicityData;
	var memberData = new Object();
	var familyData = new Object();
	
	$.ajax({
				type: 'GET',
				url: REGULAR_REPORT_URL,
				contentType: 'text/plain',
				//data: params,
				success: function(results, status) {
					var data = results.data[0];
					
					//data for Adults/Kids Chart
					familyData.Adults = data.totalAdults;
					familyData.Kids = data.totalKids;
					
					//data for Is BCC Attendee Chart
					memberData.BccAttendees = data.totalBccAttendees;
					memberData.NonBccAttendees = data.totalNonBccAttendees;
					
					//data for Ethnicity Chart
					ethnicityData = data.raceReports;
					drawEthnicityChart(ethnicityData);
					
					//data for BCC Service Chart
					serviceData = data.serviceReports;
				},
				error: function(xhr, status) {
					alert('failure. \n' + xhr);
				}
			});
		
    
});

function drawEthnicityChart(ethnicityData) {
	// prepare jqxChart settings
	var settings = {
		title: "BCC Member Chart",
		enableAnimations: true,
		showLegend: true,
		showBorderLine: true,
		legendLayout: { left: 500, top: 160, width: 300, height: 200, flow: 'horizontal' },
		padding: { left: 5, top: 5, right: 5, bottom: 5 },
		titlePadding: { left: 0, top: 0, right: 0, bottom: 10 },
		source: ethnicityData,
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
								displayText: 'race',
								labelRadius: 170,
								initialAngle: 15,
								radius: 145,
								centerOffset: 0,
								formatFunction: function (value) {
									if (isNaN(value))
										return value;
									return parseFloat(value) + '%';
								},
							}
						]
				}
			]
	};
	
	// setup the chart
	$('#bccMemberChart').jqxChart(settings);
}