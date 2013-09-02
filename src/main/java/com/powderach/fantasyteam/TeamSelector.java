package com.powderach.fantasyteam;

import com.powderach.fantasyteam.store.TeamStore;

public class TeamSelector {
    private final PlayerSelector playerSelector;
    private final TeamStore teamStore;

    public TeamSelector(PlayerSelector playerSelector, TeamStore teamStore) {
        this.playerSelector = playerSelector;
        this.teamStore = teamStore;
    }

    public Team selectAndStore() {
        TeamBuilder teamBuilder = new TeamBuilder();

        teamBuilder.with(playerSelector.mostSelectedGoalkeepers(1));
        teamBuilder.with(playerSelector.mostSelectedForwards(2));
        teamBuilder.with(playerSelector.mostSelectedMidfielders(2));
        teamBuilder.with(playerSelector.mostSelectedDefenders(2));

        teamBuilder.with(playerSelector.cheapestMidfieldersInMostMostPopular(3, 15));
        teamBuilder.with(playerSelector.cheapestDefendersInMostPopular(3, 15));
        teamBuilder.with(playerSelector.cheapestForwardInMostPopular(1, 20));
        teamBuilder.with(playerSelector.cheapestGoalkeeperInMostPopular(1, 20));

        Team team = teamBuilder.build();

        teamStore.store(team);

        return team;
    }

}
