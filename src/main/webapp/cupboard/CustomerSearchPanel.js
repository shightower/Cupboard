Ext.define('Cupboard.CustomerSearchPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.customerSearchPanel',
  layout: {
  	type: 'vbox',
  	pack: 'center',
  	align: 'center'
  },
  region: 'center',
  bodyPadding: false,
  padding: false,
  items: [{
  	xtype: 'container',
  	width: 350,
	border: 2,
  	fieldDefaults: {
  		msgTarget: 'side',
  		labelWidth: 100
  	},
  	defaults: {
  		labelAlign: 'left'
  	},
  	items: [{
		xtype: 'textfield',
		itemId: 'firstNameField',
  		fieldLabel: 'First Name',
  		name: 'firstName'
  	},{		
		xtype: 'textfield',
		itemId: 'lastNameField',
  		fieldLabel: 'Last Name',
  		name: 'lastName'
  	},{
		xtype: 'button',
		text: 'Search',
		handler: function() {
			var store = Ext.data.StoreManager.lookup('customerSearchStore');
			var proxy = store.proxy;
			
			var fNameAttr = Ext.ComponentQuery.query('#firstNameField')[0].getValue();
			var lNameAttr = Ext.ComponentQuery.query('#lastNameField')[0].getValue();
			
			fNameAttr = new String(fNameAttr);
			lNameAttr = new String(lNameAttr);
			
			if(fNameAttr.trim() == '' && lNameAttr.trim() == '') {
				alert('Must provide at least a first or last name value!');
				return;
			} else {
				//in case the user attempts another search, clear out any previous attributes
				proxy.url = proxy.url.split('?')[0];
				proxy.url = proxy.url + '?firstName=' + fNameAttr + '&lastName=' + lNameAttr;
			}
			var results = Ext.ComponentQuery.query('#searchResults')[0];
			
			store.load(function() {
				if(results.isHidden()) {
					results.show();
				}
			});
		}
	},{
		xtype: 'gridpanel',
		itemId: 'searchResults',
		title: 'Search Results',
		store: 'customerSearchStore',
		columns: [
			{text: 'First Name', dataIndex: 'firstName', hideable: false},
			{text: 'Last Name', dataIndex: 'lastName', hideable: false},
			{text: 'Phone Number', dataIndex: 'phoneNumber', hideable: false},
			{text: 'Address', dataIndex: 'address', hideable: false}
		],
		height: 600,
		width: 350,
		hidden: true
	}]
  }]
});