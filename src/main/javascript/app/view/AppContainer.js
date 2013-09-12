Ext.define('Team.view.AppContainer', {
    extend: 'Ext.container.Container',
    alias: 'widget.appContainer',

    requires: [
        'Ext.layout.container.Border'
    ],

    cls: 'team-app-container',
    itemId: 'appContainer',
    layout: {
        type: 'border'
    },

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'component',
                    html: 'Fantasy Team'
                }

            ]
        });

        me.callParent(arguments);
    }

});