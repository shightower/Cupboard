var ALL_CUST_URL = 'rest/customer/all';
var SEARCH_BY_NAME_URL = 'rest/customer/search/name';
var ADD_ORDER_URL = 'rest/orders/new';
var ADD_TEFAP_ORDER_URL = 'rest/orders/tefap/new';

var lastNameFilterGroup = new $.jqx.filter();
var firstNameFilterGroup = new $.jqx.filter();
var filterCondition = 'contains';
var or_filter_operator = 1;

$(document).ready(function () {
		//initially hide the clear filter div
		$('#clearSearchDiv').hide();
		
		//Round Search
		$('#searchBox').corner('5px');
		
		$('#searchButton').jqxButton({
			width: 100,
			theme: theme
		});

		$('#searchButton').click(function() {
			var searchValue = $('#searchBox').val();
			var firstName = '';
			var lastName = '';
			
			if(searchValue != null && searchValue != "") {
				var names = searchValue.split(' ');
				
				if(names.length == 1) {
					lastName = names[0];
					
					var lastNameFilter = lastNameFilterGroup.createfilter('stringfilter', lastName, filterCondition);
					lastNameFilterGroup.addfilter(or_filter_operator, lastNameFilter);
					$('#addOrderGrid').jqxGrid('addfilter', 'lastName', lastNameFilterGroup);
					$('#addOrderGrid').jqxGrid('applyFilters');
				} else {
					firstName = names[0];
					lastName = names[1];

					var lastNameFilter = lastNameFilterGroup.createfilter('stringfilter', lastName, filterCondition);
					lastNameFilterGroup.addfilter(or_filter_operator, lastNameFilter);
					
					var firstNameFilter = firstNameFilterGroup.createfilter('stringfilter', firstName, filterCondition);
					firstNameFilterGroup.addfilter(or_filter_operator, firstNameFilter);
					
					$('#addOrderGrid').jqxGrid('addfilter', 'firstName', firstNameFilterGroup);
					$('#addOrderGrid').jqxGrid('applyFilters');
				}
				
				//show the clear filter option
				$('#clearSearchDiv').show();								
			} else {
				var n = noty({
						layout: 'center',
						type: 'error', 
						text: '<h3>Provide a Search Value</h3>',
						timeout: 2500
					});
			}
		});
		
		$('#clearButton').jqxButton({
			width: 100,
			theme: theme
		});
		
		$('#clearButton').click(function() {
			$("#addOrderGrid").jqxGrid('removefilter', 'firstName');
			$("#addOrderGrid").jqxGrid('removefilter', 'lastName');
			$("#addOrderGrid").jqxGrid('applyfilters');
			$('#clearSearchDiv').hide();
		});
		
		$('#searchBox').jqxInput({
			placeHolder: 'Search',
			height: 25,
			width: '60%',
			minLength: 1
		});
		
		var source = {
			datatype: "json",
			datafields: [
				{ name: 'id', type: 'int'},
				{ name: 'firstName', type: 'string' },
				{ name: 'lastName', type: 'int' },
				{ name: 'phoneNumber', type: 'string' },
				{ name: 'nextAvailableDate', type: 'date' }
			],
			root: 'data',
			id: 'id',
			url: ALL_CUST_URL
		};
		
		var dataAdapter = new $.jqx.dataAdapter(source, {
			downloadComplete: function (data, status, xhr) {
			},
			loadComplete: function (data) {
			},
			loadError: function (xhr, status, error) {
				alert('error occurred');
			}
		});
		
		var selectedCustomer = -1;
		// initialize jqxGrid
		$("#addOrderGrid").jqxGrid({
			width: 815,
			source: dataAdapter,                
			pageable: true,
			autoheight: true,
			sortable: true,
			altrows: true,
			theme: theme,
			columns: [
			  { text: 'Order Number', datafield: 'id', hidden: true},
			  { text: 'First Name', datafield: 'firstName', filterable: true, align: 'center', width: 120, },
			  { text: 'Last Name', datafield: 'lastName', filterable: true, align: 'center', width: 145 },
			  { text: 'Phone Number', datafield: 'phoneNumber', align: 'center', width: 125 },
			  { text: 'Next Order Date', datafield: 'nextAvailableDate', align: 'center', width: 150, cellsalign: 'center', cellsformat: 'ddd M/dd/y'},
			  { text: 'New Order', datafield: 'New Order', columntype: 'button', width: 125, cellsrenderer: function()
				{
					return 'New Order';
				}, buttonclick: function(row) {					
					// prompt user for confirmation before adding new order
					var r = confirm("Create new order?");
					if (r == true) {
						selectedCustomer = row;
					
						 // get the clicked row's data and initialize the input fields.
						 var dataRecord = $("#addOrderGrid").jqxGrid('getrowdata', selectedCustomer);
						 
						 //ajax call to create new order						 
						 if(checkNextAvailableDate(dataRecord.nextAvailableDate)) {
							//ajax call to create new order
							submitNewOrder(dataRecord.id, ADD_ORDER_URL);
						 } else {
							selectedCustomer = -1;
						 }
					} else {
						selectedCustomer = -1;
					}				 
				}
			  },
			  { text: 'New Tefap Order', datafield: 'New Tefap Order', columntype: 'button', width: 150, cellsrenderer: function()
				{
					return 'New Tefap Order';
				}, buttonclick: function(row) {				
					// prompt user for confirmation before adding new order
					var r = confirm("Create new TEFAP order?");
					if (r == true) {
						selectedCustomer = row;
					
						 // get the clicked row's data and initialize the input fields.
						 var dataRecord = $("#addOrderGrid").jqxGrid('getrowdata', selectedCustomer);
						 
						 if(checkNextAvailableDate(dataRecord.nextAvailableDate)) {
							//ajax call to create new order
							submitNewOrder(dataRecord.id, ADD_TEFAP_ORDER_URL);
						 } else {
							selectedCustomer = -1;
						 }
					} else {
						selectedCustomer = -1;
					}				 
				}
			  }
			]
		});				
});

function checkNextAvailableDate(nextAvailableDate) {
	//check to make sure the customer is not attempting an order before their
	//next available date
	if(nextAvailableDate == null) {
		return true;
	} else {
		//zero out minutes and seconds for dates as we only care about the month and day
		nextAvailableDate = compactDate(nextAvailableDate);
		var currentDate = compactDate(new Date());
		
		if(nextAvailableDate > currentDate) {
			var r = confirm("It's too soon for customer's next available order.\nWould you like to override this alert and continue anyways?");
			if(r == true) {
				return true;
			} else {
				return false;
			}
		}
	}
}

function submitNewOrder(customerId, path) {
	
	var params = 'customerId=';
	params += customerId;
	
	//send update request
	$.ajax({
		type: 'GET',
		url: path,
		contentType: 'text/plain',
		data: params,
		success: function(data, status) {
			var n = noty({
				layout: 'center',
				type: 'success', 
				text: '<h3>New Order Adding to Pending List</h3>',
				timeout: 1250
			});
		},
		error: function(xhr, status) {
			var text = '<h3>Unable to Update Customer</h3>';
			text += 'Reason: ';
			text += xhr.responseText;
			
			var n = noty({
				layout: 'center',
				type: 'error', 
				text: text,
				timeout: 5000
			});
		}
	});
}

function compactDate(date) {
	date.setHours(0);
	date.setMinutes(0);
	date.setSeconds(0);
	date.setMilliseconds(0);
	return date;
}