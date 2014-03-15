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
    width: 500,
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
      xtype: 'button',
      width: 100,
      text: Cupboard.constants.tab.label.customer,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(1);
      }
    },{
      xtype: 'button',
      width: 100,
      text: Cupboard.constants.tab.label.order,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(2);
      }
    },{
      xtype: 'button',
      width: 100,
      text: Cupboard.constants.tab.label.tefap,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(3);
      }
    },{
      xtype: 'button',
      width: 100,
      text: Cupboard.constants.tab.label.reports,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(4);
      }
    }]
  }, //end tbar
  items: [{
  	xtype: 'overviewpanel'
  },{
  	xtype: 'customerpanel',
  }]

});
