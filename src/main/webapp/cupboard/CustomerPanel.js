var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

Ext.define('Cupboard.CustomerPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.customerpanel',
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
  		fieldLabel: 'Address',
  		afterLabelTextTpl: required,
  		name: 'address',
  		allowBlank: false,
  		tooltip: "Enter Customer's Street Information"
  	},{
  		xtype: 'combobox',
  		fieldLabel: 'State',
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
  		name: 'numAdults',
  		value: 0,
  		minValue: 0,
  		maxValue: 100,
  		tooltip: "# of Adults in Family"
  	},{
  		xtype: 'numberfield',
  		fieldLabel: '# Of Kids',
  		afterLabelTextTpl: required,
  		name: 'numKids',
  		value: 0,
  		minValue: 0,
  		maxValue: 100,
  		tooltip: "# of Kids in Family"
  	}],
  	buttons: [{
  		text: 'Save',
  		handler: function() {
  			this.up('form').getForm().isValid();
  		}
  	},{
  		text: 'Cancel',
  		handler: function() {
  			this.up('form').getForm().isValid();
  		}
  	}]
  }]
});