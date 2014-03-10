Ext.application({
  name: 'Cupboard',
  launch: function() {
    //Ext.QuickTips.init();
    
    Ext.create('Ext.container.Viewport', {
      layout: 'border',
      items: [{
        xtype: 'mainPanel'
      }]
    } //end of viewport
  } //end of launch

});
