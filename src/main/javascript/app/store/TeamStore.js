Ext.define('Team.store.TeamStore', {
    extend: 'Ext.data.Store',
    requires: 'Team.model.Player',
    autoLoad: true,
    model: 'Team.model.Player',
     proxy: {
            type: 'ajax',
            url: 'api/team',
            reader: {
                type: 'json',
                root: 'team'
            }
        }
});