$(document).ready(function () {		
        // Create a jqxMenu and set its width and height.
        $("#jqxMenu").jqxMenu({ 
        	width: '100%',
        	height: '30px',
        	theme: 'darkblue' });
			
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
		
        // Round the banner
        $('#content').corner('20px');
		
		$("#addCustFrame").fancybox({
			title		: 'Customer Information',
			maxWidth	: 800,
			maxHeight	: 600,
			fitToView	: false,
			width		: '50%',
			height		: '75%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'elastic',
			closeEffect	: 'fade',
			autoCenter	: true,
		});
		
		/**
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
		*/
});