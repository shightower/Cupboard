var theme = 'ui-sunny';

$(document).ready(function () {		
        // Create a jqxMenu and set its width and height.
        $("#menuBar").jqxMenu({ 
        	width: '100%',
        	height: '30px',
        	theme: theme,
			minimizeWidth: null});
			
		// Center menuBar            
		var centerItems = function () {
			var firstItem = $($("#menuBar ul:first").children()[0]);
			firstItem.css('margin-left', 0);
			var width = 0;
			var borderOffset = 2;
			$.each($("#menuBar ul:first").children(), function () {
				width += $(this).outerWidth(true) + borderOffset;
			});
			var menuWidth = $("#menuBar").outerWidth();
			firstItem.css('margin-left', (menuWidth / 2 ) - (width / 2));
		}
        
		centerItems();
		$(window).resize(function () {
			centerItems();
		});
		
        // Round the banner
        $('#content').corner('20px');
		
		$('#logo').corner('top 20px');
		
		$("#addCustFrame").fancybox({
			title		: 'Customer Information',
			maxWidth	: 450,
			fitToView	: false,
			width		: '40%',
			height		: '75%',
			autoSize	: false,
			closeClick	: false,
			openEffect	: 'elastic',
			closeEffect	: 'fade',
			autoCenter	: true
		});
		
});