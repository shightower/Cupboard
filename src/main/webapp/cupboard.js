Ext.application({
  name: 'Cupboard',
  launch: function() {
    //Ext.QuickTips.init();
    
    Ext.create('Ext.container.Viewport', {
      layout: 'border',
      items: [{
        xtype: 'mainpanel'
      }]
    } //end of viewport
  } //end of launch

});
