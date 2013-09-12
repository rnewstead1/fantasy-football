Ext.Loader.setConfig({
    enabled: true
});

Ext.application({
    name: 'Team',
    launch: function () {
        Ext.create('Team.view.FantasyTeamView');
    }
});