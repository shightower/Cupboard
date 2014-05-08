var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.require([
	'Ext.form.*',
	'Ext.Img',
	'Ext.tip.QuickTipManager'
]);

Ext.define('Cupboard.CustomerPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.customerPanel',
  layout: {
  	type: 'vbox',
  	pack: 'center',
  	align: 'center'
  },
  region: 'center',
  border: false,
  bodyBorder: false,
  bodyPadding: false,
  padding: false,
  items: [{
  	xtype: 'form',
  	layout: 'form',
  	frame: true,
  	title: 'Create New Customer',
  	width: 350,
  	fieldDefaults: {
  		msgTarget: 'side',
  		labelWidth: 100
  	},
  	defaultType: 'textfield',
  	defaults: {
  		labelAlign: 'right'
  	},
  	items: [{
  		fieldLabel: 'First Name',
  		afterLabelTextTpl: required,
  		name: 'firstName',
  		allowBlank: false,
  		tooltip: "Enter Customer's First Name"
  	},{
  		fieldLabel: 'Last Name',
  		afterLabelTextTpl: required,
  		name: 'lastName',
  		allowBlank: false,
  		tooltip: "Enter Customer's Last Name"
  	},{
  		fieldLabel: 'Street',
  		afterLabelTextTpl: required,
  		name: 'street',
  		allowBlank: false,
  		tooltip: "Enter Customer's Street Information"
  	},{
  		fieldLabel: 'City',
  		name: 'city',
  		tooltip: "Enter Customer's City Information"
  	}{
  		xtype: 'combobox',
  		fieldLabel: 'State',
  		name: 'state',
  		store: 'stateStore',
  		queryMode: 'local',
  		displayField: 'state',
  		valueField: 'abbr',
  		tooltip: "Enter State"
  	},{
  		fieldLabel: 'Zip',
  		name: 'zip',
  		maxLength: 5,
  		allowBlank: false,
  		tooltip: "Enter Zip Code"
  	},{
  		fieldLabel: 'Phone Number',
  		name: 'phoneNumber',
  		allowBlank: true,
  		tooltip: "Enter Customer's Phone Number"
  	},{
  		xtype: 'numberfield',
  		fieldLabel: '# Of Adults',
  		afterLabelTextTpl: required,
  		name: 'numOfAdults',
  		value: 0,
  		minValue: 0,
  		maxValue: 100,
  		tooltip: "# of Adults in Family"
  	},{
  		xtype: 'numberfield',
  		fieldLabel: '# Of Kids',
  		afterLabelTextTpl: required,
  		name: 'numOfKids',
  		value: 0,
  		minValue: 0,
  		maxValue: 100,
  		tooltip: "# of Kids in Family"
  	}],
  	buttons: [{
		formBind: true,
		disabled: true,
  		text: 'Add New Customer',
  		handler: function(btn,event) {
  			var form = this.up('form').getForm();
  			
  			Ext.Ajax.request({
				url: '/cupboard/rest/customer/add',
  				headers: {'Content-Type':'application/json'},
  				params: Ext.encode(form.getValues()),
  				success: function(response) {
  					var x = 0;
  				},
  				failure: function(response) {
  					var x = 0;
  				}
  			});
			
			/**
			form.submit({
				clientValidation: true,
				headers: {'Content-Type':'application/json'},
				,
				success: function(form, action) {
				
				},
				failure: function(form, action) {
				
				}
			});
			*/
  		}
  	},{
  		text: 'Cancel',
  		handler: function() {
  			this.up('form').getForm().isValid();
  		}
  	}]
  }]
});