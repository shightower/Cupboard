Ext.application({
  name: 'Cupboard',
  launch: function() {
    //Ext.QuickTips.init();
    
    Ext.create('Ext.container.Viewport', {
	  id: 'vp',
      layout: 'border',
      items: [{
        xtype: 'mainpanel',
		id: 'vpMainPanel',
		hidden: true
      },{
		xtype: 'loginpanel',
		id: 'vpLoginPanel',
		hidden: false
	  }]
    }); //end of viewport
  } //end of launch

});
