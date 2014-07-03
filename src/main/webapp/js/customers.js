$(document).ready(function () {	
		//Round Search
		$('#search').corner('5px');
		
		$('#searchButton').jqxButton({
			width: 100,
			theme: 'energyblue'
		});		
		
		$('#search').jqxInput({
			placeHolder: 'Search',
			height: 25,
			width: '60%',
			minLength: 1
		});
		
		var source = {
			datatype: "json",
			datafields: [
				{ name: 'firstName', type: 'string' },
				{ name: 'lastName', type: 'int' },
				{ name: 'street', type: 'string' },
				{ name: 'city', type: 'string' },,
				{ name: 'state', type: 'string' },
				{ name: 'zip', type: 'string' },
				{ name: 'phoneNumber', type: 'string' },
				{ name: 'numOfAdults', type: 'string' },
				{ name: 'numOfKids', type: 'string' }
			],
			root: 'data',
			id: 'id',
			url: 'rest/customer/all'
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
		
		// initialize jqxGrid
		$("#jqxgrid").jqxGrid({
			width: 925,
			source: dataAdapter,                
			pageable: true,
			autoheight: true,
			sortable: true,
			altrows: true,
			enabletooltips: true,
			editable: true,
			selectionmode: 'singlerow',
			theme: 'energyblue',
			columns: [
			  { text: 'First Name', datafield: 'firstName', align: 'center', width: 100 },
			  { text: 'Last Name', datafield: 'lastName', align: 'center', width: 125 },
			  { text: 'Phone Number', datafield: 'phoneNumber', align: 'center', width: 100 },
			  { text: 'Street', datafield: 'street', align: 'center', width: 200 },
			  { text: 'City', datafield: 'city', align: 'center', width: 100  },
			  { text: 'Zip', datafield: 'zip', align: 'center',  width: 50  },
			  { text: 'Num of Adults', datafield: 'numOfAdults', align: 'center', width: 125  },
			  { text: 'Num Of Kids', datafield: 'numOfKids', align: 'center', width: 125  }
			]
		});
});