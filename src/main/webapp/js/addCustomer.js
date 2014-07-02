$(document).ready(function () {	
								
		$('#addCustButton').jqxButton({
			width: 150,
			theme: 'energyblue'
		});
		
		$('#addCustButton').click(function(event) {
			var params = $('#addCustForm').serialize();
			$.ajax({
				type: 'GET',
				url: 'rest/customer/add',
				contentType: 'text/plain',
				data: params,
				success: function(data, status) {
					var n = noty({
						layout: 'centerLeft',
						type: 'success', 
						text: '<h3>Added Customer</h3>',
						timeout: 3000
					});						
					
					$('#addCustForm').find('input[type=text]').val('');
				},
				error: function(xhr, status) {
					alert('failure. \n' + xhr);
				}
			});
			
			
		});
});