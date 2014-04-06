Ext.define('Cupboard.MainPanel', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.mainpanel',
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
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(0);
      }
    },{
      xtype: 'splitbutton',
	  handler: onButtonClick,
	  width: 100,
      text: 'Customer',
	  menu: new Ext.menu.Menu({
		items: [{
			text: 'New Customer',
			handler: onItemClick
		},{
			text: 'Update Customer Info',
			handler: onItemClick
		},{
			text: 'Search',
			handler: onItemClick
		},{
			text: 'Delete',
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 100,
      text: 'Order',
      menu: new Ext.menu.Menu({
		items: [{
			text: 'New Order',
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 100,
      text: 'Tefap',
      menu: new Ext.menu.Menu({
		items: [{
			text: 'New Order',
			handler: onItemClick
		}]
	  })
    },{
      xtype: 'splitbutton',
      width: 120,
      text: Cupboard.constants.tab.label.reports,
      menu: new Ext.menu.Menu({
		items: [{
			text: 'Generated Reports',
			handler: onItemClick
		},{
			text: 'Statistics',
			handler: onItemClick
		}]
	  })
    }]
  }, //end tbar
  items: [{
  	xtype: 'overviewpanel'
  },{
  	xtype: 'customerpanel',
  }]

});

function onButtonClick(btn) {

}

function onItemClick(itm) {

}
