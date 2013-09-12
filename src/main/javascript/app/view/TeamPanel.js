Ext.define('Team.view.TeamPanel', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.grid.column.Action'
    ],
    
    alias: 'widget.teamPanel',

    cls: 'team-panel',
    itemId: 'teamPanel',
    xtype: 'array-grid',
    store: 'TeamStore',
    stateful: true,
    collapsible: true,
    multiSelect: true,
    stateId: 'stateGrid',
    height: 350,
    title: 'Array Grid',
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },

    initComponent: function () {
        this.width = 650;
        this.columns = [
            {
                text     : 'Company',
                flex     : 1,
                sortable : false,
                dataIndex: 'Forward'
            },
            {
                text     : 'Price',
                width    : 75,
                sortable : true,
                renderer : 'usMoney',
            },
            {
                text     : 'Change',
                width    : 80,
                sortable : true
            }
        ];
        debugger;
        this.callParent();
    }
});