Ext.namespace('Cupboard').constants = {
  defaults: {
    title: 'BCC Cupboard'
  },
  url: {
  	addCustomer: '/cupboard/rest/customer/add',
	customerSearch: '/cupboard/rest/customer/search',
  	getCustomers: '/cupboard/rest/customer/retrieveAll'
  },
  tab: {
  	label: {
  		overview: 'Overview',
  		newCustomer: 'New Customer',
		updateCustomer: 'Update Customer Info',
		searchCustomer: 'Customer Search',
		deleteCustomer: 'Remove Customer',
  		newOrder: 'New Order',
		updateOrder: 'Update Order',
  		newTefap: 'New TEFAP Order',
		updateTefap: 'Update TEFAP Order',
  		generateReports: 'Cupboard Reports',
		statistics: 'Statistics',
		reports: 'Reports'
  	}
  },
  
};
