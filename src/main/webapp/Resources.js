Ext.create('Ext.data.Store', {
  storeId: 'customerStore',
  model: 'CustomerModel',
  autoLoad: false,
  proxy: {
    type: 'ajax',
    url: Cupboard.url.getCustomers,
    headers: {
      'Accept': 'application/json'
    },
    reader: {
      type: 'json',
      root: 'data'
    }
  } //end of proxy
});
