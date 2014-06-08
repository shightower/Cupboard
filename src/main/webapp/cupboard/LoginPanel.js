Ext.define('Cupboard.LoginPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.loginpanel',
  layout: {
  	type: 'vbox',
  	pack: 'center',
  	align: 'center'
  },
  region: 'center',
  bodyPadding: false,
  padding: false,
  items: [{
  	xtype: 'form',
  	layout: 'form',
  	frame: true,
  	title: 'Account Login',
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
  		fieldLabel: 'Username',
  		name: 'username',
  		allowBlank: false
  	},{
  		fieldLabel: 'Password',
  		name: 'password',
  		allowBlank: false
  	}],
  	buttons: [{
		formBind: true,
  		text: 'Login',
  		handler: function(btn,event) {
  			var form = this.up('form').getForm();
  			
  			Ext.Ajax.request({
				url: '/cupboard/rest/admin/authenticate',
  				headers: {'Content-Type':'application/json'},
  				params: Ext.encode(form.getValues()),
  				success: function(response) {
					var vp = Ext.getCmp('vp');
					vp.items.items[0].setVisible(true);
					vp.items.items[1].setVisible(false);
  				},
  				failure: function(response) {
  					var x = 0;
  				}
  			});
  		}
  	},{
  		text: 'Cancel',
  		handler: function() {
  			this.up('form').getForm().isValid();
  		}
  	}]
  }]
});