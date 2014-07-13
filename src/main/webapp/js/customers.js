var ALL_CUST_URL = 'rest/customer/all';
var SEARCH_BY_NAME_URL = 'rest/customer/search/name';
var EDIT_URL = 'rest/customer/update';

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
				{ name: 'street', type: 'string' },
				{ name: 'city', type: 'string' },
				{ name: 'state', type: 'string' },
				{ name: 'zip', type: 'string' },
				{ name: 'phoneNumber', type: 'string' },
				{ name: 'numOfAdults', type: 'string' },
				{ name: 'numOfKids', type: 'string' }
			],
			root: 'data',
			id: 'id',
			url: ALL_CUST_URL
		};
		
		//standard inputs
		var defaultHeight = 25;
		$("#firstName").width(100);
		$("#firstName").height(defaultHeight);
		$("#lastName").width(150);
		$("#lastName").height(defaultHeight);		
		$("#street").width(250);
		$("#street").height(defaultHeight);
		$("#city").width(100);
		$("#city").height(defaultHeight);
		$("#zip").width(75);
		$("#zip").height(defaultHeight);
		$("#phoneNumber").width(75);
		$("#phoneNumber").height(defaultHeight);
		
		$("#numOfAdults").jqxNumberInput({inputMode: 'simeple', spinMode: 'simple', width: 50, height: defaultHeight, min: 0, decimalDigits: 0, spinButtons: true });
		$("#numOfKids").jqxNumberInput({inputMode: 'simeple', spinMode: 'simple', width: 50, height: defaultHeight, min: 0, decimalDigits: 0, spinButtons: true });
		
		
		var dataAdapter = new $.jqx.dataAdapter(source, {
			downloadComplete: function (data, status, xhr) {
			},
			loadComplete: function (data) {
			},
			loadError: function (xhr, status, error) {
				alert('error occurred');
			}
		});
		
		var editRow = -1;
		// initialize jqxGrid
		$("#jqxgrid").jqxGrid({
			width: 1000,
			source: dataAdapter,                
			pageable: true,
			autoheight: true,
			sortable: true,
			altrows: true,
			showsortmenuitems: true,
			theme: 'energyblue',
			columns: [
			  { text: 'Id', datafield: 'id', hidden: true},
			  { text: 'First Name', datafield: 'firstName', filterable: true, align: 'center', width: 120, },
			  { text: 'Last Name', datafield: 'lastName', filterable: true, align: 'center', width: 145 },
			  { text: 'Phone Number', datafield: 'phoneNumber', align: 'center', width: 125 },
			  { text: 'Street', datafield: 'street', align: 'center', width: 200 },
			  { text: 'City', datafield: 'city', align: 'center', width: 125  },
			  { text: 'Zip', datafield: 'zip', align: 'center',  width: 60, cellformat: 'n', cellsalign: 'center'  },
			  { text: 'Adults', datafield: 'numOfAdults', align: 'center', width: 75, cellsalign: 'center'  },
			  { text: 'Kids', datafield: 'numOfKids', align: 'center', width: 75, cellsalign: 'center' },
			  { text: 'Edit', datafield: 'Edit', columntype: 'button', width: 75, cellsrenderer: function()
				{
					return 'Edit';
				}, buttonclick: function(row) {
					editRow = row;
					
                     // get the clicked row's data and initialize the input fields.
                     var dataRecord = $("#jqxgrid").jqxGrid('getrowdata', editRow);
					 $("#id").val(dataRecord.id);
                     $("#firstName").val(dataRecord.firstName);
                     $("#lastName").val(dataRecord.lastName);
                     $("#phoneNumber").val(dataRecord.phoneNumber);
                     $("#street").val(dataRecord.street);
                     $("#city").val(dataRecord.city);
                     $("#zip").val(dataRecord.zip);
                     $("#numOfAdults").jqxNumberInput({ decimal: dataRecord.numOfAdults });
                     $("#numOfKids").jqxNumberInput({ decimal: dataRecord.numOfKids });
                     // show the popup window.
                     $("#popupWindow").jqxWindow('open');
					 
				}
			  }
			]
		});
		
		$('#popupWindow').jqxWindow({
			width: 400,
			height: 400,
			resizable: false,
			isModal: true,
			autoOpen: false,
			cancelButton: $('#cancelButton'),
			animationType: 'combined',
			theme: theme
		});
		
		$('#popupWindow').on('open', function() {
			$('#firstName').jqxInput('selectAll');
		});
		
		$('#cancelButton').jqxButton({theme: theme});
		$('#saveButton').jqxButton({theme: theme});
		
		$('#saveButton').click(function() {			
			var params = '';
			params += 'id=' + $('#id').val() + '&';
			params += 'firstName=' + $('#firstName').val() + '&';
			params += 'lastName=' + $('#lastName').val() + '&';
			params += 'phoneNumber=' + $('#phoneNumber').val() + '&';
			params += 'street=' + $('#street').val() + '&';
			params += 'city=' + $('#city').val() + '&';
			params += 'zip=' + $('#zip').val() + '&';
			params += 'numOfAdults=' + $('#numOfAdults').val() + '&';
			params += 'numOfKids=' + $('#numOfKids').val();
			
			//send update request
			$.ajax({
				type: 'GET',
				url: 'rest/customer/update',
				contentType: 'text/plain',
				data: params,
				success: function(data, status) {
					var n = noty({
						layout: 'center',
						type: 'success', 
						text: '<h3>Update Applied Successfully</h3>',
						timeout: 750,
						callback: {
							afterClose: function() {
								$('#popupWindow').jqxWindow('close');
								
								//refresh page, and force manual pull of new data
								location.reload(true);
							}
						}
					});
				},
				error: function(xhr, status) {
					var n = noty({
						layout: 'center',
						type: 'error', 
						text: '<h3>Unable to Update Customer</h3>',
						timeout: 5000
					});
				}
			});
		});
		
});