Ext.namespace('Cupboard').constants = {
  defaults: {
    title: 'BCC Cupboard'
  },
  url: {
  	addCustomer: '/cupboard/customer/add',
	findCustomer: '/cupboard/customer/search',
  	getCustomers: '/cupboard/customer/retrieveAll'
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
