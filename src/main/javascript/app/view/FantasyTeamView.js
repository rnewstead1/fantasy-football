Ext.define('Team.view.FantasyTeamView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.fantasyTeamView',

    requires: [
        'Team.view.AppContainer'
    ],

    itemId: 'fantasyTeamView',
    layout: {
        type: 'fit'
    },

    initComponent: function () {
        var me = this;

        Ext.applyIf(me, {

            items: [
                {
                    xtype: 'appContainer'
                }
            ]
        });

        me.callParent(arguments);
    }

});