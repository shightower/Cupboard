Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.form.*',
    'Ext.ux.CheckColumn'
]);

Ext.define('Cupboard.OrderPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.orderPanel',
	layout: {
		type: 'vbox',
		pack: 'center',
		align: 'center'
	},
	region: 'center',
	bodyPadding: false,
	padding: false,
	item: 'New Order List',
	items: [{
		xtype: 'gridpanel',
		store: 'orderStore',
		columns: [{
			header: 'Name',
			dataIndex: 'name',
			flex: 1,
			editor: {
				allowBlank: false
			}
		},{
			xtype: 'numbercolumn',
			header: 'Number of Bags',
			dataIndex: 'numOfBags',
			width: 50,
			editor: {
				xtype: 'numberfield',
				allowBlank: false,
				minValue: 1,
				maxValue: 10
			}
		},{
			xtype: 'numbercolumn',
			header: 'Total Weight (lbs)',
			dataIndex: 'orderWeight',
			width: 50,
			editor: {
				xtype: 'numberfield',
				allowBlank: false,
				minValue: 1,
				maxValue: 99
			}
		},{
			xtype: 'checkcolumn'
			header: 'TEFAP Order',
			dataIndex: 'isTefap',
			width: 40,
			editor: {
				xtype: 'checkbox',
				cls: 'x-grid-checkheader-editor'
			}
		}],
		tbar: [{
			text: 'Add Customer',
			handler: function() {
				rowEditing.cancelEdit();
				
				
			}
		},{
			text: 'Remove Order',
			handler: function() {
				/**
				var sm = grid.getSelectionModel();
				rowEditing.cancelEdit();
				store.remove(sm.getSelection());
				sm.select(0);
				*/
			}
		}]
	}]
};