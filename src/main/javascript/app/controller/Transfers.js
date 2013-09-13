Ext.define('Team.controller.Transfers', {
    extend: 'Ext.app.Controller',
    alias: 'controller.transfers',
    init: function () {
        this.listen({
            component: {
                'teamPanel': {
                    playerSelected: this.onPlayerSelected
                }
            }
        });
    },

    onPlayerSelected: function (player) {
		console.log(player.get('player_name'))
    }

});