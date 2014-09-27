var ALL_PENDING_URL = 'rest/orders/pending/all';
var COMPLETE_ORDER_URL = 'rest/orders/pending/complete';
var COMPLETE_TEFAP_URL = 'rest/orders/pending/tefap/complete';
var DELETE_URL = 'rest/orders/pending/remove';
var REFRESH_RATE = 30000;

function refresh() {
	window.location.reload(true);
}

setTimeout(refresh, REFRESH_RATE);

$(document).ready(function () {
		// source of pending orders
		var source = {
			datatype: "json",
			datafields: [
				{ name: 'orderNumber', type: 'int'},
				{ name: 'orderDate', type: 'date' },
				{ name: 'tefapCount', type: 'int' },
				{ name: 'numOfBags', type: 'int' },
				{ name: 'orderWeight', type: 'int' },
				{ name: 'orderType', type: 'string' },
				{ name: 'customerFirstName', type: 'string' },
				{ name: 'customerLastName', type: 'string' },
				{ name: 'tefap', type: 'bool' }
			],
			root: 'data',
			id: 'orderNumber',
			url: ALL_PENDING_URL
		};
		
		//default height for all input fields
		var defaultHeight = 25;
		
		//standard inputs for regular orders
		$("#firstName").width(100);
		$("#firstName").height(defaultHeight);
		$("#lastName").width(150);
		$("#lastName").height(defaultHeight);		
		$("#orderDate").width(150);
		$("#orderDate").height(defaultHeight);	
		$("#orderWeight").jqxNumberInput({inputMode: 'simeple',  width: 75, height: defaultHeight, min: 0, decimalDigits: 0});
		$("#numOfBags").jqxNumberInput({inputMode: 'simeple',  width: 75, height: defaultHeight, min: 0, decimalDigits: 0});
		
		//standard inputs for TEFAP orders
		$("#tefapFirstName").width(100);
		$("#tefapFirstName").height(defaultHeight);
		$("#tefapLastName").width(150);
		$("#tefapLastName").height(defaultHeight);		
		$("#tefapDate").width(150);
		$("#tefapDate").height(defaultHeight);		
		$("#tefapWeight").jqxNumberInput({inputMode: 'simeple', width: 75, height: defaultHeight, min: 0, decimalDigits: 0});
		$("#tefapCount").jqxNumberInput({inputMode: 'simeple', width: 75, height: defaultHeight, min: 0, decimalDigits: 0});
		
		
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
		// initialize pendingOrdersGrid
		$("#pendingOrdersGrid").jqxGrid({
			width: 890,
			source: dataAdapter,                
			pageable: true,
			autoheight: true,
			sortable: true,
			altrows: true,
			showsortmenuitems: true,
			theme: theme,
			columns: [
			  { text: 'Order #', datafield: 'orderNumber', cellsalign: 'center', width: 80},
			  { text: 'First Name', datafield: 'customerFirstName', align: 'center', width: 125},
			  { text: 'Last Name', datafield: 'customerLastName', align: 'center', width: 150},
			  { text: 'Order Date', datafield: 'orderDate', align: 'center', cellsformat: 'ddd M/dd/y hh:mm tt', width: 175},
			  { text: '# Bags', datafield: 'numOfBags', align: 'center', cellsalign: 'center', width: 60},
			  { text: 'Order Type', datafield: 'orderType', align: 'center', cellsalign: 'center', width: 100},
			  { text: 'Complete', datafield: 'Complete', columntype: 'button', align: 'center', width: 100, cellsrenderer: function()
				{
					return 'Complete';
				}, buttonclick: function(rowIndex) {
					completeOrder(rowIndex);
				}
			  },
			  { text: 'Remove', datafield: 'Remove', columntype: 'button', align: 'center', width: 100, cellsrenderer: function()
				{
					return 'Remove';
				}, buttonclick: function(rowIndex) {
					var dataRecord = $("#pendingOrdersGrid").jqxGrid('getrowdata', rowIndex);
					
					// prompt user for confirmation before adding new order
					var r = confirm("Remove Order-" + dataRecord.orderNumber + "?");
					
					if(r == true) {
						deletePendingOrder(dataRecord.orderNumber);
					}
				}
			  }
			]
		});
		
		$('#pendingOrdersGrid').on('rowdoubleclick', function (event)  { 
			rowIndex = event.args.rowindex;
			completeOrder(rowIndex); 
		});
		
		$('#popupOrder').jqxWindow({
			width: 350,
			height: 400,
			resizable: false,
			isModal: true,
			autoOpen: false,
			cancelButton: $('#cancelOrderButton'),
			animationType: 'combined',
			theme: theme
		});
		
		$('#popupTefap').jqxWindow({
			width: 350,
			height: 400,
			resizable: false,
			isModal: true,
			autoOpen: false,
			cancelButton: $('#cancelTefapButton'),
			animationType: 'combined',
			theme: theme
		});
		
		$('#popupOrder').on('open', function() {
			$('#firstName').jqxInput('selectAll');
		});
		
		$('#popupTefap').on('open', function() {
			$('#tefapFirstName').jqxInput('selectAll');
		});
		
		$('#cancelOrderButton').jqxButton({theme: theme});
		$('#completeOrderButton').jqxButton({theme: theme});
		
		$('#cancelTefapButton').jqxButton({theme: theme});
		$('#completeTefapButton').jqxButton({theme: theme});
		
		$('#completeOrderButton').click(function() {			
			var params = '';
			params += 'orderNumber=' + $('#orderNumber').val() + '&';
			params += 'orderDate=' + $('#orderDate').val() + '&';
			params += 'orderWeight=' + $('#orderWeight')[0].value + '&';
			params += 'numOfBags=' + $('#numOfBags')[0].value;
			
			//send update request
			$.ajax({
				type: 'GET',
				url: COMPLETE_ORDER_URL,
				contentType: 'text/plain',
				data: params,
				success: function(data, status) {
					$('#popupOrder').jqxWindow('close');
					clearOrderPopup();
					
					var n = noty({
						layout: 'center',
						type: 'success', 
						text: '<h3>Order Completed!</h3>',
						timeout: 750,
						callback: {
							afterClose: function() {
								
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
						text: '<h3>Unable to Complete Order</h3>',
						timeout: 3000
					});
				}
			});
		});

		$('#completeTefapButton').click(function() {			
			var params = '';
			params += 'orderNumber=' + $('#tefapOrderNum').val() + '&';
			params += 'orderDate=' + $('#tefapDate').val() + '&';
			params += 'orderWeight=' + $('#tefapWeight')[0].value + '&';
			params += 'tefapCount=' + $('#tefapCount')[0].value;
			
			//send update request
			$.ajax({
				type: 'GET',
				url: COMPLETE_TEFAP_URL,
				contentType: 'text/plain',
				data: params,
				success: function(data, status) {
					$('#popupTefap').jqxWindow('close');
					clearTefapPopup();
					var n = noty({
						layout: 'center',
						type: 'success', 
						text: '<h3>TEFAP Order Completed!</h3>',
						timeout: 750,
						callback: {
							afterClose: function() {
								
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
						text: '<h3>Unable to Complete Order</h3>',
						timeout: 3000
					});
				}
			});
		});
		
		// if cancel button clicked, clear any populated fields in the popup window
		$('#cancelOrderButton').click(function() {
			clearOrderPopup();
		});
		
		// if cancel button clicked, clear any populated fields in the popup window
		$('#cancelTefapButton').click(function() {
			clearTefapPopup();
		});
});

function completeOrder(rowIndex) {
	// get the clicked row's data and initialize the input fields.
	 var dataRecord = $("#pendingOrdersGrid").jqxGrid('getrowdata', rowIndex);
	 
	 if(dataRecord.tefap) {
		$("#tefapOrderNum").val(dataRecord.orderNumber);
		$("#tefapFirstName").val(dataRecord.customerFirstName);
		$("#tefapLastName").val(dataRecord.customerLastName);
		$("#tefapDate").val(dataRecord.orderDate);
		 
		// show the popup window.
		$("#popupTefap").jqxWindow('open');
		 
	 } else {
		$("#orderNumber").val(dataRecord.orderNumber);
		$("#firstName").val(dataRecord.customerFirstName);
		$("#lastName").val(dataRecord.customerLastName);
		$("#orderDate").val(formatDate(dataRecord.orderDate));
		 
		// show the popup window.
		$("#popupOrder").jqxWindow('open');
	 }
}

function deletePendingOrder(orderNumber) {
	var params = 'orderNumber=' + orderNumber;
	
	//send update request
	$.ajax({
		type: 'GET',
		url: DELETE_URL,
		contentType: 'text/plain',
		data: params,
		success: function(data, status) {
			
			var n = noty({
				layout: 'center',
				type: 'success', 
				text: '<h3>Order Removed!</h3>',
				timeout: 750,
				callback: {
					afterClose: function() {
						
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
				text: '<h3>Unable to Remove Order</h3>',
				timeout: 1500
			});
		}
	});
}

function clearOrderPopup() {
	$("#orderNumber").val('');
	$("#firstName").val('');
	$("#lastName").val('');
	$("#orderDate").val('');
	$("#orderWeight").val('');
	$("#numOfBags").val('');
}

function clearTefapPopup() {
	$("#tefapOrderNum").val('');
	$("#tefapFirstName").val('');
	$("#tefapLastName").val('');
	$("#tefapDate").val('');
	$("#tefapCount").val('');
	$("#tefapWeight").val('');
}

function formatDate(date) {
	var str = date.getMonth() + 1;
	str += '/' + date.getDate();
	str += '/' + date.getFullYear();
	str += '  ' + date.getHours() % 12 == 0 ? 12 : date.getHours() % 12;
	str += ':' + date.getMinutes();
	
	if(date.getHours() / 12 > 1) {
		str += ' PM';
	} else {
		str += ' AM';
	}
	return str;	
}