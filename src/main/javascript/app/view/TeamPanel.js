Ext.define('Team.view.TeamPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.teamPanel',
	requires: ['Team.store.TeamStore', 'Ext.grid.Panel'],
	store: 'TeamStore',
	border: 0,
	closable: true,
	initComponent: function() {
		Ext.applyIf(this, {
			title: 'Team',
			id: 'teamPanel',
			items: [{
				xtype: 'gridpanel',
				store: this.store,
				selModel: {
					selType: 'checkboxmodel',
					checkOnly: true
				},
				border: 0,
				listeners: {
					cellclick: {
						fn: this.onCellClicked,
						scope: this
					}
				},
				columns: [{
					text: 'Player Name',
					flex: 1,
					dataIndex: 'player_name'
				}, {
					text: 'Position',
					dataIndex: 'position'
				}, {
					text: 'Price',
					dataIndex: 'price'
				}, {
					text: 'Team',
					dataIndex: 'team'
				}, {
					text: 'Selected By',
					dataIndex: 'selected_by'
				}, {
					text: 'Points',
					dataIndex: 'points'
				}

				]
			}]
		});
		this.callParent(arguments);
	},

	load: function() {
		this.store.load()
	},

	onCellClicked: function(view, td, cellIndex, record) {
		if (cellIndex > 0) {
		    this.fireEvent('playerSelected', record);
		}
	}
});
