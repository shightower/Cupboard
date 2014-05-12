Ext.define('CustomerModel', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'id', type: 'int'},
		{name: 'firstName', type: 'string'},
		{name: 'lastName', type: 'string'},
		{name: 'street', type: 'string'},
		{name: 'city', type: 'string'},
		{name: 'state', type: 'string'},
		{name: 'zip', type: 'string'},
		{name: 'phoneNumber', type: 'string'},
		{name: 'numOfAdults', type: 'int'},
		{name: 'numOfKids', type: 'int'}
	]
});

Ext.define('EditCustomerModel', {
	extend: 'CustomerModel',
	proxy: {
		type: 'ajax',
		url: Cupboard.constants.url.customerIdSearch,
		headers: {
		  'Accept': 'application/json'
		},
		reader: {
		  type: 'json',
		  root: 'data'
		}
  }
});

Ext.define('StateModel', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'state', type: 'string'},
		{name: 'abbr', type: 'string'}
	]
});

Ext.create('Ext.data.Store', {
	storeId: 'stateStore',
	model: 'StateModel',
	data: [
		{state: 'Alabama', abbr: 'AL'},
		{state: 'Alaska', abbr: 'AS'},
		{state: 'Arizona', abbr: 'AA'},
		{state: 'Arkansas', abbr: 'AK'},
		{state: 'California', abbr: 'CA'},
		{state: 'Colorado', abbr: 'CO'},
		{state: 'Connecticut', abbr: 'CT'},
		{state: 'Delaware', abbr: 'DE'},
		{state: 'Florida', abbr: 'FL'},
		{state: 'Georgia', abbr: 'GA'},
		{state: 'Hawaii', abbr: 'HI'},
		{state: 'Idaho', abbr: 'ID'},
		{state: 'Illinois', abbr: 'IL'},
		{state: 'Indiana', abbr: 'IN'},
		{state: 'Iowa', abbr: 'IA'},
		{state: 'Kansas', abbr: 'KS'},
		{state: 'Kentucky', abbr: 'KY'},
		{state: 'Louisiana', abbr: 'LA'},
		{state: 'Maine', abbr: 'ME'},
		{state: 'Maryland', abbr: 'MD'},
		{state: 'Massachusetts', abbr: 'MA'},
		{state: 'Michigan', abbr: 'MN'},
		{state: 'Missouri', abbr: 'MI'},
		{state: 'Mississippi', abbr: 'MS'},
		{state: 'Montana', abbr: 'MN'},
		{state: 'Nebraska', abbr: 'NA'},
		{state: 'Nevada', abbr: 'NE'},
		{state: 'New Hampshire', abbr: 'NH'},
		{state: 'New Jersey', abbr: 'NJ'},
		{state: 'New Mexico', abbr: 'NM'},
		{state: 'New York', abbr: 'NY'},
		{state: 'North Carolina', abbr: 'NC'},
		{state: 'North Dakota', abbr: 'ND'},
		{state: 'Ohio', abbr: 'OH'},
		{state: 'Oklahoma', abbr: 'OK'},
		{state: 'Oregon', abbr: 'OR'},
		{state: 'Pennsylvania', abbr: 'PA'},
		{state: 'Rhode Island', abbr: 'RI'},
		{state: 'South Carolina', abbr: 'SC'},
		{state: 'South Dakota', abbr: 'SD'},
		{state: 'Tennessee', abbr: 'TN'},
		{state: 'Texas', abbr: 'TX'},
		{state: 'Utah', abbr: 'UT'},
		{state: 'Vermont', abbr: 'VT'},
		{state: 'Virginia', abbr: 'VA'},
		{state: 'Washington', abbr: 'WA'},
		{state: 'West Virginia', abbr: 'WV'},
		{state: 'Wisconsin', abbr: 'WI'},
		{state: 'Wyoming', abbr: 'WY'}
	]
});

Ext.create('Ext.data.Store', {
  storeId: 'customerStore',
  model: 'CustomerModel',
  autoLoad: false,
  proxy: {
    type: 'ajax',
    url: Cupboard.constants.url.getCustomers,
    headers: {
      'Accept': 'application/json'
    },
    reader: {
      type: 'json',
      root: 'data'
    }
  } //end of proxy
});

Ext.create('Ext.data.Store', {
  storeId: 'customerSearchStore',
  model: 'CustomerModel',
  autoLoad: false,
  proxy: {
    type: 'ajax',
    url: Cupboard.constants.url.customerNameSearch,
    headers: {
      'Accept': 'application/json'
    },
    reader: {
      type: 'json',
      root: 'data'
    }
  } //end of proxy
});
