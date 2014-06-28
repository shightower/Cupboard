$(document).ready(function () {
		
        // Create a jqxMenu and set its width and height.
        $("#jqxMenu").jqxMenu({ 
        	width: '100%',
        	height: '30px',
        	theme: 'darkblue'
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
        
        // Round the banner
        $('#content').corner('20px');
						
		$('#addCustSubmit').jqxButton({
			width: 150,
			theme: 'energyblue'
		});
		
		//listeners for changes to Number of Kids/Adults that 
		//affect the number of bags
		$("#numAdults").keyup(function() {		
			updateNumberOfBags();
		});
		
		$("#numKids").keyup(function() {		
			updateNumberOfBags();
		});
		
		$("#numAdults").mouseup(function() {		
			updateNumberOfBags();
		});
		
		$("#numKids").mouseup(function() {		
			updateNumberOfBags();
		});
		
		$('#addCustForm').submit(function(event) {
			var params = $(this).serializeArray();
			var x = 0;
			
			
		});
});

function updateNumberOfBags() {
	var kids = $("#numKids").val();
	var adults = $("#numAdults").val();
	var bags = 1;
	
	if(kids < 2 && adults < 2) {
		bags = 1;
	} else if(kids >= 2 || adults >= 2) {
		bags = 2;
	}
		
	var field = $("#numBags");
	field.val(bags);
}