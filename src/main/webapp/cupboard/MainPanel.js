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
      pack: 'center'
    },
    defaults: {
      cls: 'boldText'
    },
    items: [{
      xtype: 'button',
      text: Cupboard.label.overview,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(0);
      }
    },{
      xtype: 'button',
      text: Cupboard.label.createUser,
      handler: function() {
        var panel = this.up('panel');
        panel.getLayout().setActiveItem(1);
      }
    }]
  }, //end tbar
  items: [{
    xtype: 'overviewpanel'
  },{
    xtype: 'customerpanel'
  }]

});
