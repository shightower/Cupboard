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
			theme: 'energyblue'
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
					$('#jqxgrid').jqxGrid('addfilter', 'lastName', lastNameFilterGroup);
					$('#jqxgrid').jqxGrid('applyFilters');
				} else {
					firstName = names[0];
					lastName = names[1];

					var lastNameFilter = lastNameFilterGroup.createfilter('stringfilter', lastName, filterCondition);
					lastNameFilterGroup.addfilter(or_filter_operator, lastNameFilter);
					
					var firstNameFilter = firstNameFilterGroup.createfilter('stringfilter', firstName, filterCondition);
					firstNameFilterGroup.addfilter(or_filter_operator, firstNameFilter);
					
					$('#jqxgrid').jqxGrid('addfilter', 'firstName', firstNameFilterGroup);
					$('#jqxgrid').jqxGrid('applyFilters');
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
			theme: 'energyblue'
		});
		
		$('#clearButton').click(function() {
			$("#jqxgrid").jqxGrid('removefilter', 'firstName');
			$("#jqxgrid").jqxGrid('removefilter', 'lastName');
			$("#jqxgrid").jqxGrid('applyfilters');
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
				{ name: 'numOfAdults', type: 'string' },
				{ name: 'numOfKids', type: 'string' }
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
		$("#jqxgrid").jqxGrid({
			width: 815,
			source: dataAdapter,                
			pageable: true,
			autoheight: true,
			sortable: true,
			altrows: true,
			theme: theme,
			columns: [
			  { text: 'Id', datafield: 'id', hidden: true},
			  { text: 'First Name', datafield: 'firstName', filterable: true, align: 'center', width: 120, },
			  { text: 'Last Name', datafield: 'lastName', filterable: true, align: 'center', width: 145 },
			  { text: 'Phone Number', datafield: 'phoneNumber', align: 'center', width: 125 },
			  { text: 'Adults', datafield: 'numOfAdults', align: 'center', width: 75, cellsalign: 'center'  },
			  { text: 'Kids', datafield: 'numOfKids', align: 'center', width: 75, cellsalign: 'center' },
			  { text: 'New Order', datafield: 'New Order', columntype: 'button', width: 125, cellsrenderer: function()
				{
					return 'New Order';
				}, buttonclick: function(row) {					
					// prompt user for confirmation before adding new order
					var r = confirm("Create new order?");
					if (r == true) {
						selectedCustomer = row;
					
						 // get the clicked row's data and initialize the input fields.
						 var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', selectedCustomer);
						 
						 //ajax call to create new order
						 submitNewOrder(dataRecord.id, ADD_ORDER_URL);
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
						 var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', selectedCustomer);
						 
						 //ajax call to create new order
						 submitNewOrder(dataRecord.id, ADD_TEFAP_ORDER_URL);
					} else {
						selectedCustomer = -1;
					}				 
				}
			  }
			]
		});				
});

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