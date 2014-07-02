$(document).ready(function () {	
		//Round Search
		$('#search').corner('10px');
		
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
});