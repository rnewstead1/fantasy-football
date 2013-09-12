Ext.define('Team.store.TeamStore', {
    extend: 'Ext.data.Store',
    requires: 'Team.model.Team',
    autoLoad: true,
    model: 'Team.model.Team',
     proxy: {
            type: 'ajax',
            url: 'api/team',
            reader: {
                type: 'json'
            }
        }
});