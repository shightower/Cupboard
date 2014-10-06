$(document).ready(function () {	
								
		$('#addCustButton').jqxButton({
			width: 150,
			theme: 'ui-sunny'
		});
		
		$('#attendee').change(function() {
			var YES = '1';
			var NO = '0';
			var htmlStr = '<label for="service" >Service:</label><select name="service"><option value="8">8</option><option value="10">10</option><option value="10:30">10:30</option><option value="12">12</option></select><br/>';
			
			if(this.value === YES) {
				$('#serviceDiv').html(htmlStr);
			} else {
				$('#serviceDiv').html('N/A');
			}
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
						timeout: 1500
					});						
					
					$('#addCustForm').find('input[type=text]').val('');
					$('#state').val('Maryland');
					$('#addCustForm').find('input[type=number]').val(0);
				},
				error: function(xhr, status) {
					alert('failure. \n' + xhr);
				}
			});			
		});
});