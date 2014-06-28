$(document).ready(function () {
		
        // Create a jqxMenu and set its width and height.
        $("#jqxMenu").jqxMenu({ 
        	width: '100%',
        	height: '30px',
        	theme: 'darkblue' });
        
        // Round the banner
        $('#content').corner('20px');
		
		//Round Search
		$('#search').corner('10px');
		
		$('#searchButton').jqxButton({
			width: 100,
			theme: 'energyblue'
		});
		
		$('#addCustSubmit').jqxButton({
			width: 150,
			theme: 'energyblue'
		});
		
		$('#search').jqxInput({
			placeHolder: 'Search',
			height: 25,
			width: '60%',
			minLength: 1
		});
		
		// Center jqxMenu            
		var centerItems = function () {
			var firstItem = $($("#jqxMenu ul:first").children()[0]);
			firstItem.css('margin-left', 0);
			var width = 0;
			var borderOffset = 2;
			$.each($("#jqxMenu ul:first").children(), function () {
				width += $(this).outerWidth(true) + borderOffset;
			});
			var menuWidth = $("#jqxMenu").outerWidth();
			firstItem.css('margin-left', (menuWidth / 2 ) - (width / 2));
		}
		
		centerItems();
		$(window).resize(function () {
			centerItems();
		});
		
		// prepare the data
		var data = new Array();
		var firstNames =
		[
			"Andrew", "Nancy", "Shelley", "Regina", "Yoshi", "Antoni", "Mayumi", "Ian", "Peter", "Lars", "Petra", "Martin", "Sven", "Elio", "Beate", "Cheryl", "Michael", "Guylene"
		];
		
		var lastNames =
		[
			"Fuller", "Davolio", "Burke", "Murphy", "Nagase", "Saavedra", "Ohno", "Devling", "Wilson", "Peterson", "Winkler", "Bein", "Petersen", "Rossi", "Vileid", "Saylor", "Bjorn", "Nodier"
		];
		
		var address =
		[
			"Black Tea", "Green Tea", "Caffe Espresso", "Doubleshot Espresso", "Caffe Latte", "White Chocolate Mocha", "Cramel Latte", "Caffe Americano", "Cappuccino", "Espresso Truffle", "Espresso con Panna", "Peppermint Mocha Twist"
		];
		
		var numbers =
		[
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 16, 18
		];
		
		for (var i = 0; i < 200; i++) {
			var row = {};
			var addressindex = Math.floor(Math.random() * address.length);
			var kidsindex = Math.floor(Math.random() * numbers.length);
			var adultsindex = Math.floor(Math.random() * numbers.length);
			var bagsindex = Math.floor(Math.random() * numbers.length);
			row["firstname"] = firstNames[Math.floor(Math.random() * firstNames.length)];
			row["lastname"] = lastNames[Math.floor(Math.random() * lastNames.length)];
			row["address"] = address[addressindex];
			row["numOfKids"] = numbers[kidsindex];
			row["numOfAdults"] = numbers[adultsindex];
			row["numOfBags"] = numbers[bagsindex];
			row["isTefap"] = true;
			data[i] = row;
		}
		
		var source =
		{
			localdata: data,
			datatype: "array"
		};
		
		var dataAdapter = new $.jqx.dataAdapter(source, {
			loadComplete: function (data) { },
			loadError: function (xhr, status, error) { }      
		});
		
		$("#jqxgrid").jqxGrid({
			width: 700,
			source: dataAdapter,
			sortable: true,
			filterable: true,
			pageable: true,
			autoheight: true,
    		autoshowfiltericon: true,
    		editable: true,
    		theme: 'energyblue',
			columns: [
			  { text: 'First Name', datafield: 'firstname', width: 110, editable: false },
			  { text: 'Last Name', datafield: 'lastname', width: 110, editable: false },
			  { text: 'Address', datafield: 'address', width: 200, sortable: false, editable: false },
			  { text: '# of Kids', datafield: 'numOfKids', width: 70, cellsalign: 'right', sortable: false, editable: false },
			  { text: '# of Adults', datafield: 'numOfAdults', width: 80, cellsalign: 'center', sortable: false, editable: false },
			  { text: '# of Bags', datafield: 'numOfBags', width: 70, cellsalign: 'center', sortable: false, editable: false },
			  { text: 'Tefap?', datafield: 'isTefap', threestatecheckbox: false, columntype: 'checkbox', width: 60, cellsaligh: 'center', sortable: false}
			]
		});
});