Ext.define('Cupboard.MainPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.mainpanel',
  itemId: 'mainPanel',
  layout: 'card',
  region: 'center',
  border: false,
  bodyBorder: false,
  bodyPadding: false,
  padding: false,
  tbar: {
    layout: {
      type: 'hbox',
      pack: 'center',
      align: 'middle'
    },
    width: 520,
    defaults: {
      cls: 'boldText'
    },
    items: [{
      xtype: 'button',
      width: 100,
      text: Cupboard.constants.tab.label.overview,
      handler: onItemClick
    },{
      xtype: 'splitbutton',
	  handler: onButtonClick,
	  width: 100,
      text: 'Customer',
	  menu: new Ext.menu.Menu({
		items: [{
			text: Cupboard.constants.tab.label.newCustomer,
			handler: onItemClick
		},{
			text: Cupboard.constants.tab.label.updateCustomer,
			handler: onItemClick
		},{
			text: Cupboard.constants.tab.label.searchCustomer,
			handler: onItemClick
		},{
			text: Cupboard.constants.tab.label.deleteCustomer,
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 100,
      text: 'Order',
      menu: new Ext.menu.Menu({
		items: [{
			text: Cupboard.constants.tab.label.newOrder,
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 100,
      text: 'Tefap',
      menu: new Ext.menu.Menu({
		items: [{
			text: Cupboard.constants.tab.label.newTefap,
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 120,
      text: Cupboard.constants.tab.label.reports,
      menu: new Ext.menu.Menu({
		items: [{
			text: Cupboard.constants.tab.label.generateReports,
			handler: onItemClick
		},{
			text: Cupboard.constants.tab.label.statistics,
			handler: onItemClick
		}]
	  })
    }]
  }, //end tbar
  items: [{
  	xtype: 'overviewPanel'
  },{
  	xtype: 'customerPanel',
  },{
	xtype: 'customerSearchPanel',
  }]

});

function onButtonClick(btn) {

}

function onItemClick(itm) {
	var selection = itm.text;
	var panel = itm.up('panel').up('panel');
	
	if(selection === Cupboard.constants.tab.label.overview) {
		panel.getLayout().setActiveItem(0);
	} else if(selection === Cupboard.constants.tab.label.newCustomer) {
		panel.getLayout().setActiveItem(1);
	} else if(selection === Cupboard.constants.tab.label.updateCustomer) {
		panel.getLayout().setActiveItem(2);
	
	} else if(selection === Cupboard.constants.tab.label.searchCustomer) {
		//panel.getLayout().setActiveItem(1);
	
	} else if(selection === Cupboard.constants.tab.label.deleteCustomer) {
		//panel.getLayout().setActiveItem(1);
	
	} else if(selection === Cupboard.constants.tab.label.newOrder) {
		//panel.getLayout().setActiveItem(1);
	
	} else if(selection === Cupboard.constants.tab.label.newTefap) {
		//panel.getLayout().setActiveItem(1);
	
	} else if(selection === Cupboard.constants.tab.label.generateReports) {
		//panel.getLayout().setActiveItem(1);
	
	} else if(selection === Cupboard.constants.tab.label.statistics) {
		//panel.getLayout().setActiveItem(1);
	
	}
}
