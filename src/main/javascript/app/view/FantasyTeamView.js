Ext.define('Team.view.FantasyTeamView', {
    extend: 'Ext.container.Viewport',
    alias: 'widget.fantasyTeamView',

    requires: [
        'Team.view.TeamPanel'
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
                    xtype: 'teamPanel'
                }
            ]
        });

        me.callParent(arguments);
    }

});