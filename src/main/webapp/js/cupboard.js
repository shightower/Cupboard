var theme = 'energyblue';

$(document).ready(function () {		
        // Create a jqxMenu and set its width and height.
        $("#jqxMenu").jqxMenu({ 
        	width: '100%',
        	height: '30px',
        	theme: 'darkblue',
			minimizeWidth: null});
			
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
			autoCenter	: true
		});
		
});