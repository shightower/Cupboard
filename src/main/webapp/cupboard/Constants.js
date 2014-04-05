Ext.namespace('Cupboard').constants = {
  defaults: {
    title: 'BCC Cupboard'
  },
  url: {
  	addCustomer: '/cupboard/rest/customer/add',
	findCustomer: '/cupboard/rest/customer/search',
  	getCustomers: '/cupboard/rest/customer/retrieveAll'
  },
  tab: {
  	label: {
  		overview: 'Overview',
  		customer: 'New Customer',
  		order: 'New Order',
  		tefap: 'New TEFAP Order',
  		reports: 'Cupboard Reports'
  	}
  }
};
