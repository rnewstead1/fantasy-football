Ext.Loader.setConfig({
    enabled: true
});

Ext.application({
    name: 'Team',
    models: ['Player'],
    stores: ['TeamStore'],
    launch: function () {
        Ext.create('Team.view.FantasyTeamView');
    }
});